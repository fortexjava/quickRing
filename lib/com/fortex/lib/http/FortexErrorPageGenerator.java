/*    */ package com.fortex.lib.http;
/*    */ 
/*    */ import org.glassfish.grizzly.http.server.ErrorPageGenerator;
/*    */ import org.glassfish.grizzly.http.server.Request;
/*    */ 
/*    */ public class FortexErrorPageGenerator implements ErrorPageGenerator
/*    */ {
/*    */   protected final String a;
/*    */   
/*    */   public FortexErrorPageGenerator(String paramString)
/*    */   {
/* 12 */     this.a = paramString;
/*    */   }
/*    */   
/*    */   public String generate(Request paramRequest, int paramInt, String paramString1, String paramString2, Throwable paramThrowable) {
/* 16 */     String str1 = paramRequest.getRequestURI();
/* 17 */     String str2 = paramRequest.getRequestURL().toString();
/* 18 */     String str3 = str2.substring(0, str2.length() - str1.length());
/*    */     
/* 20 */     String str4 = null;
/*    */     
/* 22 */     if (!this.a.isEmpty()) {
/*    */       try {
/* 24 */         str4 = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(this.a, new String[0])));
/* 25 */         str4 = str4.replaceAll("##HOST##", str3);
/* 26 */         str4 = str4.replaceAll("##REQ_URL##", str1);
/*    */       } catch (Exception localException) {
/* 28 */         System.out.println(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*    */       }
/*    */     }
/*    */     
/* 32 */     if (str4 == null) {
/* 33 */       str4 = "<!DOCTYPE HTML>\n<html>\n<head>\n    <meta charset=\"utf-8\" />\n    <title>Error</title>\n</head>\n\n<body>\nThe requested URL \"" + str1 + "\" was not found on this server.\n" + "</body>\n" + "\n" + "</html>";
/*    */     }
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
/* 48 */     return str4;
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\http\FortexErrorPageGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */