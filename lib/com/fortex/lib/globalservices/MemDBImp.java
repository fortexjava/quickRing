/*    */ package com.fortex.lib.globalservices;
/*    */ 
/*    */ import com.fortex.lib.MemDB.MemDB;
/*    */ import com.fortex.lib.MemDB.MemDBInf;
/*    */ import java.util.Map;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class MemDBImp
/*    */   implements MemDBInf
/*    */ {
/*    */   public boolean removeMap(String paramString)
/*    */   {
/*    */     try
/*    */     {
/* 28 */       return MemDB.removeMap(paramString);
/*    */     }
/*    */     catch (Exception localException) {
/* 31 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*    */     }
/* 33 */     return false;
/*    */   }
/*    */   
/*    */   public Map getMap(String paramString)
/*    */   {
/*    */     try {
/* 39 */       return MemDB.getMap(paramString);
/*    */     }
/*    */     catch (Exception localException) {
/* 42 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*    */     }
/* 44 */     return null;
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\globalservices\MemDBImp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */