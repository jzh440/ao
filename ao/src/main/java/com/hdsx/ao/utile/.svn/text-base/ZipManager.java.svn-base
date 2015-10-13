package com.hdsx.ao.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
// TODO: Auto-generated Javadoc
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 此类用于文件解压缩.
 *
 * @
 * @author    jingzh(2013-4-15)
 * @version   1.0.0
 */
public class ZipManager {
	private static Logger log=LoggerFactory.getLogger(ZipManager.class);
	public static List<String> unZipFile(String startUrl,String destUrl)
	{
		List<String> urllist=new ArrayList<String>();
		try {
			 ZipFile zip = new ZipFile(new File(startUrl), "GBK");
			 Enumeration<?> entries = zip.getEntries();
			 while(entries.hasMoreElements()){
		        	ZipEntry entry = (ZipEntry)entries.nextElement(); 
		        	InputStream in = zip.getInputStream(entry); 
		            String entryName=destUrl+"\\"+ entry.getName();
		            String outPath = (entryName).replaceAll("\\\\", "/");
		            System.out.println("-------------->"+outPath);
		            String outDir=outPath.substring(0,outPath.lastIndexOf("/"));
		            File file = new File(outDir); 
			        if(!file.exists()){ 
			             file.mkdirs(); 
			        } 
			        if(new File(outPath).isDirectory())
			        {
			        	continue;
			        }
			        System.out.println("------------->"+outPath);
		            OutputStream out = new FileOutputStream(outPath); 
		            byte[] buf1 = new byte[1024]; 
		            int len=0; 
				    while((len=in.read(buf1))!=-1){ 
				           out.write(buf1,0,len); 
				    } 
		           in.close(); 
			       out.close(); 
			       urllist.add(entryName);
		        }
		} catch (IOException e) {
			log.error("解压文件出错："+e.getMessage());
			e.printStackTrace();
		} 
        System.out.println("******************解压完毕********************"); 
        return urllist;
	}
	 /**
 	 * Release zip files.
 	 *
 	 * @param zipFile the zip file
 	 * @param descDir the desc dir
 	 */
 	public static void releaseZipFiles(File zipFile,String descDir){
		 try {
			 ZipFile zip = new ZipFile(zipFile, "GBK");
			 Enumeration<?> entries = zip.getEntries();
			 while(entries.hasMoreElements()){
				 ZipEntry entry = (ZipEntry)entries.nextElement(); 
				 InputStream in = zip.getInputStream(entry); 
				 String outPath = (descDir+entry.getName()).replaceAll("\\\\", "/");; 
				 if(new File(outPath).isDirectory()){ 
					 continue; 
				 } 
				 File file = new File(descDir); 
				 if(!file.exists()){ 
					 file.mkdirs(); 
				 } 
				 OutputStream out = new FileOutputStream(outPath); 
				 byte[] buf1 = new byte[1024]; 
				 int len; 
				 while((len=in.read(buf1))>0){ 
					 out.write(buf1,0,len); 
				 } 
				 in.close(); 
				 out.close(); 
			 }
		 } catch (IOException e) {
			 log.error("解压文件出错："+e.getMessage());
			 e.printStackTrace();
		 } 
		 System.out.println("******************解压完毕********************"); 
	 }
	 
 	/**
 	 * Description:给压缩文件命名.
 	 *
 	 * @param zipName the zip name
 	 * @param relativePath  ：输出文件相对路径
 	 * @param directory ：压缩文件路径
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	 public static void compressedFile(String zipName,String relativePath,String directory) throws IOException{
		 
		 if(zipName==null||zipName.equals(""))
		 {
			 File temp=new File(directory);
			 if (temp.isDirectory()) 			//输出路径是一个目录
			 {
				 zipName = directory + ".zip";
	         } 
			 else if(directory.indexOf(".") > 0)//输出路径是一个文件
			 {
	        	 zipName = directory.substring(0, directory.lastIndexOf("."))+ ".zip";
	                    
	         }
			 else								
	         {
				zipName = directory + ".zip";   //输出路径指向文件木有类型
	         }
		 }
		try 
		{
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName));
			compressedFile(zos,relativePath,directory);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		 
	 } 
	 
 	/**
 	 * Description:判断压缩文件里面是否有子文件夹.
 	 *
 	 * @param zos the zos
 	 * @param relativePath the relative path
 	 * @param absolutPath the absolut path
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	 private static void compressedFile(ZipOutputStream zos,String relativePath,String absolutPath) throws IOException{
		 File file = new File(absolutPath);
	     if (file.isDirectory()) 
	     {
	            File[] files = file.listFiles();
	            for(File temp:files)
	            {
	            	if(temp.isDirectory())
	            	{
	            		String newRelativePath = relativePath + temp.getName() + File.separator;//一个分隔符
	                    createZipNode(zos, newRelativePath);
	                    compressedFile(zos, newRelativePath, temp.getPath());
	            	}
	            	else
	            	{
	            		compressedFile(zos, temp, relativePath);
	            	}
	            }
	            
	            
	        } 
	     else 
	     {
	    	 compressedFile(zos, file, relativePath);
	     }
	 }
	 
 	/**
 	 * Description:压缩文件.
 	 *
 	 * @param zos the zos
 	 * @param file the file
 	 * @param relativePath the relative path
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	 private static void compressedFile(ZipOutputStream zos, File file,String relativePath) throws IOException {
	        ZipEntry entry = new ZipEntry(relativePath + file.getName());
	        zos.putNextEntry(entry);
	        InputStream is = null;
	        try 
	        {
	            is = new FileInputStream(file);
	            int BUFFERSIZE = 2 << 10;
	            int length = 0;
	            byte[] buffer = new byte[BUFFERSIZE];
	            while ((length = is.read(buffer, 0, BUFFERSIZE)) >= 0) {
	                zos.write(buffer, 0, length);
	            }
	            zos.flush();
	            zos.closeEntry();
	        } 
	        catch (IOException ex) 
	        {
	             ex.printStackTrace();
	        } 
	        finally 
	        {
	            if (null != is) 
	            {
	                is.close();
	            }
	        }
	    }
	
	/**
	 * Description：创建相对路径目录.
	 *
	 * @param zos the zos
	 * @param relativePath the relative path
	 */
	private static void createZipNode(ZipOutputStream zos, String relativePath)
	{
	        ZipEntry zipEntry = new ZipEntry(relativePath);
	        try 
	        {
				zos.putNextEntry(zipEntry);
				zos.closeEntry();
			}
	        catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	 }
	public static void main(String[] args) {
		unZipFile("E:\\file\\路线.zip","E:\\file\\zipout\\");
	}

}
