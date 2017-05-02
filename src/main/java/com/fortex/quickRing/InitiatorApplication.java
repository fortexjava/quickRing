package com.fortex.quickRing;

import org.apache.log4j.Logger;

import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.UnsupportedMessageType;
import quickfix.field.MsgType;
import quickfix.field.Password;
import quickfix.field.Username;
import quickfix.fix44.MessageCracker;

public abstract class InitiatorApplication extends MessageCracker implements Application {
	protected SessionSettings settings = null;
	
	@Override
	public void onCreate(SessionID sessionId) {

	}

	@Override
	public void onLogon(SessionID sessionId) {

	}

	@Override
	public void onLogout(SessionID sessionId) {

	}

	@Override
	public void toAdmin(Message message, SessionID sessionId) {
		try {
			if (message.getHeader().getString(MsgType.FIELD).equals(MsgType.LOGON)) {
				message.setString(Username.FIELD, getUserName());
				message.setString(Password.FIELD, getPassword());
				//message.setString(ResetSeqNumFlag.FIELD, getResetMsgSqeNum());
			}
		} catch (FieldNotFound e) {
			Logger.getLogger("EventError").error("toAdmin ocurs an error.", e);
		}
	}

	@Override
	public void toApp(Message message, SessionID sessionId) throws DoNotSend {	
	}
	
	@Override
	public void fromApp(Message message, SessionID sessionId)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		/*
		if (count == 0) {
			startTime = System.currentTimeMillis();
		}
		*/
		
		parseMsgAndSendToClient(message);
	
	}

	@Override
	public void fromAdmin(Message message, SessionID sessionId)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
	//	parseMsgAndSendToClient(message);
	}
	
	protected abstract void parseMsgAndSendToClient(Message message);

	/**
	 * 
	 * <p>
	 * Description:Get user name to logon for FQS or FTS
	 * </p>
	 *
	 * @author Patrick Chi
	 * @date 2016-08-05
	 * @return
	 */
	protected abstract String getUserName();

	/**
	 * 
	 * <p>
	 * Description:Get password to logon for FQS or FTS
	 * </p>
	 *
	 * @author Patrick Chi
	 * @date 2016-08-05
	 * @return
	 */

	protected abstract String getPassword();

	/**
	 * 
	 * <p>
	 * Description:Identify if the message sequence number should be reset. 'Y'
	 * for trade, 'N' for quote
	 * </p>
	 *
	 * @author Patrick Chi
	 * @date 2016-08-05
	 * @return
	 */
	//protected abstract String getResetMsgSqeNum();

}
