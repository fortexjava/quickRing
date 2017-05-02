/*    */ package com.fortex.lib.globalservices;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class BrowserDetector
/*    */ {
/*    */   public static class BrowserInfo
/*    */   {
/* 12 */     public String b_name = "NA";
/* 13 */     public String b_ver = "NA";
/* 14 */     public String os_name = "NA";
/* 15 */     public String os_ver = "NA";
/* 16 */     public boolean isMobile = false;
/*    */     
/*    */     public String toString()
/*    */     {
/* 20 */       StringBuilder localStringBuilder = new StringBuilder();
/* 21 */       localStringBuilder.append(this.b_name == null ? "NA" : this.b_name);
/* 22 */       localStringBuilder.append("/");
/* 23 */       localStringBuilder.append(this.b_ver == null ? "NA" : this.b_ver);
/* 24 */       localStringBuilder.append("/");
/* 25 */       localStringBuilder.append(this.os_name == null ? "NA" : this.os_name);
/* 26 */       localStringBuilder.append("/");
/* 27 */       localStringBuilder.append(this.os_ver == null ? "NA" : this.os_ver);
/* 28 */       localStringBuilder.append("/");
/* 29 */       localStringBuilder.append(this.isMobile ? "Mobile" : "Desktop");
/* 30 */       return localStringBuilder.toString();
/*    */     }
/*    */   }
/*    */   
/*    */   private static String a(String paramString1, JSONObject paramJSONObject, String paramString2, int paramInt, String paramString3) {
/*    */     try {
/* 36 */       if ((paramString1 == null) || (paramJSONObject == null) || (paramString2 == null) || (paramInt < 0)) {
/* 37 */         return paramString3;
/*    */       }
/* 39 */       String str = paramJSONObject.optString(paramString2);
/* 40 */       if ((str == null) || (str.length() == 0)) {
/* 41 */         return paramString3;
/*    */       }
/*    */       
/*    */ 
/*    */ 
/* 46 */       Pattern localPattern = Pattern.compile(str);
/* 47 */       Matcher localMatcher = localPattern.matcher(paramString1);
/* 48 */       if ((localMatcher.find()) && (localMatcher.groupCount() >= paramInt)) {
/* 49 */         return localMatcher.group(paramInt);
/*    */       }
/*    */     } catch (Exception localException) {
/* 52 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*    */     }
/* 54 */     return paramString3;
/*    */   }
/*    */   
/*    */   public static BrowserInfo getBrowserInfo(String paramString, JSONObject paramJSONObject) {
/* 58 */     BrowserInfo localBrowserInfo = new BrowserInfo();
/* 59 */     if ((paramString == null) || (paramJSONObject == null)) {
/* 60 */       return localBrowserInfo;
/*    */     }
/*    */     try {
/* 63 */       JSONObject localJSONObject2 = paramJSONObject.optJSONObject("default");
/* 64 */       localBrowserInfo.os_name = a(paramString, localJSONObject2, "os_name", 1, null);
/* 65 */       localBrowserInfo.os_ver = a(paramString, localJSONObject2, "os_ver", 1, null);
/* 66 */       localBrowserInfo.isMobile = (a(paramString, localJSONObject2, "mobile", 0, null) != null);
/*    */       
/* 68 */       JSONArray localJSONArray = paramJSONObject.optJSONArray("custom");
/* 69 */       for (int i = 0; (localJSONArray != null) && (i < localJSONArray.length()); i++) {
/* 70 */         JSONObject localJSONObject1 = localJSONArray.getJSONObject(i);
/* 71 */         if (a(paramString, localJSONObject1, "b_id", 0, null) != null) {
/* 72 */           localBrowserInfo.b_name = localJSONObject1.optString("b_name");
/* 73 */           localBrowserInfo.b_ver = a(paramString, localJSONObject1, "b_ver", 1, localBrowserInfo.b_ver);
/* 74 */           localBrowserInfo.os_name = a(paramString, localJSONObject1, "os_name", 1, localBrowserInfo.os_name);
/* 75 */           localBrowserInfo.os_ver = a(paramString, localJSONObject1, "os_ver", 1, localBrowserInfo.os_ver);
/* 76 */           localBrowserInfo.isMobile = (a(paramString, localJSONObject1, "mobile", 0, localBrowserInfo.isMobile ? "" : null) != null);
/* 77 */           break;
/*    */         }
/*    */       }
/*    */     } catch (Exception localException) {
/* 81 */       FortexLogger.defaultLogger.error(new Throwable().getStackTrace()[0] + " exception: " + localException.getMessage());
/*    */     }
/* 83 */     return localBrowserInfo;
/*    */   }
/*    */ }


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\globalservices\BrowserDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */