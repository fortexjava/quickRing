/*     */ package com.fortex.lib.connection;
/*     */ 
/*     */ import com.fortex.lib.globalservices.FortexLogger;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.glassfish.grizzly.CloseListener;
/*     */ import org.glassfish.grizzly.Closeable;
/*     */ import org.glassfish.grizzly.ICloseType;
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
/*     */ 
/*     */ public abstract class FortexConnection<msgClass>
/*     */   implements CloseListener
/*     */ {
/*  28 */   protected FortexConnectionMgrInf<msgClass> a = null;
/*  29 */   protected String b = "";
/*  30 */   protected Logger c = null;
/*  31 */   public long lastMessageRecvTime = 0L;
/*     */   public Config cfg;
/*  33 */   public boolean loginSuccess = false;
/*     */   
/*     */   public FortexConnection(FortexConnectionMgrInf<msgClass> paramFortexConnectionMgrInf, Logger paramLogger, String paramString, Config paramConfig) {
/*  36 */     this.a = paramFortexConnectionMgrInf;
/*  37 */     this.c = paramLogger;
/*  38 */     this.b = paramString;
/*  39 */     this.cfg = paramConfig;
/*     */     
/*  41 */     if ((paramLogger == null) && ((paramConfig.enableInLog) || (paramConfig.enableOutLog))) {
/*  42 */       paramLogger = FortexLogger.createCommonLogger("io_" + paramString, "//comment: this is the in/out message log for this application and " + paramString + "@" + paramConfig.ip + "@" + paramConfig.port + "\r\n", "[%d{yyyyMMdd-HH:mm:ss.SSS}] %m%n", "yyyyMMdd");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  47 */     this.c = paramLogger;
/*     */   }
/*     */   
/*     */   public static boolean initCfg(Config paramConfig, JSONObject paramJSONObject) {
/*     */     try {
/*  52 */       paramConfig.ip = paramJSONObject.getString("IP");
/*  53 */       paramConfig.port = paramJSONObject.getInt("Port");
/*  54 */       paramConfig.username = paramJSONObject.getString("Username");
/*  55 */       paramConfig.password = paramJSONObject.getString("Password");
/*  56 */       paramConfig.enableSSL = paramJSONObject.optBoolean("EnableSSL");
/*  57 */       paramConfig.enableInLog = paramJSONObject.optBoolean("EnableInMsgLog");
/*  58 */       paramConfig.enableOutLog = paramJSONObject.optBoolean("EnableOutMsgLog");
/*  59 */       paramConfig.reconnectIntvMs = paramJSONObject.optInt("ReconnectIntvMS");
/*  60 */       paramConfig.loginTimeoutMs = paramJSONObject.optInt("LoginTimeoutMs");
/*  61 */       return true;
/*     */     } catch (Exception localException) {
/*  63 */       String str = new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage();
/*  64 */       System.out.println(str);
/*  65 */       if (FortexLogger.defaultLogger != null) {
/*  66 */         FortexLogger.defaultLogger.error(str);
/*     */       }
/*     */     }
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  73 */     return this.b;
/*     */   }
/*     */   
/*  76 */   public FortexConnectionMgrInf<msgClass> getManager() { return this.a; }
/*     */   
/*     */   public boolean init(String paramString1, String paramString2) {
/*  79 */     return false;
/*     */   }
/*     */   
/*  82 */   public int connect(Config paramConfig) { return 0; }
/*     */   
/*     */   public int disconnect() {
/*  85 */     return 0;
/*     */   }
/*     */   
/*     */   public void onClosed(Closeable paramCloseable, ICloseType paramICloseType)
/*     */   {
/*     */     try {
/*  91 */       this.lastMessageRecvTime = 0L;
/*  92 */       this.loginSuccess = false;
/*  93 */       if (FortexLogger.defaultLogger != null) {
/*  94 */         FortexLogger.defaultLogger.info(this.b + "@" + this.cfg.ip + "@" + this.cfg.port + " is closed.");
/*     */       }
/*  96 */       if (this.a != null) {
/*  97 */         this.a.onClosed(this);
/*     */       }
/*     */     } catch (Exception localException) {
/* 100 */       if (FortexLogger.defaultLogger != null) {
/* 101 */         FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isConnected() {
/* 107 */     return false;
/*     */   }
/*     */   
/* 110 */   public int logout() { return 0; }
/*     */   
/*     */ 
/* 113 */   public int sendMsg(msgClass parammsgClass) { return 0; }
/*     */   
/*     */   public int onMsgRecved(msgClass parammsgClass) {
/* 116 */     this.lastMessageRecvTime = new Date().getTime();
/* 117 */     if (this.a != null) {
/* 118 */       return this.a.processFortexConnectionMsg(this, parammsgClass);
/*     */     }
/* 120 */     return 0;
/*     */   }
/*     */   
/*     */   public void logInMsg(String paramString) {
/* 124 */     if (this.c != null)
/* 125 */       this.c.info("recv:" + paramString);
/*     */   }
/*     */   
/*     */   public void logOutMsg(String paramString) {
/* 129 */     if (this.c != null) {
/* 130 */       this.c.info("send:" + paramString);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Config
/*     */   {
/*     */     public String ip;
/*     */     public int port;
/*     */     public String username;
/*     */     public String password;
/*     */     public boolean enableSSL;
/*     */     public boolean enableInLog;
/*     */     public boolean enableOutLog;
/*     */     public int reconnectIntvMs;
/*     */     public int reconnectDueMs;
/*     */     public int loginTimeoutMs;
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\FortexConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */