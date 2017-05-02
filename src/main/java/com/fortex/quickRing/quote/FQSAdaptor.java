package com.fortex.quickRing.quote;

import static com.fortex.lib.globalservices.FortexLogger.defaultLogger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.FileUtils;
import org.json.JSONObject;

import com.fortex.lib.connection.FortexConnection;
import com.fortex.lib.connection.FortexConnectionMgrInf;
import com.fortex.lib.connection.FortexFXJConnectionTcp;
import com.fortex.lib.connection.FortexJSONFilter;
import com.fortex.lib.globalservices.TimerRunner;
import com.fortex.lib.protocol.ErrDef;
import com.fortex.lib.protocol.ErrDef.ErrInfo;
import com.fortex.lib.protocol.FIX_DEF;
import com.fortex.lib.protocol.FXJ_DEF;
import com.fortex.lib.protocol.FortexJSONMsg;
import com.fortex.lib.protocol.XCLOUD_DEF;
import com.fortex.quickRing.utils.ConfigSetting;

public class FQSAdaptor implements FortexConnectionMgrInf<FortexJSONMsg>, TimerRunner.TimerCallBackInf  {

	protected QuoteInitiatorApplication quoteInitiatorApp = null;
	public FortexFXJConnectionTcp connMd = null;
	public FortexFXJConnectionTcp.Config connMdCfg = new FortexFXJConnectionTcp.Config();
	private static final SimpleDateFormat FORMATER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final String CONNMDCFG_ENABLEINLOG = "connMdCfg.enableInLog";
	private static final String CONNMDCFG_ENABLEOUTLOG = "connMdCfg.enableOutLog";
	private static final String CONNMDCFG_LOGINTIMEOUTMS = "connMdCfg.loginTimeoutMs";
	private static final String CONNMDCFG_IP = "connMdCfg.ip";
	private static final String CONNMDCFG_PASSWORD = "connMdCfg.password";
	private static final String CONNMDCFG_PORT = "connMdCfg.port";
	private static final String CONNMDCFG_RECONNECTINTVMS = "connMdCfg.reconnectIntvMs";
	private static final String CONNMDCFG_RECONNECTDUEMS = "connMdCfg.reconnectDueMs";
	private static final String CONNMDCFG_SENDERID = "connMdCfg.senderId";
	private static final String CONNMDCFG_USERNAME = "connMdCfg.username";
	private static final String CONNMDCFG_CHECKCONNECTION = "connMdCfg.checkConnection";
	private final String FQS_NOT_CONNECTED = "FQS(MD) is not connected";
	
	protected boolean qmSubscribed = false;

	protected int reconnTimerIntvMs = 0;
	private enum TIMEREVENTID{CONN_CHK};
	private TimerRunner connChkTimer = null;

	public FQSAdaptor() {

	}

	@Override
	public void onClosed(FortexConnection conn) {
	}
	
	/**
	 * <p>Description:</p>
	 * @author Ivan Huo
	 * @date 2016-08-23	
	 * @param quoteApp
	 */
	public void setQuoteInitiatorApp(QuoteInitiatorApplication quoteApp) {
		this.quoteInitiatorApp = quoteApp;
	}

	/**
	 * <p>Description:</p>
	 * @author Ivan Huo
	 * @date 2016-08-23	
	 * @return
	 */
	public void init() {
		try {
			defaultLogger.info("Initing FortexFXJ Connection...");
			connChkTimer = new TimerRunner(this, Integer.parseInt(ConfigSetting.getAdapterProperty(CONNMDCFG_CHECKCONNECTION)));
			connMd = new FortexFXJConnectionTcp(this, null, "MdCht", connMdCfg);
			connMd.init(new FortexJSONFilter(connMd, null), null);
			connMdCfg.enableInLog = Boolean.valueOf(ConfigSetting.getAdapterProperty(CONNMDCFG_ENABLEINLOG));
			connMdCfg.enableOutLog = Boolean.valueOf(ConfigSetting.getAdapterProperty(CONNMDCFG_ENABLEOUTLOG));
			connMdCfg.loginTimeoutMs = Integer.parseInt(ConfigSetting.getAdapterProperty(CONNMDCFG_LOGINTIMEOUTMS));
			connMdCfg.ip = ConfigSetting.getAdapterProperty(CONNMDCFG_IP);
			connMdCfg.password = ConfigSetting.getAdapterProperty(CONNMDCFG_PASSWORD);
			connMdCfg.port = Integer.parseInt(ConfigSetting.getAdapterProperty(CONNMDCFG_PORT));
			connMdCfg.reconnectDueMs = Integer.parseInt(ConfigSetting.getAdapterProperty(CONNMDCFG_RECONNECTDUEMS));
			connMdCfg.reconnectIntvMs = Integer.parseInt(ConfigSetting.getAdapterProperty(CONNMDCFG_RECONNECTINTVMS));
			connMdCfg.senderId = ConfigSetting.getAdapterProperty(CONNMDCFG_SENDERID);
			connMdCfg.username = ConfigSetting.getAdapterProperty(CONNMDCFG_USERNAME);
			if (connMdCfg.reconnectIntvMs > 0) {
				reconnTimerIntvMs = connMdCfg.reconnectIntvMs;
				connChkTimer.schedule(connMdCfg.reconnectIntvMs, connMdCfg.reconnectIntvMs);
			}
			checkConnection();
		} catch (Exception e) {
			Logger.getLogger("EventError").error(new Throwable().getStackTrace()[0] + " exception: " + e.getMessage(), e);
			
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fortex.lib.connection.FortexConnectionMgrInf#
	 * processFortexConnectionMsg(com.fortex.lib.connection.FortexConnection,
	 * java.lang.Object)
	 */
	@Override
	public int processFortexConnectionMsg(FortexConnection conn, FortexJSONMsg msg) {
		// TODO Auto-generated method stub
		if (msg == null) {
			Logger.getLogger("EventError").error("Disconnecting " + conn.getName() + " on null message.");
			conn.disconnect();
			return 0;
		}
		int retVal = 0;
		FXJ_DEF.SERVER_MESSAGE_TYPES msgType = FXJ_DEF.SERVER_MESSAGE_TYPES.UNKNOWN;
		try {
			final String sMsgType;
			msgType = FXJ_DEF.SERVER_MESSAGE_TYPES.getType(sMsgType = msg.getMsgType());
			final JSONObject xCloudMsg = msg.jsMsg.getJSONObject(XCLOUD_DEF.tagNames.xCloudMsgs);
			final JSONObject jsPayload = xCloudMsg.optJSONObject(sMsgType);
			switch (msgType) {

			case XCLOUD_STATUS:
				int xStatusType = FortexJSONMsg.optInt(jsPayload, FIX_DEF.TAGS.xStatusType, -1);
				int xStatusResponse = FortexJSONMsg.optInt(jsPayload, FIX_DEF.TAGS.xStatusResponse, -1);
				if (xStatusType == XCLOUD_DEF.xCloudStatus.type.LOGIN) {
					final boolean loginOk = (xStatusResponse == XCLOUD_DEF.xCloudStatus.code.SUCCEEDED);
					conn.loginSuccess = loginOk;
					defaultLogger.info(conn.getName() + " login to FQS" + (loginOk ? " successfully" : " failed"));
					///Login successfully, subscribe QM to FQS
					quoteInitiatorApp.onFqsMsg(conn, loginOk ? ProtoDef.MSGTYPE.Login : ProtoDef.MSGTYPE.Logout, null);
				} 
				/*
				else if (xStatusType == XCLOUD_DEF.xCloudStatus.type.STATUS_REQUEST) {
					
				}
				*/
				break;
			case QM_SETTINGS:
					//Get the QM Setting
					quoteInitiatorApp.onFqsMsg(connMd, ProtoDef.MSGTYPE.QuoteMaker, jsPayload);
				break;
			default: {
				break;
			}
			}
		} catch (Exception e) {
			Logger.getLogger("EventError").error(new Throwable().getStackTrace()[0] + " exception: " + e.getMessage(),e);
			Logger.getLogger("EventError").error(String.format("local var: msgType=%d", msgType.ordinal()));
		}

		return retVal;
	}
	
	/**
	 * <p>Description:</p>
	 * @author Ivan Huo
	 * @date 2016-08-23
	 */
	public void checkConnection() {
		try {
			connMdCfg.reconnectDueMs -= reconnTimerIntvMs;
			if (connMdCfg.reconnectDueMs <= 0) {
				connMdCfg.reconnectDueMs = connMdCfg.reconnectIntvMs;
				connMd.checkTcpConnection(connMdCfg, connMdCfg.reconnectIntvMs * 3, connMdCfg.reconnectIntvMs * 9);	
			}
			if (connMd.isConnected()) {
				FileUtils.fileWrite(ConfigSetting.getLocPath() + "qmConnection.status", "ip:" + connMdCfg.ip + ",account:" + connMdCfg.username  + ",qmStatus:Connected,updatedTime:" + FORMATER.format(Calendar.getInstance().getTime()));
			} else {
				FileUtils.fileWrite(ConfigSetting.getLocPath() + "qmConnection.status", "ip:" + connMdCfg.ip + ",account:" + connMdCfg.username  + ",qmStatus:Disconnected,updatedTime:"  + FORMATER.format(Calendar.getInstance().getTime()));
			}
		} catch (Exception e) {
			Logger.getLogger("EventError").error(new Throwable().getStackTrace()[0] + " exception: " + e.getMessage(), e);
		}
	}
	
	
	public void subscribeQMSettings(String uid, String qmSubType, String domain, String sym, ErrInfo errInfo) {
		try {
			if (connMd.loginSuccess == false) {
				errInfo.SetVal(ErrDef.ErrCode.E_NOTREADY, FQS_NOT_CONNECTED);
				return;
			}

			if ((domain.equals("*") && sym.equals("*") && qmSubscribed)) {
				return;
			}
			qmSubscribed = true;

			JSONObject jsQmReq = new JSONObject();
			jsQmReq.put(FIX_DEF.TAGS.Fortex_CtrlOrderType, "101");
			jsQmReq.put(FIX_DEF.TAGS.Fortex_QM_SubType, qmSubType);
			jsQmReq.put(FIX_DEF.TAGS.Fortex_Domain, domain);
			jsQmReq.put(FIX_DEF.TAGS.Symbol, sym);
			connMd.sendFxjMsg(FXJ_DEF.CLIENT_REQUEST_TYPES.getTypeExpression(FXJ_DEF.CLIENT_REQUEST_TYPES.QUOTE_MAKER),
					jsQmReq, uid);
		} catch (Exception e) {
			Logger.getLogger("EventError").error(new Throwable().getStackTrace()[0] + " exception: " + e.getMessage());
			errInfo.SetVal(ErrDef.ErrCode.E_EXCEPTION, e.getMessage());
		}
	}
	
	@Override
	public void onTimer(int timerID) {
		if(timerID==TIMEREVENTID.CONN_CHK.ordinal()){
			checkConnection();
		}
	}
}