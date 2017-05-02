/*    */ package com.fortex.lib.http;
/*    */ 
/*    */ import org.glassfish.grizzly.http.server.Request;
/*    */ 
/*    */ public class Util
/*    */ {
/*    */   public static String getRemoteIP(Request paramRequest) {
/*  8 */     if (paramRequest.getHeader("X-Forwarded-For") == null) {
/*  9 */       return paramRequest.getRemoteAddr();
/*    */     }
/* 11 */     return paramRequest.getHeader("X-Forwarded-For");
/*    */   }
/*    */   
/*    */   public static String getRemoteIP(org.glassfish.grizzly.http.HttpRequestPacket paramHttpRequestPacket) {
/* 15 */     if (paramHttpRequestPacket.getHeader("X-Forwarded-For") == null) {
/* 16 */       return paramHttpRequestPacket.getRemoteAddress();
/*    */     }
/* 18 */     return paramHttpRequestPacket.getHeader("X-Forwarded-For");
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\http\Util.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */