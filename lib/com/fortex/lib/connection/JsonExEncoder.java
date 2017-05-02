/*    */ package com.fortex.lib.connection;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.nio.charset.Charset;
/*    */ import org.glassfish.grizzly.AbstractTransformer;
/*    */ import org.glassfish.grizzly.Buffer;
/*    */ import org.glassfish.grizzly.TransformationException;
/*    */ import org.glassfish.grizzly.TransformationResult;
/*    */ import org.glassfish.grizzly.attributes.AttributeStorage;
/*    */ import org.glassfish.grizzly.memory.MemoryManager;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class JsonExEncoder
/*    */   extends AbstractTransformer<JSONObject, Buffer>
/*    */ {
/* 16 */   protected static int a = 8;
/*    */   protected Charset b;
/* 18 */   protected FortexConnection c = null;
/*    */   
/*    */   public JsonExEncoder() {
/* 21 */     this(null, null);
/*    */   }
/*    */   
/*    */   public JsonExEncoder(FortexConnection paramFortexConnection, Charset paramCharset) {
/* 25 */     this.c = paramFortexConnection;
/* 26 */     this.b = (paramCharset != null ? paramCharset : Charset.defaultCharset());
/*    */   }
/*    */   
/*    */   public String getName()
/*    */   {
/* 31 */     return "JsonExEncoder";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected TransformationResult<JSONObject, Buffer> transformImpl(AttributeStorage paramAttributeStorage, JSONObject paramJSONObject)
/*    */     throws TransformationException
/*    */   {
/* 39 */     if (paramJSONObject == null) {
/* 40 */       throw new TransformationException("Input could not be null");
/*    */     }
/*    */     byte[] arrayOfByte;
/*    */     try
/*    */     {
/* 45 */       String str1 = paramJSONObject.toString();
/* 46 */       String str2 = String.format("%08d", new Object[] { Integer.valueOf(str1.length() + a) }) + str1;
/* 47 */       if (this.c != null) {
/* 48 */         this.c.logOutMsg(str2);
/*    */       }
/* 50 */       arrayOfByte = str2.getBytes(this.b.name());
/*    */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/* 52 */       throw new TransformationException("Charset " + this.b.name() + " is not supported", localUnsupportedEncodingException);
/*    */     }
/*    */     
/* 55 */     Buffer localBuffer = obtainMemoryManager(paramAttributeStorage).allocate(arrayOfByte.length);
/* 56 */     localBuffer.put(arrayOfByte);
/* 57 */     localBuffer.flip();
/* 58 */     localBuffer.allowBufferDispose(true);
/*    */     
/* 60 */     return TransformationResult.createCompletedResult(localBuffer, null);
/*    */   }
/*    */   
/*    */   public boolean hasInputRemaining(AttributeStorage paramAttributeStorage, JSONObject paramJSONObject)
/*    */   {
/* 65 */     return paramJSONObject != null;
/*    */   }
/*    */   
/*    */   public Charset getCharset() {
/* 69 */     return this.b;
/*    */   }
/*    */   
/*    */   public void setCharset(Charset paramCharset) {
/* 73 */     this.b = paramCharset;
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\JsonExEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */