package com.fortex.quickRing.trade;

import org.apache.log4j.Logger;

import com.fortex.quickRing.InitiatorApplication;
import com.fortex.quickRing.Statistics.TradeStatistician;
import com.fortex.quickRing.cache.SessionCache;
import com.fortex.quickRing.cache.SessionCache.SERVER_TYPE;
import com.fortex.quickRing.cache.TradeCache;
import com.fortex.quickRing.model.SessionResponsedModel;
import com.fortex.quickRing.utils.GenerateFortexFix;

import quickfix.ConfigError;
import quickfix.DoNotSend;
import quickfix.FieldConvertError;
import quickfix.FieldNotFound;
import quickfix.Message;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.field.ClOrdID;
import quickfix.field.MsgSeqNum;
import quickfix.field.MsgType;
import quickfix.field.OrdStatus;
import quickfix.field.OrderID;
import quickfix.field.OrigClOrdID;
import quickfix.field.RefSeqNum;

public class TradeInitiatorApplication extends InitiatorApplication {
	public static final int ORIGINAL_SESSION_ID = 8888;
	
	public static final int ORIGINAL_MESSAGE_SEQ_NUM = 8889;

	private static final String KEY_FTS_USER_NAME = "FTSUserName";

	private static final String KEY_FTS_PASSWORD = "FTSPassword";
	
	private static final String KEY_CLEAN_THREAD_TIME = "CleanThreadTime";
	
	private static final String KEY_CLEAN_TIME = "CleanTime";
	
	public TradeInitiatorApplication(SessionSettings settings) {
		this.settings = settings;
		new Thread(new Runnable() {
			public void run() {
 				while (true) {
 					 try {
 						Thread.sleep(settings.getLong(KEY_CLEAN_THREAD_TIME) * 1000);
 						TradeCache.cleanMdReqIdSessionIdByMdReqId(settings.getLong(KEY_CLEAN_TIME) * 1000);
 					 }
 					 catch(InterruptedException | ConfigError | FieldConvertError e){
 						Logger.getLogger("EventError").error(e.getMessage(),e);
 					 } 
 				}
			}
		}).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fortex.quickRing.InatiatorApplication#onLogon(quickfix.SessionID)
	 */
	@Override
	public void onLogon(SessionID sessionID) {
		SessionCache.setSession(SERVER_TYPE.FTS, Session.lookupSession(sessionID));
		TradeCache.resetPendingOrderPerSession(TradeCache.getPendingOrderCount());
	}
	
	@Override
	public void onLogout(SessionID sessionId) {
		TradeCache.resetAllPendingCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fortex.quickRing.InatiatorApplication#onLogon(quickfix.SessionID)
	 */
	@Override
	protected String getUserName() {
		String userName = null;
		try {
			userName = settings.getString(KEY_FTS_USER_NAME);
		} catch (ConfigError | FieldConvertError e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		} 
		return userName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fortex.quickRing.InatiatorApplication#onLogon(quickfix.SessionID)
	 */
	@Override
	protected String getPassword() {
		String password = null;
		try {
			password = settings.getString(KEY_FTS_PASSWORD);
		} catch (ConfigError | FieldConvertError e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		} 
		return password;
	}
	
	/**
	 * @author Patrick Chi
	 */
	@Override
	public void toApp(Message message, SessionID sessionId) throws DoNotSend {
		
		try {
			SessionID originalSessionID = new SessionID(message.getString(ORIGINAL_SESSION_ID));
			SessionResponsedModel model = new SessionResponsedModel(originalSessionID, message.getInt(ORIGINAL_MESSAGE_SEQ_NUM));
			TradeCache.putModelWithOrderId(message.getString(ClOrdID.FIELD), model);
			TradeCache.putModelWithSeqNo(message.getHeader().getInt(MsgSeqNum.FIELD), model);
			message.removeField(ORIGINAL_SESSION_ID);
		} catch (FieldNotFound e) {
			Logger.getLogger("EventError").error(e.getMessage(),e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fortex.quickRing.InitiatorApplication#parseMsgAndSendToClient(quickfix.Message)
	 * 
	 */
	@Override
	protected void parseMsgAndSendToClient(Message message) {
		TradeStatistician statistician = TradeStatistician.getInstance();
		statistician.addOrderRouteRespTotal();
		Session session = null;
		Message msg = null;
		SessionResponsedModel model = null;
		try {
			if (MsgType.EXECUTION_REPORT.equals(message.getHeader().getString(MsgType.FIELD))) {
				model = TradeCache.getModelByOrderId(message.getString(ClOrdID.FIELD));
				if (model != null) {
					session = Session.lookupSession(model.getSessionID());
					if (message.isSetField(OrdStatus.FIELD)) {
						if (message.getChar(OrdStatus.FIELD) == OrdStatus.REJECTED)
							statistician.addOrderRouteReject();
						else if (message.getChar(OrdStatus.FIELD) == OrdStatus.FILLED) {
							statistician.addOrderRouteFill();
						}
					}
				} else {
					Logger.getLogger("Event").info("#message [" + message + "] maybe not sent to client for trading. #");
				}
			} else {
				int refSeqNum = message.getInt(RefSeqNum.FIELD);
				model = TradeCache.getModelBySeqNo(refSeqNum);
				if (model != null) {
					String msgType = message.getHeader().getString(MsgType.FIELD);
					session = Session.lookupSession(model.getSessionID());
					if (MsgType.BUSINESS_MESSAGE_REJECT.equals(msgType) || MsgType.REJECT.equals(msgType))
						statistician.addOrderRouteReject();
				} else {
					Logger.getLogger("Event").info("#message [" + message + "] maybe not sent to client for trading. seqno:" + refSeqNum + "#");
				}
			}
			if (session != null) {
				msg = GenerateFortexFix.generateMessage(message, session, true);
				resetOrderId(msg, message);
				if (message.isSetField(RefSeqNum.FIELD) && model != null) {
					msg.setInt(RefSeqNum.FIELD, model.getOriginalMsgSeqNum());
					
				}
			}
		} catch (Exception e) {
			Logger.getLogger("EventError").error(e.getMessage() + (msg != null ? ",latest message:" + msg.toString() : ",orginal message:" + message.toString()),e);
		}	
		if(session != null && msg != null) {
			session.send(msg);
			TradeStatistician.getInstance().proccessForRespTime(System.nanoTime() - message.getReceivedTime());	
		}
		if (model != null && !model.isResponsed()) {
			model.setResponsed(true);
			TradeCache.reducePendingCount(session.getSessionID());
		}
	}
	
	private void resetOrderId(Message msg, Message message) throws FieldNotFound {
		if (message.isSetField(OrderID.FIELD))
			msg.setString(OrderID.FIELD, splitOrderIdFromFTS(message.getString(OrderID.FIELD)));
		if (message.isSetField(ClOrdID.FIELD))
			msg.setString(ClOrdID.FIELD, splitOrderIdFromFTS(message.getString(ClOrdID.FIELD)));
		if (message.isSetField(OrigClOrdID.FIELD))
			msg.setString(OrigClOrdID.FIELD, splitOrderIdFromFTS(message.getString(OrigClOrdID.FIELD)));
	}
	
	private static String splitOrderIdFromFTS(String orderIdFromFTS) {
		String[] orderFromFTS = orderIdFromFTS.split("\\-");
		String newOrderId = "";
		for(int i = 1; i < orderFromFTS.length; i++)
			if (!"".equals(newOrderId))
				newOrderId = newOrderId + "-" + orderFromFTS[i];
			else
				newOrderId = orderFromFTS[i];
		
		return newOrderId;
	}
}
