/**<p>Description</p>
 * @author Ivan Huo
 */
package com.fortex.quickRing;

import java.util.ArrayList;
import java.util.List;

import com.fortex.quickRing.utils.ConfigSetting;

/**
 * @author Administrator
 *
 */
public class Test {	
	
	public static void main(String args[]) {
//		System.out.println("start.." + System.currentTimeMillis());
//		LockSupport.parkNanos(1000000);
//		System.out.println("end  .." + System.currentTimeMillis());
		
//		try{
//			List lst = new ArrayList();
//			while(true){
//				lst.add(new OmObject());
//			}
//		}
//		catch(Throwable  e){
//			e.printStackTrace();
//		}
//		System.out.println("hello world again");
		 //String[] cmd = new String[] { "cmd.exe", "/C", "wmic process get name" };
		String cmd="nohup java -server -Xmx5g -Xms5g -Xmn3g -Xss512k -XX:MaxDirectMemorySize=2048m  -XX:+UseFastAccessorMethods  -XX:+ScavengeBeforeFullGC -XX:+UseBiasedLocking -XX:+AggressiveOpts -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:TargetSurvivorRatio=95 -XX:InitialTenuringThreshold=15 -XX:MaxTenuringThreshold=15 -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled -Djava.nio.channels.spi.SelectorProvider=sun.nio.ch.EPollSelectorProvider -jar /home/Demo/TRADE/quickRing-trade.jar > running.log &";
//		 String[] cmd=new String[]{"nohup java -server -Xmx5g -Xms5g -Xmn3g -Xss512k -XX:MaxDirectMemorySize=2048m  -XX:+UseFastAccessorMethods  -XX:+ScavengeBeforeFullGC -XX:+UseBiasedLocking -XX:+AggressiveOpts -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:TargetSurvivorRatio=95 -XX:InitialTenuringThreshold=15 -XX:MaxTenuringThreshold=15 -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled -Djava.nio.channels.spi.SelectorProvider=sun.nio.ch.EPollSelectorProvider -jar", "/home/Demo/TRADE/quickRing-trade.jar"," > running.log &"};
//		String []cmd = new String[]{"cd /home/Demo/TRADE/","nohup java -server -Xmx5g -Xms5g -Xmn3g -Xss512k -jar quickRing-trade.jar > running.log &" };
	   
//		try {	        	
//	            Process process = Runtime.getRuntime().exec(cmd);	            
//	            process.getOutputStream().close();  	  
//	            int exitValue = process.waitFor();  
//	            System.out.println("return value:" + exitValue);  
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//	        }
		
		if(false && (check())){
			System.out.println("Hello");
		}
		System.out.println("Go");
	} 
	
	static boolean check(){
		System.out.println("222");
		return true;
	}
	
}
