/*     */ package com.fortex.lib.connection;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.glassfish.grizzly.Connection;
/*     */ 
/*     */ public class FortexDDOSDefender
/*     */ {
/*     */   public static class DDOSDefenderCfg
/*     */   {
/*  14 */     public int ipLockupThreshold = 0;
/*  15 */     public int ipLockupDurationMS = 0;
/*  16 */     public int initMsgTimeoutMS = 0;
/*  17 */     public int maxPendingConnOfBadIP = 0;
/*  18 */     public int maxPendingConn = 0;
/*  19 */     public int maxPendingConnOfSingleIp = 0;
/*     */   }
/*     */   
/*  22 */   public static int BHV_InitMsgTimeout = 1;
/*  23 */   public static int BHV_BadMsgFmt = 2;
/*     */   private final Map<Connection, ConnectionInfo> a;
/*     */   private final Map<String, IPInfo> b;
/*     */   private final Map<String, IPInfo> c;
/*     */   private final Logger d;
/*     */   
/*     */   public static class IPInfo {
/*     */     public String ip;
/*     */     public int pendingConn;
/*     */     public int badReputation;
/*     */     
/*  34 */     public IPInfo(String paramString) { this.ip = paramString; }
/*     */     
/*     */ 
/*  37 */     boolean a() { return (this.badReputation > 0) || (this.blockedTimes > 0); }
/*     */     
/*     */     public long blockDueTime;
/*     */     public int blockedTimes;
/*     */     public boolean blockedByAdmin;
/*     */   }
/*     */   
/*     */   private class ConnectionInfo { public long connectTime;
/*     */     
/*  46 */     public ConnectionInfo() { this.connectTime = new Date().getTime();
/*  47 */       this.loginSucceed = false;
/*     */     }
/*     */     
/*     */ 
/*     */     public FortexDDOSDefender.IPInfo ipInfo;
/*     */     public boolean loginSucceed;
/*     */   }
/*     */   
/*  55 */   private int e = 0;
/*     */   private final DDOSDefenderCfg f;
/*     */   
/*     */   public FortexDDOSDefender(DDOSDefenderCfg paramDDOSDefenderCfg, Logger paramLogger) {
/*  59 */     this.a = new java.util.concurrent.ConcurrentHashMap();
/*  60 */     this.b = new java.util.concurrent.ConcurrentHashMap();
/*  61 */     this.c = new java.util.concurrent.ConcurrentHashMap();
/*  62 */     this.f = paramDDOSDefenderCfg;
/*  63 */     this.d = paramLogger;
/*     */   }
/*     */   
/*     */   public void setBlockIpThreshold(int paramInt) {
/*  67 */     this.f.ipLockupThreshold = paramInt;
/*     */   }
/*     */   
/*  70 */   public void setBlockDurationMs(int paramInt) { this.f.ipLockupDurationMS = paramInt; }
/*     */   
/*     */   public void setInitMsgMaxWaitingMs(int paramInt) {
/*  73 */     this.f.initMsgTimeoutMS = paramInt;
/*     */   }
/*     */   
/*  76 */   public void setMaxPendingConnOfBadIp(int paramInt) { this.f.maxPendingConnOfBadIP = paramInt; }
/*     */   
/*     */   public void setMaxPendingConn(int paramInt) {
/*  79 */     this.f.maxPendingConn = paramInt;
/*     */   }
/*     */   
/*  82 */   public void setSingleIpMaxPendingConnection(int paramInt) { this.f.maxPendingConnOfSingleIp = paramInt; }
/*     */   
/*     */   public boolean check(String paramString)
/*     */   {
/*  86 */     if (this.a.size() > this.f.maxPendingConn) {
/*  87 */       this.d.warn("DDOS: Rejecting new connection from " + paramString + " on pending connection count " + this.a.size() + " > setting limit " + this.f.maxPendingConn);
/*  88 */       return false;
/*     */     }
/*     */     
/*  91 */     IPInfo localIPInfo = (IPInfo)this.b.get(paramString);
/*  92 */     if (localIPInfo == null) {
/*  93 */       return true;
/*     */     }
/*  95 */     if (localIPInfo.blockedByAdmin) {
/*  96 */       this.d.warn("DDOS: Rejecting new connection from " + paramString + " which is blocked by admin");
/*  97 */       return false;
/*     */     }
/*  99 */     if ((localIPInfo.a()) && (this.e > this.f.maxPendingConnOfBadIP)) {
/* 100 */       this.d.warn("DDOS: Rejecting new connection from " + paramString + " on bad IPs' pending connection count " + this.e + " > setting limit " + this.f.maxPendingConnOfBadIP);
/* 101 */       return false;
/*     */     }
/* 103 */     if ((this.f.ipLockupThreshold > 0) && (localIPInfo.badReputation > this.f.ipLockupThreshold)) {
/* 104 */       this.d.warn("DDOS: Rejecting new connection from " + paramString + " on bad reputation " + localIPInfo.badReputation + " > setting limit " + this.f.ipLockupThreshold);
/* 105 */       return false;
/*     */     }
/* 107 */     int i = localIPInfo.pendingConn + 1;
/* 108 */     if ((this.f.maxPendingConnOfSingleIp > 0) && (i > this.f.maxPendingConnOfSingleIp)) {
/* 109 */       this.d.warn("DDOS: Rejecting new connection from " + paramString + " on pending connections count of single IP " + i + " > setting limit " + this.f.maxPendingConnOfSingleIp);
/* 110 */       return false;
/*     */     }
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   public void onNewConnection(Connection paramConnection, String paramString) {
/* 116 */     ConnectionInfo localConnectionInfo = new ConnectionInfo();
/* 117 */     this.a.put(paramConnection, localConnectionInfo);
/* 118 */     localConnectionInfo.ipInfo = ((IPInfo)this.b.get(paramString));
/* 119 */     if (localConnectionInfo.ipInfo == null) {
/* 120 */       localConnectionInfo.ipInfo = new IPInfo(paramString);
/* 121 */       localConnectionInfo.ipInfo.ip = paramString;
/* 122 */       this.b.put(paramString, localConnectionInfo.ipInfo);
/*     */     }
/*     */     
/* 125 */     localConnectionInfo.ipInfo.pendingConn += 1;
/* 126 */     if (localConnectionInfo.ipInfo.a()) {
/* 127 */       this.e += 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getIP(Connection paramConnection) {
/* 132 */     java.net.InetSocketAddress localInetSocketAddress = (java.net.InetSocketAddress)paramConnection.getPeerAddress();
/* 133 */     java.net.InetAddress localInetAddress = localInetSocketAddress.getAddress();
/* 134 */     return localInetAddress.getHostAddress();
/*     */   }
/*     */   
/*     */   public void onConnBadBehavior(String paramString, Connection paramConnection, int paramInt) {
/* 138 */     IPInfo localIPInfo = (IPInfo)this.b.get(paramString);
/* 139 */     if (localIPInfo == null) {
/* 140 */       return;
/*     */     }
/*     */     
/* 143 */     localIPInfo.badReputation += paramInt;
/* 144 */     this.d.warn("DDOS: " + localIPInfo.ip + " has a bad behaviour " + paramInt + " total reputation=" + localIPInfo.badReputation + "(max=" + this.f.ipLockupThreshold + ")");
/* 145 */     if ((localIPInfo.badReputation > this.f.ipLockupThreshold) && (localIPInfo.badReputation - paramInt <= this.f.ipLockupThreshold)) {
/* 146 */       int i = this.f.ipLockupDurationMS * (int)Math.pow(2.0D, localIPInfo.blockedTimes);
/* 147 */       blockIP(localIPInfo, i, false);
/* 148 */       paramConnection.close();
/*     */       
/* 150 */       this.d.warn("DDOS: connection from " + localIPInfo.ip + " is closed on bad reputation " + localIPInfo.badReputation + " > setting limit " + this.f.ipLockupThreshold + " ,ip locked up for " + i / 1000 + " seconds" + " ,this IP has been locked " + localIPInfo.blockedTimes + " times");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void connLoginSucceed(Connection paramConnection)
/*     */   {
/* 158 */     ConnectionInfo localConnectionInfo = (ConnectionInfo)this.a.get(paramConnection);
/* 159 */     if (localConnectionInfo != null) {
/* 160 */       localConnectionInfo.loginSucceed = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void blockIP(String paramString, long paramLong, boolean paramBoolean) {
/* 165 */     IPInfo localIPInfo = (IPInfo)this.b.get(paramString);
/* 166 */     if (localIPInfo == null) {
/* 167 */       localIPInfo = new IPInfo(paramString);
/* 168 */       this.b.put(paramString, localIPInfo);
/*     */     }
/* 170 */     blockIP(localIPInfo, paramLong, paramBoolean);
/*     */   }
/*     */   
/* 173 */   public void blockIP(IPInfo paramIPInfo, long paramLong, boolean paramBoolean) { paramIPInfo.blockDueTime = (new Date().getTime() + paramLong);
/* 174 */     paramIPInfo.blockedByAdmin = paramBoolean;
/* 175 */     if (!paramBoolean) {
/* 176 */       paramIPInfo.blockedTimes += 1;
/*     */     }
/*     */     
/* 179 */     this.c.put(paramIPInfo.ip, paramIPInfo);
/*     */   }
/*     */   
/*     */   public IPInfo getBlockedIPInfo(String paramString) {
/* 183 */     return (IPInfo)this.c.get(paramString);
/*     */   }
/*     */   
/* 186 */   public Map<String, IPInfo> getAllBlockedIPInfo() { return this.c; }
/*     */   
/*     */   public void unblock(String paramString) {
/* 189 */     IPInfo localIPInfo = (IPInfo)this.b.get(paramString);
/* 190 */     this.c.remove(paramString);
/* 191 */     a(localIPInfo);
/*     */   }
/*     */   
/* 194 */   private void a(IPInfo paramIPInfo) { if (paramIPInfo != null) {
/* 195 */       paramIPInfo.blockDueTime = 0L;
/* 196 */       paramIPInfo.badReputation = 0;
/* 197 */       paramIPInfo.blockedByAdmin = false;
/* 198 */       this.d.warn("DDOS: IP " + paramIPInfo.ip + " is unblocked");
/*     */     }
/*     */   }
/*     */   
/*     */   public void removePendingConnection(Connection paramConnection, String paramString) {
/* 203 */     ConnectionInfo localConnectionInfo = (ConnectionInfo)this.a.remove(paramConnection);
/* 204 */     if (localConnectionInfo == null) {
/* 205 */       return;
/*     */     }
/* 207 */     this.d.warn("DDOS: pending connection " + localConnectionInfo.ipInfo.ip + ":" + 
/* 208 */       ((java.net.InetSocketAddress)paramConnection.getPeerAddress()).getPort() + " is removed from pending on reason " + paramString);
/*     */     
/* 210 */     a(localConnectionInfo);
/*     */   }
/*     */   
/*     */   private void a(ConnectionInfo paramConnectionInfo) {
/* 214 */     if (paramConnectionInfo.ipInfo.a()) {
/* 215 */       this.e -= 1;
/*     */     }
/* 217 */     paramConnectionInfo.connectTime = 0L;
/* 218 */     paramConnectionInfo.ipInfo.pendingConn -= 1;
/*     */   }
/*     */   
/*     */   public void onTimer()
/*     */   {
/*     */     try {
/* 224 */       long l = new Date().getTime();
/*     */       Iterator localIterator;
/* 226 */       Map.Entry localEntry; Object localObject; if (this.a.size() > 0) {
/* 227 */         localIterator = this.a.entrySet().iterator();
/*     */         
/* 229 */         while (localIterator.hasNext()) {
/* 230 */           localEntry = (Map.Entry)localIterator.next();
/* 231 */           localObject = (ConnectionInfo)localEntry.getValue();
/* 232 */           if (((ConnectionInfo)localObject).connectTime + this.f.initMsgTimeoutMS < l) {
/* 233 */             onConnBadBehavior(((ConnectionInfo)localObject).ipInfo.ip, (Connection)localEntry.getKey(), BHV_InitMsgTimeout);
/*     */             
/* 235 */             ((Connection)localEntry.getKey()).close();
/* 236 */             localIterator.remove();
/* 237 */             this.d.warn("DDOS: connection from " + ((ConnectionInfo)localObject).ipInfo.ip + " is kicked out because sending nothing in " + this.f.initMsgTimeoutMS / 1000 + " seconds after it connnected");
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 243 */       if (this.c.size() > 0) {
/* 244 */         localIterator = this.c.entrySet().iterator();
/*     */         
/* 246 */         while (localIterator.hasNext()) {
/* 247 */           localEntry = (Map.Entry)localIterator.next();
/* 248 */           localObject = (IPInfo)localEntry.getValue();
/* 249 */           if ((((IPInfo)localObject).blockDueTime > 0L) && (((IPInfo)localObject).blockDueTime < l)) {
/* 250 */             a((IPInfo)localObject);
/* 251 */             this.d.warn("DDOS: IP " + ((IPInfo)localObject).ip + " is unblocked because timeout due");
/* 252 */             localIterator.remove();
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception localException) {
/* 257 */       this.d.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\FortexDDOSDefender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */