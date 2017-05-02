/*    */ package com.fortex.lib.globalservices;
/*    */ 
/*    */ import java.io.File;
/*    */ import org.apache.log4j.FileAppender;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.log4j.rolling.RollingFileAppender;
/*    */ import org.apache.log4j.rolling.TimeBasedRollingPolicy;
/*    */ 
/*    */ public class FortexLogger
/*    */ {
/*    */   public static Logger defaultLogger;
/*    */   
/*    */   public static class CustomHeaderPatternLayout extends org.apache.log4j.PatternLayout
/*    */   {
/*    */     protected final String a;
/* 16 */     protected FileAppender b = null;
/*    */     
/*    */     CustomHeaderPatternLayout(String paramString) {
/* 19 */       this.a = paramString;
/*    */     }
/*    */     
/*    */     public String getHeader() {
/* 23 */       if (this.b != null) {
/* 24 */         File localFile = new File(this.b.getFile());
/* 25 */         if (localFile.length() == 0L) {
/* 26 */           return this.a;
/*    */         }
/*    */       }
/* 29 */       return null;
/*    */     }
/*    */     
/* 32 */     public void SetAppender(FileAppender paramFileAppender) { this.b = paramFileAppender; }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static boolean initDefaultLogger(String paramString1, String paramString2)
/*    */   {
/* 39 */     org.apache.log4j.PropertyConfigurator.configure(paramString1);
/* 40 */     defaultLogger = Logger.getLogger(paramString2);
/* 41 */     return defaultLogger != null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static Logger createCommonLogger(String paramString1, String paramString2, String paramString3, String paramString4)
/*    */   {
/* 52 */     return createCommonLogger(paramString1, ".log", paramString2, paramString3, paramString4);
/*    */   }
/*    */   
/*    */   public static Logger createCommonLogger(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
/*    */     try {
/* 57 */       Logger localLogger = Logger.getLogger(paramString1);
/* 58 */       localLogger.removeAllAppenders();
/*    */       
/* 60 */       CustomHeaderPatternLayout localCustomHeaderPatternLayout = new CustomHeaderPatternLayout(paramString3);
/* 61 */       if (paramString4 == null) {
/* 62 */         paramString4 = "[%d{yyyyMMdd-HH:mm:ss.SSS}] %m%n";
/*    */       }
/* 64 */       localCustomHeaderPatternLayout.setConversionPattern(paramString4);
/*    */       
/* 66 */       TimeBasedRollingPolicy localTimeBasedRollingPolicy = new TimeBasedRollingPolicy();
/* 67 */       localTimeBasedRollingPolicy.setFileNamePattern("./log/" + paramString1 + "_%d{" + paramString5 + "}" + paramString2);
/*    */       
/* 69 */       RollingFileAppender localRollingFileAppender = new RollingFileAppender();
/* 70 */       localCustomHeaderPatternLayout.SetAppender(localRollingFileAppender);
/*    */       
/* 72 */       localRollingFileAppender.setRollingPolicy(localTimeBasedRollingPolicy);
/* 73 */       localRollingFileAppender.setLayout(localCustomHeaderPatternLayout);
/* 74 */       localRollingFileAppender.activateOptions();
/*    */       
/* 76 */       localLogger.addAppender(localRollingFileAppender);
/* 77 */       localLogger.setAdditivity(false);
/* 78 */       localLogger.setLevel(org.apache.log4j.Level.DEBUG);
/*    */       
/* 80 */       return localLogger;
/*    */     } catch (Exception localException) {
/* 82 */       defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*    */     }
/* 84 */     return null;
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\globalservices\FortexLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */