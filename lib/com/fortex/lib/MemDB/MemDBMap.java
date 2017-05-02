/*    */ package com.fortex.lib.MemDB;
/*    */ 
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ public class MemDBMap<K, V>
/*    */   extends ConcurrentHashMap<K, V>
/*    */ {
/*    */   protected String a;
/*    */   
/*    */   public MemDBMap(String paramString)
/*    */   {
/* 12 */     this.a = paramString;
/*    */   }
/*    */   
/*    */   public String getRefID()
/*    */   {
/* 17 */     return this.a;
/*    */   }
/*    */   
/*    */   public V put(K paramK, V paramV)
/*    */   {
/* 22 */     return (V)super.put(paramK, paramV);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public V remove(Object paramObject)
/*    */   {
/* 29 */     return (V)super.remove(paramObject);
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\MemDB\MemDBMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */