/*     */ package com.fortex.lib.protocol;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class FortexJSONMsg
/*     */ {
/*     */   public Header header;
/*     */   public JSONObject jsMsg;
/*     */   
/*     */   public class Header
/*     */   {
/*  14 */     public int msgLen = 0;
/*  15 */     public String sendingTime = "";
/*  16 */     public String senderSrv = "";
/*  17 */     public String encryption = "";
/*     */     
/*     */     public Header() {}
/*     */   }
/*     */   
/*  22 */   public String msgType = "";
/*     */   
/*     */   public FortexJSONMsg() {
/*  25 */     this.header = new Header();
/*  26 */     this.jsMsg = new JSONObject();
/*     */   }
/*     */   
/*  29 */   public FortexJSONMsg(Header paramHeader) { this.header = paramHeader;
/*  30 */     this.jsMsg = new JSONObject();
/*     */   }
/*     */   
/*  33 */   public FortexJSONMsg(JSONObject paramJSONObject) { this.header = new Header();
/*  34 */     this.jsMsg = paramJSONObject;
/*     */   }
/*     */   
/*  37 */   public FortexJSONMsg(Header paramHeader, JSONObject paramJSONObject) { this.header = paramHeader;
/*  38 */     this.jsMsg = paramJSONObject;
/*     */   }
/*     */   
/*     */   public FortexJSONMsg(String paramString) {
/*     */     try {
/*  43 */       int i = paramString.indexOf(']');
/*  44 */       if ((paramString.charAt(0) == '[') && (i > 0)) {
/*  45 */         this.header = new Header();
/*  46 */         java.util.StringTokenizer localStringTokenizer = new java.util.StringTokenizer(paramString.substring(1, i));
/*  47 */         while (localStringTokenizer.hasMoreTokens()) {
/*  48 */           String str = localStringTokenizer.nextToken();
/*  49 */           if (str.startsWith("BodyLen=")) {
/*  50 */             this.header.msgLen = Integer.parseInt(str.substring(8));
/*  51 */           } else if (str.startsWith("SendingTime=")) {
/*  52 */             this.header.sendingTime = str.substring(12);
/*  53 */           } else if (str.startsWith("SendServer=")) {
/*  54 */             this.header.senderSrv = str.substring(11);
/*  55 */           } else if (str.startsWith("Encryption=")) {
/*  56 */             this.header.encryption = str.substring(11);
/*     */           }
/*     */         }
/*     */         
/*  60 */         int j = paramString.indexOf('{', i);
/*  61 */         if (j > 0) {
/*  62 */           this.jsMsg = new JSONObject(paramString.substring(j));
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {
/*  67 */       com.fortex.lib.globalservices.FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*  68 */       com.fortex.lib.globalservices.FortexLogger.defaultLogger.error("The faulty msg is: [" + paramString + "]");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/*  75 */     String str1 = this.jsMsg.toString();
/*  76 */     String str2 = "[BodyLen=%06d SendingTime=%s SendServer=%s Encryption=%s]%s";
/*     */     
/*     */ 
/*  79 */     int i = "[BodyLen=%06d SendingTime=%s SendServer=%s Encryption=%s]%s".length() - 12 + 6 + this.header.senderSrv.length() + this.header.sendingTime.length() + this.header.encryption.length() + str1.length();
/*     */     
/*  81 */     String str3 = String.format("[BodyLen=%06d SendingTime=%s SendServer=%s Encryption=%s]%s", new Object[] { Integer.valueOf(i), this.header.sendingTime, this.header.senderSrv, this.header.encryption, str1 });
/*  82 */     return str3;
/*     */   }
/*     */   
/*     */   public String getMsgType() {
/*  86 */     if ((this.msgType == null) || (this.msgType.length() == 0)) {
/*     */       try {
/*  88 */         JSONObject localJSONObject = this.jsMsg.getJSONObject("XCloudMsgs");
/*  89 */         if (localJSONObject == null) {
/*  90 */           return "Unknown";
/*     */         }
/*  92 */         Iterator localIterator = localJSONObject.keys();
/*  93 */         int i = 0;
/*     */         
/*  95 */         while ((i == 0) && (localIterator.hasNext())) {
/*  96 */           String str1 = (String)localIterator.next();
/*  97 */           for (String str2 : FXJ_DEF.SERVER_MESSAGE_TYPES.types) {
/*  98 */             if (str2.equals(str1)) {
/*  99 */               i = 1;
/* 100 */               this.msgType = str2;
/* 101 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       } catch (Exception localException) {
/* 106 */         com.fortex.lib.globalservices.FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */       }
/*     */     }
/*     */     
/* 110 */     return this.msgType;
/*     */   }
/*     */   
/*     */   public static java.util.List<JSONObject> serializeJSONObject(JSONObject paramJSONObject) {
/* 114 */     java.util.ArrayList localArrayList = new java.util.ArrayList();
/*     */     try {
/* 116 */       Iterator localIterator = paramJSONObject.keys();
/*     */       
/* 118 */       while (localIterator.hasNext()) {
/* 119 */         String str1 = (String)localIterator.next();
/* 120 */         JSONObject localJSONObject = paramJSONObject.optJSONObject(str1);
/* 121 */         if (localJSONObject != null) {
/* 122 */           localJSONObject.put("35", FXJ_DEF.SERVER_MESSAGE_TYPES.getType(str1));
/* 123 */           localArrayList.add(localJSONObject);
/*     */         } else {
/* 125 */           JSONArray localJSONArray = paramJSONObject.optJSONArray(str1);
/* 126 */           for (int i = 0; i < localJSONArray.length(); i++) {
/* 127 */             localJSONObject = localJSONArray.getJSONObject(i);
/* 128 */             if (localJSONObject != null)
/*     */             {
/* 130 */               String str2 = FIX_DEF.getMsgTypeName(str1);
/* 131 */               localJSONObject.put("35", str2);
/* 132 */               localArrayList.add(localJSONObject);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception localException) {
/* 138 */       com.fortex.lib.globalservices.FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/* 140 */     return localArrayList;
/*     */   }
/*     */   
/*     */   public static int optInt(JSONObject paramJSONObject, String paramString, int paramInt) {
/* 144 */     int i = paramJSONObject.optInt(paramString, paramInt);
/* 145 */     if (i == paramInt) {
/* 146 */       i = paramJSONObject.optInt(FIX_DEF.getTagName(paramString), paramInt);
/*     */     }
/* 148 */     return i;
/*     */   }
/*     */   
/*     */   public static double optDouble(JSONObject paramJSONObject, String paramString, double paramDouble) {
/* 152 */     double d = paramJSONObject.optDouble(paramString, paramDouble);
/* 153 */     if (d == paramDouble) {
/* 154 */       d = paramJSONObject.optDouble(FIX_DEF.getTagName(paramString), paramDouble);
/*     */     }
/* 156 */     return d;
/*     */   }
/*     */   
/*     */   public static String optString(JSONObject paramJSONObject, String paramString1, String paramString2) {
/* 160 */     String str = paramJSONObject.optString(paramString1);
/* 161 */     if ((str == null) || (str.isEmpty())) {
/* 162 */       str = paramJSONObject.optString(FIX_DEF.getTagName(paramString1));
/* 163 */       if (str == null) {
/* 164 */         str = paramString2;
/*     */       }
/*     */     }
/* 167 */     return str;
/*     */   }
/*     */   
/*     */   public static JSONObject optJSONObject(JSONObject paramJSONObject, String paramString) {
/* 171 */     JSONObject localJSONObject = paramJSONObject.optJSONObject(paramString);
/* 172 */     if (localJSONObject == null) {
/* 173 */       localJSONObject = paramJSONObject.optJSONObject(FIX_DEF.getTagName(paramString));
/*     */     }
/* 175 */     return localJSONObject;
/*     */   }
/*     */   
/*     */   public static JSONArray optJSONArray(JSONObject paramJSONObject, String paramString) {
/* 179 */     JSONArray localJSONArray = paramJSONObject.optJSONArray(paramString);
/* 180 */     if (localJSONArray == null) {
/* 181 */       localJSONArray = paramJSONObject.optJSONArray(FIX_DEF.getTagName(paramString));
/*     */     }
/* 183 */     return localJSONArray;
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\protocol\FortexJSONMsg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */