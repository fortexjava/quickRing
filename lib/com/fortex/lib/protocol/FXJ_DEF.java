/*     */ package com.fortex.lib.protocol;
/*     */ 
/*     */ import com.fortex.lib.globalservices.FortexLogger;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FXJ_DEF
/*     */ {
/*     */   public static enum CLIENT_REQUEST_TYPES
/*     */   {
/*  24 */     private static final String[] a = { "XLogin", "XSubscriptionRequests", "XMarketDataRequests", "XQMRequests", "XOrderRequest", "XAdminRequest", "XChartRequest", "XNewsRequest", "XStatusRequest" };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private CLIENT_REQUEST_TYPES() {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public static String getTypeExpression(CLIENT_REQUEST_TYPES paramCLIENT_REQUEST_TYPES)
/*     */     {
/*  36 */       return a[paramCLIENT_REQUEST_TYPES.ordinal()];
/*     */     }
/*     */     
/*     */     public static CLIENT_REQUEST_TYPES getType(String paramString) {
/*  40 */       for (CLIENT_REQUEST_TYPES localCLIENT_REQUEST_TYPES : ) {
/*  41 */         if (a[localCLIENT_REQUEST_TYPES.ordinal()].equalsIgnoreCase(paramString))
/*  42 */           return localCLIENT_REQUEST_TYPES;
/*     */       }
/*  44 */       return UNKNOWN;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public class CLIENT_SUBSCRIPTION_TYPES
/*     */   {
/*     */     public static final int iJOURNAL = 1;
/*     */     
/*     */     public static final int iCHANGED = 2;
/*     */     
/*     */     public static final int iCACHE = 4;
/*     */     
/*     */     public static final int iLOGIN_SETTINGS = 8;
/*     */     
/*     */     public static final int iTOTALS = 16;
/*     */     
/*     */     public static final int iRECALL = 32;
/*     */     
/*     */     public static final int iSYSTEM_SETTINGS = 64;
/*     */     
/*     */     public static final String sJOURNAL = "JOURNAL";
/*     */     
/*     */     public static final String sCHANGED = "CHANGED";
/*     */     
/*     */     public static final String sCACHE = "CACHE";
/*     */     
/*     */     public static final String sLOGIN_SETTINGS = "LOGIN_SETTINGS";
/*     */     
/*     */     public static final String sTOTALS = "TOTALS";
/*     */     
/*     */     public static final String sRECALL = "RECALL";
/*     */     
/*     */     public static final String sSYS_SETTINGS = "SYSTEMS_SETTINGS";
/*     */     
/*     */     public CLIENT_SUBSCRIPTION_TYPES() {}
/*     */   }
/*     */   
/*     */   public static enum SERVER_MESSAGE_TYPES
/*     */   {
/*  84 */     public static final String[] types = { "XCloudStatus", "XQuoteMulticast", "XJournalMessages", "XDataReplication", "XAccountSettings", "XDataChanges", "XDataCacheUpdates", "XMessageRecall", "XChartData", "XMarketData", "XAdminMessage", "XQMSettings", "XStatusResponse", "XChartMulticast", "XSystemsSettings", "Unknown" };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private SERVER_MESSAGE_TYPES() {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public static String getTypeExpression(SERVER_MESSAGE_TYPES paramSERVER_MESSAGE_TYPES)
/*     */     {
/* 103 */       return types[paramSERVER_MESSAGE_TYPES.ordinal()];
/*     */     }
/*     */     
/*     */     public static SERVER_MESSAGE_TYPES getType(String paramString) {
/* 107 */       for (SERVER_MESSAGE_TYPES localSERVER_MESSAGE_TYPES : ) {
/* 108 */         if (types[localSERVER_MESSAGE_TYPES.ordinal()].equalsIgnoreCase(paramString))
/* 109 */           return localSERVER_MESSAGE_TYPES;
/*     */       }
/* 111 */       return UNKNOWN;
/*     */     }
/*     */   }
/*     */   
/*     */   public static JSONObject createFqsMdSubscriptionMsg(String paramString1, String paramString2)
/*     */   {
/*     */     try {
/* 118 */       JSONObject localJSONObject1 = new JSONObject();JSONObject localJSONObject2 = new JSONObject();
/* 119 */       JSONArray localJSONArray = new JSONArray();
/* 120 */       localJSONArray.put(localJSONObject2);
/* 121 */       localJSONObject1.put("263", paramString2);
/* 122 */       localJSONObject1.put("264", String.valueOf(0));
/* 123 */       localJSONObject1.put("xSymbols", localJSONArray);
/* 124 */       localJSONObject2.put("55", paramString1);
/*     */       
/* 126 */       return localJSONObject1;
/*     */     } catch (Exception localException) {
/* 128 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/* 130 */     return null;
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\protocol\FXJ_DEF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */