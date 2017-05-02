package com.fortex.quickRing.quote;

import static com.fortex.lib.globalservices.FortexLogger.defaultLogger;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.json.JSONObject;

import com.fortex.constants.Constants;
import com.fortex.lib.connection.FortexConnection;
import com.fortex.lib.protocol.ErrDef.ErrInfo;
import com.fortex.quickRing.InitiatorApplication;
import com.fortex.quickRing.Statistics.QuoteStatistician;
import com.fortex.quickRing.cache.QuoteCache;
import com.fortex.quickRing.cache.SessionCache;
import com.fortex.quickRing.cache.SessionCache.SERVER_TYPE;
import com.fortex.quickRing.db.DBOperation;
import com.fortex.quickRing.model.FxTableModel;
import com.fortex.quickRing.model.SessionIDMarketDepthModel;
import com.fortex.quickRing.model.UserModel;

import quickfix.ConfigError;
import quickfix.FieldConvertError;
import quickfix.FieldNotFound;
import quickfix.Group;
import quickfix.Message;
import quickfix.Message.Header;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.field.MDEntryPx;
import quickfix.field.MDEntryType;
import quickfix.field.MDReqID;
import quickfix.field.MDUpdateType;
import quickfix.field.MarketDepth;
import quickfix.field.MsgType;
import quickfix.field.NoMDEntries;
import quickfix.field.SubscriptionRequestType;
import quickfix.field.Symbol;
import quickfix.fix44.MarketDataRequest;

public class QuoteInitiatorApplication extends InitiatorApplication {
	private static final String KEY_FQS_USER_NAME = "FQSUserName";
	private static final String KEY_FQS_PASSWORD = "FQSPassword";
	private static final String KEY_SCHEDULED_WRITE_BYTES = "ScheduleWriteBytes";
	private static final LinkedHashSet<String> symbols = new LinkedHashSet<String>();
	private static final ReentrantLock lock = new ReentrantLock();
	private static final Condition condition = lock.newCondition();
	
	private FQSAdaptor fqsAdaptor;
	private QuoteMakerAdapter qmAdpt;
	private SessionSettings settings;

	public FQSAdaptor getFqsAdaptor() {
		return fqsAdaptor;
	}

	public void setFqsAdaptor(FQSAdaptor fqsAdaptor) {
		this.fqsAdaptor = fqsAdaptor;
	}

	public QuoteInitiatorApplication(SessionSettings settings, QuoteMakerAdapter qmAdpt) {
		this.settings = settings;
		this.qmAdpt = qmAdpt;
		new QuoteDispatcher().start();
		
	}
	
	public void addSymbol(String symbol){
		lock.lock();
		symbols.add(symbol);
		condition.signal();
		lock.unlock();
	}
	/*
	 * /*
	 * (non-Javadoc)
	 * 
	 * @author Patrick Chi
	 * 
	 * 
	 */
	 
	private  class QuoteDispatcher extends Thread {
		public void run() {
			while (true) {
				lock.lock();
				if (symbols.isEmpty()) {
					try {
						condition.await();
					} catch (InterruptedException e) {}
				}
				String symbolsArray[] = symbols.toArray(new String[]{});
				lock.unlock();
				
				
				for(String symbol : symbolsArray) {
					lock.lock();
					symbols.remove(symbol);
					lock.unlock();
					List<Group> groups = QuoteCache.getMarketDataBySymble(symbol);
						
					// send message market data snapshot to clients.
					ConcurrentSkipListSet<SessionIDMarketDepthModel> models = QuoteCache.getSessionIDsBySymbol(symbol);	
					if (models != null) {
						for (SessionIDMarketDepthModel model : models) {
							long t = System.nanoTime();
							Session session = Session.lookupSession(model.getSessionID());
							IoSession ioSession = session.getIoSession();
							
							try {
								
								if ((double)ioSession.getScheduledWriteBytes() / (double)1024 <= settings.getLong(KEY_SCHEDULED_WRITE_BYTES)) {
									UserModel user = SessionCache.getUserModelBySessionId(model.getSessionID());
									
									if (user != null) {
										Integer domain = user.getDomain();
										Message msg = session.getMessageFactory().create(
												model.getSessionID().getBeginString(),
												MsgType.MARKET_DATA_SNAPSHOT_FULL_REFRESH);
										msg.setString(MDReqID.FIELD, model.getMdReqId());
										msg.setString(Symbol.FIELD, symbol);

										// get market depth for each client.
										int depth = model.getMarketDepth();
										if (user.getQuoteService() == Constants.QUOTE_SERVICE_1
												|| user.getQuoteService() == Constants.QUOTE_SERVICE_2) {
											depth = 1;
										} else if (depth <= 0 || depth > groups.size())
											depth = groups.size();

										// calculate for QM.
										Message m = qmAdpt.calculate(symbol, domain, groups, msg, depth);
										QuoteStatistician.getInstance().addTotalSended();
										session.send(m);
										QuoteStatistician.getInstance()
												.proccessForTime(System.nanoTime() - t
														+ QuoteCache.getSymbolEncodedTime(symbol));
										
									}
								} else {
									QuoteStatistician.getInstance().addtotalDropped();
								}	
							} catch (ConfigError | FieldConvertError | FieldNotFound e) {
								Logger.getLogger("EventError").error(e);
							}
						}
					}
				}
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @author Ivan Huo
	 * 
	 * @see
	 * com.fortex.quickRing.InitiatorApplication#onLogon(quickfix.SessionID)
	 */
	@Override
	public void onLogon(SessionID sessionID) {
		SessionCache.setSession(SERVER_TYPE.FQS, Session.lookupSession(sessionID));


		/* Get All Market Data Update Refreshs */

		defaultLogger.info("### subscribe Market Data into cache ##");

		try {
			Map<String, FxTableModel> symbols = DBOperation.getSymbols();
			qmAdpt.setDecimalMapping(symbols);

			Set<Entry<String, FxTableModel>> set = symbols.entrySet();
			try {
				for (Entry<String, FxTableModel> entry : set) {
					FxTableModel model = entry.getValue();
					QuoteCache.addSupportedSymbol(model.getSymbol());
					generateMarketDataRequest(model.getSymbol(), sessionID);
				}
			} catch (Exception e) {
				Logger.getLogger("EventError").error(e.getMessage(), e);
			}

		} catch (SQLException e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		}

	}

	/**
	 * <p>
	 * Description:Generate Market Data Request on local
	 * </p>
	 * 
	 * @author Ivan Huo
	 * @date 2016-08-25
	 * @param symbol
	 * @param sessionID
	 * @throws InterruptedException
	 */
	private static void generateMarketDataRequest(String symbol, SessionID sessionID) throws InterruptedException {
		quickfix.fix44.MarketDataRequest request = new quickfix.fix44.MarketDataRequest();
		request.set(new MDReqID(java.util.UUID.randomUUID().toString()));
		request.set(new SubscriptionRequestType(SubscriptionRequestType.SNAPSHOT_PLUS_UPDATES));
		request.set(new MDUpdateType(0));// Must be 0 (Full Refresh), required
											// if Subscription RequestType <263>
											// = Snapshot + Updates (1)
		request.set(new MarketDepth(0));

		/**
		 * MarketDataRequest.NoMDEntryTypes noMdEntryTypes = new
		 * MarketDataRequest.NoMDEntryTypes(); request.addGroup(noMdEntryTypes);
		 **/

		MarketDataRequest.NoMDEntryTypes noMdEntryTypes1 = new MarketDataRequest.NoMDEntryTypes();
		noMdEntryTypes1.set(new MDEntryType(MDEntryType.BID));
		request.addGroup(noMdEntryTypes1);

		MarketDataRequest.NoMDEntryTypes noMdEntryTypes2 = new MarketDataRequest.NoMDEntryTypes();
		noMdEntryTypes2.set(new MDEntryType(MDEntryType.OFFER));
		request.addGroup(noMdEntryTypes2);

		MarketDataRequest.NoRelatedSym norelatedSym = new MarketDataRequest.NoRelatedSym();
		norelatedSym.set(new Symbol(symbol));
		request.addGroup(norelatedSym);
		Session session = Session.lookupSession(sessionID);
		session.send(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fortex.quickRing.InitiatorApplication#getUserName()
	 */
	@Override
	protected String getUserName() {
		String userName = null;
		try {
			userName = settings.getString(KEY_FQS_USER_NAME);
		} catch (ConfigError | FieldConvertError e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		} 
		return userName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fortex.quickRing.InatiatorApplication#getPassword()
	 */
	@Override
	protected String getPassword() {
		String password = null;
		try {
			password = settings.getString(KEY_FQS_PASSWORD);
		} catch (ConfigError | FieldConvertError e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		} 
		return password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fortex.quickRing.InatiatorApplication#getResetMsgSqeNum()
	 */
	/*
	@Override
	protected String getResetMsgSqeNum() {
		return "Y";
	}
	*/
	/**
	 * <p>
	 * Description:send msg to QM server
	 * </p>
	 * 
	 * @author Ivan Huo
	 * @date 2016-08-23
	 * @param conn
	 * @param msgType
	 * @param msg
	 */
	public void onFqsMsg(FortexConnection<?> conn, String msgType, JSONObject msg) {
		try {
			ErrInfo errInfo = new ErrInfo();
			switch (msgType) {
			case ProtoDef.MSGTYPE.Login: {
				fqsAdaptor.subscribeQMSettings(fqsAdaptor.connMdCfg.username, "0", "*", "*", errInfo);
				break;
			}
			case ProtoDef.MSGTYPE.QuoteMaker: {
				// Save QM Update
				qmAdpt.onQmUpdate(msg);
				break;
			}
			}
		} catch (Exception e) {
			Logger.getLogger("EventError").error(new Throwable().getStackTrace()[0] + " exception: " + e.getMessage(), e);
		}
	}
	
	/**
	 * <p>
	 * Description:cache market from FQS
	 * </p>
	 * 
	 * @author Patrick Chi
	 * @date 2016-08-24
	 * @param message
	 * 
	 */

	private void parseMarketData(Message message) throws FieldNotFound {
		
		String symbol = message.getString(Symbol.FIELD);
		if (symbol != null && message.hasGroup(NoMDEntries.FIELD) && message.getGroups(NoMDEntries.FIELD) != null
				&& message.getGroups(NoMDEntries.FIELD).size() > 0) {
			//sort all groups by symbol from FQS
			message.getGroups(NoMDEntries.FIELD).sort(new Comparator<Group>() {
				@Override
				public int compare(Group g1, Group g2) {
					int result = 0;
					try {
						if (!(g1 == g2)) {
							char mdEntryType1 = g1.getChar(MDEntryType.FIELD);
							char mdEntryType2 = g2.getChar(MDEntryType.FIELD);
						
							if (mdEntryType1 == MDEntryType.BID && mdEntryType2 == MDEntryType.OFFER) {
								result = 1;
							} else if (mdEntryType1 == MDEntryType.OFFER && mdEntryType2 == MDEntryType.BID) {
								result = -1;
							} else if (mdEntryType1 == MDEntryType.BID && mdEntryType2 == MDEntryType.BID) {								
								result = g2.getDecimal(MDEntryPx.FIELD).compareTo(g1.getDecimal(MDEntryPx.FIELD));
							} else if (mdEntryType1 == MDEntryType.OFFER && mdEntryType2 == MDEntryType.OFFER) {
								result = g1.getDecimal(MDEntryPx.FIELD).compareTo(g2.getDecimal(MDEntryPx.FIELD));
							}
						}
					} catch (FieldNotFound e) {
						Logger.getLogger("EventError").error(e.getMessage(), e);
					}
					return result;
				}
			});
			QuoteCache.cacheMarketDataGroup(symbol, message.getGroups(NoMDEntries.FIELD));
			QuoteCache.putSymbolEncodedTime(symbol, System.nanoTime() - message.getReceivedTime());
			QuoteStatistician.getInstance().addTotalReceived();
			addSymbol(symbol);
		}
	}
	
	/**
	 * <p>
	 * Description:Call parseMarketData
	 * </p>
	 * 
	 * @author Patrick Chi
	 * @date 2016-08-24
	 * @param message
	 * 
	 */

	protected void parseMsgAndSendToClient(Message message) {
		try {
			Header header = message.getHeader();
			String msgType = header.getString(MsgType.FIELD);

			if (MsgType.MARKET_DATA_SNAPSHOT_FULL_REFRESH.equals(msgType)) {
				parseMarketData(message);
			} 
		} catch (Exception e) {
			Logger.getLogger("EventError").error(e.getMessage() + " message:" + message, e);
		}
	}

	@Override
	public void onLogout(SessionID sessionId) {
		String[] symbols = QuoteCache.getAllSymbols();
		for (String symbol : symbols) {
			ConcurrentSkipListSet<SessionIDMarketDepthModel> models = QuoteCache.getSessionIDsBySymbol(symbol);	
			for (SessionIDMarketDepthModel model : models) {
				Session session = Session.lookupSession(model.getSessionID());
				Message msg = session.getMessageFactory().create(
						model.getSessionID().getBeginString(),
						MsgType.MARKET_DATA_SNAPSHOT_FULL_REFRESH);
				msg.setString(MDReqID.FIELD, model.getMdReqId());
				msg.setString(Symbol.FIELD, symbol);	
				msg.setField(new NoMDEntries(0));
				session.send(msg);
			}
		}
	}
}
