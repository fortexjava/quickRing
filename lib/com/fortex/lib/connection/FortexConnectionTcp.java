/*     */ package com.fortex.lib.connection;
/*     */ 
/*     */ import com.fortex.lib.globalservices.FortexLogger;
/*     */ import com.fortex.lib.protocol.ProtoImpBase;
/*     */ import java.io.IOException;
/*     */ import java.security.SecureRandom;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Date;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import javax.net.ssl.X509TrustManager;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.glassfish.grizzly.Connection;
/*     */ import org.glassfish.grizzly.GrizzlyFuture;
/*     */ import org.glassfish.grizzly.filterchain.BaseFilter;
/*     */ import org.glassfish.grizzly.filterchain.FilterChainBuilder;
/*     */ import org.glassfish.grizzly.filterchain.TransportFilter;
/*     */ import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
/*     */ import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
/*     */ import org.glassfish.grizzly.ssl.SSLContextConfigurator;
/*     */ import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
/*     */ import org.glassfish.grizzly.ssl.SSLFilter;
/*     */ 
/*     */ public class FortexConnectionTcp<msgClass> extends FortexConnection<msgClass>
/*     */ {
/*  29 */   protected Connection d = null;
/*  30 */   protected TCPNIOTransport e = null;
/*  31 */   protected ProtoImpBase f = null;
/*     */   
/*     */   public long loginInitTime;
/*     */   
/*     */   public FortexConnectionTcp(FortexConnectionMgrInf<msgClass> paramFortexConnectionMgrInf, Logger paramLogger, String paramString, FortexConnection.Config paramConfig)
/*     */   {
/*  37 */     super(paramFortexConnectionMgrInf, paramLogger, paramString, paramConfig);
/*     */   }
/*     */   
/*     */   public boolean init(BaseFilter paramBaseFilter, ProtoImpBase paramProtoImpBase)
/*     */   {
/*  42 */     this.f = paramProtoImpBase;
/*     */     
/*  44 */     FilterChainBuilder localFilterChainBuilder = FilterChainBuilder.stateless();
/*  45 */     localFilterChainBuilder.add(new TransportFilter());
/*     */     
/*  47 */     if (this.cfg.enableSSL) {
/*  48 */       SSLEngineConfigurator localSSLEngineConfigurator1 = getSSLEnginConfig();
/*  49 */       SSLEngineConfigurator localSSLEngineConfigurator2 = localSSLEngineConfigurator1.copy().setClientMode(true);
/*  50 */       SSLFilter localSSLFilter = new SSLFilter(localSSLEngineConfigurator1, localSSLEngineConfigurator2);
/*  51 */       localFilterChainBuilder.add(localSSLFilter);
/*     */     }
/*     */     
/*  54 */     localFilterChainBuilder.add(paramBaseFilter);
/*  55 */     localFilterChainBuilder.add(new FortexConnectionFilter(this));
/*     */     
/*  57 */     this.e = TCPNIOTransportBuilder.newInstance().build();
/*  58 */     this.e.setProcessor(localFilterChainBuilder.build());
/*  59 */     return this.e != null;
/*     */   }
/*     */   
/*     */   public int connect(FortexConnection.Config paramConfig)
/*     */   {
/*     */     try {
/*  65 */       if (FortexLogger.defaultLogger != null) {
/*  66 */         FortexLogger.defaultLogger.info(this.b + " is trying to connect to " + paramConfig.ip + "@" + paramConfig.port);
/*     */       }
/*  68 */       if (this.e.isStopped()) {
/*  69 */         this.e.start();
/*     */       }
/*  71 */       GrizzlyFuture localGrizzlyFuture = this.e.connect(paramConfig.ip, paramConfig.port);
/*  72 */       this.d = ((Connection)localGrizzlyFuture.get(10L, TimeUnit.SECONDS));
/*  73 */       if (this.d != null) {
/*  74 */         this.d.addCloseListener(this);
/*     */       }
/*     */     } catch (TimeoutException localTimeoutException) {
/*  77 */       this.d = null;
/*  78 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " 10s timeout");
/*     */     } catch (Exception localException) {
/*  80 */       this.d = null;
/*  81 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/*     */     
/*  84 */     return this.d == null ? 0 : 1;
/*     */   }
/*     */   
/*     */   public boolean isConnected()
/*     */   {
/*  89 */     return (this.d != null) && (this.d.isOpen());
/*     */   }
/*     */   
/*     */   public int disconnect()
/*     */   {
/*     */     try {
/*  95 */       if (this.d != null) {
/*  96 */         if (FortexLogger.defaultLogger != null) {
/*  97 */           FortexLogger.defaultLogger.info("Disconnecting " + this.b + " from " + this.cfg.ip + "@" + this.cfg.port);
/*     */         }
/*  99 */         this.d.closeSilently();
/*     */       }
/* 101 */       this.e.stop();
/*     */     } catch (IOException localIOException) {
/* 103 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localIOException.getMessage());
/*     */     }
/* 105 */     return 1;
/*     */   }
/*     */   
/*     */   public int sendMsg(msgClass parammsgClass)
/*     */   {
/*     */     try {
/* 111 */       this.d.write(parammsgClass);
/*     */     } catch (Exception localException) {
/* 113 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/* 115 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SSLEngineConfigurator getSSLEnginConfig()
/*     */   {
/* 127 */     TrustManager[] arrayOfTrustManager = { new X509TrustManager()
/*     */     {
/*     */       public X509Certificate[] getAcceptedIssuers() {
/* 130 */         return null;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */       public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString) {}
/*     */       
/*     */ 
/*     */ 
/*     */       public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString) {}
/* 140 */     } };
/* 141 */     SSLContextConfigurator localSSLContextConfigurator = new SSLContextConfigurator();
/*     */     SSLEngineConfigurator localSSLEngineConfigurator;
/*     */     try
/*     */     {
/* 145 */       SSLContext localSSLContext = localSSLContextConfigurator.createSSLContext();
/* 146 */       localSSLContext.init(null, arrayOfTrustManager, new SecureRandom());
/* 147 */       localSSLEngineConfigurator = new SSLEngineConfigurator(localSSLContext, false, false, false);
/*     */     } catch (Exception localException) {
/* 149 */       localSSLEngineConfigurator = null;
/* 150 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/*     */     
/* 153 */     return localSSLEngineConfigurator;
/*     */   }
/*     */   
/*     */   protected void onConnected() {
/* 157 */     Object localObject = this.f == null ? null : this.f.createLogonMsg(this);
/* 158 */     if (localObject != null) {
/* 159 */       this.loginSuccess = false;
/* 160 */       this.loginInitTime = new Date().getTime();
/* 161 */       sendMsg((msgClass) localObject);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void onHeartbeatDue() {
/* 166 */     Object localObject = this.f == null ? null : this.f.createHeartbeatMsg(this);
/* 167 */     if (localObject != null) {
/* 168 */       sendMsg((msgClass) localObject);
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkTcpConnection(FortexConnection.Config paramConfig, int paramInt1, int paramInt2)
/*     */   {
/*     */     try
/*     */     {
/* 176 */       if (!isConnected()) {
/* 177 */         int i = connect(paramConfig);
/* 178 */         if (i > 0) {
/* 179 */           FortexLogger.defaultLogger.info(this.b + " connected to " + paramConfig.ip + "@" + paramConfig.port);
/* 180 */           this.lastMessageRecvTime = new Date().getTime();
/* 181 */           onConnected();
/*     */         }
/*     */       } else {
/* 184 */         long l1 = new Date().getTime();
/* 185 */         long l2 = l1 - this.lastMessageRecvTime;
/* 186 */         if ((!this.loginSuccess) && (paramConfig.loginTimeoutMs > 0)) {
/* 187 */           long l3 = l1 - this.loginInitTime;
/* 188 */           if (l3 > paramConfig.loginTimeoutMs) {
/* 189 */             FortexLogger.defaultLogger.warn("Going to disconnect " + this.b + " from " + paramConfig.ip + "@" + paramConfig.port + " because login timeout");
/* 190 */             disconnect();
/*     */           }
/*     */         }
/* 193 */         else if (l2 > paramInt2) {
/* 194 */           FortexLogger.defaultLogger.warn("Disconnecting " + this.b + " from " + paramConfig.ip + "@" + paramConfig.port + " because heartbeat timeout");
/* 195 */           disconnect();
/* 196 */         } else if (l2 >= paramInt1) {
/* 197 */           onHeartbeatDue();
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {
/* 202 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\FortexConnectionTcp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */