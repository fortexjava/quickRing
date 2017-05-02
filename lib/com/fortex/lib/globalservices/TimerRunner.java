/*    */ package com.fortex.lib.globalservices;
/*    */ 
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimerRunner
/*    */   extends TimerTask
/*    */ {
/*    */   private final TimerCallBackInf a;
/*    */   private final int b;
/* 13 */   private final Timer c = new Timer();
/*    */   
/*    */   public TimerRunner(TimerCallBackInf paramTimerCallBackInf, int paramInt) {
/* 16 */     this.a = paramTimerCallBackInf;
/* 17 */     this.b = paramInt;
/*    */   }
/*    */   
/*    */   public void schedule(int paramInt1, int paramInt2) {
/* 21 */     this.c.schedule(this, paramInt1, paramInt2);
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/* 26 */     this.a.onTimer(this.b);
/*    */   }
/*    */   
/*    */   public static abstract interface TimerCallBackInf
/*    */   {
/*    */     public abstract void onTimer(int paramInt);
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\globalservices\TimerRunner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */