/*     */ package com.fortex.lib.connection;
/*     */ 
/*     */ import com.fortex.lib.globalservices.FortexLogger;
/*     */ import java.io.IOException;
/*     */ import java.net.Inet4Address;
/*     */ import java.net.Inet6Address;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.NetworkInterface;
/*     */ import java.net.SocketException;
/*     */ import java.net.StandardProtocolFamily;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.glassfish.grizzly.GrizzlyFuture;
/*     */ import org.glassfish.grizzly.filterchain.BaseFilter;
/*     */ import org.glassfish.grizzly.filterchain.FilterChain;
/*     */ import org.glassfish.grizzly.filterchain.FilterChainBuilder;
/*     */ import org.glassfish.grizzly.filterchain.TransportFilter;
/*     */ import org.glassfish.grizzly.nio.transport.UDPNIOConnection;
/*     */ import org.glassfish.grizzly.nio.transport.UDPNIOTransport;
/*     */ import org.glassfish.grizzly.nio.transport.UDPNIOTransportBuilder;
/*     */ 
/*     */ public class FortexConnectionUdp<msgClass>
/*     */   extends FortexConnection<msgClass>
/*     */ {
/*  29 */   private UDPNIOConnection d = null;
/*  30 */   private UDPNIOTransport e = null;
/*     */   private final boolean f;
/*  32 */   private NetworkInterface g = null;
/*  33 */   private InetAddress h = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FortexConnectionUdp(FortexConnectionMgrInf<msgClass> paramFortexConnectionMgrInf, boolean paramBoolean, Logger paramLogger, String paramString, FortexConnection.Config paramConfig)
/*     */   {
/*  42 */     super(paramFortexConnectionMgrInf, paramLogger, paramString, paramConfig);
/*  43 */     this.f = paramBoolean;
/*     */   }
/*     */   
/*     */   public boolean init(BaseFilter paramBaseFilter) {
/*  47 */     this.g = GetFirstNICInterface(StandardProtocolFamily.INET);
/*  48 */     FilterChainBuilder localFilterChainBuilder = FilterChainBuilder.stateless();
/*  49 */     localFilterChainBuilder.add(new TransportFilter());
/*  50 */     localFilterChainBuilder.add(paramBaseFilter);
/*  51 */     localFilterChainBuilder.add(new FortexConnectionFilter(this));
/*     */     
/*  53 */     FilterChain localFilterChain = localFilterChainBuilder.build();
/*  54 */     this.e = ((UDPNIOTransportBuilder)UDPNIOTransportBuilder.newInstance().setProcessor(localFilterChain)).build();
/*  55 */     return this.e != null;
/*     */   }
/*     */   
/*     */   public int connect(FortexConnection.Config paramConfig)
/*     */   {
/*     */     try {
/*  61 */       if (this.e.isStopped()) {
/*  62 */         this.e.start();
/*     */       }
/*     */       
/*  65 */       GrizzlyFuture localGrizzlyFuture = this.e.connect(null, new InetSocketAddress(paramConfig.port));
/*  66 */       this.d = ((UDPNIOConnection)localGrizzlyFuture.get(10L, TimeUnit.SECONDS));
/*  67 */       if ((this.f) && (this.d != null) && (this.g != null)) {
/*  68 */         this.h = InetAddress.getByName(paramConfig.ip);
/*  69 */         this.d.join(this.h, this.g, null);
/*     */       }
/*     */     } catch (Exception localException) {
/*  72 */       if (this.d != null) {
/*  73 */         this.d.close();
/*  74 */         this.d = null;
/*     */       }
/*  76 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/*     */     
/*  79 */     return this.d == null ? 0 : 1;
/*     */   }
/*     */   
/*     */   public boolean isConnected()
/*     */   {
/*  84 */     return (this.d != null) && (this.d.isOpen());
/*     */   }
/*     */   
/*     */   public int disconnect()
/*     */   {
/*     */     try {
/*  90 */       if (this.d != null) {
/*  91 */         if (this.f) {
/*  92 */           this.d.drop(this.h, this.g);
/*     */         }
/*  94 */         this.d.closeSilently();
/*     */       }
/*  96 */       this.e.stop();
/*     */     } catch (IOException localIOException) {
/*  98 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localIOException.getMessage());
/*     */     }
/* 100 */     return 1;
/*     */   }
/*     */   
/*     */   public static NetworkInterface GetFirstNICInterface(StandardProtocolFamily paramStandardProtocolFamily)
/*     */   {
/* 105 */     NetworkInterface localNetworkInterface = null;
/*     */     try
/*     */     {
/* 108 */       Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
/* 109 */       int i = 0;
/* 110 */       while ((i == 0) && (localEnumeration1.hasMoreElements())) {
/* 111 */         localNetworkInterface = (NetworkInterface)localEnumeration1.nextElement();
/* 112 */         if ((localNetworkInterface.supportsMulticast()) && (localNetworkInterface.isUp()) && (!localNetworkInterface.isLoopback()) && (!localNetworkInterface.isVirtual())) {
/* 113 */           Enumeration localEnumeration2 = localNetworkInterface.getInetAddresses();
/* 114 */           for (InetAddress localInetAddress : Collections.list(localEnumeration2)) {
/* 115 */             if (((paramStandardProtocolFamily == StandardProtocolFamily.INET) && ((localInetAddress instanceof Inet4Address))) || ((paramStandardProtocolFamily == StandardProtocolFamily.INET6) && ((localInetAddress instanceof Inet6Address))))
/*     */             {
/*     */ 
/* 118 */               i = 1;
/* 119 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (SocketException localSocketException) {
/* 125 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localSocketException.getMessage());
/*     */     }
/*     */     
/* 128 */     return localNetworkInterface;
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\FortexConnectionUdp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */