/*    */ package com.fortex.lib.connection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.glassfish.grizzly.filterchain.BaseFilter;
/*    */ import org.glassfish.grizzly.filterchain.FilterChainContext;
/*    */ 
/*    */ public class FortexConnectionFilter<msgClass> extends BaseFilter
/*    */ {
/*  9 */   private FortexConnection a = null;
/*    */   
/*    */   public FortexConnectionFilter(FortexConnection paramFortexConnection) {
/* 12 */     this.a = paramFortexConnection;
/*    */   }
/*    */   
/*    */   public org.glassfish.grizzly.filterchain.NextAction handleRead(FilterChainContext paramFilterChainContext)
/*    */     throws IOException
/*    */   {
/* 18 */     Object localObject = paramFilterChainContext.getMessage();
/* 19 */     if (this.a != null) {
/* 20 */       this.a.onMsgRecved(localObject);
/*    */     }
/* 22 */     return paramFilterChainContext.getStopAction();
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\FortexConnectionFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */