/*    */ package com.fortex.lib.connection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.glassfish.grizzly.filterchain.FilterChainContext;
/*    */ import org.glassfish.grizzly.filterchain.NextAction;
/*    */ 
/*    */ public class xCloudFlowControlFilter extends org.glassfish.grizzly.filterchain.BaseFilter
/*    */ {
/*    */   protected final FlowControlInf a;
/*    */   
/*    */   public xCloudFlowControlFilter(FlowControlInf paramFlowControlInf)
/*    */   {
/* 13 */     this.a = paramFlowControlInf;
/*    */   }
/*    */   
/*    */   public NextAction handleAccept(FilterChainContext paramFilterChainContext)
/*    */     throws IOException
/*    */   {
/* 19 */     if ((this.a != null) && (!this.a.handleAcceptFilter(paramFilterChainContext))) {
/* 20 */       org.glassfish.grizzly.Connection localConnection = paramFilterChainContext.getConnection();
/* 21 */       if (localConnection != null)
/*    */       {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 27 */         localConnection.close();
/*    */       }
/* 29 */       return paramFilterChainContext.getStopAction();
/*    */     }
/* 31 */     return paramFilterChainContext.getInvokeAction();
/*    */   }
/*    */   
/*    */   public NextAction handleClose(FilterChainContext paramFilterChainContext) throws IOException
/*    */   {
/* 36 */     if (this.a != null) {
/* 37 */       this.a.handleCloseFilter(paramFilterChainContext);
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 47 */     return paramFilterChainContext.getInvokeAction();
/*    */   }
/*    */   
/*    */   public NextAction handleRead(FilterChainContext paramFilterChainContext) throws IOException
/*    */   {
/* 52 */     if (this.a != null) {
/* 53 */       this.a.handleRead(paramFilterChainContext);
/*    */     }
/* 55 */     return paramFilterChainContext.getInvokeAction();
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\xCloudFlowControlFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */