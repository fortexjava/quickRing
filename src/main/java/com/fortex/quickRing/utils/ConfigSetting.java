package com.fortex.quickRing.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author Ivan Huo
 *
 */
public class ConfigSetting {
	private static final Properties SERVER = new Properties();
	private static final Properties DATABASE = new Properties();
	private static final Properties ADAPTER = new Properties();
	static{
		try {
			SERVER.load(new FileInputStream(getLocPath() + "config/server/server.properties"));
			DATABASE.load(new FileInputStream(getLocPath() + "config/server/database.properties"));
			if (new File(getLocPath() + "config/quote/qmSetting.properties").exists())
				ADAPTER.load(new FileInputStream(getLocPath() +"config/quote/qmSetting.properties"));
		} catch (IOException e) {
			Logger.getLogger("EventError").error("load config file error.", e);
		}
	}
	
	public static String getLocPath(){
		String path= ConfigSetting.class.getProtectionDomain().getCodeSource().getLocation().getFile();
				
		if(path.toLowerCase().indexOf(".jar")>0){
			if(path.indexOf(File.separator)>0)
				path=path.substring(0, path.lastIndexOf(File.separator)) + File.separator;
			else
				path=path.substring(0, path.lastIndexOf("/")) + File.separator;
		}else{
			path = path + ".." + File.separator;
		}
		return path;
	}
	
	public static String getServerProperty(String key) {
		return SERVER.getProperty(key);
	}
	
	public static String getDatabaseProperty(String key) {
		return DATABASE.getProperty(key);
	}
	
	public static String getAdapterProperty(String key) {
		return ADAPTER.getProperty(key);
	}
}
