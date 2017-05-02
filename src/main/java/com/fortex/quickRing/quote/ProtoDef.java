package com.fortex.quickRing.quote;


import com.fortex.lib.protocol.ErrDef.ErrCodeDef;
public class ProtoDef {
	static public enum QuoteType{L1,L2};
	
	public class NAME {	//JSON names for all name/value pairs.
		public final static String objAccountSetting="AcctSetting";
		public final static String ObjAccountValue	="AcctVal";
		public final static String ObjConSymbol		="ConSymbol";
		public final static String ObjConSymbolAry	="ConSymbolAry";
		public final static String ObjChartHisReq	="ChartHisReq";
		public final static String ObjChartDataAry	="ChartDataAry";
		public final static String ObjMarketData	="MD";
		public final static String ObjMsgAry		="MsgAry";
		public final static String ObjConDomain		="ConDomain";
		public final static String ObjExecReport	="ExecRp";
		public final static String ObjQuote			="Q";
		public final static String ObjQuoteRequest	="QReq";
		public final static String ObjOrdReq		="OrdReq";
		public final static String ObjOrdCancelReq	="OrdCxlReq";
		public final static String ObjOrdCxlRepReq	="OrdCxlRepReq";
		public final static String ObjProfile		="Profile";
		public final static String ObjStringAry		="StrAry";
		public final static String ObjTradeHisReq	="TradeHisReq";
		public final static String ObjUserInfo		="UserInfo";
		
		//public final static String Comment		="Comment";
		//public final static String Description	="Desc";
		public final static String ExecType		="ExecType";
		public final static String Login		="Login";
		public final static String Group		="Grp";
		public final static String MsgType		="MT";
		public final static String ReferenceMsg ="ReferenceMsg";
		//public final static String RejectReason	="RejReason";
		public final static String SrvURL		="SrvURL";
		public final static String Subscribe	="Subscribe";
		public final static String SubType		="SubType";
		public final static String Token		="Tok";
		public final static String TextMsg		="TxtMsg";
	}
	
	public class MSGTYPE {	//all options for name=MsgType
		//xCloudWeb, client, NwsWebAdaptor share the same MsgTypes.
		public final static String Ack			="Ack";
		public final static String ChartHisReq	="ChartHisReq";
		public final static String ChartHisData	="ChartHisData";
		public final static String ExecReport	="ExecRp";
		public final static String FilledTicketReport	="FillTickRp";
		public final static String GetAcctInfo	="GetAcctInfo";
		public final static String GetTickets	="GetTickets";
		public final static String Heartbeat	="HB";
		public final static String Login		="Login";
		public final static String Logout		="Logout";
		public final static String MarketData	="MD";
		public final static String MsgAry		="MsgAry";
		public final static String NetPosReq	="NetPosReq";
		public final static String OpenOrdReq	="OpenOrdReq";
		public final static String OrderRequest	="OrdReq";
		public final static String OrdCancelReq	="OrdCxlReq";
		public final static String OrdCxlRepReq	="OrdCxlRepReq";
		public final static String Quote		="Q";
		public final static String QuoteMaker	="QM";
		public final static String QuoteRequest	="QReq";
		public final static String Reject		="Reject";
		public final static String ReqConfirm	="ReqConfirm";
		public final static String ReqResultRp	="ReqRsltRp";
		public final static String SaveProfile	="SaveProfile";
		public final static String SymListReq	="SymListReq";
		public final static String TradeHisReq	="TradeHisReq";
		public final static String TradeRecord	="TradeRecord";
		public final static String UpdateInfo	="UpdInf";
		public final static String xCloudLogin				="xLogin";
		public final static String xCloudLogout				="xLogout";
		public final static String xCloudUserSubscription	="xUserSubsrpt";
		
		//MsgTypes for xCloudWeb and client only
		public final static String RestResp		="RestResp";
		public final static String Status		="Status";
		
	}
	
	public class UserInfo
	{
		public final static String domain	="domain";   // login
		public final static String login	="login";   // login
		public final static String group	="group";   // group
		public final static String password	="password";// password
		public final static String name		="name";    // name
		public final static String ip		="ip";      // IP address
		public final static String enable	="enable";	// this acct is enable or not
		public final static String enable_change_password="enable_change_password"; // allow to change password
		public final static String enable_read_only	="enable_read_only";   // allow to open/positions (TRUE-may not trade)
		public final static String leverage		="leverage";               // leverage
		public final static String agent_account="agent_account";          // agent account
		public final static String balance		="balance";                // balance
		public final static String credit		="credit";                 // credit
		public final static String prevbalance	="prevbalance";            // previous day balance
		public final static String lastOrdId	="lastOrdId";
		
	};
	
	public class  AcctSetting
	{
		public final static String login			="login";   
		public final static String enable			="enable";  
		public final static String viewonly			="viewonly";
		public final static String maintenMarginRatio="mntnMrgnRt";
		public final static String liquidMarginRatio="liqdMrgnRt";

		public final static String domain			="domain";
		public final static String lastOrdId		="lastOrdId";
	};
	
	public class  QuoteRequest
	{
		public final static String sym				="sym";			// symbol, max length=12.
		public final static String subscription_type="sub_type";	// optional value in SubscriptionType
		public final static String quote_type		="quote_type";	// optional value in QuoteType
		
		public class SubscriptionType {
			//public final static int Snapshot			= 0;	//doesn't support yet
			public final static int SnapshotPlusUpdate	= 1;
			public final static int Unsubscribe			= 2;
		};
		public class QuoteType {
			public final static int L1		= 1;
			public final static int L2		= 2;
			public final static int L1AndL2 = 3;
		};
	};
	
	public class  Quote
	{
		public final static String time="t";	// YYYYMMDD-HH:MM:SS.sss
		public final static String bid="b";		// bid price
		public final static String ask="a";		// ask price
		public final static String sym="s";		// symbol, max length=12.
	};
	
	public class MarketData
	{
		public final static String sym="s";		// symbol, max length=12
		public final static String bid="b";		// Entry array for bid
		public final static String ask="a";		// Entry array for ask
		public final static String time="t";	// GMT0, YYYYMMDD-HH:MM:SS.sss
		public class Entry{
			public final static String price	="p";		// price
			public final static String size		="s";		// size
		}
	};
	
	public class TradeHisReq {
		public final static String login	="login";	// which account you want to query
		public final static String days		="days";	// how many days you want to query
	};
	
	public static class ErrCodeEx{
	
		public final static ErrCodeDef E_ORDREJ_UNK				= new ErrCodeDef(48	,"Reason Unknown"					);
		public final static ErrCodeDef E_ORDREJ_NOTLOGON		= new ErrCodeDef(49	,"Not Logon"						);
		public final static ErrCodeDef E_ORDREJ_DST_NOTAVLB		= new ErrCodeDef(50	,"Destination Not Available"		);
		public final static ErrCodeDef E_ORDREJ_USER_EXCD_PERM	= new ErrCodeDef(51	,"User Exceeding Permission Level"	);
		public final static ErrCodeDef E_ORDREJ_ORD_NOT_FOUND	= new ErrCodeDef(52 ,"Order Not Found"					);
		public final static ErrCodeDef E_ORDREJ_NO_SHARE_TO_SELL= new ErrCodeDef(53 ,"No Shares To Sell"				);
		public final static ErrCodeDef E_ORDREJ_CANT_SHORT_DOWN	= new ErrCodeDef(54 ,"Can’t Short Downtick"				);
		public final static ErrCodeDef E_ORDREJ_STK_UNBORROW	= new ErrCodeDef(55 ,"Stock Unborrowable"				);
		public final static ErrCodeDef E_ORDREJ_TOO_MANY_SHARES	= new ErrCodeDef(56 ,"Too Many Shares"					);
		public final static ErrCodeDef E_ORDREJ_TOO_MANY_POS	= new ErrCodeDef(57 ,"Too Many Positions"				);
		public final static ErrCodeDef E_ORDREJ_QUOTE_NOT_AVLB	= new ErrCodeDef(97 ,"Quote Not Available"				);
		public final static ErrCodeDef E_ORDREJ_NOT_ENGH_BUY_POW= new ErrCodeDef(98 ,"Not Enough Buying Power"			);
		public final static ErrCodeDef E_ORDREJ_REJ_BY_DST		= new ErrCodeDef(99 ,"Rejected By Destination"			);
		public final static ErrCodeDef E_ORDREJ_INVLD_SYM		= new ErrCodeDef(100,"Invalid Symbol"					);
		public final static ErrCodeDef E_ORDREJ_TIME_LMT_VIOLA	= new ErrCodeDef(101,"Time Limit Violation"				);
		public final static ErrCodeDef E_ORDREJ_REJ_BY_ADMIN	= new ErrCodeDef(102,"Rejected By Admin"				);
		public final static ErrCodeDef E_ORDREJ_EXCD_PERM		= new ErrCodeDef(103,"Exceeding Permission Level"		);
		public final static ErrCodeDef E_ORDREJ_POS_HEG_LOCKED	= new ErrCodeDef(104,"Position Is Locked For Hedging"	);
		public final static ErrCodeDef E_ORDREJ_POS_NOT_HEGED	= new ErrCodeDef(105,"Position Not Hedged"				);
		public final static ErrCodeDef E_ORDREJ_INVLD_OPT_TYPE	= new ErrCodeDef(106,"Invalid Option Type"				);
		public final static ErrCodeDef E_ORDREJ_INVLD_STRIKE_PX	= new ErrCodeDef(107,"Invalid Strike Price"				);
		public final static ErrCodeDef E_ORDREJ_WRONG_OPEN_CLS	= new ErrCodeDef(108,"Incorrect To Open Or To Close"	);
		public final static ErrCodeDef E_ORDREJ_BAD_PX			= new ErrCodeDef(109,"Bad Price"						);
		public final static ErrCodeDef E_ORDREJ_IN_PROGRESS		= new ErrCodeDef(110,"Order In Progress"				);
		public final static ErrCodeDef E_ORDREJ_TIME_OUT		= new ErrCodeDef(111,"Timed Out"						);
		public final static ErrCodeDef E_ORDREJ_NO_LIQUID		= new ErrCodeDef(112,"No Liquidity"						);
		public final static ErrCodeDef E_ORDREJ_DUP_ORDID		= new ErrCodeDef(113,"Duplicated Order ID"				);
	}
	
	public static class ChartReq {
		public final static String sym	="sym";	
		public final static String type	="type";	//0=daily, 1=minute
		public final static String days	="days";	//how many days data before the "endTime"
		public final static String reqId="reqId";	//your own unique ID to identify this request, number only.
		public final static String endTime="endTime";	//to (ending at) what time(YYYYMMDD-HH:MM:SS).
	};
	
	public static class CharData {
		public final static String time		="t";	
		public final static String open		="o";	
		public final static String close	="c";
		public final static String high		="h";
		public final static String low		="l";
		public final static String volume	="v";
	};
	
	public static class OrdReq {
		public final static String account		="acct";
		public final static String symbol		="sym";
		public final static String securityType	="secType";
		public final static String side			="side";
		public final static String qty			="qty";
		public final static String price		="px";
		public final static String type			="type";
		public final static String timeInForce	="tif";
		public final static String stopLoss		="sl";
		public final static String takeProfit	="tp";
		public final static String execDestination="execDst";
		public final static String minQty		="minQty";
		
		public final static String qtyRsrv		="qtyRsrv";
		public final static String maxShow		="maxShow";
		public final static String execBroker	="execBrk";
		public final static String execInst		="execInst";
		public final static String price2		="px2";
		public final static String stopPrice	="stopPx";
		public final static String transactTime	="txTime";
		public final static String handlInst	="handlInst";
		public final static String principalAgency	="prnAgc";
		
		public final static String slippage		="slpg";
		
		public final static String clOrdId		="clOrdId";
		
		public final static String ticketType	="tktType";
		public final static String ticketNo		="tktNo";
		public final static String refTicketNo	="refTktNo";
		
	}
	
	public static class ExecReport{
		public final static String account			 ="acct";
		public final static String averagePx		 ="avgPx";
		public final static String clOrdId			 ="clOrdId";
		public final static String commission		 ="commission";
		public final static String conversionRate	 ="convRate";
		public final static String cumQty			 ="cumQty";
		public final static String exedId			 ="execId";
		public final static String execType			 ="execType";
		public final static String leavesQty		 ="leavQty";
		public final static String lastQty			 ="lastQty";
		public final static String lastPx			 ="lastPx";
		public final static String method			 ="method"		;//refer to namespace Method
		public final static String ordStatus		 ="ordStat";
		public final static String ordId			 ="ordId";
		public final static String orgClOrdId		 ="orgClOrdId";
		public final static String ordQty			 ="ordQty";
		public final static String ordType			 ="ordType";
		public final static String ordUser			 ="ordUser";
		public final static String price			 ="px";
		public final static String securityType		 ="secuType";
		public final static String settleDate		 ="settDate";
		public final static String side				 ="side";
		public final static String symbol			 ="sym";
		public final static String tradeDate		 ="trdDate";
		public final static String transactTime		 ="txTime";
		public final static String text				 ="text";
	}
	
	public static class OrdCancel{
		public final static String user				 ="user";
		public final static String orgUser			 ="orgUser";
		public final static String clOrdId			 ="clOrdId";
		public final static String orgClOrdId		 ="orgClOrdId";
		public final static String account			 ="acct";
		public final static String type				 ="type";
		public final static String side				 ="side";
		public final static String qty				 ="qty";
		public final static String symbol			 ="sym";
		public final static String securityType		 ="secuType";
		public final static String execDestination	 ="execDst";
	}
	
	public static class OrdCxlRep{
		public final static String user				 ="user"		;	
		public final static String orgUser			 ="orgUser"		;
        public final static String acct				 ="acct"		;
		public final static String clOrdId			 ="clOrdId"		;
		public final static String orgClOrdId		 ="orgClOrdId"	;
		public final static String type				 ="type"		;
		public final static String side				 ="side"		;
		public final static String qty				 ="qty"			;
		public final static String symbol			 ="sym"			;
		public final static String securityType		 ="secuType"	;
		public final static String cumulativeQty	 ="cumQty"		;
		public final static String price			 ="px"			;
		public final static String stopPrice		 ="stopPx"		;
		public final static String price2			 ="px2"			;
		public final static String timeInForce		 ="tif"			;
		public final static String execInst			 ="execInst"	;
		public final static String handlInst		 ="handInst"	;
		public final static String execDestination	 ="execDst"		;
		public final static String transactTime		 ="txTime"		;
		
        public final static String ticketType	="tktType";
		public final static String ticketNo		="tktNo";
		public final static String refTicketNo	="refTktNo";  
		
		public final static String stopLoss		="sl";
		public final static String takeProfit	="tp";
	}
}
