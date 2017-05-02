/*     */ package com.fortex.lib.globalservices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class SubscriptionMgr
/*     */ {
/*  15 */   protected final Map<Types, SingleTypeSubscription> a = new EnumMap(Types.class);
/*     */   
/*     */   public SubscriptionMgr() {
/*  18 */     for (Types localTypes : Types.values()) {
/*  19 */       this.a.put(localTypes, new SingleTypeSubscription(localTypes));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean subscribe(Subscriber paramSubscriber, Types paramTypes, String paramString) {
/*  24 */     return ((SingleTypeSubscription)this.a.get(paramTypes)).subscribe(paramSubscriber, paramString);
/*     */   }
/*     */   
/*     */   public boolean unsubscribe(Subscriber paramSubscriber, Types paramTypes, String paramString) {
/*  28 */     return ((SingleTypeSubscription)this.a.get(paramTypes)).unsubscribe(paramSubscriber, paramString);
/*     */   }
/*     */   
/*     */   public Set<Subscriber> getSubscriberList(Types paramTypes, String paramString) {
/*  32 */     return ((SingleTypeSubscription)this.a.get(paramTypes)).getSubscriberList(paramString);
/*     */   }
/*     */   
/*     */   public List<String> getTargetList(Types paramTypes) {
/*  36 */     return ((SingleTypeSubscription)this.a.get(paramTypes)).getTargetList();
/*     */   }
/*     */   
/*     */ 
/*  40 */   public boolean removeTarget(Types paramTypes, String paramString) { return ((SingleTypeSubscription)this.a.get(paramTypes)).removeTarget(paramString); }
/*     */   
/*     */   public static enum Types {
/*     */     private Types() {}
/*     */   }
/*     */   
/*     */   public static class Subscriber { public Object container;
/*  47 */     private Map<String, SubcriptionInfo> a = new ConcurrentHashMap();
/*     */     
/*     */     public class SubcriptionInfo {
/*     */       final SubscriptionMgr.Types a;
/*     */       final String b;
/*     */       final SubscriptionMgr.SingleTypeSubscription c;
/*     */       
/*  54 */       SubcriptionInfo(SubscriptionMgr.Types paramTypes, String paramString, SubscriptionMgr.SingleTypeSubscription paramSingleTypeSubscription) { this.a = paramTypes;
/*  55 */         this.b = paramString;
/*  56 */         this.c = paramSingleTypeSubscription;
/*     */       }
/*     */     }
/*     */     
/*     */     public Subscriber(Object paramObject) {
/*  61 */       this.container = paramObject;
/*     */     }
/*     */     
/*     */     public void onSubscription(SubscriptionMgr.SingleTypeSubscription paramSingleTypeSubscription, SubscriptionMgr.Types paramTypes, String paramString) {
/*     */       try {
/*  66 */         StringBuilder localStringBuilder = new StringBuilder();
/*  67 */         localStringBuilder.append(paramSingleTypeSubscription.hashCode()).append(":").append(paramTypes.toString()).append(":").append(paramString);
/*  68 */         this.a.put(localStringBuilder.toString(), new SubcriptionInfo(paramTypes, paramString, paramSingleTypeSubscription));
/*     */       } catch (Exception localException) {
/*  70 */         FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */       }
/*     */     }
/*     */     
/*     */     public void onUnsubscription(SubscriptionMgr.SingleTypeSubscription paramSingleTypeSubscription, SubscriptionMgr.Types paramTypes, String paramString) {
/*     */       try {
/*  76 */         StringBuilder localStringBuilder = new StringBuilder();
/*  77 */         localStringBuilder.append(paramSingleTypeSubscription.hashCode()).append(":").append(paramTypes.toString()).append(":").append(paramString);
/*  78 */         this.a.remove(localStringBuilder.toString());
/*     */       } catch (Exception localException) {
/*  80 */         FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */       }
/*     */     }
/*     */     
/*     */     public void RemoveAllSubscription() {
/*     */       try {
/*  86 */         for (SubcriptionInfo localSubcriptionInfo : this.a.values()) {
/*  87 */           localSubcriptionInfo.c.unsubscribe(this, localSubcriptionInfo.b);
/*     */         }
/*     */       } catch (Exception localException) {
/*  90 */         FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */       }
/*     */     }
/*     */     
/*     */     public Map getAllSubscription() {
/*  95 */       return this.a;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SingleTypeSubscription
/*     */   {
/*     */     protected final SubscriptionMgr.Types a;
/* 102 */     protected final Map<String, Set<SubscriptionMgr.Subscriber>> b = new HashMap();
/*     */     
/*     */     public SingleTypeSubscription(SubscriptionMgr.Types paramTypes) {
/* 105 */       this.a = paramTypes;
/*     */     }
/*     */     
/*     */     public boolean subscribe(SubscriptionMgr.Subscriber paramSubscriber, String paramString)
/*     */     {
/*     */       try {
/* 111 */         synchronized (this.b) { Object localObject1;
/* 112 */           if ((localObject1 = (Set)this.b.get(paramString)) == null) {
/* 113 */             localObject1 = new HashSet();
/* 114 */             this.b.put(paramString, localObject1);
/*     */           }
/* 116 */           ((Set)localObject1).add(paramSubscriber);
/*     */         }
/* 118 */         paramSubscriber.onSubscription(this, this.a, paramString);
/* 119 */         return true;
/*     */       }
/*     */       catch (Exception localException) {
/* 122 */         FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */       }
/* 124 */       return false;
/*     */     }
/*     */     
/*     */     public boolean unsubscribe(SubscriptionMgr.Subscriber paramSubscriber, String paramString) {
/*     */       try {
/*     */         Set localSet;
/* 130 */         synchronized (this.b) {
/* 131 */           if ((localSet = (Set)this.b.get(paramString)) != null) {
/* 132 */             localSet.remove(paramSubscriber);
/*     */           }
/*     */         }
/* 135 */         paramSubscriber.onUnsubscription(this, this.a, paramString);
/* 136 */         return (localSet != null) && (!localSet.isEmpty());
/*     */       }
/*     */       catch (Exception localException) {
/* 139 */         FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */       }
/* 141 */       return false;
/*     */     }
/*     */     
/*     */     public Set<SubscriptionMgr.Subscriber> getSubscriberList(String paramString)
/*     */     {
/*     */       try {
/* 147 */         synchronized (this.b) { Set localSet;
/* 148 */           if ((localSet = (Set)this.b.get(paramString)) != null) {
/* 149 */             return localSet;
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Exception localException) {
/* 154 */         FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */       }
/* 156 */       return null;
/*     */     }
/*     */     
/*     */     public boolean removeTarget(String paramString) {
/*     */       try {
/* 161 */         synchronized (this.b) {
/* 162 */           Set localSet = (Set)this.b.remove(paramString);
/* 163 */           if (localSet != null) {
/* 164 */             for (SubscriptionMgr.Subscriber localSubscriber : localSet) {
/* 165 */               localSubscriber.onUnsubscription(this, this.a, paramString);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 170 */         return true;
/*     */       }
/*     */       catch (Exception localException) {
/* 173 */         FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */       }
/* 175 */       return false;
/*     */     }
/*     */     
/*     */     public List<String> getTargetList() {
/*     */       try {
/* 180 */         synchronized (this.b) {
/* 181 */           ArrayList localArrayList = new ArrayList(this.b.keySet());
/* 182 */           return localArrayList;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 188 */         return null;
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/* 186 */         FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\globalservices\SubscriptionMgr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */