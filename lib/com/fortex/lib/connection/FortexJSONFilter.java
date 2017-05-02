/*    */ package com.fortex.lib.connection;
/*    */ 
/*    */ import com.fortex.lib.protocol.FortexJSONMsg;
/*    */ import java.nio.charset.Charset;
/*    */ import org.glassfish.grizzly.filterchain.AbstractCodecFilter;
/*    */ 
/*    */ public final class FortexJSONFilter extends AbstractCodecFilter<org.glassfish.grizzly.Buffer, FortexJSONMsg>
/*    */ {
/*    */   public FortexJSONFilter()
/*    */   {
/* 11 */     this(null, null);
/*    */   }
/*    */   
/*    */   public FortexJSONFilter(FortexConnection paramFortexConnection, Charset paramCharset) {
/* 15 */     super(new FortexJSONDecoder(paramFortexConnection, paramCharset), new FortexJSONEncoder(paramFortexConnection, paramCharset));
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\FortexJSONFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */