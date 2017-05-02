/*    */ package com.fortex.lib.globalservices;
/*    */ 
/*    */ import java.util.concurrent.ConcurrentMap;
/*    */ 
/*    */ public class TwoTiersSubscriptionMgr
/*    */ {
/*    */   protected final ConcurrentMap<String, SubscriptionMgr.SingleTypeSubscription> a;
/*    */   
/*    */   public TwoTiersSubscriptionMgr()
/*    */   {
/* 11 */     this.a = new java.util.concurrent.ConcurrentHashMap();
/*    */   }
/*    */   
/*    */   public boolean subscribe(SubscriptionMgr.Subscriber paramSubscriber, String paramString1, String paramString2) {
/* 15 */     SubscriptionMgr.SingleTypeSubscription localSingleTypeSubscription = (SubscriptionMgr.SingleTypeSubscription)this.a.get(paramString1);
/* 16 */     if (localSingleTypeSubscription == null) {
/* 17 */       localSingleTypeSubscription = new SubscriptionMgr.SingleTypeSubscription(SubscriptionMgr.Types.GROUP);
/* 18 */       this.a.put(paramString1, localSingleTypeSubscription);
/*    */     }
/* 20 */     return localSingleTypeSubscription.subscribe(paramSubscriber, paramString2);
/*    */   }
/*    */   
/*    */   public boolean unsubscribe(SubscriptionMgr.Subscriber paramSubscriber, String paramString1, String paramString2) {
/* 24 */     SubscriptionMgr.SingleTypeSubscription localSingleTypeSubscription = (SubscriptionMgr.SingleTypeSubscription)this.a.get(paramString1);
/* 25 */     if (localSingleTypeSubscription == null) {
/* 26 */       return true;
/*    */     }
/* 28 */     return localSingleTypeSubscription.unsubscribe(paramSubscriber, paramString2);
/*    */   }
/*    */   
/*    */   public java.util.Set<SubscriptionMgr.Subscriber> getSubscriberList(String paramString1, String paramString2) {
/* 32 */     SubscriptionMgr.SingleTypeSubscription localSingleTypeSubscription = (SubscriptionMgr.SingleTypeSubscription)this.a.get(paramString1);
/* 33 */     if (localSingleTypeSubscription == null) {
/* 34 */       return null;
/*    */     }
/* 36 */     return localSingleTypeSubscription.getSubscriberList(paramString2);
/*    */   }
/*    */   
/*    */   public java.util.Set<String> getTopTierTargetList(String paramString) {
/* 40 */     return this.a.keySet();
/*    */   }
/*    */   
/*    */   public SubscriptionMgr.SingleTypeSubscription getTopTierMgr(String paramString) {
/* 44 */     return (SubscriptionMgr.SingleTypeSubscription)this.a.get(paramString);
/*    */   }
/*    */   
/*    */   public boolean removeTarget(String paramString1, String paramString2) {
/* 48 */     SubscriptionMgr.SingleTypeSubscription localSingleTypeSubscription = (SubscriptionMgr.SingleTypeSubscription)this.a.get(paramString1);
/* 49 */     if (localSingleTypeSubscription == null) {
/* 50 */       return true;
/*    */     }
/* 52 */     return localSingleTypeSubscription.removeTarget(paramString2);
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\globalservices\TwoTiersSubscriptionMgr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */