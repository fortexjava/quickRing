package com.fortex.lib.protocol;

public class XCLOUD_DEF
{
  public static final String heartbeatIdStr = "HeArTbEaT";
  
  public class xCloudStatus
  {
    public xCloudStatus() {}
    
    public class code
    {
      public static final int SUCCEEDED = 100;
      public static final int FAILED = 200;
      public static final int ACK = 300;
      
      public code() {}
    }
    
    public class type
    {
      public static final int LOGIN = 100;
      public static final int ORDER_REQUEST = 200;
      public static final int STATUS_REQUEST = 300;
      public static final int ADMIN_REQUEST = 400;
      
      public type() {}
    }
  }
  
  public class tagNames
  {
    public static final String xServer = "xServer";
    public static final String xCloudPW = "xCloudPW";
    public static final String ServerTokenID = "ServerTokenID";
    public static final String ClientTokenID = "ClientTokenID";
    public static final String xSubscriptions = "xSubscriptions";
    public static final String xSubType = "xSubType";
    public static final String xSubAccount = "xSubAccount";
    public static final String xSubCtrl = "xSubCtrl";
    public static final String xStatusResponse = "xStatusResponse";
    public static final String xStatusType = "xStatusType";
    public static final String xMsgType = "xMsgType";
    public static final String xEngineName = "xEngineName";
    public static final String xSendingTime = "xSendingTime";
    public static final String xJSONServerName = "xJSONServerName";
    public static final String xJSONSendingTime = "xJSONSendingTime";
    public static final String xEnclosingSymbol = "xEnclosingSymbol";
    public static final String xCloudMsgs = "XCloudMsgs";
    public static final String xCloudStatus = "XCloudStatus";
    public static final String xSessionDate = "xSessionDate";
    public static final String FORTEX_ACCOUNT_SETTINGS = "Fortex_Account_Settings";
    public static final String FORTEX_DOMAIN_SETTING = "Fortex_Domain_Setting";
    public static final String FORTEX_MARKET_SETTING = "Fortex_Market_Setting";
    public static final String PAYLOAD = "PAYLOAD";
    public static final String ACCOUNTTRADEITEMS = "AccountTradeItems";
    public static final String JOURNALITEMS = "JournalItems";
    public static final String AdminItems = "AdminItems";
    
    public tagNames() {}
  }
}


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\protocol\XCLOUD_DEF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */