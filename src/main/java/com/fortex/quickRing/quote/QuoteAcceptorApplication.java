package com.fortex.quickRing.quote;

import java.util.List;

import org.apache.log4j.Logger;

import com.fortex.constants.Constants;
import com.fortex.quickRing.AcceptorApplication;
import com.fortex.quickRing.cache.QuoteCache;
import com.fortex.quickRing.cache.SessionCache;
import com.fortex.quickRing.cache.SessionCache.SERVER_TYPE;
import com.fortex.quickRing.model.SessionIDMarketDepthModel;
import com.fortex.quickRing.model.UserModel;

import quickfix.FieldNotFound;
import quickfix.Group;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.MDReqID;
import quickfix.field.MDReqRejReason;
import quickfix.field.MDUpdateType;
import quickfix.field.MarketDepth;
import quickfix.field.MsgDirection;
import quickfix.field.MsgType;
import quickfix.field.NoMDEntryTypes;
import quickfix.field.NoRelatedSym;
import quickfix.field.RefMsgType;
import quickfix.field.SubscriptionRequestType;
import quickfix.field.Symbol;
import quickfix.field.Text;
import quickfix.fix44.Logon;
import quickfix.fix44.MarketDataRequest;
import quickfix.fix44.MarketDataRequestReject;
import quickfix.fix44.MarketDataSnapshotFullRefresh;

public class QuoteAcceptorApplication extends AcceptorApplication {

	private static final int RELATED_SYM_SIZE = 1;

	private static final int MD_ENTRY_TYPES_SIZE = 2;

	private static final int QUOTE_LOGIN_TYPE = 3;
	
	private static final char UNKNOWN_SYMBOL = '1';

	private QuoteMakerAdapter qmAdpt;

	public QuoteAcceptorApplication(QuoteMakerAdapter qmAdpt) {
		this.qmAdpt = qmAdpt;
	}

	/*
	@Override
	public void onLogon(SessionID sessionId) {
		defaultLogger.info("### On client logon ## session id:" + sessionId);
		try {
			String account = SessionCache.getAccountBySessionId(sessionId);
			int domain = DBOperation.getDomainByAccount(account);
			SessionCache.putDomain(sessionId, domain);
		} catch (SQLException e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		}
	}
	*/
	@Override
	protected Session getServerSession() {
		return SessionCache.getSession(SERVER_TYPE.FQS);
	}

	@Override
	public void onLogout(SessionID sessionID) {
		super.onLogout(sessionID);
		QuoteCache.removeSessionIDForSymbol(sessionID);
		//SessionCache.removeSessionDomainCache(sessionID);
	}

	@Override
	protected void doFromApp(Message message, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		String msgType = message.getHeader().getString(MsgType.FIELD);
		List<Group> groups = message.getGroups(NoRelatedSym.FIELD);
		Group group = groups.get(0);
		String symbol = group.getString(Symbol.FIELD);
		UserModel user = SessionCache.getUserModelBySessionId(sessionID);
		Logger.getLogger("Event").info(sessionID + " subscribe market data with message:" + message);
		if (MsgType.MARKET_DATA_REQUEST.equals(msgType)) {
		//	MarketDataCacheModel model = QuoteCache.getMarketDataBySymble(symbol);
			if (!QuoteCache.isSupportedSymbol(symbol)){
				sendMarketDataRequestReject(message, sessionID, "Unknown symbol.");
			} else if (message.getChar(SubscriptionRequestType.FIELD) == (SubscriptionRequestType.SNAPSHOT_PLUS_UPDATES)) {
				if (user.getQuoteService() != Constants.QUOTE_SERVICE_0) {
					QuoteCache.addASessionIDToSymbolSessionID(symbol, new SessionIDMarketDepthModel(sessionID,
							message.getInt(MarketDepth.FIELD), message.getString(MDReqID.FIELD)));
					List<Group> marketDatas = QuoteCache.getMarketDataBySymble(symbol);
					if (marketDatas != null && user != null) {
						if (user.getQuoteService() == Constants.QUOTE_SERVICE_1 || user.getQuoteService() == Constants.QUOTE_SERVICE_2) {
							responseGroup(sessionID, msgType, marketDatas, symbol, 1,
									message.getString(MDReqID.FIELD));
						} else {
							responseGroup(sessionID, msgType, marketDatas, symbol, message.getInt(MarketDepth.FIELD),
									message.getString(MDReqID.FIELD));
						}
					} 
				}  else {
					sendMarketDataRequestReject(message, sessionID, "User has no permission to subscribe any Market Data.");
				}
			} else if (message.getChar(
					SubscriptionRequestType.FIELD) == (SubscriptionRequestType.DISABLE_PREVIOUS_SNAPSHOT_PLUS_UPDATE_REQUEST)) {
				QuoteCache.removeSessionIDFromSymbolSessionID(symbol, sessionID);
			}
		}
	}
	
	private void sendMarketDataRequestReject(Message originalMessage, SessionID sessionID, String reason) throws FieldNotFound {
		Session session = Session.lookupSession(sessionID);
		Message msg = session.getMessageFactory().create(sessionID.getBeginString(), MsgType.MARKET_DATA_REQUEST_REJECT);
		msg.setString(MsgType.FIELD, MarketDataRequestReject.MSGTYPE);
		msg.setString(MDReqID.FIELD, originalMessage.getString(MDReqID.FIELD));
		msg.setChar(MDReqRejReason.FIELD, UNKNOWN_SYMBOL);
		msg.setString(Text.FIELD, reason);
		session.send(msg);
		Logger.getLogger("Event").info(sessionID + " rejected market data request with message:" + msg);
	}

	protected void responseGroup(SessionID sessionID, String msgType, List<Group> marketDatas, String symbol,
			int depth, String reqId) throws FieldNotFound {
		UserModel user = SessionCache.getUserModelBySessionId(sessionID);
		if (user != null) {
			Session session = Session.lookupSession(sessionID);
			Message msg = session.getMessageFactory().create(sessionID.getBeginString(),
					MsgType.MARKET_DATA_SNAPSHOT_FULL_REFRESH);
			Integer domain = user.getDomain();
			msg.setString(Symbol.FIELD, symbol);
			msg.setString(MDReqID.FIELD, reqId);
			if (depth <= 0 || depth > marketDatas.size())
				depth = marketDatas.size();
			Message m = qmAdpt.calculate(symbol, domain, marketDatas, msg, depth);
			session.send(m);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @author Ivan Huo
	 * 
	 * @see
	 * com.fortex.quickRing.AcceptorApplication#verifyMessageContent(quickfix.
	 * Message)
	 */
	@Override
	protected void verifyMessageContent(Message message)
			throws FieldNotFound, IncorrectDataFormat, UnsupportedMessageType {
		// TODO Auto-generated method stub
		
		String msgType = message.getHeader().getString(MsgType.FIELD);
		if (MsgType.MARKET_DATA_REQUEST.equals(msgType)) {
			char subRequestType = message.getChar(SubscriptionRequestType.FIELD);
			if (subRequestType == SubscriptionRequestType.SNAPSHOT_PLUS_UPDATES) {
				if (!message.isSetField(MDUpdateType.FIELD))
					throw new FieldNotFound(MDUpdateType.FIELD);
			}
			if (message.getGroups(NoRelatedSym.FIELD).size() != RELATED_SYM_SIZE) {
				throw new IncorrectDataFormat(NoRelatedSym.FIELD);
			}

			if (message.getGroups(NoMDEntryTypes.FIELD).size() != MD_ENTRY_TYPES_SIZE) {
				throw new IncorrectDataFormat(NoMDEntryTypes.FIELD);
			}
		} else {
			throw new UnsupportedMessageType();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fortex.quickRing.AcceptorApplication#getLoginType()
	 */
	@Override
	public int getLoginType() {
		// TODO Auto-generated method stub
		return QUOTE_LOGIN_TYPE;
	}

	@Override
	protected void responseMsgTypes(Message message) {
		Group marketDataRequest = new Logon.NoMsgTypes();
		marketDataRequest.setString(RefMsgType.FIELD,  MarketDataRequest.MSGTYPE);
		marketDataRequest.setChar(MsgDirection.FIELD,  MsgDirection.RECEIVE);
		message.addGroup(marketDataRequest);
		
		Group marketDataSnapshotFullRefresh = new Logon.NoMsgTypes();
		marketDataSnapshotFullRefresh.setString(RefMsgType.FIELD,  MarketDataSnapshotFullRefresh.MSGTYPE);
		marketDataSnapshotFullRefresh.setChar(MsgDirection.FIELD,  MsgDirection.SEND);
		message.addGroup(marketDataSnapshotFullRefresh);
		
		Group marketDataRequestReject = new Logon.NoMsgTypes();
		marketDataRequestReject.setString(RefMsgType.FIELD,  MarketDataRequestReject.MSGTYPE);
		marketDataRequestReject.setChar(MsgDirection.FIELD,  MsgDirection.SEND);
		message.addGroup(marketDataRequestReject);
		
	}

	@Override
	public void onLogon(SessionID sessionId) {
		
	}

}
