/*    */ package com.fortex.lib.connection;
/*    */ 
/*    */ import com.fortex.lib.globalservices.FortexLogger;
/*    */ import com.fortex.lib.globalservices.GlobalRuntime;
import com.fortex.lib.protocol.FXJ_DEF;
/*    */ import com.fortex.lib.protocol.FXJ_DEF.CLIENT_REQUEST_TYPES;
/*    */ import com.fortex.lib.protocol.FortexJSONMsg;
/*    */ import java.io.PrintStream;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class FortexFXJConnectionTcp
/*    */   extends FortexConnectionTcp<FortexJSONMsg>
/*    */ {
/*    */   public Config cfg;
/*    */   
/*    */   public FortexFXJConnectionTcp(FortexConnectionMgrInf paramFortexConnectionMgrInf, Logger paramLogger, String paramString, Config paramConfig)
/*    */   {
/* 18 */     super(paramFortexConnectionMgrInf, paramLogger, paramString, paramConfig);
/* 19 */     this.cfg = paramConfig;
/*    */   }
/*    */   
/*    */   public static boolean initCfg(Config paramConfig, JSONObject paramJSONObject) {
/*    */     try {
/* 24 */       paramConfig.senderId = paramJSONObject.getString("SenderID");
/* 25 */       return FortexConnectionTcp.initCfg(paramConfig, paramJSONObject);
/*    */     } catch (Exception localException) {
/* 27 */       String str = new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage();
/* 28 */       System.out.println(str);
/* 29 */       FortexLogger.defaultLogger.error(str);
/*    */     }
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   public int sendFxjMsg(String paramString1, JSONObject paramJSONObject, String paramString2) {
/* 35 */     return sendFxjMsg(paramString1, paramJSONObject, paramString2, null);
/*    */   }
/*    */   
/*    */   public int sendFxjMsg(String paramString1, JSONObject paramJSONObject, String paramString2, String paramString3)
/*    */   {
/* 40 */     int i = 0;
/*    */     try {
/* 42 */       JSONObject localJSONObject1 = new JSONObject();JSONObject localJSONObject2 = new JSONObject();
/* 43 */       FortexJSONMsg localFortexJSONMsg = new FortexJSONMsg(localJSONObject1);
/* 44 */       localJSONObject1.put("XCloudMsgs", localJSONObject2);
/* 45 */       localJSONObject2.put("SendingTime", GlobalRuntime.getFormattedDateTime(null));
/* 46 */       localJSONObject2.put("SendServer", this.cfg.senderId);
/* 47 */       if ((paramString2 != null) && (!paramString2.isEmpty())) {
/* 48 */         localJSONObject2.put("ClientTokenID", paramString2);
/*    */       }
/* 50 */       if ((paramString3 != null) && (!paramString3.isEmpty())) {
/* 51 */         localJSONObject2.put("ServerTokenID", paramString3);
/*    */       }
/* 53 */       localJSONObject2.put(paramString1, paramJSONObject);
/* 54 */       i = sendMsg(localFortexJSONMsg);
/*    */     } catch (Exception localException) {
/* 56 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*    */     }
/* 58 */     return i;
/*    */   }
/*    */   
/*    */   protected void onConnected()
/*    */   {
/*    */     try {
/* 64 */       JSONObject localJSONObject = new JSONObject();
/* 65 */       localJSONObject.put("xServer", this.cfg.username);
/* 66 */       localJSONObject.put("xCloudPW", this.cfg.password);
/* 67 */       sendFxjMsg(FXJ_DEF.CLIENT_REQUEST_TYPES.getTypeExpression(FXJ_DEF.CLIENT_REQUEST_TYPES.LOGIN), localJSONObject, this.b);
/*    */     }
/*    */     catch (Exception localException) {
/* 70 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*    */     }
/*    */   }
/*    */   
/*    */   protected void onHeartbeatDue()
/*    */   {
/* 76 */     JSONObject localJSONObject = new JSONObject();
/* 77 */     sendFxjMsg(FXJ_DEF.CLIENT_REQUEST_TYPES.getTypeExpression(FXJ_DEF.CLIENT_REQUEST_TYPES.STATUS), localJSONObject, "HeArTbEaT");
/*    */   }
/*    */   
/*    */   public static class Config
/*    */     extends FortexConnection.Config
/*    */   {
/*    */     public String senderId;
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\FortexFXJConnectionTcp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */