/*    */ package com.fortex.lib.connection;
/*    */ 
/*    */ import org.glassfish.grizzly.filterchain.FilterChainBuilder;
/*    */ 
/*    */ public class xCloudWebSocketAddOn extends org.glassfish.grizzly.websockets.WebSocketAddOn
/*    */ {
/*    */   protected final FlowControlInf a;
/*    */   
/*    */   public xCloudWebSocketAddOn(FlowControlInf paramFlowControlInf)
/*    */   {
/* 11 */     this.a = paramFlowControlInf;
/*    */   }
/*    */   
/*    */ 
/*    */   public void setup(org.glassfish.grizzly.http.server.NetworkListener paramNetworkListener, FilterChainBuilder paramFilterChainBuilder)
/*    */   {
/* 17 */     paramFilterChainBuilder.add(0, new xCloudFlowControlFilter(this.a));
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 27 */     super.setup(paramNetworkListener, paramFilterChainBuilder);
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\xCloudWebSocketAddOn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */