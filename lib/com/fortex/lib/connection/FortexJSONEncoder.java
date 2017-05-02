/*    */ package com.fortex.lib.connection;
/*    */ 
/*    */ import com.fortex.lib.protocol.FortexJSONMsg;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.nio.charset.Charset;
/*    */ import org.glassfish.grizzly.Buffer;
/*    */ import org.glassfish.grizzly.TransformationException;
/*    */ import org.glassfish.grizzly.TransformationResult;
/*    */ import org.glassfish.grizzly.attributes.AttributeStorage;
/*    */ import org.glassfish.grizzly.memory.MemoryManager;
/*    */ 
/*    */ public class FortexJSONEncoder extends org.glassfish.grizzly.AbstractTransformer<FortexJSONMsg, Buffer>
/*    */ {
/*    */   protected Charset a;
/* 15 */   protected FortexConnection b = null;
/*    */   
/*    */   public FortexJSONEncoder() {
/* 18 */     this(null, null);
/*    */   }
/*    */   
/*    */   public FortexJSONEncoder(FortexConnection paramFortexConnection, Charset paramCharset) {
/* 22 */     this.b = paramFortexConnection;
/* 23 */     this.a = (paramCharset != null ? paramCharset : Charset.defaultCharset());
/*    */   }
/*    */   
/*    */   public String getName()
/*    */   {
/* 28 */     return "FortexJSONEncoder";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected TransformationResult<FortexJSONMsg, Buffer> transformImpl(AttributeStorage paramAttributeStorage, FortexJSONMsg paramFortexJSONMsg)
/*    */     throws TransformationException
/*    */   {
/* 36 */     if (paramFortexJSONMsg == null) {
/* 37 */       throw new TransformationException("Input could not be null");
/*    */     }
/*    */     byte[] arrayOfByte;
/*    */     try
/*    */     {
/* 42 */       String str = paramFortexJSONMsg.toString();
/* 43 */       if (this.b != null) {
/* 44 */         this.b.logOutMsg(str);
/*    */       }
/* 46 */       arrayOfByte = str.getBytes(this.a.name());
/*    */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/* 48 */       throw new TransformationException("Charset " + this.a.name() + " is not supported", localUnsupportedEncodingException);
/*    */     }
/*    */     
/* 51 */     Buffer localBuffer = obtainMemoryManager(paramAttributeStorage).allocate(arrayOfByte.length);
/* 52 */     localBuffer.put(arrayOfByte);
/* 53 */     localBuffer.flip();
/* 54 */     localBuffer.allowBufferDispose(true);
/*    */     
/* 56 */     return TransformationResult.createCompletedResult(localBuffer, null);
/*    */   }
/*    */   
/*    */   public boolean hasInputRemaining(AttributeStorage paramAttributeStorage, FortexJSONMsg paramFortexJSONMsg)
/*    */   {
/* 61 */     return paramFortexJSONMsg != null;
/*    */   }
/*    */   
/*    */   public Charset getCharset() {
/* 65 */     return this.a;
/*    */   }
/*    */   
/*    */   public void setCharset(Charset paramCharset) {
/* 69 */     this.a = paramCharset;
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\FortexJSONEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */