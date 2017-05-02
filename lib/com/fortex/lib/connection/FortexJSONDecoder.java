/*     */ package com.fortex.lib.connection;
/*     */ 
/*     */ import com.fortex.lib.globalservices.FortexLogger;
/*     */ import com.fortex.lib.protocol.FortexJSONMsg;
/*     */ import java.nio.charset.Charset;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.glassfish.grizzly.AbstractTransformer;
/*     */ import org.glassfish.grizzly.Buffer;
/*     */ import org.glassfish.grizzly.TransformationException;
/*     */ import org.glassfish.grizzly.TransformationResult;
/*     */ import org.glassfish.grizzly.attributes.AttributeStorage;
/*     */ 
/*     */ public class FortexJSONDecoder
/*     */   extends AbstractTransformer<Buffer, FortexJSONMsg>
/*     */ {
/*     */   protected Charset a;
/*  17 */   protected final byte[] b = "[BodyLen=".getBytes();
/*  18 */   protected FortexConnection c = null;
/*     */   
/*     */   public FortexJSONDecoder() {
/*  21 */     this(null, null);
/*     */   }
/*     */   
/*     */   public FortexJSONDecoder(FortexConnection paramFortexConnection, Charset paramCharset) {
/*  25 */     this.c = paramFortexConnection;
/*  26 */     this.a = (paramCharset != null ? paramCharset : Charset.defaultCharset());
/*     */   }
/*     */   
/*     */ 
/*     */   public String getName()
/*     */   {
/*  32 */     return "FortexJSONDecoder";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected TransformationResult<Buffer, FortexJSONMsg> transformImpl(AttributeStorage paramAttributeStorage, Buffer paramBuffer)
/*     */     throws TransformationException
/*     */   {
/*  40 */     if (paramBuffer == null) {
/*  41 */       throw new TransformationException("Input could not be null");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  49 */     FortexJSONMsg localFortexJSONMsg = null;
/*  50 */     String str1 = null;
/*     */     try {
/*  52 */       int i = 0;int j = paramBuffer.position();
/*  53 */       if (paramBuffer.remaining() > this.b.length + 6) {
/*  54 */         int k = 1;
/*  55 */         for (int m = 0; m < this.b.length; m++) {
/*  56 */           if (paramBuffer.get(j + m) != this.b[m]) {
/*  57 */             k = 0;
/*  58 */             break;
/*     */           }
/*     */         }
/*     */         
/*  62 */         if (k != 0) {
/*  63 */           m = j + this.b.length;
/*  64 */           String str2 = paramBuffer.toStringContent(this.a, m, m + 6);
/*  65 */           i = Integer.parseInt(str2);
/*     */         } else {
/*  67 */           throw new Exception("Message doesn't start with predefined header \"" + new String(this.b) + "\"");
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  72 */       if ((i == 0) || (paramBuffer.remaining() < i)) {
/*  73 */         return TransformationResult.createIncompletedResult(paramBuffer);
/*     */       }
/*     */       
/*  76 */       str1 = paramBuffer.toStringContent(this.a, j, j + i);
/*  77 */       paramBuffer.position(j + i);
/*  78 */       if (this.c != null) {
/*  79 */         this.c.logInMsg(str1);
/*     */       }
/*  81 */       localFortexJSONMsg = new FortexJSONMsg(str1);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*  85 */       FortexLogger.defaultLogger.error("Exception when processing:[" + (str1 == null ? "(null)" : str1) + "]");
/*  86 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*     */     }
/*     */     
/*     */ 
/*  90 */     TransformationResult localTransformationResult = TransformationResult.createCompletedResult(localFortexJSONMsg, paramBuffer);
/*     */     
/*  92 */     return localTransformationResult;
/*     */   }
/*     */   
/*     */   public boolean hasInputRemaining(AttributeStorage paramAttributeStorage, Buffer paramBuffer)
/*     */   {
/*  97 */     return (paramBuffer != null) && (paramBuffer.hasRemaining());
/*     */   }
/*     */   
/*     */   public Charset getCharset() {
/* 101 */     return this.a;
/*     */   }
/*     */   
/*     */   public void setCharset(Charset paramCharset) {
/* 105 */     this.a = paramCharset;
/*     */   }
/*     */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\FortexJSONDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */