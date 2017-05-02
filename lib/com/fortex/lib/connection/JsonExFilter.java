/*    */ package com.fortex.lib.connection;
/*    */ 
/*    */ import java.nio.charset.Charset;
/*    */ import org.glassfish.grizzly.Buffer;
/*    */ import org.glassfish.grizzly.filterchain.AbstractCodecFilter;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ 
/*    */ public final class JsonExFilter
/*    */   extends AbstractCodecFilter<Buffer, JSONObject>
/*    */ {
/*    */   public JsonExFilter()
/*    */   {
/* 14 */     this(null, null);
/*    */   }
/*    */   
/*    */   public JsonExFilter(FortexConnection paramFortexConnection, Charset paramCharset) {
/* 18 */     super(new JsonExDecoder(paramFortexConnection, paramCharset), new JsonExEncoder(paramFortexConnection, paramCharset));
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\JsonExFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */