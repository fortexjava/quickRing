/*     */ package com.fortex.lib.MemDB;
/*     */ 
/*     */ import com.fortex.lib.globalservices.FortexLogger;
/*     */ import com.hazelcast.client.HazelcastClient;
/*     */ import com.hazelcast.client.config.ClientConfig;
/*     */ import com.hazelcast.client.config.ClientNetworkConfig;
/*     */ import com.hazelcast.core.HazelcastInstance;
/*     */ import com.hazelcast.core.IMap;
/*     */ import com.hazelcast.core.ISet;
/*     */ import com.hazelcast.core.LifecycleService;
/*     */ import com.hazelcast.security.UsernamePasswordCredentials;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MemDB
/*     */ {
/*  27 */   protected static final MemDB a = new MemDB();
/*  28 */   protected final ConcurrentMap<String, Map> b = new ConcurrentHashMap();
/*  29 */   protected final ConcurrentMap<String, Set> c = new ConcurrentHashMap();
/*     */   protected static MemDBConfig d;
/*  31 */   public static HazelcastInstance hazelClient = null;
/*     */   
/*     */   public static boolean init(MemDBConfig paramMemDBConfig) {
/*     */     try {
/*  35 */       d = paramMemDBConfig;
/*     */       
/*     */ 
/*  38 */       ClientConfig localClientConfig = new ClientConfig();
/*  39 */       ClientNetworkConfig localClientNetworkConfig = new ClientNetworkConfig();
/*  40 */       String[] arrayOfString = d.gridClusterSrvIPList.split(",");
/*  41 */       for (int i = 0; (arrayOfString != null) && (i < arrayOfString.length); i++) {
/*  42 */         localClientNetworkConfig.addAddress(new String[] { arrayOfString[i] });
/*     */       }
/*  44 */       localClientConfig.setNetworkConfig(localClientNetworkConfig);
/*     */       
/*  46 */       UsernamePasswordCredentials localUsernamePasswordCredentials = new UsernamePasswordCredentials();
/*  47 */       localUsernamePasswordCredentials.setUsername(d.gridClusterUsername);
/*  48 */       localUsernamePasswordCredentials.setPassword(d.gridClusterPassword);
/*  49 */       localClientConfig.setCredentials(localUsernamePasswordCredentials);
/*     */       
/*  51 */       hazelClient = HazelcastClient.newHazelcastClient(localClientConfig);
/*     */     }
/*     */     catch (Exception localException) {
/*  54 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/*  56 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean removeMap(String paramString)
/*     */   {
/*  61 */     return a.b.remove(paramString) != null;
/*     */   }
/*     */   
/*     */   public static <K, V> Map<K, V> getMap(String paramString)
/*     */   {
/*  66 */     Object localObject = (Map)a.b.get(paramString);
/*  67 */     if (localObject == null) {
/*  68 */       localObject = new MemDBMap(paramString);
/*  69 */       a.b.put(paramString, (Map) localObject);
/*     */     }
/*  71 */     return (Map<K, V>)localObject;
/*     */   }
/*     */   
/*     */   public static <K, V> Map<K, V> getGridMap(String paramString)
/*     */   {
/*     */     try {
/*  77 */       if (hazelClient == null) {
/*  78 */         return null;
/*     */       }
/*     */       
/*  81 */       IMap localIMap1 = hazelClient.getMap(paramString);
/*  82 */       a.b.put(paramString, localIMap1);
/*  83 */       return localIMap1;
/*     */     } catch (Exception localException1) {
/*  85 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException1.getMessage());
/*     */       
/*     */ 
/*     */       try
/*     */       {
/*  90 */         if (checkServerClusterConnection()) {
/*  91 */           IMap localIMap2 = hazelClient.getMap(paramString);
/*  92 */           a.b.put(paramString, localIMap2);
/*  93 */           return localIMap2;
/*     */         }
/*     */       } catch (Exception localException2) {
/*  96 */         FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException2.getMessage());
/*     */       }
/*     */     }
/*  99 */     return null;
/*     */   }
/*     */   
/*     */   public static <V> Set<V> getGridSet(String paramString)
/*     */   {
/*     */     try {
/* 105 */       if (hazelClient == null) {
/* 106 */         return null;
/*     */       }
/*     */       
/* 109 */       ISet localISet1 = hazelClient.getSet(paramString);
/* 110 */       a.c.put(paramString, localISet1);
/* 111 */       return localISet1;
/*     */     } catch (Exception localException1) {
/* 113 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException1.getMessage());
/*     */       
/*     */ 
/*     */       try
/*     */       {
/* 118 */         if (checkServerClusterConnection()) {
/* 119 */           ISet localISet2 = hazelClient.getSet(paramString);
/* 120 */           a.c.put(paramString, localISet2);
/* 121 */           return localISet2;
/*     */         }
/*     */       } catch (Exception localException2) {
/* 124 */         FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException2.getMessage());
/*     */       }
/*     */     }
/* 127 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean checkServerClusterConnection() {
/*     */     try {
/* 132 */       if (hazelClient == null) {
/* 133 */         return (init(d)) && (hazelClient != null);
/*     */       }
/* 135 */       LifecycleService localLifecycleService = hazelClient.getLifecycleService();
/* 136 */       return (localLifecycleService.isRunning()) || ((init(d)) && (hazelClient != null));
/*     */     }
/*     */     catch (Exception localException) {
/* 139 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/* 141 */     return false;
/*     */   }
/*     */   
/*     */   public static class MemDBConfig
/*     */   {
/*     */     public String gridClusterSrvIPList;
/*     */     public String gridClusterUsername;
/*     */     public String gridClusterPassword;
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\MemDB\MemDB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */