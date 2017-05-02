/*     */ package com.fortex.lib.connection;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class SecurityChecker
/*     */ {
/*     */   private final java.util.List<SecurityFilterInf> a;
/*     */   
/*     */   public SecurityChecker()
/*     */   {
/*  12 */     this.a = new java.util.ArrayList();
/*     */   }
/*     */   
/*     */   public boolean checkSecurity(JSONObject paramJSONObject1, JSONObject paramJSONObject2) {
/*  16 */     boolean bool = true;
/*  17 */     for (int i = 0; i < this.a.size(); i++) {
/*  18 */       SecurityFilterInf localSecurityFilterInf = (SecurityFilterInf)this.a.get(i);
/*  19 */       if (!localSecurityFilterInf.check(paramJSONObject1, paramJSONObject2)) {
/*  20 */         bool = false;
/*  21 */         break;
/*     */       }
/*     */     }
/*  24 */     return bool;
/*     */   }
/*     */   
/*     */   public void addSecurityFilter(SecurityFilterInf paramSecurityFilterInf) {
/*  28 */     this.a.add(paramSecurityFilterInf);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class SecurityFilterLoginFailureBlocking
/*     */     implements SecurityChecker.SecurityFilterInf
/*     */   {
/*     */     private final Map<String, LoginFailedUserInfo> a;
/*     */     
/*     */ 
/*     */     public final int maxLoginFailureCnt;
/*     */     
/*     */ 
/*     */     public final long blockingTimeMin;
/*     */     
/*     */ 
/*     */ 
/*     */     public SecurityFilterLoginFailureBlocking(int paramInt, long paramLong)
/*     */     {
/*  48 */       this.maxLoginFailureCnt = paramInt;
/*  49 */       this.blockingTimeMin = paramLong;
/*  50 */       this.a = new java.util.concurrent.ConcurrentHashMap();
/*     */     }
/*     */     
/*     */     protected long getBlocikingDueTime() {
/*  54 */       return new java.util.Date().getTime() + this.blockingTimeMin * 60L * 1000L;
/*     */     }
/*     */     
/*  57 */     public String getUsername(JSONObject paramJSONObject) { return null; }
/*     */     
/*     */ 
/*     */     public void onUserBlocked(long paramLong, JSONObject paramJSONObject) {}
/*     */     
/*     */     public boolean check(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
/*     */     {
/*     */       try
/*     */       {
/*  66 */         String str = getUsername(paramJSONObject1);
/*  67 */         if (str == null) {
/*  68 */           return false;
/*     */         }
/*     */         
/*  71 */         LoginFailedUserInfo localLoginFailedUserInfo = (LoginFailedUserInfo)this.a.get(str);
/*  72 */         if (localLoginFailedUserInfo == null) {
/*  73 */           return true;
/*     */         }
/*     */         
/*  76 */         long l = new java.util.Date().getTime();
/*  77 */         if (localLoginFailedUserInfo.blockingDueTime > 0L) {
/*  78 */           if (localLoginFailedUserInfo.blockingDueTime > l) {
/*  79 */             localLoginFailedUserInfo.blockingDueTime = getBlocikingDueTime();
/*  80 */             onUserBlocked(localLoginFailedUserInfo.blockingDueTime, paramJSONObject2);
/*     */             
/*  82 */             return false;
/*     */           }
/*  84 */           localLoginFailedUserInfo.loginFailureCnt = 0;
/*  85 */           localLoginFailedUserInfo.blockingDueTime = 0L;
/*  86 */           com.fortex.lib.globalservices.FortexLogger.defaultLogger.error("User '" + str + "' is unblocked because time due.");
/*     */         }
/*     */         
/*     */ 
/*  90 */         return true;
/*     */       } catch (Exception localException) {
/*  92 */         com.fortex.lib.globalservices.FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */       }
/*  94 */       return false;
/*     */     }
/*     */     
/*     */     public int onLoginResult(String paramString, boolean paramBoolean)
/*     */     {
/*  99 */       if (paramBoolean) {
/* 100 */         this.a.remove(paramString);
/* 101 */         return this.maxLoginFailureCnt;
/*     */       }
/* 103 */       LoginFailedUserInfo localLoginFailedUserInfo = (LoginFailedUserInfo)this.a.get(paramString);
/* 104 */       if (localLoginFailedUserInfo == null) {
/* 105 */         localLoginFailedUserInfo = new LoginFailedUserInfo();
/* 106 */         this.a.put(paramString, localLoginFailedUserInfo);
/*     */       }
/* 108 */       localLoginFailedUserInfo.loginFailureCnt += 1;
/* 109 */       if (localLoginFailedUserInfo.loginFailureCnt >= this.maxLoginFailureCnt) {
/* 110 */         localLoginFailedUserInfo.blockingDueTime = getBlocikingDueTime();
/* 111 */         com.fortex.lib.globalservices.FortexLogger.defaultLogger.error("User '" + paramString + "'" + " is blocked for " + this.blockingTimeMin + " minutes" + " because continuously " + this.maxLoginFailureCnt + " times login failure.");
/*     */         
/*     */ 
/*     */ 
/* 115 */         return 0;
/*     */       }
/*     */       
/* 118 */       return this.maxLoginFailureCnt - localLoginFailedUserInfo.loginFailureCnt;
/*     */     }
/*     */     
/*     */     public class LoginFailedUserInfo
/*     */     {
/*     */       public String userName;
/*     */       public int loginFailureCnt;
/*     */       public long blockingDueTime;
/*     */       
/*     */       public LoginFailedUserInfo() {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract interface SecurityFilterInf
/*     */   {
/*     */     public abstract boolean check(JSONObject paramJSONObject1, JSONObject paramJSONObject2);
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\SecurityChecker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */