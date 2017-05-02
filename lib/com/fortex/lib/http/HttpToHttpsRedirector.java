/*    */ package com.fortex.lib.http;
/*    */ 
/*    */ import org.glassfish.grizzly.http.server.Request;
/*    */ 
/*    */ public class HttpToHttpsRedirector extends org.glassfish.grizzly.http.server.HttpHandler
/*    */ {
/*    */   protected int a;
/*    */   
/*    */   public HttpToHttpsRedirector(int paramInt)
/*    */   {
/* 11 */     this.a = paramInt;
/*    */   }
/*    */   
/*    */   public void service(Request paramRequest, org.glassfish.grizzly.http.server.Response paramResponse) throws Exception
/*    */   {
/* 16 */     String str = "https://" + paramRequest.getServerName();
/* 17 */     if (this.a != 443) {
/* 18 */       str = str + ":" + this.a;
/*    */     }
/* 20 */     str = str + paramRequest.getRequestURI();
/* 21 */     paramResponse.sendRedirect(str);
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\http\HttpToHttpsRedirector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */