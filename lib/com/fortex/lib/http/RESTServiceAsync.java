/*     */ package com.fortex.lib.http;
/*     */ 
/*     */ import com.fortex.lib.globalservices.FortexLogger;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.glassfish.grizzly.ReadHandler;
/*     */ import org.glassfish.grizzly.http.Method;
/*     */ import org.glassfish.grizzly.http.io.NIOReader;
/*     */ import org.glassfish.grizzly.http.server.Request;
/*     */ import org.glassfish.grizzly.http.server.Response;
/*     */ import org.glassfish.grizzly.http.util.Header;
/*     */ import org.glassfish.grizzly.http.util.HttpStatus;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class RESTServiceAsync extends org.glassfish.grizzly.http.server.HttpHandler
/*     */ {
/*     */   public RESTServiceHandlerInf sessCtrller;
/*     */   protected final String a;
/*     */   protected final int b;
/*     */   protected final int c;
/*     */   
/*     */   public static class PendingRestReqInfo
/*     */   {
/*     */     public final RESTServiceAsync restSvs;
/*     */     public final String tok;
/*     */     public final Request req;
/*     */     public final Response resp;
/*     */     public final String msgType;
/*     */     public final JSONObject orgMsg;
/*     */     public final long expiryTime;
/*     */     
/*     */     public PendingRestReqInfo(RESTServiceAsync paramRESTServiceAsync, String paramString1, Request paramRequest, Response paramResponse, String paramString2, JSONObject paramJSONObject, long paramLong)
/*     */     {
/*  37 */       this.restSvs = paramRESTServiceAsync;
/*  38 */       this.tok = paramString1;
/*  39 */       this.req = paramRequest;
/*  40 */       this.resp = paramResponse;
/*  41 */       this.msgType = paramString2;
/*  42 */       this.orgMsg = paramJSONObject;
/*  43 */       this.expiryTime = paramLong;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  51 */   protected final Map d = new HashMap();
/*     */   protected final boolean e;
/*     */   protected final java.util.concurrent.ConcurrentMap<String, PendingRestReqInfo> f;
/*     */   
/*     */   public RESTServiceAsync(String paramString, int paramInt, JSONArray paramJSONArray) {
/*  56 */     this.a = paramString;
/*  57 */     this.e = this.a.equals("/api");
/*  58 */     this.b = (paramString.length() + 1);
/*  59 */     this.c = (paramInt > 0 ? paramInt : 65536);
/*  60 */     this.f = new ConcurrentHashMap();
/*     */     
/*     */ 
/*  63 */     for (int i = 0; (paramJSONArray != null) && (i < paramJSONArray.length()); i++) {
/*  64 */       String str = paramJSONArray.optString(i);
/*  65 */       if (str != null) {
/*  66 */         str = str.trim();
/*     */       }
/*  68 */       this.d.put(str, "");
/*     */     }
/*     */   }
/*     */   
/*     */   public String getServingURL() {
/*  73 */     return this.a;
/*     */   }
/*     */   
/*     */   public void service(final Request paramRequest, final Response paramResponse) throws Exception
/*     */   {
/*  78 */     paramResponse.suspend();
/*  79 */     final NIOReader localNIOReader = paramRequest.getNIOReader();
/*  80 */     final RESTServiceAsync localRESTServiceAsync = this;
/*     */     
/*  82 */     localNIOReader.notifyAvailable(new ReadHandler()
/*     */     {
/*     */       StringBuffer a;
/*     */       
/*     */ 
/*     */       char[] b;
/*     */       
/*     */ 
/*     */       protected int readData(NIOReader paramAnonymousNIOReader, char[] paramAnonymousArrayOfChar, StringBuffer paramAnonymousStringBuffer)
/*     */       {
/*  92 */         int i = 0;
/*     */         try {
/*  94 */           while (paramAnonymousNIOReader.isReady()) {
/*  95 */             int j = paramAnonymousNIOReader.read(paramAnonymousArrayOfChar);
/*  96 */             if (j <= 0) {
/*     */               break;
/*     */             }
/*  99 */             i += j;
/* 100 */             if (paramAnonymousStringBuffer.length() + j > RESTServiceAsync.this.c) {
/* 101 */               FortexLogger.defaultLogger.warn(paramRequest.getMethod().getMethodString() + " request from " + 
/* 102 */                 Util.getRemoteIP(paramRequest) + " with " + (paramAnonymousStringBuffer.length() + j) + " bytes payload exceeds the limit " + RESTServiceAsync.this.c + ", first 256 bytes=" + paramAnonymousStringBuffer
/*     */                 
/* 104 */                 .substring(0, 256));
/*     */               
/*     */ 
/* 107 */               i = -1;
/* 108 */               paramResponse.setError();
/* 109 */               paramResponse.setStatus(HttpStatus.REQUEST_ENTITY_TOO_LARGE_413);
/* 110 */               paramResponse.setDetailMessage("request's payload is too long, max = " + RESTServiceAsync.this.c);
/*     */               
/* 112 */               break;
/*     */             }
/* 114 */             paramAnonymousStringBuffer.append(paramAnonymousArrayOfChar, 0, j);
/*     */           }
/*     */         }
/*     */         catch (Exception localException) {
/* 118 */           i = -1;
/* 119 */           FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */         }
/*     */         
/* 122 */         return i;
/*     */       }
/*     */       
/*     */       public void onDataAvailable() throws Exception
/*     */       {
/* 127 */         if (readData(localNIOReader, this.b, this.a) < 0) {
/* 128 */           paramResponse.resume();
/*     */         } else {
/* 130 */           localNIOReader.notifyAvailable(this);
/*     */         }
/*     */       }
/*     */       
/*     */       public void onError(Throwable paramAnonymousThrowable)
/*     */       {
/* 136 */         paramResponse.resume();
/*     */       }
/*     */       
/*     */       public void onAllDataRead() throws Exception
/*     */       {
/*     */         try {
/* 142 */           Method localMethod = paramRequest.getMethod();
/* 143 */           String str1 = "";
/* 144 */           String str2; if ((localMethod == Method.GET) || (localMethod == Method.DELETE)) {
/* 145 */             str2 = paramRequest.getDecodedRequestURI();
/* 146 */             str1 = str2.substring(RESTServiceAsync.this.b);
/* 147 */           } else if ((localMethod == Method.POST) || (localMethod == Method.PUT)) {
/* 148 */             if (readData(localNIOReader, this.b, this.a) < 0) {
/* 149 */               paramResponse.resume();
/* 150 */               return;
/*     */             }
/* 152 */             str1 = this.a.toString();
/*     */ 
/*     */           }
/* 155 */           else if (localMethod == Method.OPTIONS) {
/* 156 */             str2 = paramRequest.getHeader("Origin");
/* 157 */             if (RESTServiceAsync.this.d.containsKey(str2)) {
/* 158 */               String str3 = paramRequest.getHeader("Access-Control-Request-Method");
/* 159 */               paramResponse.setHeader("Access-Control-Allow-Methods", str3);
/*     */               
/* 161 */               String str4 = paramRequest.getHeader("Access-Control-Request-Headers");
/* 162 */               if ((str4 != null) && (str4.length() > 0)) {
/* 163 */                 paramResponse.setHeader("Access-Control-Allow-Headers", str4);
/*     */               }
/*     */               
/* 166 */               paramResponse.setStatus(HttpStatus.OK_200);
/*     */             } else {
/* 168 */               FortexLogger.defaultLogger.warn("CORS from '" + str2 + "' is not allowed.");
/* 169 */               paramResponse.setStatus(HttpStatus.UNAUTHORIZED_401);
/*     */             }
/*     */             
/* 172 */             RESTServiceAsync.this.onRestResponse(paramRequest, paramResponse, "");
/* 173 */             return;
/*     */           }
/* 175 */           RESTServiceAsync.this.sessCtrller.onRestMsg(localRESTServiceAsync, paramRequest, paramResponse, str1);
/*     */         } catch (Exception localException) {
/* 177 */           FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void onRestResponse(Request paramRequest, Response paramResponse, String paramString)
/*     */   {
/*     */     try {
/* 186 */       String str = paramRequest.getHeader("Origin");
/* 187 */       if (this.d.containsKey(str)) {
/* 188 */         paramResponse.setHeader("Access-Control-Allow-Origin", str);
/*     */       }
/*     */       
/* 191 */       paramResponse.setHeader(Header.ContentType, "application/json");
/* 192 */       paramResponse.getNIOWriter().write(paramString);
/*     */     } catch (Exception localException) {
/* 194 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/* 196 */     paramResponse.resume();
/*     */   }
/*     */   
/*     */   public boolean isAPI() {
/* 200 */     return this.e;
/*     */   }
/*     */   
/*     */   public static abstract interface RESTServiceHandlerInf
/*     */   {
/*     */     public abstract void onRestMsg(RESTServiceAsync paramRESTServiceAsync, Request paramRequest, Response paramResponse, String paramString);
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\http\RESTServiceAsync.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */