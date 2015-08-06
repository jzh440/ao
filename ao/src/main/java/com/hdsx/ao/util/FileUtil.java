package com.hdsx.ao.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.ao.resource.Resources;




public class FileUtil {
	private Logger log=LoggerFactory.getLogger(FileUtil.class);
	/** 读取文件路径. */
	private static String targetPath;
	
	/** 解压文件路径. */
	private static String  destPath;
	
	/** 存储解压Excel文件路径. */
	public List<String> xlsPaths=new ArrayList<String>();
	
	/** 存储解压SHAPE文件路径. */
	public List<String> shpPaths=new ArrayList<String>();
	
	/**
	 * 解压文件.
	 *
	 * @param fileName the file name
	 */
	public void unZipFile(String path){
		 System.out.println(path+","+path.substring(0,path.lastIndexOf("\\")));
		 List<String> urls=ZipManager.unZipFile(path, path.substring(0,path.lastIndexOf("\\")));
		 for(String url:urls)
		 {
			 log.debug("解压文件路径：["+url+"]");
			 if(url.endsWith(".xls"))
				 xlsPaths.add(url);
			 else if(url.endsWith(".shp"))
				 shpPaths.add(url);
			 else
				 log.debug("不记录路径 ["+url+"]");
		 }
	}
	
	/**
	 * 删除解压文件.
	 *
	 * @return true, if successful
	 */
	public boolean delete(){
		File outfile=new File(destPath);
		return outfile.delete();
	}
	/**
	 * 读取配置文件
	 */
	static
	{
		try 
		{
			Properties prop=Resources.getResourceAsProperties("fileUrl.properties");
			targetPath=prop.get("targetPath").toString();
			destPath=prop.get("destPath").toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the target path.
	 *
	 * @return the target path
	 */
	public static String getTargetPath(){
		return targetPath;
	}
	
	/**
	 * Gets the dest path.
	 *
	 * @return the dest path
	 */
	public static String getDestPath(){
		return destPath;
	}
	
}
