package com.fortex.quickRing.trade;

import com.fortex.quickRing.AcceptorApplication;
import com.fortex.quickRing.Statistics.TradeStatistician;
import com.fortex.quickRing.cache.SessionCache;
import com.fortex.quickRing.cache.SessionCache.SERVER_TYPE;
import com.fortex.quickRing.cache.TradeCache;
import com.fortex.quickRing.model.UserModel;
import com.fortex.quickRing.utils.GenerateFortexFix;

import quickfix.FieldNotFound;
import quickfix.Group;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.UnsupportedMessageType;
import quickfix.field.Account;
import quickfix.field.BusinessRejectReason;
import quickfix.field.ClOrdID;
import quickfix.field.MsgDirection;
import quickfix.field.MsgSeqNum;
import quickfix.field.MsgType;
import quickfix.field.OrdType;
import quickfix.field.OrigClOrdID;
import quickfix.field.Price;
import quickfix.field.RefMsgType;
import quickfix.field.SessionRejectReason;
import quickfix.fix44.ExecutionReport;
import quickfix.fix44.Logon;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.OrderCancelReject;
import quickfix.fix44.OrderCancelRequest;

public class TradeAcceptorApplication extends AcceptorApplication {
	
	private static final int TRADE_LOGIN_TYPE = 2;
	
	private static final int LENGTH_OF_ACCOUNT = 15; 
	
	private static final int LENGTH_OF_ORDER_ID = 50;
	
	private static final int BUSINESS_REJECT_REASON = 100;
	
	public static final int ORIGINAL_SESSION_ID = 8888;
	
	public static final int ORIGINAL_MESSAGE_SEQ_NUM = 8889;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fortex.quickRing.AcceptorApplication#getSession()
	 */
	
	public TradeAcceptorApplication(SessionSettings settings) {
		
	}
	
	@Override
	protected Session getServerSession() {
		return SessionCache.getSession(SERVER_TYPE.FTS);
	}


	/*
	 * (non-Javadoc)
	 * @author Patrick Chi
	 * @see com.fortex.quickRing.AcceptorApplication#doFromApp(quickfix.Message, quickfix.SessionID)
	 */
	@Override
	protected void doFromApp(Message message, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		Session session = this.getServerSession();
		String username = message.getString(Account.FIELD);
		UserModel user = SessionCache.getUserModelBySessionId(sessionID);
		Session clientSession = Session.lookupSession(sessionID);
		TradeStatistician statistician = TradeStatistician.getInstance();
		if (user != null) {
			if (!username.equals(user.getUserName())) {
				clientSession.send(generateReject(clientSession, message, "Account does not match with user name.",
						MsgType.REJECT, SessionRejectReason.FIELD, SessionRejectReason.OTHER));
			} else {
				if (TradeCache.getPendingOrderPerSession() > TradeCache.getPendingCount(sessionID)) {
					message.setString(ClOrdID.FIELD, user.getUserName() + "-" + message.getString(ClOrdID.FIELD));
					if (message.isSetField(OrigClOrdID.FIELD))
						message.setString(OrigClOrdID.FIELD,
								user.getUserName() + "-" + message.getString(OrigClOrdID.FIELD));

					Message msg = GenerateFortexFix.generateMessage(message, session, false);
					msg.setString(ORIGINAL_SESSION_ID, sessionID.toString());
					msg.setInt(ORIGINAL_MESSAGE_SEQ_NUM, message.getHeader().getInt(MsgSeqNum.FIELD));
					session.send(msg);
					TradeStatistician.getInstance().proccessForRouteTime(System.nanoTime() - message.getReceivedTime());
					TradeCache.addPendingCount(sessionID);
				} else {
					Message msg = generateReject(session, message, "Number of pending orders exceeds threshold.",
							MsgType.BUSINESS_MESSAGE_REJECT, BusinessRejectReason.FIELD, BUSINESS_REJECT_REASON);
					clientSession.send(msg);
					statistician.addOrderSendToClientTotal();
				}
				statistician.addOrderReceivedTotal();
			}
		}
	}

	/* (non-Javadoc)
	 * @author Ivan Huo
	 * @see com.fortex.quickRing.AcceptorApplication#verifyMessageContent(quickfix.Message)
	 */
	@Override
	protected void verifyMessageContent(Message message) throws FieldNotFound,IncorrectDataFormat,UnsupportedMessageType, IncorrectTagValue {
		// TODO Auto-generated method stub
		//char timeInForce = message.getChar(TimeInForce.FIELD);
		String msgType = message.getHeader().getString(MsgType.FIELD);
		if(MsgType.ORDER_SINGLE.equals(msgType) || MsgType.ORDER_CANCEL_REQUEST.equals(msgType)) {
			if (MsgType.ORDER_SINGLE.equals(msgType)){
				String clOrderID = message.getString(ClOrdID.FIELD);
				String account = message.getString(Account.FIELD);
				if (message.getString(Account.FIELD).length() > LENGTH_OF_ACCOUNT) {
					throw new IncorrectTagValue(Account.FIELD, message.getString(Account.FIELD));
				} else if(clOrderID.length() >= LENGTH_OF_ORDER_ID - account.length()) {
					throw new IncorrectTagValue(ClOrdID.FIELD, clOrderID);
				} else if (message.getChar(OrdType.FIELD) == OrdType.LIMIT && !message.isSetField(Price.FIELD)) {
					throw new FieldNotFound(Price.FIELD);
				}
			}
		} else {
			throw new UnsupportedMessageType();
		}
	}

	/* (non-Javadoc)
	 * @author Ivan Huo
	 * @see com.fortex.quickRing.AcceptorApplication#setLoginType()
	 */
	@Override
	public int getLoginType() {
		return TRADE_LOGIN_TYPE;
	}



	@Override
	protected void responseMsgTypes(Message message) {
		Group newOrderSingle = new Logon.NoMsgTypes();
		newOrderSingle.setString(RefMsgType.FIELD,  NewOrderSingle.MSGTYPE);
		newOrderSingle.setChar(MsgDirection.FIELD,  MsgDirection.RECEIVE);
		message.addGroup(newOrderSingle);
		
		Group orderCancelRequest = new Logon.NoMsgTypes();
		orderCancelRequest.setString(RefMsgType.FIELD,  OrderCancelRequest.MSGTYPE);
		orderCancelRequest.setChar(MsgDirection.FIELD,  MsgDirection.RECEIVE);
		message.addGroup(orderCancelRequest);
		
		Group executionReport = new Logon.NoMsgTypes();
		executionReport.setString(RefMsgType.FIELD,  ExecutionReport.MSGTYPE);
		executionReport.setChar(MsgDirection.FIELD,  MsgDirection.SEND);
		message.addGroup(executionReport);
		
		Group orderCancelReject = new Logon.NoMsgTypes();
		orderCancelReject.setString(RefMsgType.FIELD,  OrderCancelReject.MSGTYPE);
		orderCancelReject.setChar(MsgDirection.FIELD,  MsgDirection.SEND);
		message.addGroup(orderCancelReject);
	}

	@Override
	public void onLogon(SessionID sessionId) {
		TradeCache.resetPendingOrderPerSession(TradeCache.getPendingOrderCount());
	}

	@Override
	public void onLogout(SessionID sessionID) {
		super.onLogout(sessionID);
		TradeCache.resetPendingOrderPerSession(TradeCache.getPendingOrderCount());
	}
}
