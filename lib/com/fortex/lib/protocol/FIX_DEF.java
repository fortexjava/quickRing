/*      */ package com.fortex.lib.protocol;
/*      */ 
/*      */ import java.util.HashMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FIX_DEF
/*      */ {
/*    9 */   private static final HashMap a = new HashMap();
/*   10 */   private static final HashMap b = new HashMap();
/*   11 */   static { String[][] arrayOfString1 = { { "0", "Heartbeat" }, { "1", "TestRequest" }, { "2", "ResendRequest" }, { "3", "Reject" }, { "4", "SequenceReset" }, { "5", "Logout" }, { "6", "IndicationOfInterest" }, { "7", "Advertisement" }, { "8", "ExecutionReport" }, { "9", "OrderCancelReject" }, { "a", "QuoteStatusRequest" }, { "b", "MassQuoteAcknowledgement" }, { "c", "SecurityDefinitionRequest" }, { "d", "SecurityDefinition" }, { "e", "SecurityStatusRequest" }, { "f", "SecurityStatus" }, { "g", "TradingSessionStatusRequest" }, { "h", "TradingSessionStatus" }, { "i", "MassQuote" }, { "j", "BusinessMessageReject" }, { "k", "BidRequest" }, { "l", "BidResponse" }, { "m", "ListStrikePric" }, { "o", "RegistrationInstructions" }, { "p", "RegistrationInstructionsResponse" }, { "q", "OrderMassCancelRequest" }, { "r", "OrderMassCancelReport" }, { "s", "NewOrderCross" }, { "t", "CrossOrderCancelReplaceRequest" }, { "u", "CrossOrderCancelRequest" }, { "v", "SecurityTypeRequest" }, { "w", "SecurityTypes" }, { "x", "SecurityListRequest" }, { "y", "SecurityList" }, { "z", "DerivativeSecurityListRequest" }, { "A", "Logon" }, { "B", "News" }, { "C", "Email" }, { "D", "NewOrderSingle" }, { "E", "NewOrderList" }, { "F", "OrderCancelRequest" }, { "G", "OrderCancelReplaceRequest" }, { "H", "OrderStatusRequest" }, { "J", "AllocationInstruction" }, { "K", "ListCancelRequest" }, { "L", "ListExecute" }, { "M", "ListStatusRequest" }, { "N", "ListStatus" }, { "P", "AllocationInstructionAck" }, { "Q", "DontKnowTrade" }, { "R", "QuoteRequest" }, { "S", "Quote" }, { "T", "SettlementInstructions" }, { "V", "MarketDataRequest" }, { "W", "MarketDataSnapshotFullRefresh" }, { "X", "MarketDataIncrementalRefresh" }, { "Y", "MarketDataRequestReject" }, { "Z", "QuoteCancel" }, { "AA", "DerivativeSecurityList" }, { "AB", "NewOrderMultileg" }, { "AC", "MultilegOrderCancelReplaceRequest" }, { "AD", "TradeCaptureReportRequest" }, { "AE", "TradeCaptureReport" }, { "AF", "OrderMassStatusRequest" }, { "AG", "QuoteRequestReject" }, { "AH", "RFQRequest" }, { "AI", "QuoteStatusReport" }, { "AJ", "QuoteResponse" }, { "AK", "Confirmation" }, { "AL", "PositionMaintenanceRequest" }, { "AM", "PositionMaintenanceReport" }, { "AN", "RequestForPositions" }, { "AO", "RequestForPositionsAck" }, { "AP", "PositionReport" }, { "AQ", "TradeCaptureReportRequestAck" }, { "AR", "TradeCaptureReportAck" }, { "AS", "AllocationReport" }, { "AT", "AllocationReportAck" }, { "AU", "ConfirmationAck" }, { "AV", "SettlementInstructionRequest" }, { "AW", "AssignmentReport" }, { "AX", "CollateralRequest" }, { "AY", "CollateralAssignment" }, { "AZ", "CollateralResponse" }, { "BA", "CollateralReport" }, { "BB", "CollateralInquiry" }, { "BE", "UserRequest" }, { "BF", "UserResponse" }, { "BG", "CollateralInquiryAck" }, { "BH", "ConfirmationRequest" }, { "FA", "Fortex_Account_Settings" }, { "FC", "Fortex_Client" }, { "FD", "Fortex_Domain_Settings" }, { "FE", "Fortex_Executions" }, { "FF", "Fortex_Dealer_Msg" }, { "FG", "Fortex_Admin_Msg" }, { "FH", "Fortex_FS_Command" }, { "FK", "Fortex_Ticker" }, { "FL", "Fortex_OrderLog" }, { "FM", "Fortex_Market_Settings" }, { "FN", "Fortex_Notification" }, { "FO", "Fortex_Orders" }, { "FP", "Fortex_Positions" }, { "FQ", "Fortex_Account_MsgInfo" }, { "FR", "Fortex_FS_Response" }, { "FS", "Fortex_Server" }, { "FT", "Fortex_Trade_Settings" }, { "FV", "Fortex_OverNPositions" }, { "FW", "Fortex_MSG_Aggregation" }, { "FY", "Fortex_ChartDataRequest" }, { "XS", "XCloudStatus" }, { "XQM", "XQuoteMulticast" }, { "XJM", "XJournalMessages" }, { "XDR", "XDataReplication" }, { "XAS", "XAccountSettings" }, { "XDC", "XDataChanges" }, { "XDCU", "XDataCacheUpdates" }, { "XMR", "XMessageRecall" }, { "XCT", "XChartData " }, { "XMD", "XMarketData" }, { "XAM", "XAdminMessage" }, { "XQM", "XQMSettings" }, { "XSR", "XStatusResponse" }, { "XCM", "XChartMulticast" }, { "XFMS", "Fortex_Market_Settings" }, { "XFDS", "Fortex_Domain_Settings" }, { "XFAS", "Fortex_Account_Settings" }, { "XATI", "AccountTradeItems" }, { "XACI", "AccountCashItems" }, { "XQ", "xQuote" } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  149 */     for (String[] arrayOfString : arrayOfString1) {
/*  150 */       a.put(arrayOfString[0], arrayOfString[1]);
/*  151 */       b.put(arrayOfString[1], arrayOfString[0]);
/*      */     }
/*      */   }
/*      */   
/*  155 */   public static String getMsgTypeName(String paramString) { return (String)b.get(paramString); }
/*      */   
/*      */   public static String getMsgTypeDesc(String paramString) {
/*  158 */     return (String)a.get(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */   public class MSGTYPES
/*      */   {
/*      */     public static final String Heartbeat = "0";
/*      */     
/*      */     public static final String Logon = "A";
/*      */     
/*      */     public static final String TestRequest = "1";
/*      */     
/*      */     public static final String ResendRequest = "2";
/*      */     
/*      */     public static final String Reject = "3";
/*      */     public static final String SequenceReset = "4";
/*      */     public static final String Logout = "5";
/*      */     public static final String BusinessMessageReject = "j";
/*      */     public static final String UserRequest = "BE";
/*      */     public static final String UserResponse = "BF";
/*      */     public static final String Advertisement = "7";
/*      */     public static final String IndicationOfInterest = "6";
/*      */     public static final String News = "B";
/*      */     public static final String Email = "C";
/*      */     public static final String QuoteRequest = "R";
/*      */     public static final String QuoteResponse = "AJ";
/*      */     public static final String QuoteRequestReject = "AG";
/*      */     public static final String RFQRequest = "AH";
/*      */     public static final String Quote = "S";
/*      */     public static final String QuoteCancel = "Z";
/*      */     public static final String QuoteStatusRequest = "a";
/*      */     public static final String QuoteStatusReport = "AI";
/*      */     public static final String MassQuote = "i";
/*      */     public static final String MassQuoteAcknowledgement = "b";
/*      */     public static final String MarketDataRequest = "V";
/*      */     public static final String MarketDataSnapshotFullRefresh = "W";
/*      */     public static final String MarketDataIncrementalRefresh = "X";
/*      */     public static final String MarketDataRequestReject = "Y";
/*      */     public static final String SecurityDefinitionRequest = "c";
/*      */     public static final String SecurityDefinition = "d";
/*      */     public static final String SecurityTypeRequest = "v";
/*      */     public static final String SecurityTypes = "w";
/*      */     public static final String SecurityListRequest = "x";
/*      */     public static final String SecurityList = "y";
/*      */     public static final String DerivativeSecurityListRequest = "z";
/*      */     public static final String DerivativeSecurityList = "AA";
/*      */     public static final String SecurityStatusRequest = "e";
/*      */     public static final String SecurityStatus = "f";
/*      */     public static final String TradingSessionStatusRequest = "g";
/*      */     public static final String TradingSessionStatus = "h";
/*      */     public static final String NewOrderSingle = "D";
/*      */     public static final String ExecutionReport = "8";
/*      */     public static final String DontKnowTrade = "Q";
/*      */     public static final String OrderCancelReplaceRequest = "G";
/*      */     public static final String OrderCancelRequest = "F";
/*      */     public static final String OrderCancelReject = "9";
/*      */     public static final String OrderStatusRequest = "H";
/*      */     public static final String OrderMassCancelRequest = "q";
/*      */     public static final String OrderMassCancelReport = "r";
/*      */     public static final String OrderMassStatusRequest = "AF";
/*      */     public static final String NewOrderCross = "s";
/*      */     public static final String CrossOrderCancelReplaceRequest = "t";
/*      */     public static final String CrossOrderCancelRequest = "u";
/*      */     public static final String NewOrderMultileg = "AB";
/*      */     public static final String MultilegOrderCancelReplaceRequest = "AC";
/*      */     public static final String BidRequest = "k";
/*      */     public static final String BidResponse = "l";
/*      */     public static final String NewOrderList = "E";
/*      */     public static final String ListStrikePrice = "m";
/*      */     public static final String ListStatus = "N";
/*      */     public static final String ListExecute = "L";
/*      */     public static final String ListCancelRequest = "K";
/*      */     public static final String ListStatusRequest = "M";
/*      */     public static final String AllocationInstruction = "J";
/*      */     public static final String AllocationInstructionAck = "P";
/*      */     public static final String AllocationReport = "AS";
/*      */     public static final String AllocationReportAck = "AT";
/*      */     public static final String Confirmation = "AK";
/*      */     public static final String ConfirmationAck = "AU";
/*      */     public static final String ConfirmationRequest = "BH";
/*      */     public static final String SettlementInstructions = "T";
/*      */     public static final String SettlementInstructionRequest = "AV";
/*      */     public static final String TradeCaptureReportRequest = "AD";
/*      */     public static final String TradeCaptureReportRequestAck = "AQ";
/*      */     public static final String TradeCaptureReport = "AE";
/*      */     public static final String TradeCaptureReportAck = "AR";
/*      */     public static final String RegistrationInstructions = "o";
/*      */     public static final String RegistrationInstructionsResponse = "p";
/*      */     public static final String PositionMaintenanceRequest = "AL";
/*      */     public static final String PositionMaintenanceReport = "AM";
/*      */     public static final String RequestForPositions = "AN";
/*      */     public static final String RequestForPositionsAck = "AO";
/*      */     public static final String PositionReport = "AP";
/*      */     public static final String AssignmentReport = "AW";
/*      */     public static final String CollateralRequest = "AX";
/*      */     public static final String CollateralAssignment = "AY";
/*      */     public static final String CollateralResponse = "AZ";
/*      */     public static final String CollateralReport = "BA";
/*      */     public static final String CollateralInquiry = "BB";
/*      */     public static final String CollateralInquiryAck = "BG";
/*      */     public static final String Fortex_Account_Settings = "FA";
/*      */     public static final String Fortex_Client = "FC";
/*      */     public static final String Fortex_Domain_Settings = "FD";
/*      */     public static final String Fortex_Executions = "FE";
/*      */     public static final String Fortex_Dealer_Msg = "FF";
/*      */     public static final String Fortex_Admin_Msg = "FG";
/*      */     public static final String Fortex_OrderLog = "FL";
/*      */     public static final String Fortex_Market_Settings = "FM";
/*      */     public static final String Fortex_Orders = "FO";
/*      */     public static final String Fortex_Positions = "FP";
/*      */     public static final String Fortex_Server = "FS";
/*      */     public static final String Fortex_Trade_Settings = "FT";
/*      */     public static final String Fortex_OverNPositions = "FV";
/*      */     public static final String Fortex_Account_MsgInfo = "FQ";
/*      */     public static final String Fortex_Notification = "FN";
/*      */     public static final String Fortex_MSG_Aggregation = "FW";
/*      */     public static final String Fortex_FS_Command = "FH";
/*      */     public static final String Fortex_FS_Response = "FR";
/*      */     public static final String Fortex_Ticker = "FK";
/*      */     public static final String Fortex_ChartDataRequest = "FY";
/*      */     public static final String XCloudStatus = "XS";
/*      */     public static final String XQuoteMulticast = "XQM";
/*      */     public static final String XJournalMessages = "XJM";
/*      */     public static final String XDataReplication = "XDR";
/*      */     public static final String XAccountSettings = "XAS";
/*      */     public static final String XDataChanges = "XDC";
/*      */     public static final String XDataCacheUpdates = "XDCU";
/*      */     public static final String XMessageRecall = "XMR";
/*      */     public static final String XChartData = "XCT";
/*      */     public static final String XMarketData = "XMD";
/*      */     public static final String XAdminMessage = "XAM";
/*      */     public static final String XQMSettings = "XQM";
/*      */     public static final String XStatusResponse = "XSR";
/*      */     public static final String XChartMulticast = "XCM";
/*      */     public static final String XFORTEX_MARKET_SETTINGS = "XFMS";
/*      */     public static final String XFORTEX_DOMAIN_SETTINGS = "XFDS";
/*      */     public static final String XFORTEX_ACCOUNT_SETTINGS = "XFAS";
/*      */     public static final String XACCOUNTTRADEITEMS = "XATI";
/*      */     public static final String XACCOUNTCASHITEMS = "XACI";
/*      */     public static final String XQUOTE = "XQ";
/*      */     public static final String XACK = "XACK";
/*      */     public static final String XGROUP = "XGROUP";
/*      */     public static final String XSESSIONINFO = "XSESSION";
/*      */     
/*      */     public MSGTYPES() {}
/*      */   }
/*      */   
/*      */   public class EXECTYPE
/*      */   {
/*      */     public static final String New = "0";
/*      */     public static final String Canceled = "4";
/*      */     public static final String Replace = "4";
/*      */     public static final String PendingCancel = "6";
/*      */     public static final String Rejected = "8";
/*      */     public static final String Suspended = "9";
/*      */     public static final String PendingReplace = "E";
/*      */     public static final String Trade = "F";
/*      */     public static final String TradeCancel = "H";
/*      */     public static final String OrderStatus = "I";
/*      */     
/*      */     public EXECTYPE() {}
/*      */   }
/*      */   
/*      */   public class ORDSTATUS
/*      */   {
/*      */     public static final String New = "0";
/*      */     public static final String PartiallyFilled = "1";
/*      */     public static final String Filled = "2";
/*      */     public static final String Canceled = "4";
/*      */     public static final String PendingCancel = "6";
/*      */     public static final String Rejected = "8";
/*      */     public static final String PendingReplace = "E";
/*      */     
/*      */     public ORDSTATUS() {}
/*      */   }
/*      */   
/*      */   public static enum LOGOUTCODE
/*      */   {
/*  336 */     public static final String[] types = { "ClientInitiated", "RecallRejected", "ServerReset", "UnknownSession", "UnknownUser", "BadMsg", "DuplicatedLogin", "SystemNotReady", "SystemBusy", "UnknownVersion", "Unknown" };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private LOGOUTCODE() {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public static String getTypeExpression(LOGOUTCODE paramLOGOUTCODE)
/*      */     {
/*  350 */       return types[paramLOGOUTCODE.ordinal()];
/*      */     }
/*      */     
/*      */     public static LOGOUTCODE getType(String paramString) {
/*  354 */       for (LOGOUTCODE localLOGOUTCODE : ) {
/*  355 */         if (types[localLOGOUTCODE.ordinal()].equalsIgnoreCase(paramString))
/*  356 */           return localLOGOUTCODE;
/*      */       }
/*  358 */       return UNKNOWN;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static enum XCLOUDCLIENTTYPE
/*      */   {
/*  374 */     public static final String[] types = { "10", "20", "21", "22", "23", "30", "31", "32", "40", "Unknown" };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private XCLOUDCLIENTTYPE() {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     public static String getTypeExpression(XCLOUDCLIENTTYPE paramXCLOUDCLIENTTYPE)
/*      */     {
/*  387 */       return types[paramXCLOUDCLIENTTYPE.ordinal()];
/*      */     }
/*      */     
/*      */     public static XCLOUDCLIENTTYPE getType(String paramString) {
/*  391 */       for (XCLOUDCLIENTTYPE localXCLOUDCLIENTTYPE : ) {
/*  392 */         if (types[localXCLOUDCLIENTTYPE.ordinal()].equalsIgnoreCase(paramString))
/*  393 */           return localXCLOUDCLIENTTYPE;
/*      */       }
/*  395 */       return UNKNOWN;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1559 */   private static final String[] c = { "Account", "AdvId", "AdvRefID", "AdvSide", "AdvTransType", "AvgPx", "BeginSeqNo", "BeginString", "BodyLength", "CheckSum", "ClOrdID", "Commission", "CommType", "CumQty", "Currency", "EndSeqNo", "ExecID", "ExecInst", "ExecRefID", "", "HandlInst", "SecurityIDSource", "IOIid", "", "IOIQltyInd", "IOIRefID", "IOIQty", "IOITransType", "LastCapacity", "LastMkt", "LastPx", "LastQty", "LinesOfText", "MsgSeqNum", "MsgType", "NewSeqNo", "OrderID", "OrderQty", "OrdStatus", "OrdType", "OrigClOrdID", "OrigTime", "PossDupFlag", "Price", "RefSeqNum", "", "", "SecurityID", "SenderCompID", "SenderSubID", "", "SendingTime", "Quantity", "Side", "Symbol", "TargetCompID", "TargetSubID", "Text", "TimeInForce", "TransactTime", "Urgency", "ValidUntilTime", "SettlType", "SettlDate", "SymbolSfx", "ListID", "ListSeqNo", "ListNoOrds", "ListExecInst", "AllocID", "AllocTransType", "RefAllocID", "NoOrders", "AvgPxPrecision", "TradeDate", "ExecBroker", "PositionEffect", "NoAllocs", "AllocAccount", "AllocQty", "ProcessCode", "NoRpts", "RptSeq", "CxlQty", "NoDlvyInst", "", "AllocStatus", "AllocRejCode", "Signature", "SecureDataLen", "SecureData", "", "SignatureLength", "EmailType", "RawDataLength", "RawData", "PossResend", "EncryptMethod", "StopPx", "ExDestination", "", "CxlRejReason", "OrdRejReason", "IOIQualifier", "WaveNo", "Issuer", "SecurityDesc", "HeartBtInt", "", "MinQty", "MaxFloor", "TestReqID", "ReportToExch", "LocateReqd", "OnBehalfOfCompID", "OnBehalfOfSubID", "QuoteID", "NetMoney", "SettlCurrAmt", "SettlCurrency", "ForexReq", "OrigSendingTime", "GapFillFlag", "NoExecs", "", "ExpireTime", "DKReason", "DeliverToCompID", "DeliverToSubID", "IOINaturalFlag", "QuoteReqID", "BidPx", "OfferPx", "BidSize", "OfferSize", "NoMiscFees", "MiscFeeAmt", "MiscFeeCurr", "MiscFeeType", "PrevClosePx", "ResetSeqNumFlag", "SenderLocationID", "TargetLocationID", "OnBehalfOfLocationID", "DeliverToLocationID", "NoRelatedSym", "Subject", "Headline", "URLLink", "ExecType", "LeavesQty", "CashOrderQty", "AllocAvgPx", "AllocNetMoney", "SettlCurrFxRate", "SettlCurrFxRateCalc", "NumDaysInterest", "AccruedInterestRate", "AccruedInterestAmt", "SettlInstMode", "AllocText", "SettlInstID", "SettlInstTransType", "EmailThreadID", "SettlInstSource", "", "SecurityType", "EffectiveTime", "StandInstDbType", "StandInstDbName", "StandInstDbID", "SettlDeliveryType", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "BidSpotRate", "BidForwardPoints", "OfferSpotRate", "OfferForwardPoints", "OrderQty2", "SettlDate2", "LastSpotRate", "LastForwardPoints", "AllocLinkID", "AllocLinkType", "SecondaryOrderID", "NoIOIQualifiers", "MaturityMonthYear", "", "StrikePrice", "CoveredOrUncovered", "", "", "OptAttribute", "SecurityExchange", "NotifyBrokerOfCredit", "AllocHandlInst", "MaxShow", "PegOffsetValue", "XmlDataLen", "XmlData", "SettlInstRefID", "NoRoutingIDs", "RoutingType", "RoutingID", "Spread", "", "BenchmarkCurveCurrency", "BenchmarkCurveName", "BenchmarkCurvePoint", "CouponRate", "CouponPaymentDate", "IssueDate", "RepurchaseTerm", "RepurchaseRate", "Factor", "TradeOriginationDate", "ExDate", "ContractMultiplier", "NoStipulations", "StipulationType", "StipulationValue", "YieldType", "Yield", "TotalTakedown", "Concession", "RepoCollateralSecurityType", "RedemptionDate", "UnderlyingCouponPaymentDate", "UnderlyingIssueDate", "UnderlyingRepoCollateralSecurityType", "UnderlyingRepurchaseTerm", "UnderlyingRepurchaseRate", "UnderlyingFactor", "UnderlyingRedemptionDate", "LegCouponPaymentDate", "LegIssueDate", "LegRepoCollateralSecurityType", "LegRepurchaseTerm", "LegRepurchaseRate", "LegFactor", "LegRedemptionDate", "CreditRating", "UnderlyingCreditRating", "LegCreditRating", "TradedFlatSwitch", "BasisFeatureDate", "BasisFeaturePrice", "", "MDReqID", "SubscriptionRequestType", "MarketDepth", "MDUpdateType", "AggregatedBook", "NoMDEntryTypes", "NoMDEntries", "MDEntryType", "MDEntryPx", "MDEntrySize", "MDEntryDate", "MDEntryTime", "TickDirection", "MDMkt", "QuoteCondition", "TradeCondition", "MDEntryID", "MDUpdateAction", "MDEntryRefID", "MDReqRejReason", "MDEntryOriginator", "LocationID", "DeskID", "DeleteReason", "OpenCloseSettlFlag", "SellerDays", "MDEntryBuyer", "MDEntrySeller", "MDEntryPositionNo", "FinancialStatus", "CorporateAction", "DefBidSize", "DefOfferSize", "NoQuoteEntries", "NoQuoteSets", "QuoteStatus", "QuoteCancelType", "QuoteEntryID", "QuoteRejectReason", "QuoteResponseLevel", "QuoteSetID", "QuoteRequestType", "TotNoQuoteEntries", "UnderlyingSecurityIDSource", "UnderlyingIssuer", "UnderlyingSecurityDesc", "UnderlyingSecurityExchange", "UnderlyingSecurityID", "UnderlyingSecurityType", "UnderlyingSymbol", "UnderlyingSymbolSfx", "UnderlyingMaturityMonthYear", "", "", "UnderlyingStrikePrice", "UnderlyingOptAttribute", "UnderlyingCurrency", "", "SecurityReqID", "SecurityRequestType", "SecurityResponseID", "SecurityResponseType", "SecurityStatusReqID", "UnsolicitedIndicator", "SecurityTradingStatus", "HaltReason", "InViewOfCommon", "DueToRelated", "BuyVolume", "SellVolume", "HighPx", "LowPx", "Adjustment", "TradSesReqID", "TradingSessionID", "ContraTrader", "TradSesMethod", "TradSesMode", "TradSesStatus", "TradSesStartTime", "TradSesOpenTime", "TradSesPreCloseTime", "TradSesCloseTime", "TradSesEndTime", "NumberOfOrders", "MessageEncoding", "EncodedIssuerLen", "EncodedIssuer", "EncodedSecurityDescLen", "EncodedSecurityDesc", "EncodedListExecInstLen", "EncodedListExecInst", "EncodedTextLen", "EncodedText", "EncodedSubjectLen", "EncodedSubject", "EncodedHeadlineLen", "EncodedHeadline", "EncodedAllocTextLen", "EncodedAllocText", "EncodedUnderlyingIssuerLen", "EncodedUnderlyingIssuer", "EncodedUnderlyingSecurityDescLen", "EncodedUnderlyingSecurityDesc", "AllocPrice", "QuoteSetValidUntilTime", "QuoteEntryRejectReason", "LastMsgSeqNumProcessed", "", "RefTagID", "RefMsgType", "SessionRejectReason", "BidRequestTransType", "ContraBroker", "ComplianceID", "SolicitedFlag", "ExecRestatementReason", "BusinessRejectRefID", "BusinessRejectReason", "GrossTradeAmt", "NoContraBrokers", "MaxMessageSize", "NoMsgTypes", "MsgDirection", "NoTradingSessions", "TotalVolumeTraded", "DiscretionInst", "DiscretionOffsetValue", "BidID", "ClientBidID", "ListName", "TotNoRelatedSym", "BidType", "NumTickets", "SideValue", "SideValue", "NoBidDescriptors", "BidDescriptorType", "BidDescriptor", "SideValueInd", "LiquidityPctLow", "LiquidityPctHigh", "LiquidityValue", "EFPTrackingError", "FairValue", "OutsideIndexPct", "ValueOfFutures", "LiquidityIndType", "WtAverageLiquidity", "ExchangeForPhysical", "OutMainCntryUIndex", "CrossPercent", "ProgRptReqs", "ProgPeriodInterval", "IncTaxInd", "NumBidders", "BidTradeType", "BasisPxType", "NoBidComponents", "Country", "TotNoStrikes", "PriceType", "DayOrderQty", "DayCumQty", "DayAvgPx", "GTBookingInst", "NoStrikes", "ListStatusType", "NetGrossInd", "ListOrderStatus", "ExpireDate", "ListExecInstType", "CxlRejResponseTo", "UnderlyingCouponRate", "UnderlyingContractMultiplier", "ContraTradeQty", "ContraTradeTime", "", "", "LiquidityNumSecurities", "MultiLegReportingType", "StrikeTime", "ListStatusText", "EncodedListStatusTextLen", "EncodedListStatusText", "PartyIDSource", "PartyID", "", "", "NetChgPrevDay", "PartyRole", "NoPartyIDs", "NoSecurityAltID", "SecurityAltID", "SecurityAltIDSource", "NoUnderlyingSecurityAltID", "UnderlyingSecurityAltID", "UnderlyingSecurityAltIDSource", "Product", "CFICode", "UnderlyingProduct", "UnderlyingCFICode", "TestMessageIndicator", "QuantityType", "BookingRefID", "IndividualAllocID", "RoundingDirection", "RoundingModulus", "CountryOfIssue", "StateOrProvinceOfIssue", "LocaleOfIssue", "NoRegistDtls", "MailingDtls", "InvestorCountryOfResidence", "PaymentRef", "DistribPaymentMethod", "CashDistribCurr", "CommCurrency", "CancellationRights", "MoneyLaunderingStatus", "MailingInst", "TransBkdTime", "ExecPriceType", "ExecPriceAdjustment", "DateOfBirth", "TradeReportTransType", "CardHolderName", "CardNumber", "CardExpDate", "CardIssNum", "PaymentMethod", "RegistAcctType", "Designation", "TaxAdvantageType", "RegistRejReasonText", "FundRenewWaiv", "CashDistribAgentName", "CashDistribAgentCode", "CashDistribAgentAcctNumber", "CashDistribPayRef", "CashDistribAgentAcctName", "CardStartDate", "PaymentDate", "PaymentRemitterID", "RegistStatus", "RegistRejReasonCode", "RegistRefID", "RegistDtls", "NoDistribInsts", "RegistEmail", "DistribPercentage", "RegistID", "RegistTransType", "ExecValuationPoint", "OrderPercent", "OwnershipType", "NoContAmts", "ContAmtType", "ContAmtValue", "ContAmtCurr", "OwnerType", "PartySubID", "NestedPartyID", "NestedPartyIDSource", "SecondaryClOrdID", "SecondaryExecID", "OrderCapacity", "OrderRestrictions", "MassCancelRequestType", "MassCancelResponse", "MassCancelRejectReason", "TotalAffectedOrders", "NoAffectedOrders", "AffectedOrderID", "AffectedSecondaryOrderID", "QuoteType", "NestedPartyRole", "NoNestedPartyIDs", "TotalAccruedInterestAmt", "MaturityDate", "UnderlyingMaturityDate", "InstrRegistry", "CashMargin", "NestedPartySubID", "Scope", "MDImplicitDelete", "CrossID", "CrossType", "CrossPrioritization", "OrigCrossID", "NoSides", "Username", "Password", "NoLegs", "LegCurrency", "TotNoSecurityTypes", "NoSecurityTypes", "SecurityListRequestType", "SecurityRequestResult", "RoundLot", "MinTradeVol", "MultiLegRptTypeReq", "LegPositionEffect", "LegCoveredOrUncovered", "LegPrice", "TradSesStatusRejReason", "TradeRequestID", "TradeRequestType", "PreviouslyReported", "TradeReportID", "TradeReportRefID", "MatchStatus", "MatchType", "OddLot", "NoClearingInstructions", "ClearingInstruction", "TradeInputSource", "TradeInputDevice", "NoDates", "AccountType", "CustOrderCapacity", "ClOrdLinkID", "MassStatusReqID", "MassStatusReqType", "OrigOrdModTime", "LegSettlType", "LegSettlDate", "DayBookingInst", "BookingUnit", "PreallocMethod", "UnderlyingCountryOfIssue", "UnderlyingStateOrProvinceOfIssue", "UnderlyingLocaleOfIssue", "UnderlyingInstrRegistry", "LegCountryOfIssue", "LegStateOrProvinceOfIssue", "LegLocaleOfIssue", "LegInstrRegistry", "LegSymbol", "LegSymbolSfx", "LegSecurityID", "LegSecurityIDSource", "NoLegSecurityAltID", "LegSecurityAltID", "LegSecurityAltIDSource", "LegProduct", "LegCFICode", "LegSecurityType", "LegMaturityMonthYear", "LegMaturityDate", "LegStrikePrice", "LegOptAttribute", "LegContractMultiplier", "LegCouponRate", "LegSecurityExchange", "LegIssuer", "EncodedLegIssuerLen", "EncodedLegIssuer", "LegSecurityDesc", "EncodedLegSecurityDescLen", "EncodedLegSecurityDesc", "LegRatioQty", "LegSide", "TradingSessionSubID", "AllocType", "NoHops", "HopCompID", "HopSendingTime", "HopRefID", "MidPx", "BidYield", "MidYield", "OfferYield", "ClearingFeeIndicator", "WorkingIndicator", "LegLastPx", "PriorityIndicator", "PriceImprovement", "Price2", "LastForwardPoints2", "BidForwardPoints2", "OfferForwardPoints2", "RFQReqID", "MktBidPx", "MktOfferPx", "MinBidSize", "MinOfferSize", "QuoteStatusReqID", "LegalConfirm", "UnderlyingLastPx", "UnderlyingLastQty", "", "LegRefID", "ContraLegRefID", "SettlCurrBidFxRate", "SettlCurrOfferFxRate", "QuoteRequestRejectReason", "SideComplianceID", "AcctIDSource", "AllocAcctIDSource", "BenchmarkPrice", "BenchmarkPriceType", "ConfirmID", "ConfirmStatus", "ConfirmTransType", "ContractSettlMonth", "DeliveryForm", "LastParPx", "NoLegAllocs", "LegAllocAccount", "LegIndividualAllocID", "LegAllocQty", "LegAllocAcctIDSource", "LegSettlCurrency", "LegBenchmarkCurveCurrency", "LegBenchmarkCurveName", "LegBenchmarkCurvePoint", "LegBenchmarkPrice", "LegBenchmarkPriceType", "LegBidPx", "LegIOIQty", "NoLegStipulations", "LegOfferPx", "LegOrderQty", "LegPriceType", "LegQty", "LegStipulationType", "LegStipulationValue", "LegSwapType", "Pool", "QuotePriceType", "QuoteRespID", "QuoteRespType", "QuoteQualifier", "YieldRedemptionDate", "YieldRedemptionPrice", "YieldRedemptionPriceType", "BenchmarkSecurityID", "ReversalIndicator", "YieldCalcDate", "NoPositions", "PosType", "LongQty", "ShortQty", "PosQtyStatus", "PosAmtType", "PosAmt", "PosTransType", "PosReqID", "NoUnderlyings", "PosMaintAction", "OrigPosReqRefID", "PosMaintRptRefID", "ClearingBusinessDate", "SettlSessID", "SettlSessSubID", "AdjustmentType", "ContraryInstructionIndicator", "PriorSpreadIndicator", "PosMaintRptID", "PosMaintStatus", "PosMaintResult", "PosReqType", "ResponseTransportType", "ResponseDestination", "TotalNumPosReports", "PosReqResult", "PosReqStatus", "SettlPrice", "SettlPriceType", "UnderlyingSettlPrice", "UnderlyingSettlPriceType", "PriorSettlPrice", "NoQuoteQualifiers", "AllocSettlCurrency", "AllocSettlCurrAmt", "InterestAtMaturity", "LegDatedDate", "LegPool", "AllocInterestAtMaturity", "AllocAccruedInterestAmt", "DeliveryDate", "AssignmentMethod", "AssignmentUnit", "OpenInterest", "ExerciseMethod", "TotNumTradeReports", "TradeRequestResult", "TradeRequestStatus", "TradeReportRejectReason", "SideMultiLegReportingType", "NoPosAmt", "AutoAcceptIndicator", "AllocReportID", "NoNestedPartyIDs", "NestedPartyID", "NestedPartyIDSource", "NestedPartyRole", "NestedPartySubID", "BenchmarkSecurityIDSource", "SecuritySubType", "UnderlyingSecuritySubType", "LegSecuritySubType", "AllowableOneSidednessPct", "AllowableOneSidednessValue", "AllowableOneSidednessCurr", "NoTrdRegTimestamps", "TrdRegTimestamp", "TrdRegTimestampType", "TrdRegTimestampOrigin", "ConfirmRefID", "ConfirmType", "ConfirmRejReason", "BookingType", "IndividualAllocRejCode", "SettlInstMsgID", "NoSettlInst", "LastUpdateTime", "AllocSettlInstType", "NoSettlPartyIDs", "SettlPartyID", "SettlPartyIDSource", "SettlPartyRole", "SettlPartySubID", "SettlPartySubIDType", "DlvyInstType", "TerminationType", "NextExpectedMsgSeqNum", "OrdStatusReqID", "SettlInstReqID", "SettlInstReqRejCode", "SecondaryAllocID", "AllocReportType", "AllocReportRefID", "AllocCancReplaceReason", "CopyMsgIndicator", "AllocAccountType", "OrderAvgPx", "OrderBookingQty", "NoSettlPartySubIDs", "NoPartySubIDs", "PartySubIDType", "NoNestedPartySubIDs", "NestedPartySubIDType", "NoNestedPartySubIDs", "NestedPartySubIDType", "AllocIntermedReqType", "", "UnderlyingPx", "PriceDelta", "ApplQueueMax", "ApplQueueDepth", "ApplQueueResolution", "ApplQueueAction", "NoAltMDSource", "AltMDSourceID", "SecondaryTradeReportID", "AvgPxIndicator", "TradeLinkID", "OrderInputDevice", "UnderlyingTradingSessionID", "UnderlyingTradingSessionSubID", "TradeLegRefID", "ExchangeRule", "TradeAllocIndicator", "ExpirationCycle", "TrdType", "TrdSubType", "TransferReason", "AsgnReqID", "TotNumAssignmentReports", "AsgnRptID", "ThresholdAmount", "PegMoveType", "PegOffsetType", "PegLimitType", "PegRoundDirection", "PeggedPrice", "PegScope", "DiscretionMoveType", "DiscretionOffsetType", "DiscretionLimitType", "DiscretionRoundDirection", "DiscretionPrice", "DiscretionScope", "TargetStrategy", "TargetStrategyParameters", "ParticipationRate", "TargetStrategyPerformance", "LastLiquidityInd", "PublishTrdIndicator", "ShortSaleReason", "QtyType", "SecondaryTrdType", "TradeReportType", "AllocNoOrdersType", "SharedCommission", "ConfirmReqID", "AvgParPx", "ReportedPx", "NoCapacities", "OrderCapacityQty", "NoEvents", "EventType", "EventDate", "EventPx", "EventText", "PctAtRisk", "NoInstrAttrib", "InstrAttribType", "InstrAttribValue", "DatedDate", "InterestAccrualDate", "CPProgram", "CPRegType", "UnderlyingCPProgram", "UnderlyingCPRegType", "UnderlyingQty", "TrdMatchID", "SecondaryTradeReportRefID", "UnderlyingDirtyPrice", "UnderlyingEndPrice", "UnderlyingStartValue", "UnderlyingCurrentValue", "UnderlyingEndValue", "NoUnderlyingStips", "UnderlyingStipType", "UnderlyingStipValue", "MaturityNetMoney", "MiscFeeBasis", "TotNoAllocs", "LastFragment", "CollReqID", "CollAsgnReason", "CollInquiryQualifier", "NoTrades", "MarginRatio", "MarginExcess", "TotalNetValue", "CashOutstanding", "CollAsgnID", "CollAsgnTransType", "CollRespID", "CollAsgnRespType", "CollAsgnRejectReason", "CollAsgnRefID", "CollRptID", "CollInquiryID", "CollStatus", "TotNumReports", "LastRptRequested", "AgreementDesc", "AgreementID", "AgreementDate", "StartDate", "EndDate", "AgreementCurrency", "DeliveryType", "EndAccruedInterestAmt", "StartCash", "EndCash", "UserRequestID", "UserRequestType", "NewPassword", "UserStatus", "UserStatusText", "StatusValue", "StatusText", "RefCompID", "RefSubID", "NetworkResponseID", "NetworkRequestID", "LastNetworkResponseID", "NetworkRequestType", "NoCompIDs", "NetworkStatusResponseType", "NoCollInquiryQualifier", "TrdRptStatus", "AffirmStatus", "UnderlyingStrikeCurrency", "LegStrikeCurrency", "TimeBracket", "CollAction", "CollInquiryStatus", "CollInquiryResult", "StrikeCurrency", "NoNestedPartyIDs", "NestedPartyID", "NestedPartyIDSource", "NestedPartyRole", "NoNestedPartySubIDs", "NestedPartySubID", "NestedPartySubIDType", "LegContractSettlMonth", "LegInterestAccrualDate" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2518 */   private static final String[] d = { "xServer", "xCloudPW", "xServerTokenID", "xClientTokenID", "xSubscriptions", "xSubType", "xSubAccount", "xSubCtrl", "xStatusResponse", "xStatusType", "xMsgType", "xEngineName", "xSendingTime", "xJSONServerName", "xJSONSendingTime", "xEnclosingSymbol" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String getTagName(String paramString)
/*      */   {
/* 2539 */     return getTagName(Integer.parseInt(paramString));
/*      */   }
/*      */   
/*      */   public static String getTagName(int paramInt) {
/* 2543 */     if ((paramInt >= 0) && (paramInt <= 956)) {
/* 2544 */       return c[(paramInt - 1)];
/*      */     }
/* 2546 */     if ((paramInt >= 13000) && (paramInt <= 13015)) {
/* 2547 */       return d[(paramInt - 13000)];
/*      */     }
/* 2549 */     return "";
/*      */   }
/*      */   
/*      */   public final class TAGS
/*      */   {
/*      */     public static final String Fortex_NULL = "0";
/*      */     public static final String Account = "1";
/*      */     public static final String AdvId = "2";
/*      */     public static final String AdvRefID = "3";
/*      */     public static final String AdvSide = "4";
/*      */     public static final String AdvTransType = "5";
/*      */     public static final String AvgPx = "6";
/*      */     public static final String BeginSeqNo = "7";
/*      */     public static final String BeginString = "8";
/*      */     public static final String BodyLength = "9";
/*      */     public static final String CheckSum = "10";
/*      */     public static final String ClOrdID = "11";
/*      */     public static final String Commission = "12";
/*      */     public static final String CommType = "13";
/*      */     public static final String CumQty = "14";
/*      */     public static final String Currency = "15";
/*      */     public static final String EndSeqNo = "16";
/*      */     public static final String ExecID = "17";
/*      */     public static final String ExecInst = "18";
/*      */     public static final String ExecRefID = "19";
/*      */     public static final String HandlInst = "21";
/*      */     public static final String SecurityIDSource = "22";
/*      */     public static final String IOIid = "23";
/*      */     public static final String IOIQltyInd = "25";
/*      */     public static final String IOIRefID = "26";
/*      */     public static final String IOIQty = "27";
/*      */     public static final String IOITransType = "28";
/*      */     public static final String LastCapacity = "29";
/*      */     public static final String LastMkt = "30";
/*      */     public static final String LastPx = "31";
/*      */     public static final String LastQty = "32";
/*      */     public static final String LinesOfText = "33";
/*      */     public static final String MsgSeqNum = "34";
/*      */     public static final String MsgType = "35";
/*      */     public static final String NewSeqNo = "36";
/*      */     public static final String OrderID = "37";
/*      */     public static final String OrderQty = "38";
/*      */     public static final String OrdStatus = "39";
/*      */     public static final String OrdType = "40";
/*      */     public static final String OrigClOrdID = "41";
/*      */     public static final String OrigTime = "42";
/*      */     public static final String PossDupFlag = "43";
/*      */     public static final String Price = "44";
/*      */     public static final String RefSeqNum = "45";
/*      */     public static final String SecurityID = "48";
/*      */     public static final String SenderCompID = "49";
/*      */     public static final String SenderSubID = "50";
/*      */     public static final String SendingTime = "52";
/*      */     public static final String Quantity = "53";
/*      */     public static final String Side = "54";
/*      */     public static final String Symbol = "55";
/*      */     public static final String TargetCompID = "56";
/*      */     public static final String TargetSubID = "57";
/*      */     public static final String Text = "58";
/*      */     public static final String TimeInForce = "59";
/*      */     public static final String TransactTime = "60";
/*      */     public static final String Urgency = "61";
/*      */     public static final String ValidUntilTime = "62";
/*      */     public static final String SettlType = "63";
/*      */     public static final String SettlDate = "64";
/*      */     public static final String SymbolSfx = "65";
/*      */     public static final String ListID = "66";
/*      */     public static final String ListSeqNo = "67";
/*      */     public static final String TotNoOrders = "68";
/*      */     public static final String ListNoOrds = "68";
/*      */     public static final String ListExecInst = "69";
/*      */     public static final String AllocID = "70";
/*      */     public static final String AllocTransType = "71";
/*      */     public static final String RefAllocID = "72";
/*      */     public static final String NoOrders = "73";
/*      */     public static final String AvgPxPrecision = "74";
/*      */     public static final String TradeDate = "75";
/*      */     public static final String ExecBroker = "76";
/*      */     public static final String PositionEffect = "77";
/*      */     public static final String NoAllocs = "78";
/*      */     public static final String AllocAccount = "79";
/*      */     public static final String AllocQty = "80";
/*      */     public static final String ProcessCode = "81";
/*      */     public static final String NoRpts = "82";
/*      */     public static final String RptSeq = "83";
/*      */     public static final String CxlQty = "84";
/*      */     public static final String NoDlvyInst = "85";
/*      */     public static final String AllocStatus = "87";
/*      */     public static final String AllocRejCode = "88";
/*      */     public static final String Signature = "89";
/*      */     public static final String SecureDataLen = "90";
/*      */     public static final String SecureData = "91";
/*      */     public static final String SignatureLength = "93";
/*      */     public static final String EmailType = "94";
/*      */     public static final String RawDataLength = "95";
/*      */     public static final String RawData = "96";
/*      */     public static final String PossResend = "97";
/*      */     public static final String EncryptMethod = "98";
/*      */     public static final String StopPx = "99";
/*      */     public static final String ExDestination = "100";
/*      */     public static final String CxlRejReason = "102";
/*      */     public static final String OrdRejReason = "103";
/*      */     public static final String IOIQualifier = "104";
/*      */     public static final String WaveNo = "105";
/*      */     public static final String Issuer = "106";
/*      */     public static final String SecurityDesc = "107";
/*      */     public static final String HeartBtString = "108";
/*      */     public static final String MinQty = "110";
/*      */     public static final String MaxFloor = "111";
/*      */     public static final String TestReqID = "112";
/*      */     public static final String ReportToExch = "113";
/*      */     public static final String LocateReqd = "114";
/*      */     public static final String OnBehalfOfCompID = "115";
/*      */     public static final String OnBehalfOfSubID = "116";
/*      */     public static final String QuoteID = "117";
/*      */     public static final String NetMoney = "118";
/*      */     public static final String SettlCurrAmt = "119";
/*      */     public static final String SettlCurrency = "120";
/*      */     public static final String ForexReq = "121";
/*      */     public static final String OrigSendingTime = "122";
/*      */     public static final String GapFillFlag = "123";
/*      */     public static final String NoExecs = "124";
/*      */     public static final String ExpireTime = "126";
/*      */     public static final String DKReason = "127";
/*      */     public static final String DeliverToCompID = "128";
/*      */     public static final String DeliverToSubID = "129";
/*      */     public static final String IOINaturalFlag = "130";
/*      */     public static final String QuoteReqID = "131";
/*      */     public static final String BidPx = "132";
/*      */     public static final String OfferPx = "133";
/*      */     public static final String BidSize = "134";
/*      */     public static final String OfferSize = "135";
/*      */     public static final String NoMiscFees = "136";
/*      */     public static final String MiscFeeAmt = "137";
/*      */     public static final String MiscFeeCurr = "138";
/*      */     public static final String MiscFeeType = "139";
/*      */     public static final String PrevClosePx = "140";
/*      */     public static final String ResetSeqNumFlag = "141";
/*      */     public static final String SenderLocationID = "142";
/*      */     public static final String TargetLocationID = "143";
/*      */     public static final String OnBehalfOfLocationID = "144";
/*      */     public static final String DeliverToLocationID = "145";
/*      */     public static final String NoRelatedSym = "146";
/*      */     public static final String Subject = "147";
/*      */     public static final String Headline = "148";
/*      */     public static final String URLLink = "149";
/*      */     public static final String ExecType = "150";
/*      */     public static final String LeavesQty = "151";
/*      */     public static final String CashOrderQty = "152";
/*      */     public static final String AllocAvgPx = "153";
/*      */     public static final String AllocNetMoney = "154";
/*      */     public static final String SettlCurrFxRate = "155";
/*      */     public static final String SettlCurrFxRateCalc = "156";
/*      */     public static final String NumDaysStringerest = "157";
/*      */     public static final String AccruedStringerestRate = "158";
/*      */     public static final String AccruedStringerestAmt = "159";
/*      */     public static final String SettlInstMode = "160";
/*      */     public static final String AllocText = "161";
/*      */     public static final String SettlInstID = "162";
/*      */     public static final String SettlInstTransType = "163";
/*      */     public static final String EmailThreadID = "164";
/*      */     public static final String SettlInstSource = "165";
/*      */     public static final String SecurityType = "167";
/*      */     public static final String EffectiveTime = "168";
/*      */     public static final String StandInstDbType = "169";
/*      */     public static final String StandInstDbName = "170";
/*      */     public static final String StandInstDbID = "171";
/*      */     public static final String SettlDeliveryType = "172";
/*      */     public static final String BidSpotRate = "188";
/*      */     public static final String BidForwardPoStrings = "189";
/*      */     public static final String OfferSpotRate = "190";
/*      */     public static final String OfferForwardPoStrings = "191";
/*      */     public static final String OrderQty2 = "192";
/*      */     public static final String SettlDate2 = "193";
/*      */     public static final String LastSpotRate = "194";
/*      */     public static final String LastForwardPoStrings = "195";
/*      */     public static final String AllocLinkID = "196";
/*      */     public static final String AllocLinkType = "197";
/*      */     public static final String SecondaryOrderID = "198";
/*      */     public static final String NoIOIQualifiers = "199";
/*      */     public static final String MaturityMonthYear = "200";
/*      */     public static final String StrikePrice = "202";
/*      */     public static final String CoveredOrUncovered = "203";
/*      */     public static final String OptAttribute = "206";
/*      */     public static final String SecurityExchange = "207";
/*      */     public static final String NotifyBrokerOfCredit = "208";
/*      */     public static final String AllocHandlInst = "209";
/*      */     public static final String MaxShow = "210";
/*      */     public static final String PegOffsetValue = "211";
/*      */     public static final String XmlDataLen = "212";
/*      */     public static final String XmlData = "213";
/*      */     public static final String SettlInstRefID = "214";
/*      */     public static final String NoRoutingIDs = "215";
/*      */     public static final String RoutingType = "216";
/*      */     public static final String RoutingID = "217";
/*      */     public static final String Spread = "218";
/*      */     public static final String BenchmarkCurveCurrency = "220";
/*      */     public static final String BenchmarkCurveName = "221";
/*      */     public static final String BenchmarkCurvePoString = "222";
/*      */     public static final String CouponRate = "223";
/*      */     public static final String CouponPaymentDate = "224";
/*      */     public static final String IssueDate = "225";
/*      */     public static final String RepurchaseTerm = "226";
/*      */     public static final String RepurchaseRate = "227";
/*      */     public static final String Factor = "228";
/*      */     public static final String TradeOriginationDate = "229";
/*      */     public static final String ExDate = "230";
/*      */     public static final String ContractMultiplier = "231";
/*      */     public static final String NoStipulations = "232";
/*      */     public static final String StipulationType = "233";
/*      */     public static final String StipulationValue = "234";
/*      */     public static final String YieldType = "235";
/*      */     public static final String Yield = "236";
/*      */     public static final String TotalTakedown = "237";
/*      */     public static final String Concession = "238";
/*      */     public static final String RepoCollateralSecurityType = "239";
/*      */     public static final String RedemptionDate = "240";
/*      */     public static final String UnderlyingCouponPaymentDate = "241";
/*      */     public static final String UnderlyingIssueDate = "242";
/*      */     public static final String UnderlyingRepoCollateralSecurityType = "243";
/*      */     public static final String UnderlyingRepurchaseTerm = "244";
/*      */     public static final String UnderlyingRepurchaseRate = "245";
/*      */     public static final String UnderlyingFactor = "246";
/*      */     public static final String UnderlyingRedemptionDate = "247";
/*      */     public static final String LegCouponPaymentDate = "248";
/*      */     public static final String LegIssueDate = "249";
/*      */     public static final String LegRepoCollateralSecurityType = "250";
/*      */     public static final String LegRepurchaseTerm = "251";
/*      */     public static final String LegRepurchaseRate = "252";
/*      */     public static final String LegFactor = "253";
/*      */     public static final String LegRedemptionDate = "254";
/*      */     public static final String CreditRating = "255";
/*      */     public static final String UnderlyingCreditRating = "256";
/*      */     public static final String LegCreditRating = "257";
/*      */     public static final String TradedFlatSwitch = "258";
/*      */     public static final String BasisFeatureDate = "259";
/*      */     public static final String BasisFeaturePrice = "260";
/*      */     public static final String MDReqID = "262";
/*      */     public static final String SubscriptionRequestType = "263";
/*      */     public static final String MarketDepth = "264";
/*      */     public static final String MDUpdateType = "265";
/*      */     public static final String AggregatedBook = "266";
/*      */     public static final String NoMDEntryTypes = "267";
/*      */     public static final String NoMDEntries = "268";
/*      */     public static final String MDEntryType = "269";
/*      */     public static final String MDEntryPx = "270";
/*      */     public static final String MDEntrySize = "271";
/*      */     public static final String MDEntryDate = "272";
/*      */     public static final String MDEntryTime = "273";
/*      */     public static final String TickDirection = "274";
/*      */     public static final String MDMkt = "275";
/*      */     public static final String QuoteCondition = "276";
/*      */     public static final String TradeCondition = "277";
/*      */     public static final String MDEntryID = "278";
/*      */     public static final String MDUpdateAction = "279";
/*      */     public static final String MDEntryRefID = "280";
/*      */     public static final String MDReqRejReason = "281";
/*      */     public static final String MDEntryOriginator = "282";
/*      */     public static final String LocationID = "283";
/*      */     public static final String DeskID = "284";
/*      */     public static final String DeleteReason = "285";
/*      */     public static final String OpenCloseSettlFlag = "286";
/*      */     public static final String SellerDays = "287";
/*      */     public static final String MDEntryBuyer = "288";
/*      */     public static final String MDEntrySeller = "289";
/*      */     public static final String MDEntryPositionNo = "290";
/*      */     public static final String FinancialStatus = "291";
/*      */     public static final String CorporateAction = "292";
/*      */     public static final String DefBidSize = "293";
/*      */     public static final String DefOfferSize = "294";
/*      */     public static final String NoQuoteEntries = "295";
/*      */     public static final String NoQuoteSets = "296";
/*      */     public static final String QuoteStatus = "297";
/*      */     public static final String QuoteCancelType = "298";
/*      */     public static final String QuoteEntryID = "299";
/*      */     public static final String QuoteRejectReason = "300";
/*      */     public static final String QuoteResponseLevel = "301";
/*      */     public static final String QuoteSetID = "302";
/*      */     public static final String QuoteRequestType = "303";
/*      */     public static final String TotNoQuoteEntries = "304";
/*      */     public static final String UnderlyingSecurityIDSource = "305";
/*      */     public static final String UnderlyingIssuer = "306";
/*      */     public static final String UnderlyingSecurityDesc = "307";
/*      */     public static final String UnderlyingSecurityExchange = "308";
/*      */     public static final String UnderlyingSecurityID = "309";
/*      */     public static final String UnderlyingSecurityType = "310";
/*      */     public static final String UnderlyingSymbol = "311";
/*      */     public static final String UnderlyingSymbolSfx = "312";
/*      */     public static final String UnderlyingMaturityMonthYear = "313";
/*      */     public static final String UnderlyingStrikePrice = "316";
/*      */     public static final String UnderlyingOptAttribute = "317";
/*      */     public static final String UnderlyingCurrency = "318";
/*      */     public static final String SecurityReqID = "320";
/*      */     public static final String SecurityRequestType = "321";
/*      */     public static final String SecurityResponseID = "322";
/*      */     public static final String SecurityResponseType = "323";
/*      */     public static final String SecurityStatusReqID = "324";
/*      */     public static final String UnsolicitedIndicator = "325";
/*      */     public static final String SecurityTradingStatus = "326";
/*      */     public static final String HaltReason = "327";
/*      */     public static final String InViewOfCommon = "328";
/*      */     public static final String DueToRelated = "329";
/*      */     public static final String BuyVolume = "330";
/*      */     public static final String SellVolume = "331";
/*      */     public static final String HighPx = "332";
/*      */     public static final String LowPx = "333";
/*      */     public static final String Adjustment = "334";
/*      */     public static final String TradSesReqID = "335";
/*      */     public static final String TradingSessionID = "336";
/*      */     public static final String ContraTrader = "337";
/*      */     public static final String TradSesMethod = "338";
/*      */     public static final String TradSesMode = "339";
/*      */     public static final String TradSesStatus = "340";
/*      */     public static final String TradSesStartTime = "341";
/*      */     public static final String TradSesOpenTime = "342";
/*      */     public static final String TradSesPreCloseTime = "343";
/*      */     public static final String TradSesCloseTime = "344";
/*      */     public static final String TradSesEndTime = "345";
/*      */     public static final String NumberOfOrders = "346";
/*      */     public static final String MessageEncoding = "347";
/*      */     public static final String EncodedIssuerLen = "348";
/*      */     public static final String EncodedIssuer = "349";
/*      */     public static final String EncodedSecurityDescLen = "350";
/*      */     public static final String EncodedSecurityDesc = "351";
/*      */     public static final String EncodedListExecInstLen = "352";
/*      */     public static final String EncodedListExecInst = "353";
/*      */     public static final String EncodedTextLen = "354";
/*      */     public static final String EncodedText = "355";
/*      */     public static final String EncodedSubjectLen = "356";
/*      */     public static final String EncodedSubject = "357";
/*      */     public static final String EncodedHeadlineLen = "358";
/*      */     public static final String EncodedHeadline = "359";
/*      */     public static final String EncodedAllocTextLen = "360";
/*      */     public static final String EncodedAllocText = "361";
/*      */     public static final String EncodedUnderlyingIssuerLen = "362";
/*      */     public static final String EncodedUnderlyingIssuer = "363";
/*      */     public static final String EncodedUnderlyingSecurityDescLen = "364";
/*      */     public static final String EncodedUnderlyingSecurityDesc = "365";
/*      */     public static final String AllocPrice = "366";
/*      */     public static final String QuoteSetValidUntilTime = "367";
/*      */     public static final String QuoteEntryRejectReason = "368";
/*      */     public static final String LastMsgSeqNumProcessed = "369";
/*      */     public static final String RefTagID = "371";
/*      */     public static final String RefMsgType = "372";
/*      */     public static final String SessionRejectReason = "373";
/*      */     public static final String BidRequestTransType = "374";
/*      */     public static final String ContraBroker = "375";
/*      */     public static final String ComplianceID = "376";
/*      */     public static final String SolicitedFlag = "377";
/*      */     public static final String ExecRestatementReason = "378";
/*      */     public static final String BusinessRejectRefID = "379";
/*      */     public static final String BusinessRejectReason = "380";
/*      */     public static final String GrossTradeAmt = "381";
/*      */     public static final String NoContraBrokers = "382";
/*      */     public static final String MaxMessageSize = "383";
/*      */     public static final String NoMsgTypes = "384";
/*      */     public static final String MsgDirection = "385";
/*      */     public static final String NoTradingSessions = "386";
/*      */     public static final String TotalVolumeTraded = "387";
/*      */     public static final String DiscretionInst = "388";
/*      */     public static final String DiscretionOffsetValue = "389";
/*      */     public static final String BidID = "390";
/*      */     public static final String ClientBidID = "391";
/*      */     public static final String ListName = "392";
/*      */     public static final String TotNoRelatedSym = "393";
/*      */     public static final String BidType = "394";
/*      */     public static final String NumTickets = "395";
/*      */     public static final String SideValue1 = "396";
/*      */     public static final String SideValue2 = "397";
/*      */     public static final String NoBidDescriptors = "398";
/*      */     public static final String BidDescriptorType = "399";
/*      */     public static final String BidDescriptor = "400";
/*      */     public static final String SideValueInd = "401";
/*      */     public static final String LiquidityPctLow = "402";
/*      */     public static final String LiquidityPctHigh = "403";
/*      */     public static final String LiquidityValue = "404";
/*      */     public static final String EFPTrackingError = "405";
/*      */     public static final String FairValue = "406";
/*      */     public static final String OutsideIndexPct = "407";
/*      */     public static final String ValueOfFutures = "408";
/*      */     public static final String LiquidityIndType = "409";
/*      */     public static final String WtAverageLiquidity = "410";
/*      */     public static final String ExchangeForPhysical = "411";
/*      */     public static final String OutMainCntryUIndex = "412";
/*      */     public static final String CrossPercent = "413";
/*      */     public static final String ProgRptReqs = "414";
/*      */     public static final String ProgPeriodStringerval = "415";
/*      */     public static final String IncTaxInd = "416";
/*      */     public static final String NumBidders = "417";
/*      */     public static final String BidTradeType = "418";
/*      */     public static final String BasisPxType = "419";
/*      */     public static final String NoBidComponents = "420";
/*      */     public static final String Country = "421";
/*      */     public static final String TotNoStrikes = "422";
/*      */     public static final String PriceType = "423";
/*      */     public static final String DayOrderQty = "424";
/*      */     public static final String DayCumQty = "425";
/*      */     public static final String DayAvgPx = "426";
/*      */     public static final String GTBookingInst = "427";
/*      */     public static final String NoStrikes = "428";
/*      */     public static final String ListStatusType = "429";
/*      */     public static final String NetGrossInd = "430";
/*      */     public static final String ListOrderStatus = "431";
/*      */     public static final String ExpireDate = "432";
/*      */     public static final String ListExecInstType = "433";
/*      */     public static final String CxlRejResponseTo = "434";
/*      */     public static final String UnderlyingCouponRate = "435";
/*      */     public static final String UnderlyingContractMultiplier = "436";
/*      */     public static final String ContraTradeQty = "437";
/*      */     public static final String ContraTradeTime = "438";
/*      */     public static final String LiquidityNumSecurities = "441";
/*      */     public static final String MultiLegReportingType = "442";
/*      */     public static final String StrikeTime = "443";
/*      */     public static final String ListStatusText = "444";
/*      */     public static final String EncodedListStatusTextLen = "445";
/*      */     public static final String EncodedListStatusText = "446";
/*      */     public static final String PartyIDSource = "447";
/*      */     public static final String PartyID = "448";
/*      */     public static final String NetChgPrevDay = "451";
/*      */     public static final String PartyRole = "452";
/*      */     public static final String NoPartyIDs = "453";
/*      */     public static final String NoSecurityAltID = "454";
/*      */     public static final String SecurityAltID = "455";
/*      */     public static final String SecurityAltIDSource = "456";
/*      */     public static final String NoUnderlyingSecurityAltID = "457";
/*      */     public static final String UnderlyingSecurityAltID = "458";
/*      */     public static final String UnderlyingSecurityAltIDSource = "459";
/*      */     public static final String Product = "460";
/*      */     public static final String CFICode = "461";
/*      */     public static final String UnderlyingProduct = "462";
/*      */     public static final String UnderlyingCFICode = "463";
/*      */     public static final String TestMessageIndicator = "464";
/*      */     public static final String QuantityType = "465";
/*      */     public static final String BookingRefID = "466";
/*      */     public static final String IndividualAllocID = "467";
/*      */     public static final String RoundingDirection = "468";
/*      */     public static final String RoundingModulus = "469";
/*      */     public static final String CountryOfIssue = "470";
/*      */     public static final String StateOrProvinceOfIssue = "471";
/*      */     public static final String LocaleOfIssue = "472";
/*      */     public static final String NoRegistDtls = "473";
/*      */     public static final String MailingDtls = "474";
/*      */     public static final String InvestorCountryOfResidence = "475";
/*      */     public static final String PaymentRef = "476";
/*      */     public static final String DistribPaymentMethod = "477";
/*      */     public static final String CashDistribCurr = "478";
/*      */     public static final String CommCurrency = "479";
/*      */     public static final String CancellationRights = "480";
/*      */     public static final String MoneyLaunderingStatus = "481";
/*      */     public static final String MailingInst = "482";
/*      */     public static final String TransBkdTime = "483";
/*      */     public static final String ExecPriceType = "484";
/*      */     public static final String ExecPriceAdjustment = "485";
/*      */     public static final String DateOfBirth = "486";
/*      */     public static final String TradeReportTransType = "487";
/*      */     public static final String CardHolderName = "488";
/*      */     public static final String CardNumber = "489";
/*      */     public static final String CardExpDate = "490";
/*      */     public static final String CardIssNum = "491";
/*      */     public static final String PaymentMethod = "492";
/*      */     public static final String RegistAcctType = "493";
/*      */     public static final String Designation = "494";
/*      */     public static final String TaxAdvantageType = "495";
/*      */     public static final String RegistRejReasonText = "496";
/*      */     public static final String FundRenewWaiv = "497";
/*      */     public static final String CashDistribAgentName = "498";
/*      */     public static final String CashDistribAgentCode = "499";
/*      */     public static final String CashDistribAgentAcctNumber = "500";
/*      */     public static final String CashDistribPayRef = "501";
/*      */     public static final String CashDistribAgentAcctName = "502";
/*      */     public static final String CardStartDate = "503";
/*      */     public static final String PaymentDate = "504";
/*      */     public static final String PaymentRemitterID = "505";
/*      */     public static final String RegistStatus = "506";
/*      */     public static final String RegistRejReasonCode = "507";
/*      */     public static final String RegistRefID = "508";
/*      */     public static final String RegistDtls = "509";
/*      */     public static final String NoDistribInsts = "510";
/*      */     public static final String RegistEmail = "511";
/*      */     public static final String DistribPercentage = "512";
/*      */     public static final String RegistID = "513";
/*      */     public static final String RegistTransType = "514";
/*      */     public static final String ExecValuationPoString = "515";
/*      */     public static final String OrderPercent = "516";
/*      */     public static final String OwnershipType = "517";
/*      */     public static final String NoContAmts = "518";
/*      */     public static final String ContAmtType = "519";
/*      */     public static final String ContAmtValue = "520";
/*      */     public static final String ContAmtCurr = "521";
/*      */     public static final String OwnerType = "522";
/*      */     public static final String PartySubID = "523";
/*      */     public static final String NestedPartyID = "524";
/*      */     public static final String NestedPartyIDSource = "525";
/*      */     public static final String SecondaryClOrdID = "526";
/*      */     public static final String SecondaryExecID = "527";
/*      */     public static final String OrderCapacity = "528";
/*      */     public static final String OrderRestrictions = "529";
/*      */     public static final String MassCancelRequestType = "530";
/*      */     public static final String MassCancelResponse = "531";
/*      */     public static final String MassCancelRejectReason = "532";
/*      */     public static final String TotalAffectedOrders = "533";
/*      */     public static final String NoAffectedOrders = "534";
/*      */     public static final String AffectedOrderID = "535";
/*      */     public static final String AffectedSecondaryOrderID = "536";
/*      */     public static final String QuoteType = "537";
/*      */     public static final String NestedPartyRole = "538";
/*      */     public static final String NoNestedPartyIDs = "539";
/*      */     public static final String TotalAccruedStringerestAmt = "540";
/*      */     public static final String MaturityDate = "541";
/*      */     public static final String UnderlyingMaturityDate = "542";
/*      */     public static final String InstrRegistry = "543";
/*      */     public static final String CashMargin = "544";
/*      */     public static final String NestedPartySubID = "545";
/*      */     public static final String Scope = "546";
/*      */     public static final String MDImplicitDelete = "547";
/*      */     public static final String CrossID = "548";
/*      */     public static final String CrossType = "549";
/*      */     public static final String CrossPrioritization = "550";
/*      */     public static final String OrigCrossID = "551";
/*      */     public static final String NoSides = "552";
/*      */     public static final String Username = "553";
/*      */     public static final String Password = "554";
/*      */     public static final String NoLegs = "555";
/*      */     public static final String LegCurrency = "556";
/*      */     public static final String TotNoSecurityTypes = "557";
/*      */     public static final String NoSecurityTypes = "558";
/*      */     public static final String SecurityListRequestType = "559";
/*      */     public static final String SecurityRequestResult = "560";
/*      */     public static final String RoundLot = "561";
/*      */     public static final String MStringradeVol = "562";
/*      */     public static final String MultiLegRptTypeReq = "563";
/*      */     public static final String LegPositionEffect = "564";
/*      */     public static final String LegCoveredOrUncovered = "565";
/*      */     public static final String LegPrice = "566";
/*      */     public static final String TradSesStatusRejReason = "567";
/*      */     public static final String TradeRequestID = "568";
/*      */     public static final String TradeRequestType = "569";
/*      */     public static final String PreviouslyReported = "570";
/*      */     public static final String TradeReportID = "571";
/*      */     public static final String TradeReportRefID = "572";
/*      */     public static final String MatchStatus = "573";
/*      */     public static final String MatchType = "574";
/*      */     public static final String OddLot = "575";
/*      */     public static final String NoClearingInstructions = "576";
/*      */     public static final String ClearingInstruction = "577";
/*      */     public static final String TradeInputSource = "578";
/*      */     public static final String TradeInputDevice = "579";
/*      */     public static final String NoDates = "580";
/*      */     public static final String AccountType = "581";
/*      */     public static final String CustOrderCapacity = "582";
/*      */     public static final String ClOrdLinkID = "583";
/*      */     public static final String MassStatusReqID = "584";
/*      */     public static final String MassStatusReqType = "585";
/*      */     public static final String OrigOrdModTime = "586";
/*      */     public static final String LegSettlType = "587";
/*      */     public static final String LegSettlDate = "588";
/*      */     public static final String DayBookingInst = "589";
/*      */     public static final String BookingUnit = "590";
/*      */     public static final String PreallocMethod = "591";
/*      */     public static final String UnderlyingCountryOfIssue = "592";
/*      */     public static final String UnderlyingStateOrProvinceOfIssue = "593";
/*      */     public static final String UnderlyingLocaleOfIssue = "594";
/*      */     public static final String UnderlyingInstrRegistry = "595";
/*      */     public static final String LegCountryOfIssue = "596";
/*      */     public static final String LegStateOrProvinceOfIssue = "597";
/*      */     public static final String LegLocaleOfIssue = "598";
/*      */     public static final String LegInstrRegistry = "599";
/*      */     public static final String LegSymbol = "600";
/*      */     public static final String LegSymbolSfx = "601";
/*      */     public static final String LegSecurityID = "602";
/*      */     public static final String LegSecurityIDSource = "603";
/*      */     public static final String NoLegSecurityAltID = "604";
/*      */     public static final String LegSecurityAltID = "605";
/*      */     public static final String LegSecurityAltIDSource = "606";
/*      */     public static final String LegProduct = "607";
/*      */     public static final String LegCFICode = "608";
/*      */     public static final String LegSecurityType = "609";
/*      */     public static final String LegMaturityMonthYear = "610";
/*      */     public static final String LegMaturityDate = "611";
/*      */     public static final String LegStrikePrice = "612";
/*      */     public static final String LegOptAttribute = "613";
/*      */     public static final String LegContractMultiplier = "614";
/*      */     public static final String LegCouponRate = "615";
/*      */     public static final String LegSecurityExchange = "616";
/*      */     public static final String LegIssuer = "617";
/*      */     public static final String EncodedLegIssuerLen = "618";
/*      */     public static final String EncodedLegIssuer = "619";
/*      */     public static final String LegSecurityDesc = "620";
/*      */     public static final String EncodedLegSecurityDescLen = "621";
/*      */     public static final String EncodedLegSecurityDesc = "622";
/*      */     public static final String LegRatioQty = "623";
/*      */     public static final String LegSide = "624";
/*      */     public static final String TradingSessionSubID = "625";
/*      */     public static final String AllocType = "626";
/*      */     public static final String NoHops = "627";
/*      */     public static final String HopCompID = "628";
/*      */     public static final String HopSendingTime = "629";
/*      */     public static final String HopRefID = "630";
/*      */     public static final String MidPx = "631";
/*      */     public static final String BidYield = "632";
/*      */     public static final String MidYield = "633";
/*      */     public static final String OfferYield = "634";
/*      */     public static final String ClearingFeeIndicator = "635";
/*      */     public static final String WorkingIndicator = "636";
/*      */     public static final String LegLastPx = "637";
/*      */     public static final String PriorityIndicator = "638";
/*      */     public static final String PriceImprovement = "639";
/*      */     public static final String Price2 = "640";
/*      */     public static final String LastForwardPoStrings2 = "641";
/*      */     public static final String BidForwardPoStrings2 = "642";
/*      */     public static final String OfferForwardPoStrings2 = "643";
/*      */     public static final String RFQReqID = "644";
/*      */     public static final String MktBidPx = "645";
/*      */     public static final String MktOfferPx = "646";
/*      */     public static final String MinBidSize = "647";
/*      */     public static final String MinOfferSize = "648";
/*      */     public static final String QuoteStatusReqID = "649";
/*      */     public static final String LegalConfirm = "650";
/*      */     public static final String UnderlyingLastPx = "651";
/*      */     public static final String UnderlyingLastQty = "652";
/*      */     public static final String LegRefID = "654";
/*      */     public static final String ContraLegRefID = "655";
/*      */     public static final String SettlCurrBidFxRate = "656";
/*      */     public static final String SettlCurrOfferFxRate = "657";
/*      */     public static final String QuoteRequestRejectReason = "658";
/*      */     public static final String SideComplianceID = "659";
/*      */     public static final String AcctIDSource = "660";
/*      */     public static final String AllocAcctIDSource = "661";
/*      */     public static final String BenchmarkPrice = "662";
/*      */     public static final String BenchmarkPriceType = "663";
/*      */     public static final String ConfirmID = "664";
/*      */     public static final String ConfirmStatus = "665";
/*      */     public static final String ConfirmTransType = "666";
/*      */     public static final String ContractSettlMonth = "667";
/*      */     public static final String DeliveryForm = "668";
/*      */     public static final String LastParPx = "669";
/*      */     public static final String NoLegAllocs = "670";
/*      */     public static final String LegAllocAccount = "671";
/*      */     public static final String LegIndividualAllocID = "672";
/*      */     public static final String LegAllocQty = "673";
/*      */     public static final String LegAllocAcctIDSource = "674";
/*      */     public static final String LegSettlCurrency = "675";
/*      */     public static final String LegBenchmarkCurveCurrency = "676";
/*      */     public static final String LegBenchmarkCurveName = "677";
/*      */     public static final String LegBenchmarkCurvePoString = "678";
/*      */     public static final String LegBenchmarkPrice = "679";
/*      */     public static final String LegBenchmarkPriceType = "680";
/*      */     public static final String LegBidPx = "681";
/*      */     public static final String LegIOIQty = "682";
/*      */     public static final String NoLegStipulations = "683";
/*      */     public static final String LegOfferPx = "684";
/*      */     public static final String LegOrderQty = "685";
/*      */     public static final String LegPriceType = "686";
/*      */     public static final String LegQty = "687";
/*      */     public static final String LegStipulationType = "688";
/*      */     public static final String LegStipulationValue = "689";
/*      */     public static final String LegSwapType = "690";
/*      */     public static final String Pool = "691";
/*      */     public static final String QuotePriceType = "692";
/*      */     public static final String QuoteRespID = "693";
/*      */     public static final String QuoteRespType = "694";
/*      */     public static final String QuoteQualifier = "695";
/*      */     public static final String YieldRedemptionDate = "696";
/*      */     public static final String YieldRedemptionPrice = "697";
/*      */     public static final String YieldRedemptionPriceType = "698";
/*      */     public static final String BenchmarkSecurityID = "699";
/*      */     public static final String ReversalIndicator = "700";
/*      */     public static final String YieldCalcDate = "701";
/*      */     public static final String NoPositions = "702";
/*      */     public static final String PosType = "703";
/*      */     public static final String LongQty = "704";
/*      */     public static final String ShortQty = "705";
/*      */     public static final String PosQtyStatus = "706";
/*      */     public static final String PosAmtType = "707";
/*      */     public static final String PosAmt = "708";
/*      */     public static final String PosTransType = "709";
/*      */     public static final String PosReqID = "710";
/*      */     public static final String NoUnderlyings = "711";
/*      */     public static final String PosMaStringAction = "712";
/*      */     public static final String OrigPosReqRefID = "713";
/*      */     public static final String PosMaStringRptRefID = "714";
/*      */     public static final String ClearingBusinessDate = "715";
/*      */     public static final String SettlSessID = "716";
/*      */     public static final String SettlSessSubID = "717";
/*      */     public static final String AdjustmentType = "718";
/*      */     public static final String ContraryInstructionIndicator = "719";
/*      */     public static final String PriorSpreadIndicator = "720";
/*      */     public static final String PosMaStringRptID = "721";
/*      */     public static final String PosMaStringStatus = "722";
/*      */     public static final String PosMaStringResult = "723";
/*      */     public static final String PosReqType = "724";
/*      */     public static final String ResponseTransportType = "725";
/*      */     public static final String ResponseDestination = "726";
/*      */     public static final String TotalNumPosReports = "727";
/*      */     public static final String PosReqResult = "728";
/*      */     public static final String PosReqStatus = "729";
/*      */     public static final String SettlPrice = "730";
/*      */     public static final String SettlPriceType = "731";
/*      */     public static final String UnderlyingSettlPrice = "732";
/*      */     public static final String UnderlyingSettlPriceType = "733";
/*      */     public static final String PriorSettlPrice = "734";
/*      */     public static final String NoQuoteQualifiers = "735";
/*      */     public static final String AllocSettlCurrency = "736";
/*      */     public static final String AllocSettlCurrAmt = "737";
/*      */     public static final String StringerestAtMaturity = "738";
/*      */     public static final String LegDatedDate = "739";
/*      */     public static final String LegPool = "740";
/*      */     public static final String AllocStringerestAtMaturity = "741";
/*      */     public static final String AllocAccruedStringerestAmt = "742";
/*      */     public static final String DeliveryDate = "743";
/*      */     public static final String AssignmentMethod = "744";
/*      */     public static final String AssignmentUnit = "745";
/*      */     public static final String OpenStringerest = "746";
/*      */     public static final String ExerciseMethod = "747";
/*      */     public static final String TotNumTradeReports = "748";
/*      */     public static final String TradeRequestResult = "749";
/*      */     public static final String TradeRequestStatus = "750";
/*      */     public static final String TradeReportRejectReason = "751";
/*      */     public static final String SideMultiLegReportingType = "752";
/*      */     public static final String NoPosAmt = "753";
/*      */     public static final String AutoAcceptIndicator = "754";
/*      */     public static final String AllocReportID = "755";
/*      */     public static final String NoNested2PartyIDs = "756";
/*      */     public static final String Nested2PartyID = "757";
/*      */     public static final String Nested2PartyIDSource = "758";
/*      */     public static final String Nested2PartyRole = "759";
/*      */     public static final String Nested2PartySubID = "760";
/*      */     public static final String BenchmarkSecurityIDSource = "761";
/*      */     public static final String SecuritySubType = "762";
/*      */     public static final String UnderlyingSecuritySubType = "763";
/*      */     public static final String LegSecuritySubType = "764";
/*      */     public static final String AllowableOneSidednessPct = "765";
/*      */     public static final String AllowableOneSidednessValue = "766";
/*      */     public static final String AllowableOneSidednessCurr = "767";
/*      */     public static final String NoTrdRegTimestamps = "768";
/*      */     public static final String TrdRegTimestamp = "769";
/*      */     public static final String TrdRegTimestampType = "770";
/*      */     public static final String TrdRegTimestampOrigin = "771";
/*      */     public static final String ConfirmRefID = "772";
/*      */     public static final String ConfirmType = "773";
/*      */     public static final String ConfirmRejReason = "774";
/*      */     public static final String BookingType = "775";
/*      */     public static final String IndividualAllocRejCode = "776";
/*      */     public static final String SettlInstMsgID = "777";
/*      */     public static final String NoSettlInst = "778";
/*      */     public static final String LastUpdateTime = "779";
/*      */     public static final String AllocSettlInstType = "780";
/*      */     public static final String NoSettlPartyIDs = "781";
/*      */     public static final String SettlPartyID = "782";
/*      */     public static final String SettlPartyIDSource = "783";
/*      */     public static final String SettlPartyRole = "784";
/*      */     public static final String SettlPartySubID = "785";
/*      */     public static final String SettlPartySubIDType = "786";
/*      */     public static final String DlvyInstType = "787";
/*      */     public static final String TerminationType = "788";
/*      */     public static final String NextExpectedMsgSeqNum = "789";
/*      */     public static final String OrdStatusReqID = "790";
/*      */     public static final String SettlInstReqID = "791";
/*      */     public static final String SettlInstReqRejCode = "792";
/*      */     public static final String SecondaryAllocID = "793";
/*      */     public static final String AllocReportType = "794";
/*      */     public static final String AllocReportRefID = "795";
/*      */     public static final String AllocCancReplaceReason = "796";
/*      */     public static final String CopyMsgIndicator = "797";
/*      */     public static final String AllocAccountType = "798";
/*      */     public static final String OrderAvgPx = "799";
/*      */     public static final String OrderBookingQty = "800";
/*      */     public static final String NoSettlPartySubIDs = "801";
/*      */     public static final String NoPartySubIDs = "802";
/*      */     public static final String PartySubIDType = "803";
/*      */     public static final String NoNestedPartySubIDs = "804";
/*      */     public static final String NestedPartySubIDType = "805";
/*      */     public static final String NoNested2PartySubIDs = "806";
/*      */     public static final String Nested2PartySubIDType = "807";
/*      */     public static final String AllocStringermedReqType = "808";
/*      */     public static final String UnderlyingPx = "810";
/*      */     public static final String PriceDelta = "811";
/*      */     public static final String ApplQueueMax = "812";
/*      */     public static final String ApplQueueDepth = "813";
/*      */     public static final String ApplQueueResolution = "814";
/*      */     public static final String ApplQueueAction = "815";
/*      */     public static final String NoAltMDSource = "816";
/*      */     public static final String AltMDSourceID = "817";
/*      */     public static final String SecondaryTradeReportID = "818";
/*      */     public static final String AvgPxIndicator = "819";
/*      */     public static final String TradeLinkID = "820";
/*      */     public static final String OrderInputDevice = "821";
/*      */     public static final String UnderlyingTradingSessionID = "822";
/*      */     public static final String UnderlyingTradingSessionSubID = "823";
/*      */     public static final String TradeLegRefID = "824";
/*      */     public static final String ExchangeRule = "825";
/*      */     public static final String TradeAllocIndicator = "826";
/*      */     public static final String ExpirationCycle = "827";
/*      */     public static final String TrdType = "828";
/*      */     public static final String TrdSubType = "829";
/*      */     public static final String TransferReason = "830";
/*      */     public static final String AsgnReqID = "831";
/*      */     public static final String TotNumAssignmentReports = "832";
/*      */     public static final String AsgnRptID = "833";
/*      */     public static final String ThresholdAmount = "834";
/*      */     public static final String PegMoveType = "835";
/*      */     public static final String PegOffsetType = "836";
/*      */     public static final String PegLimitType = "837";
/*      */     public static final String PegRoundDirection = "838";
/*      */     public static final String PeggedPrice = "839";
/*      */     public static final String PegScope = "840";
/*      */     public static final String DiscretionMoveType = "841";
/*      */     public static final String DiscretionOffsetType = "842";
/*      */     public static final String DiscretionLimitType = "843";
/*      */     public static final String DiscretionRoundDirection = "844";
/*      */     public static final String DiscretionPrice = "845";
/*      */     public static final String DiscretionScope = "846";
/*      */     public static final String TargetStrategy = "847";
/*      */     public static final String TargetStrategyParameters = "848";
/*      */     public static final String ParticipationRate = "849";
/*      */     public static final String TargetStrategyPerformance = "850";
/*      */     public static final String LastLiquidityInd = "851";
/*      */     public static final String PublishTrdIndicator = "852";
/*      */     public static final String ShortSaleReason = "853";
/*      */     public static final String QtyType = "854";
/*      */     public static final String SecondaryTrdType = "855";
/*      */     public static final String TradeReportType = "856";
/*      */     public static final String AllocNoOrdersType = "857";
/*      */     public static final String SharedCommission = "858";
/*      */     public static final String ConfirmReqID = "859";
/*      */     public static final String AvgParPx = "860";
/*      */     public static final String ReportedPx = "861";
/*      */     public static final String NoCapacities = "862";
/*      */     public static final String OrderCapacityQty = "863";
/*      */     public static final String NoEvents = "864";
/*      */     public static final String EventType = "865";
/*      */     public static final String EventDate = "866";
/*      */     public static final String EventPx = "867";
/*      */     public static final String EventText = "868";
/*      */     public static final String PctAtRisk = "869";
/*      */     public static final String NoInstrAttrib = "870";
/*      */     public static final String InstrAttribType = "871";
/*      */     public static final String InstrAttribValue = "872";
/*      */     public static final String DatedDate = "873";
/*      */     public static final String StringerestAccrualDate = "874";
/*      */     public static final String CPProgram = "875";
/*      */     public static final String CPRegType = "876";
/*      */     public static final String UnderlyingCPProgram = "877";
/*      */     public static final String UnderlyingCPRegType = "878";
/*      */     public static final String UnderlyingQty = "879";
/*      */     public static final String TrdMatchID = "880";
/*      */     public static final String SecondaryTradeReportRefID = "881";
/*      */     public static final String UnderlyingDirtyPrice = "882";
/*      */     public static final String UnderlyingEndPrice = "883";
/*      */     public static final String UnderlyingStartValue = "884";
/*      */     public static final String UnderlyingCurrentValue = "885";
/*      */     public static final String UnderlyingEndValue = "886";
/*      */     public static final String NoUnderlyingStips = "887";
/*      */     public static final String UnderlyingStipType = "888";
/*      */     public static final String UnderlyingStipValue = "889";
/*      */     public static final String MaturityNetMoney = "890";
/*      */     public static final String MiscFeeBasis = "891";
/*      */     public static final String TotNoAllocs = "892";
/*      */     public static final String LastFragment = "893";
/*      */     public static final String CollReqID = "894";
/*      */     public static final String CollAsgnReason = "895";
/*      */     public static final String CollInquiryQualifier = "896";
/*      */     public static final String NoTrades = "897";
/*      */     public static final String MarginRatio = "898";
/*      */     public static final String MarginExcess = "899";
/*      */     public static final String TotalNetValue = "900";
/*      */     public static final String CashOutstanding = "901";
/*      */     public static final String CollAsgnID = "902";
/*      */     public static final String CollAsgnTransType = "903";
/*      */     public static final String CollRespID = "904";
/*      */     public static final String CollAsgnRespType = "905";
/*      */     public static final String CollAsgnRejectReason = "906";
/*      */     public static final String CollAsgnRefID = "907";
/*      */     public static final String CollRptID = "908";
/*      */     public static final String CollInquiryID = "909";
/*      */     public static final String CollStatus = "910";
/*      */     public static final String TotNumReports = "911";
/*      */     public static final String LastRptRequested = "912";
/*      */     public static final String AgreementDesc = "913";
/*      */     public static final String AgreementID = "914";
/*      */     public static final String AgreementDate = "915";
/*      */     public static final String StartDate = "916";
/*      */     public static final String EndDate = "917";
/*      */     public static final String AgreementCurrency = "918";
/*      */     public static final String DeliveryType = "919";
/*      */     public static final String EndAccruedStringerestAmt = "920";
/*      */     public static final String StartCash = "921";
/*      */     public static final String EndCash = "922";
/*      */     public static final String UserRequestID = "923";
/*      */     public static final String UserRequestType = "924";
/*      */     public static final String NewPassword = "925";
/*      */     public static final String UserStatus = "926";
/*      */     public static final String UserStatusText = "927";
/*      */     public static final String StatusValue = "928";
/*      */     public static final String StatusText = "929";
/*      */     public static final String RefCompID = "930";
/*      */     public static final String RefSubID = "931";
/*      */     public static final String NetworkResponseID = "932";
/*      */     public static final String NetworkRequestID = "933";
/*      */     public static final String LastNetworkResponseID = "934";
/*      */     public static final String NetworkRequestType = "935";
/*      */     public static final String NoCompIDs = "936";
/*      */     public static final String NetworkStatusResponseType = "937";
/*      */     public static final String NoCollInquiryQualifier = "938";
/*      */     public static final String TrdRptStatus = "939";
/*      */     public static final String AffirmStatus = "940";
/*      */     public static final String UnderlyingStrikeCurrency = "941";
/*      */     public static final String LegStrikeCurrency = "942";
/*      */     public static final String TimeBracket = "943";
/*      */     public static final String CollAction = "944";
/*      */     public static final String CollInquiryStatus = "945";
/*      */     public static final String CollInquiryResult = "946";
/*      */     public static final String StrikeCurrency = "947";
/*      */     public static final String NoNested3PartyIDs = "948";
/*      */     public static final String Nested3PartyID = "949";
/*      */     public static final String Nested3PartyIDSource = "950";
/*      */     public static final String Nested3PartyRole = "951";
/*      */     public static final String NoNested3PartySubIDs = "952";
/*      */     public static final String Nested3PartySubID = "953";
/*      */     public static final String Nested3PartySubIDType = "954";
/*      */     public static final String LegContractSettlMonth = "955";
/*      */     public static final String LegStringerestAccrualDate = "956";
/*      */     public static final String FortexMsg = "9001";
/*      */     public static final String Fortex_isThirdPartyOk = "9002";
/*      */     public static final String Fortex_ThirdPartyApp = "9003";
/*      */     public static final String Fortex_ThirdPartyUID = "9004";
/*      */     public static final String Fortex_ThirdPartyIP = "9005";
/*      */     public static final String Fortex_ThirdPartyPort = "9006";
/*      */     public static final String Fortex_ThirdPartyOther = "9007";
/*      */     public static final String Fortex_IPQuoteServer = "9008";
/*      */     public static final String Fortex_IPChartServer = "9009";
/*      */     public static final String Fortex_IPIMServer = "9010";
/*      */     public static final String Fortex_IPIMServerChannel = "9011";
/*      */     public static final String Fortex_DomainFreeText = "9012";
/*      */     public static final String Fortex_QuoteService = "9013";
/*      */     public static final String Fortex_AccountType = "9014";
/*      */     public static final String Fortex_UserSettings = "9015";
/*      */     public static final String Fortex_AccAllowEntry = "9016";
/*      */     public static final String Fortex_AccOrderID = "9017";
/*      */     public static final String Fortex_LoginInfo = "9018";
/*      */     public static final String Fortex_InMsgNum = "9019";
/*      */     public static final String Fortex_OutMsgNum = "9020";
/*      */     public static final String Fortex_AccessCtrl = "9021";
/*      */     public static final String Fortex_Scripting = "9022";
/*      */     public static final String Fortex_Metals = "9023";
/*      */     public static final String Fortex_NoLogonOrderEntryDest = "9040";
/*      */     public static final String Fortex_EntryDestRoute = "9041";
/*      */     public static final String Fortex_EntryDestExchange = "9042";
/*      */     public static final String Fortex_EntryDestMktType = "9043";
/*      */     public static final String Fortex_EntryDestReplaceFlag = "9044";
/*      */     public static final String Fortex_EntryDestDecimalFlag = "9045";
/*      */     public static final String Fortex_EntryDestNeedOldQtyFlag = "9046";
/*      */     public static final String Fortex_NoMarketOrderTypeRoutePmx = "9050";
/*      */     public static final String Fortex_MarketOrderType = "9051";
/*      */     public static final String Fortex_NoTIFRoutePmx = "9055";
/*      */     public static final String Fortex_TIF = "9056";
/*      */     public static final String Fortex_NoFXSymbolList = "9060";
/*      */     public static final String Fortex_FXSymDecimal = "9061";
/*      */     public static final String Fortex_FXSymPipPrecision = "9062";
/*      */     public static final String Fortex_NoDomains = "9085";
/*      */     public static final String Fortex_Domain = "9086";
/*      */     public static final String Fortex_DomainDescr = "9087";
/*      */     public static final String Fortex_NoAccountDomains = "9091";
/*      */     public static final String Fortex_NoFutureMargins = "9100";
/*      */     public static final String Fortex_FutureUnitValue = "9101 ";
/*      */     public static final String Fortex_IsMarginFixed = "9102 ";
/*      */     public static final String Fortex_FixedInitMargin = "9103 ";
/*      */     public static final String Fortex_FixedMainMargin = "9104 ";
/*      */     public static final String Fortex_PctInitMargin = "9105 ";
/*      */     public static final String Fortex_PctMainMargin = "9106 ";
/*      */     public static final String Fortex_NoStockMargins = "9110";
/*      */     public static final String Fortex_NoTradePermissions = "9115";
/*      */     public static final String Fortex_TradePermissionStock = "9116";
/*      */     public static final String Fortex_TradePermissionOptions = "9117";
/*      */     public static final String Fortex_TradePermissionFutures = "9118";
/*      */     public static final String Fortex_TradePermissionOptionLevel = "9119";
/*      */     public static final String Fortex_TradePermissionFX = "9120";
/*      */     public static final String Fortex_TradePermissionFXInitMargin = "9121";
/*      */     public static final String Fortex_TradePermissionFXMainMargin = "9122";
/*      */     public static final String Fortex_TradePermissionFXContractAmount = "9123";
/*      */     public static final String Fortex_TradePermissionFXLiquidMargin = "9124";
/*      */     public static final String Fortex_NoTradeAccountSettings = "9130";
/*      */     public static final String Fortex_TradeAccountBodcash = "9131";
/*      */     public static final String Fortex_TradeAccountPurPower = "9132";
/*      */     public static final String Fortex_TradeAccountBPFactor = "9133";
/*      */     public static final String Fortex_TradeAccountBPGiveback = "9134";
/*      */     public static final String Fortex_NoTradeAccountSettingsAdmin = "9140";
/*      */     public static final String Fortex_NoOverNightPositions = "9150";
/*      */     public static final String Fortex_NoPositions = "9155";
/*      */     public static final String Fortex_NoOrders = "9160";
/*      */     public static final String Fortex_OrderOptionType = "9161";
/*      */     public static final String Fortex_OrderOptionHandlingType = "9162";
/*      */     public static final String Fortex_OrderOptionCovered = "9163";
/*      */     public static final String Fortex_OrderTag = "9164";
/*      */     public static final String Fortex_OrderMMID = "9165";
/*      */     public static final String Fortex_OrderUser = "9166";
/*      */     public static final String Fortex_OrderOrigUser = "9167";
/*      */     public static final String Fortex_OrderQtyReserved = "9168";
/*      */     public static final String Fortex_OrderFilled = "9169";
/*      */     public static final String Fortex_OrderMsgType = "9170";
/*      */     public static final String Fortex_OrderInstType = "9171";
/*      */     public static final String Fortex_OrderTradeTime = "9172";
/*      */     public static final String Fortex_OrderFillSeqno = "9173";
/*      */     public static final String Fortex_OrderChainID = "9174";
/*      */     public static final String Fortex_TicketType = "9175";
/*      */     public static final String Fortex_TicketNo = "9176";
/*      */     public static final String Fortex_ReferenceTicketNo = "9177";
/*      */     public static final String Fortex_NoReplaceOrders = "9180";
/*      */     public static final String Fortex_ReplaceOrderOrigOrderID = "9181";
/*      */     public static final String Fortex_NoOrderLog = "9190";
/*      */     public static final String Fortex_OrderLogEventType = "9191";
/*      */     public static final String Fortex_OrderLogEventText = "9192";
/*      */     public static final String Fortex_OrderLogEventTypeRef = "9193";
/*      */     public static final String Fortex_OrderPrincipalAgency = "9201";
/*      */     public static final String Fortex_OrderStopLoss = "9202";
/*      */     public static final String Fortex_OrderTakeProfit = "9203";
/*      */     public static final String Fortex_OrderSlippage = "9204";
/*      */     public static final String Fortex_OrderTrailBy = "9205";
/*      */     public static final String Fortex_CtrlOrderType = "9300";
/*      */     public static final String Fortex_CtrlValueCASH = "9301";
/*      */     public static final String Fortex_CtrlValueENABLE = "9302";
/*      */     public static final String Fortex_CtrlValueDISABLE = "9303";
/*      */     public static final String Fortex_CtrlValueAUTOPILOT = "9304";
/*      */     public static final String Fortex_CtrlValueGUARANTEEDSTOP = "9305";
/*      */     public static final String Fortex_CtrlValueSCALPGUARD = "9306";
/*      */     public static final String Fortex_CtrlValueALLORESTRICTED = "9307";
/*      */     public static final String Fortex_CtrlValueSTP = "9308";
/*      */     public static final String Fortex_CtrlValueSGSettings = "9309";
/*      */     public static final String Fortex_CtrlValueListEncoded = "9310";
/*      */     public static final String Fortex_CtrlValueAccountList = "9311";
/*      */     public static final String Fortex_CtrlValueQtyList = "9312";
/*      */     public static final String Fortex_CtrlValueVarName = "9313";
/*      */     public static final String Fortex_CtrlValueVarValue = "9314";
/*      */     public static final String Fortex_CtrlValueMsgKey = "9315";
/*      */     public static final String Fortex_NoCtrlList = "9316";
/*      */     public static final String Fortex_DealerAction = "9325";
/*      */     public static final String Fortex_ContractSize = "9328";
/*      */     public static final String Fortex_ReferencePrice = "9329";
/*      */     public static final String Fortex_TotalAggregatedFIXMsgs = "9400";
/*      */     public static final String Fortex_AggregatedFIXMsgsLen = "9401";
/*      */     public static final String Fortex_AggregatedFIXMsgs = "9402";
/*      */     public static final String Fortex_ChartBar_TimeStamp = "9420";
/*      */     public static final String Fortex_ChartBar_Open = "9421";
/*      */     public static final String Fortex_ChartBar_Close = "9422";
/*      */     public static final String Fortex_ChartBar_High = "9423";
/*      */     public static final String Fortex_ChartBar_Low = "9424";
/*      */     public static final String Fortex_ChartBar_Volume = "9425";
/*      */     public static final String Fortex_ChartBar_Type = "9426";
/*      */     public static final String Fortex_ChartBar_Days = "9427";
/*      */     public static final String Fortex_ChartBar_Symbol = "9428";
/*      */     public static final String Fortex_ChartBar_StartBarTime = "9429";
/*      */     public static final String Fortex_StatusTarget = " 9500";
/*      */     public static final String Fortex_AllocationOrder = " 9510";
/*      */     public static final String Fortex_AllocationDistCnt = " 9511";
/*      */     public static final String Fortex_AllocationDistributed = " 9512";
/*      */     public static final String Fortex_QM_SubType = "9601";
/*      */     public static final String Fortex_MinSpread = "9602";
/*      */     public static final String Fortex_MaxSpread = "9603";
/*      */     public static final String Fortex_OpenExSubType = " 9610";
/*      */     public static final String Fortex_ExchangeID = "9611";
/*      */     public static final String Fortex_Open = "9612";
/*      */     public static final String Fortex_BidQuoteId = "9613";
/*      */     public static final String Fortex_AskQuoteId = "9614";
/*      */     public static final String Fortex_TimeStamp = "9615";
/*      */     public static final String Fortex_RealPrice = "9616";
/*      */     public static final String Fortex_MktDataStatus = " 9617";
/*      */     public static final String Fortex_BankQuoteID = " 9618";
/*      */     public static final String Fortex_snapshotID = " 9619";
/*      */     public static final String Fortex_latencyTag = " 9620";
/*      */     public static final String OPENEX_QUOTEMSG_COUNT = "9700";
/*      */     public static final String OPENEX_QUOTEMSG_SEQ = "9701";
/*      */     public static final String FORTEX_SHELL_RESPONSE = "9985";
/*      */     public static final String FORTEX_SHELL_COMMAND = "9986";
/*      */     public static final String FORTEX_NOTICATION_MESSAGE = "9987";
/*      */     public static final String FORTEX_NOTICATION_CODE = "9988";
/*      */     public static final String FORTEX_LOGINMSG = "9989";
/*      */     public static final String FORTEX_FIXLOGSEQ = "9993";
/*      */     public static final String FORTEX_CARRYOVER_MSG = "9994";
/*      */     public static final String FORTEX_RECONNECTING = "9995";
/*      */     public static final String FORTEX_FAILOVER_IP = "9996";
/*      */     public static final String FORTEX_FAILOVER_PORT = "9997";
/*      */     public static final String FORTEX_GROUP_END = "9998";
/*      */     public static final String FORTEX_UNKNOWN = "9999";
/*      */     public static final String OPENEX_QUOTEMSG_MDDONE = "10003";
/*      */     public static final String FORTEX_MSG_PLACEHOLDER1 = "11001 ";
/*      */     public static final String FORTEX_BROADCAST_PERSISTENCE = "11000 ";
/*      */     public static final String FORTEX_ENDPOStringS_NUM = "11001";
/*      */     public static final String FORTEX_ENDPOString_PLACEHOLDER1 = "11002";
/*      */     public static final String FORTEX_QUOTE_ERROR_CODE = "12000";
/*      */     public static final String FORTEX_QUOTE_ERROR_MSG = "12001";
/*      */     public static final String FORTEX_RAWMESSAGE = "12002";
/*      */     public static final String xXCloudGroupTagName = "12003";
/*      */     public static final String xXCloudMultipartID = "12004";
/*      */     public static final String xCloudServer = "13000";
/*      */     public static final String xCloudPW = "13001";
/*      */     public static final String xServerTokenID = "13002";
/*      */     public static final String xClientTokenID = "13003";
/*      */     public static final String xNoSubscriptions = "13004";
/*      */     public static final String xSubscriptionType = "13005";
/*      */     public static final String xSubscriptionAccount = "13006";
/*      */     public static final String xSubscriptionControl = "13007";
/*      */     public static final String xStatusResponse = "13008";
/*      */     public static final String xStatusType = "13009";
/*      */     public static final String xXCloudMsgType = "13010";
/*      */     public static final String xXCloudServerName = "13011";
/*      */     public static final String xSendingTime = "13012";
/*      */     public static final String xJSONServerName = "13013";
/*      */     public static final String xJSONSendingTime = "13014";
/*      */     public static final String xEnclosingSymbol = "13015";
/*      */     public static final String xAllClientTokenIDs = "13500";
/*      */     public static final String FORTEX_CL_ORDER_ID = "15001";
/*      */     public static final String FORTEX_ORIG_CL_ORDER_ID = "15002";
/*      */     public static final String FORTEX_CL_ORDER_ID_FILL_NO = "15003";
/*      */     public static final String TT_SecurityAltID = "10455";
/*      */     public static final String XCLOUD_SESSION_TOKEN = "20000";
/*      */     public static final String XCLOUD_SESSION_PASSWORD = "20001";
/*      */     public static final String XCLOUD_PROTOCOL_VERSION = "20002";
/*      */     public static final String XCLOUD_SERVER_IP = "20003";
/*      */     public static final String XCLOUD_SERVER_PORT = "20004";
/*      */     public static final String XCLOUD_SERVER_TIME = "20005";
/*      */     public static final String XCLOUD_ACCOUNT_LIST = "20006";
/*      */     public static final String XCLOUD_MSGS = "20007";
/*      */     public static final String XCLOUD_CLIENT_TYPE = "20008";
/*      */     public static final String XCLOUD_LOGON_STATUS = "20009";
/*      */     public static final String XCLOUD_SERVER_TIMEZONE = "20010";
/*      */     public static final String XCLOUD_BACKOFFICE_URL = "20011";
/*      */     public static final String XCLOUD_SESSION_DATE = "20012";
/*      */     
/*      */     public TAGS() {}
/*      */   }
/*      */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\protocol\FIX_DEF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */