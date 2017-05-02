package com.fortex.quickRing;

import java.lang.reflect.Constructor;

import org.apache.log4j.Logger;

import com.fortex.lib.globalservices.FortexLogger;
import com.fortex.quickRing.utils.ConfigSetting;
import com.fortex.quickRing.utils.GenerateFortexFix;
import com.fortex.webconsle.RunningServer;
//import com.fortex.webconsle.RunningServer;

public class App {
	
	/**
	 * 
	 * <p>Description:Get all configuration file to start Initiator server and Acceptor for each instance.</p> 
	 *
	 * @author Ivan Huo
	 * @date 2016-08-05 
	 * @throws Exception
	 */
	
	private void start() throws Exception {
		String starterClassName = ConfigSetting.getServerProperty("starterClass");
		String initiatorConfigFile = ConfigSetting.getLocPath() + ConfigSetting.getServerProperty("initiatorConfigFile");
		String acceptorConfigFile = ConfigSetting.getLocPath() + ConfigSetting.getServerProperty("acceptorConfigFile");
		Constructor<?> con = Class.forName(starterClassName).getConstructor(String.class, String.class);
		AbstractServer starter = (AbstractServer)con.newInstance(initiatorConfigFile,acceptorConfigFile);	
		starter.doExecute();
	}
	
	public static void main(String[] args) throws Exception{
		
		//ConfigSetting s = new ConfigSetting();
//		try{
			RunningServer runningServer = new RunningServer();
			runningServer.start();
			GenerateFortexFix.init("/spec/FIX44_GENERATE_FILE.xml");
			App app = new App();			
			System.setProperty ("WORKDIR", ConfigSetting.getLocPath());
			org.apache.log4j.PropertyConfigurator.configure(App.class.getResourceAsStream("/log4j.properties"));
			FortexLogger.defaultLogger = Logger.getLogger("Event");
			app.start();
			
			
//		}
//		catch(Throwable e){
//			Logger.getLogger("EventError").error("Running Error:" + e);
//		}
	}
}
