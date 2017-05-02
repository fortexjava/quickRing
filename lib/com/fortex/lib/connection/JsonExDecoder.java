/*    */ package com.fortex.lib.connection;
/*    */ 
/*    */ import com.fortex.lib.globalservices.FortexLogger;
/*    */ import java.nio.charset.Charset;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.glassfish.grizzly.AbstractTransformer;
/*    */ import org.glassfish.grizzly.Buffer;
/*    */ import org.glassfish.grizzly.TransformationException;
/*    */ import org.glassfish.grizzly.TransformationResult;
/*    */ import org.glassfish.grizzly.attributes.AttributeStorage;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ 
/*    */ public class JsonExDecoder
/*    */   extends AbstractTransformer<Buffer, JSONObject>
/*    */ {
/*    */   protected Charset a;
/*    */   protected static final int b = 8;
/*    */   protected int c;
/* 20 */   protected FortexConnection d = null;
/*    */   
/*    */   public JsonExDecoder() {
/* 23 */     this(null, null);
/*    */   }
/*    */   
/*    */   public JsonExDecoder(FortexConnection paramFortexConnection, Charset paramCharset) {
/* 27 */     this.d = paramFortexConnection;
/* 28 */     this.a = (paramCharset != null ? paramCharset : Charset.defaultCharset());
/* 29 */     this.c = 0;
/*    */   }
/*    */   
/*    */   public String getName()
/*    */   {
/* 34 */     return "JsonExDecoder";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected TransformationResult<Buffer, JSONObject> transformImpl(AttributeStorage paramAttributeStorage, Buffer paramBuffer)
/*    */     throws TransformationException
/*    */   {
/* 42 */     if (paramBuffer == null) {
/* 43 */       throw new TransformationException("Input could not be null");
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 51 */     JSONObject localJSONObject = null;
/* 52 */     String str1 = null;
/*    */     try {
/* 54 */       if ((this.c == 0) && (paramBuffer.remaining() >= 8)) {
/* 55 */         String str2 = paramBuffer.toStringContent(this.a, paramBuffer
/* 56 */           .position(), paramBuffer.position() + 8);
/* 57 */         this.c = Integer.parseInt(str2);
/*    */       }
/*    */       
/* 60 */       if ((this.c == 0) || (paramBuffer.remaining() < this.c)) {
/* 61 */         return TransformationResult.createIncompletedResult(paramBuffer);
/*    */       }
/* 63 */       int i = paramBuffer.position() + this.c;
/* 64 */       str1 = paramBuffer.toStringContent(this.a, paramBuffer
/* 65 */         .position(), i);
/* 66 */       paramBuffer.position(i);
/* 67 */       this.c = 0;
/* 68 */       if (this.d != null) {
/* 69 */         this.d.logInMsg(str1);
/*    */       }
/* 71 */       localJSONObject = new JSONObject(str1.substring(8));
/*    */ 
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 76 */       FortexLogger.defaultLogger.error("Exception when processing:[" + (str1 == null ? "(null)" : str1) + "]");
/* 77 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*    */     }
/*    */     
/*    */ 
/* 81 */     TransformationResult localTransformationResult = TransformationResult.createCompletedResult(localJSONObject, paramBuffer);
/* 82 */     this.c = 0;
/*    */     
/* 84 */     return localTransformationResult;
/*    */   }
/*    */   
/*    */   public boolean hasInputRemaining(AttributeStorage paramAttributeStorage, Buffer paramBuffer)
/*    */   {
/* 89 */     return (paramBuffer != null) && (paramBuffer.hasRemaining());
/*    */   }
/*    */   
/*    */   public Charset getCharset() {
/* 93 */     return this.a;
/*    */   }
/*    */   
/*    */   public void setCharset(Charset paramCharset) {
/* 97 */     this.a = paramCharset;
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\JsonExDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */