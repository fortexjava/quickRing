package com.fortex.quickRing;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.fortex.quickRing.cache.SessionCache;
import com.fortex.quickRing.db.DBOperation;
import com.fortex.quickRing.model.UserModel;

import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.Group;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.MsgDirection;
import quickfix.field.MsgSeqNum;
import quickfix.field.MsgType;
import quickfix.field.Password;
import quickfix.field.RefMsgType;
import quickfix.field.RefSeqNum;
import quickfix.field.ResetSeqNumFlag;
import quickfix.field.SessionRejectReason;
import quickfix.field.Text;
import quickfix.field.Username;
import quickfix.fix44.BusinessMessageReject;
import quickfix.fix44.Heartbeat;
import quickfix.fix44.Logon;
import quickfix.fix44.Logout;
import quickfix.fix44.MessageCracker;
import quickfix.fix44.Reject;
import quickfix.fix44.ResendRequest;
import quickfix.fix44.SequenceReset;
import quickfix.fix44.TestRequest;

public abstract class AcceptorApplication extends MessageCracker implements Application {
	
	@Override
	public void onCreate(SessionID sessionId) {

	}
	


	public abstract int getLoginType();

	protected abstract void verifyMessageContent(Message message)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType;

	protected abstract Session getServerSession();

	protected abstract void doFromApp(Message message, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType;

	protected abstract void responseMsgTypes(Message message);

	/**
	 * 
	 * <p>Description:Set fields to response for logon</p> 
	 *
	 * @author Patrick Chi
	 * @date 2017-03-27 
	 * @param message
	 */
	private void responseCommonMsgTypes(Message message) {
		Group groupHeartbeatSend = new Logon.NoMsgTypes();
		groupHeartbeatSend.setString(RefMsgType.FIELD, Heartbeat.MSGTYPE);
		groupHeartbeatSend.setChar(MsgDirection.FIELD, MsgDirection.SEND);
		message.addGroup(groupHeartbeatSend);
		
		Group groupHeartbeatReceive = new Logon.NoMsgTypes();
		groupHeartbeatReceive.setString(RefMsgType.FIELD, Heartbeat.MSGTYPE);
		groupHeartbeatReceive.setChar(MsgDirection.FIELD, MsgDirection.RECEIVE);
		message.addGroup(groupHeartbeatReceive);
		
		Group groupTestRequestSend = new Logon.NoMsgTypes();
		groupTestRequestSend.setString(RefMsgType.FIELD,  TestRequest.MSGTYPE);
		groupTestRequestSend.setChar(MsgDirection.FIELD,  MsgDirection.SEND);
		message.addGroup(groupTestRequestSend);
		
		Group groupTestRequestReceive = new Logon.NoMsgTypes();
		groupTestRequestReceive.setString(RefMsgType.FIELD,  TestRequest.MSGTYPE);
		groupTestRequestReceive.setChar(MsgDirection.FIELD,  MsgDirection.RECEIVE);
		message.addGroup(groupTestRequestReceive);
		
		Group groupRequestResendSend = new Logon.NoMsgTypes();
		groupRequestResendSend.setString(RefMsgType.FIELD,  ResendRequest.MSGTYPE);
		groupRequestResendSend.setChar(MsgDirection.FIELD,  MsgDirection.SEND);
		message.addGroup(groupRequestResendSend);
		
		Group groupRequestResendReceive = new Logon.NoMsgTypes();
		groupRequestResendReceive.setString(RefMsgType.FIELD,  ResendRequest.MSGTYPE);
		groupRequestResendReceive.setChar(MsgDirection.FIELD,  MsgDirection.RECEIVE);
		message.addGroup(groupRequestResendReceive);
		
		Group groupRejectSend = new Logon.NoMsgTypes();
		groupRejectSend.setString(RefMsgType.FIELD,  Reject.MSGTYPE);
		groupRejectSend.setChar(MsgDirection.FIELD,  MsgDirection.SEND);
		message.addGroup(groupRejectSend);
		
		Group groupRejectReject = new Logon.NoMsgTypes();
		groupRejectReject.setString(RefMsgType.FIELD,  Reject.MSGTYPE);
		groupRejectReject.setChar(MsgDirection.FIELD,  MsgDirection.RECEIVE);
		message.addGroup(groupRejectReject);
		
		Group sequenceResetSend = new Logon.NoMsgTypes();
		sequenceResetSend.setString(RefMsgType.FIELD,  SequenceReset.MSGTYPE);
		sequenceResetSend.setChar(MsgDirection.FIELD,  MsgDirection.SEND);
		message.addGroup(sequenceResetSend);
		
		Group sequenceResetReceive = new Logon.NoMsgTypes();
		sequenceResetReceive.setString(RefMsgType.FIELD,  SequenceReset.MSGTYPE);
		sequenceResetReceive.setChar(MsgDirection.FIELD,  MsgDirection.RECEIVE);
		message.addGroup(sequenceResetReceive);
		
		Group logonSend = new Logon.NoMsgTypes();
		logonSend.setString(RefMsgType.FIELD,  Logon.MSGTYPE);
		logonSend.setChar(MsgDirection.FIELD,  MsgDirection.SEND);
		message.addGroup(logonSend);
		
		Group logonReceive = new Logon.NoMsgTypes();
		logonReceive.setString(RefMsgType.FIELD,  Logon.MSGTYPE);
		logonReceive.setChar(MsgDirection.FIELD,  MsgDirection.RECEIVE);
		message.addGroup(logonReceive);
		
		Group logoutReceive = new Logon.NoMsgTypes();
		logoutReceive.setString(RefMsgType.FIELD,  Logout.MSGTYPE);
		logoutReceive.setChar(MsgDirection.FIELD,  MsgDirection.RECEIVE);
		message.addGroup(logoutReceive);
		
		Group logoutSend = new Logon.NoMsgTypes();
		logoutSend.setString(RefMsgType.FIELD,  Logout.MSGTYPE);
		logoutSend.setChar(MsgDirection.FIELD,  MsgDirection.SEND);
		message.addGroup(logoutSend);
		
		Group businessReject = new Logon.NoMsgTypes();
		businessReject.setString(RefMsgType.FIELD,  BusinessMessageReject.MSGTYPE);
		businessReject.setChar(MsgDirection.FIELD,  MsgDirection.SEND);
		message.addGroup(businessReject);
		
		responseMsgTypes(message);
	}

	@Override
	public void onLogout(SessionID sessionID) {
		/*
		UserModel user = SessionCache.getAccountBySessionId(sessionID);
		if(user != null)
			SessionCache.removeAccountFromCache(user.getUserName());
		*/
		SessionCache.removeSessionId(sessionID);
		SessionCache.removeSessionIdForAccount(sessionID);
		Logger.getLogger("Event").info(sessionID + " logouted.");
	}

	@Override
	public void toAdmin(Message message, SessionID sessionId) {
		String msgType;
		try {
			msgType = message.getHeader().getString(MsgType.FIELD);
			if(msgType.equals(MsgType.LOGON) && message.isSetField(ResetSeqNumFlag.FIELD)) {
				message.removeField(ResetSeqNumFlag.FIELD);
				responseCommonMsgTypes(message);
			}
		} catch (FieldNotFound e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		}
		
	}

	@Override
	public void fromAdmin(Message message, SessionID sessionId)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {	
		
		try {
			String msgType = message.getHeader().getString(MsgType.FIELD);
			if (msgType.equals(MsgType.LOGON)) {
				if (!message.isSetField(Username.FIELD) || !message.isSetField(Password.FIELD)) {
					throw new RejectLogon("User name or password is not set");
				} else if (message.getHeader().getInt(MsgSeqNum.FIELD) != 1) {
					throw new RejectLogon("Field 34 must be 1 when user login");
				} else if (!"Y".equals(message.getString(ResetSeqNumFlag.FIELD))) {
					throw new RejectLogon("Field 141 must be Y when user login");
				}
				String userName = message.getString(Username.FIELD);
				String password = message.getString(Password.FIELD);
				String targetId = sessionId.getTargetCompID();
				
				//boolean blockCheck = Boolean.valueOf(ConfigSetting.getServerProperty("blockUserCheck"));	
				
//				if(blockCheck && DBOperation.isBlocking(userName))
//					throw new RejectLogon("This user is blocked");
				
				UserModel user = DBOperation.getUserInfo(userName, password,targetId, getLoginType());
				if (user == null) {
					throw new RejectLogon("User name does not match with password or SenderCompID");
				}
				
				SessionCache.putUserModelBySessionID(sessionId, user);
				SessionCache.putAccountSessionId(userName, sessionId);
				//SessionCache.putSessionIdAccount(userName, sessionId);
			}
		} catch (SecurityException e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		} catch (SQLException e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		}

	}

	@Override
	public void toApp(Message message, SessionID sessionId) throws DoNotSend {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see quickfix.Application#fromApp(quickfix.Message, quickfix.SessionID)
	 */
	@Override
	public void fromApp(Message message, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		
		Session serverSession = this.getServerSession();
		if(serverSession == null || !serverSession.isLogonReceived()){
			Session s = Session.lookupSession(sessionID);
			Message msg = s.getMessageFactory().create(sessionID.getBeginString(), Reject.MSGTYPE);
			//msg.setString(MsgType.FIELD, MsgType.REJECT);
			msg.setString(RefSeqNum.FIELD,message.getHeader().getString(MsgSeqNum.FIELD));
			msg.setString(RefMsgType.FIELD, message.getHeader().getString(MsgType.FIELD));
			msg.setInt(SessionRejectReason.FIELD, SessionRejectReason.OTHER);
			msg.setString(Text.FIELD, "Service is not available.");
			s.send(msg);
		}else{
			verifyMessageContent(message);
			doFromApp(message, sessionID);
		}
	}
	
	protected Message generateReject(Session session, Message message, String text, String rejectMsgType, int reasonField, int reasonCode) throws FieldNotFound {
		Message msg = session.getMessageFactory().create(session.getSessionID().getBeginString(), rejectMsgType);
		msg.setString(RefSeqNum.FIELD, message.getHeader().getString(MsgSeqNum.FIELD));
		msg.setString(RefMsgType.FIELD, message.getHeader().getString(MsgType.FIELD));
		msg.setInt(reasonField, reasonCode);
		msg.setString(Text.FIELD, text);
		return msg;
	}

}
