/*     */ package com.fortex.lib.protocol;
/*     */ 
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class ErrDef
/*     */ {
/*     */   public static class JsonTag
/*     */   {
/*     */     public static final String objErrInfo = "ErrInfo";
/*     */     public static final String code = "code";
/*     */     public static final String desc = "desc";
/*     */     public static final String hint = "hint";
/*     */   }
/*     */   
/*     */   public static class ErrInfo extends ErrDef.ErrCodeDef {
/*     */     public String hint;
/*     */     
/*     */     public ErrInfo() {
/*  19 */       this.hint = "";
/*     */     }
/*     */     
/*     */     public ErrInfo(int paramInt, String paramString) {
/*  23 */       super(paramString);
/*  24 */       this.hint = "";
/*     */     }
/*     */     
/*     */     public ErrInfo(int paramInt, String paramString1, String paramString2) {
/*  28 */       super(paramString1);
/*  29 */       this.hint = paramString2;
/*     */     }
/*     */     
/*     */     public ErrInfo(ErrDef.ErrCodeDef paramErrCodeDef, String paramString) {
/*  33 */       this.code = paramErrCodeDef.code;
/*  34 */       this.desc = paramErrCodeDef.desc;
/*  35 */       this.hint = paramString;
/*     */     }
/*     */     
/*     */     public ErrInfo SetVal(ErrDef.ErrCodeDef paramErrCodeDef, String paramString) {
/*  39 */       this.code = paramErrCodeDef.code;
/*  40 */       this.desc = paramErrCodeDef.desc;
/*  41 */       this.hint = paramString;
/*  42 */       return this;
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/*  47 */       StringBuilder localStringBuilder = new StringBuilder();
/*  48 */       localStringBuilder.append("code").append("=").append(this.code);
/*  49 */       localStringBuilder.append(", ").append("desc").append("=").append(this.desc);
/*  50 */       if (this.hint.length() > 0) {
/*  51 */         localStringBuilder.append(", ").append("hint").append("=").append(this.hint);
/*     */       }
/*  53 */       return localStringBuilder.toString();
/*     */     }
/*     */     
/*     */     public JSONObject toJson() {
/*     */       try {
/*  58 */         JSONObject localJSONObject = new JSONObject();
/*  59 */         localJSONObject.put("code", this.code);
/*  60 */         localJSONObject.put("desc", this.desc);
/*  61 */         if ((this.hint != null) && (this.hint.length() > 0)) {
/*  62 */           localJSONObject.put("hint", this.hint);
/*     */         }
/*  64 */         return localJSONObject;
/*     */       } catch (Exception localException) {
/*  66 */         if (com.fortex.lib.globalservices.FortexLogger.defaultLogger != null) {
/*  67 */           com.fortex.lib.globalservices.FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */         }
/*     */       }
/*  70 */       return null;
/*     */     }
/*     */     
/*     */     public boolean isOk() {
/*  74 */       return this.code == ErrDef.ErrCode.E_OK.code;
/*     */     }
/*     */     
/*     */     public void reset() {
/*  78 */       this.code = ErrDef.ErrCode.E_OK.code;
/*  79 */       this.desc = ErrDef.ErrCode.E_OK.desc;
/*  80 */       this.hint = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ErrCodeDef
/*     */   {
/*     */     public int code;
/*     */     public String desc;
/*     */     
/*     */     public ErrCodeDef() {
/*  90 */       this.code = ErrDef.ErrCode.E_OK.code;
/*  91 */       this.desc = ErrDef.ErrCode.E_OK.desc;
/*     */     }
/*     */     
/*     */     public ErrCodeDef(int paramInt, String paramString) {
/*  95 */       this.code = paramInt;
/*  96 */       this.desc = paramString;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ErrCode
/*     */   {
/* 102 */     public static final ErrDef.ErrCodeDef E_OK = new ErrDef.ErrCodeDef(0, "Success.");
/* 103 */     public static final ErrDef.ErrCodeDef E_UNKNOWN_ERR = new ErrDef.ErrCodeDef(1, "Unknown error.");
/* 104 */     public static final ErrDef.ErrCodeDef E_INTERNALERR = new ErrDef.ErrCodeDef(2, "Server internal error.");
/* 105 */     public static final ErrDef.ErrCodeDef E_NOTREADY = new ErrDef.ErrCodeDef(3, "Server is not ready.");
/* 106 */     public static final ErrDef.ErrCodeDef E_EXCEPTION = new ErrDef.ErrCodeDef(4, "Exception occurred.");
/* 107 */     public static final ErrDef.ErrCodeDef E_UNSUPT_MSGTYPE = new ErrDef.ErrCodeDef(5, "Unsupported message type.");
/* 108 */     public static final ErrDef.ErrCodeDef E_MSG_BROKEN = new ErrDef.ErrCodeDef(6, "Message is broken.");
/* 109 */     public static final ErrDef.ErrCodeDef E_MSG_WRONGVALUE = new ErrDef.ErrCodeDef(7, "Message has wrong value or missing something.");
/* 110 */     public static final ErrDef.ErrCodeDef E_PEM_VIOLATION = new ErrDef.ErrCodeDef(8, "Permission violation.");
/* 111 */     public static final ErrDef.ErrCodeDef E_TIMEOUT = new ErrDef.ErrCodeDef(9, "Server timeout.");
/* 112 */     public static final ErrDef.ErrCodeDef E_USER_TEMPBLOCK = new ErrDef.ErrCodeDef(10, "User is temporary blocked for security reason, please try again later.");
/* 113 */     public static final ErrDef.ErrCodeDef E_USER_PERMBLOCK = new ErrDef.ErrCodeDef(11, "User is permanently blocked for security reason, please contact customer service.");
/* 114 */     public static final ErrDef.ErrCodeDef E_DISCONNECTED = new ErrDef.ErrCodeDef(12, "The connection is disconnected.");
/* 115 */     public static final ErrDef.ErrCodeDef E_PWDISCHNAGED = new ErrDef.ErrCodeDef(13, "Password has been changed.");
/* 116 */     public static final ErrDef.ErrCodeDef E_USERISDELETED = new ErrDef.ErrCodeDef(14, "This user has been deleted.");
/* 117 */     public static final ErrDef.ErrCodeDef E_DISABLED_ACCOUNT = new ErrDef.ErrCodeDef(15, "This account is disabled.");
/* 118 */     public static final ErrDef.ErrCodeDef E_INVLD_LOGINPWD = new ErrDef.ErrCodeDef(16, "Invalid login or password (case sensitive).");
/* 119 */     public static final ErrDef.ErrCodeDef E_INVLD_SESSTOK = new ErrDef.ErrCodeDef(17, "Invalid session token.");
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\protocol\ErrDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */