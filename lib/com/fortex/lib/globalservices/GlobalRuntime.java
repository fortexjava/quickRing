/*     */ package com.fortex.lib.globalservices;
/*     */ 
/*     */ import com.fortex.lib.MemDB.MemDBInf;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.security.MessageDigest;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Base64;
/*     */ import java.util.Base64.Encoder;
/*     */ import java.util.Date;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.glassfish.grizzly.http.server.HttpServer;
/*     */ import org.glassfish.grizzly.http.server.NetworkListener;
/*     */ import org.glassfish.grizzly.ssl.SSLContextConfigurator;
/*     */ import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
/*     */ import org.glassfish.grizzly.utils.Charsets;
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
/*     */ public class GlobalRuntime
/*     */ {
/*  50 */   public static boolean fortexServiceReady = false;
/*  51 */   public static MemDBInf memDB = new MemDBImp();
/*     */   
/*     */   public static String getFormattedDateTime(Date paramDate) {
/*  54 */     if (paramDate == null) {
/*  55 */       paramDate = new Date();
/*     */     }
/*  57 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
/*  58 */     return localSimpleDateFormat.format(paramDate);
/*     */   }
/*     */   
/*  61 */   public static String getFormattedDateTimeNoMs(Date paramDate) { if (paramDate == null) {
/*  62 */       paramDate = new Date();
/*     */     }
/*  64 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
/*  65 */     return localSimpleDateFormat.format(paramDate);
/*     */   }
/*     */   
/*     */   public static boolean validateOrder(JSONObject paramJSONObject) {
/*  69 */     return true;
/*     */   }
/*     */   
/*     */   public static void waitForUserToTypeCmdToExit(String paramString) {
/*  73 */     System.out.println("Type \"" + paramString + "\" to shutdown the server...");
/*  74 */     BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(System.in, Charsets.ASCII_CHARSET));
/*     */     try {
/*     */       for (;;) {
/*  77 */         String str = localBufferedReader.readLine();
/*  78 */         if ((str == null) || (paramString.equals(str))) {
/*     */           return;
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     catch (IOException localIOException) {}
/*     */   }
/*     */   
/*     */   public static boolean enableSSL(HttpServer paramHttpServer, String paramString1, String paramString2) {
/*     */     try {
/*  89 */       if (!new File(paramString1).exists()) {
/*  90 */         FortexLogger.defaultLogger.fatal("Cert file \"" + paramString1 + "\" doesn't exist");
/*  91 */         return false;
/*     */       }
/*     */       
/*  94 */       localSSLContextConfigurator = new SSLContextConfigurator();
/*  95 */       localSSLContextConfigurator.setKeyStoreFile(paramString1);
/*  96 */       localSSLContextConfigurator.setKeyStorePass(paramString2);
/*  97 */       boolean bool = localSSLContextConfigurator.validateConfiguration(true);
/*  98 */       if (bool) {
/*  99 */         FortexLogger.defaultLogger.info("Cert file \"" + paramString1 + "\" and password are " + (bool ? "valid" : "invalid"));
/*     */       } else {
/* 101 */         FortexLogger.defaultLogger.warn("Cert file \"" + paramString1 + "\" and password are " + (bool ? "valid" : "invalid"));
/* 102 */         return false;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 107 */       for (NetworkListener localNetworkListener : paramHttpServer.getListeners()) {
/* 108 */         localNetworkListener.setSecure(true);
/* 109 */         SSLEngineConfigurator localSSLEngineConfigurator = new SSLEngineConfigurator(localSSLContextConfigurator).setClientMode(false).setNeedClientAuth(false);
/* 110 */         localSSLEngineConfigurator.isCipherConfigured();
/* 111 */         localNetworkListener.setSSLEngineConfig(localSSLEngineConfigurator);
/*     */       }
/*     */     } catch (Exception localException) { SSLContextConfigurator localSSLContextConfigurator;
/* 114 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/* 115 */       return false;
/*     */     }
/* 117 */     return true;
/*     */   }
/*     */   
/*     */   public static String hashPwd(String paramString) {
/*     */     try {
/* 122 */       MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
/* 123 */       localMessageDigest.update(paramString.getBytes());
/* 124 */       return Base64.getEncoder().encodeToString(localMessageDigest.digest());
/*     */     }
/*     */     catch (Exception localException) {
/* 127 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean evalBool(String paramString) {
/* 133 */     if (paramString == null) {
/* 134 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 138 */     return (paramString.equalsIgnoreCase("true")) || (Integer.parseInt(paramString) > 0) || (paramString.equalsIgnoreCase("yes")) || (paramString.equalsIgnoreCase("y"));
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\globalservices\GlobalRuntime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */