package com.fortex.quickRing.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

public class CSVUtil {
	
	private static String folderPath =ConfigSetting.getLocPath() + ConfigSetting.getServerProperty("statisticsCSVPath");
	//private static String fileName="stat";
	
	public static File writeCSVFile(String exportData, ArrayList<String> titles, String outPutPath, String fileName) throws IOException, FileNotFoundException {
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {			
			csvFile = new File(outPutPath + fileName);			
			if (!csvFile.exists()) {
				csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"),1024);			
				for (String title : titles) {
					csvFileOutputStream.write(title + ",");
				}
			}else{
				csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile,true), "UTF-8"),1024);
			}
			csvFileOutputStream.newLine();
			csvFileOutputStream.write(exportData);
			csvFileOutputStream.flush();
		}  finally {
			try {
				if(csvFileOutputStream!=null)
					csvFileOutputStream.close();
			} catch (IOException e) {
				Logger.getLogger("EventError").error(e);
			}
		}
		return csvFile;
	}
		
	public static RandomAccessFile writeCSVFileByLock(String exportData, String[] titles, String outPutPath, String fileName) throws IOException {
		//File csvFile = null;
		FileChannel channel = null;  
        FileLock lock = null;  
        RandomAccessFile raf = null;
        try {
        	File file = new File(outPutPath);
        	if(!file.exists()){
        		file.mkdirs();
        	}
             raf = new RandomAccessFile(outPutPath + fileName,"rw");             
            raf.seek(raf.length());  
            channel = raf.getChannel();                  
          do {  
              lock = channel.tryLock();  
          } while(null == lock);  
            
          if(raf.length()==0){
        	 StringBuilder cont = new StringBuilder();
        	for (String title : titles) {
        		cont.append(title);
        		cont.append(",");
			}
        	cont.append("\n");
        	cont.append(exportData + "\n");
            ByteBuffer sendBuffer=ByteBuffer.wrap(cont.toString().getBytes());            
            channel.write(sendBuffer);
          }
          else{        	  
          	  ByteBuffer sendBuffer=ByteBuffer.wrap((exportData+ "\n").toString().getBytes());            
              channel.write(sendBuffer);
          }
        }   finally {  
            if(lock != null) {  
                try {  
                    lock.release();  
                    lock = null;  
                } catch (IOException e) {  
                	Logger.getLogger("EventError").error(e);
                }  
            }  
            if(channel != null) {  
                try {  
                    channel.close();  
                    channel = null;  
                } catch (IOException e) {  
                	Logger.getLogger("EventError").error(e);
                }  
            }  
        }  
		return raf;
	}
	
	
	/**
	 * <p>Description:Write CSV stat of XRing</p>
	 * @author Ivan Huo
	 * @date 2017-02-17	
	 * @param exportData
	 * @return
	 * @throws IOException 
	 */
	public static RandomAccessFile writeXRingStat(String[] titles,String exportData, String fileName) throws IOException{
		//ArrayList<String> titles = generateXRingStaticCsvTitle();
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RandomAccessFile file = writeCSVFileByLock(exportData,titles,folderPath,fileName +"-"+ sdf.format(date) + ".csv");
		return file;
	}
}
