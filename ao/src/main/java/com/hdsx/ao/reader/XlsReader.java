package com.hdsx.ao.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsReader {
	/** The Constant V_2003. */
	private static final int V_2003=2003;
	
	/** The Constant V_2007. */
	private static final int V_2007=2007;
	
	/** The version. */
	private static int VERSION=2003;
	
	/**
 	 * Gets the workbook by version.
 	 *
 	 * @param url the url
 	 * @return the workbook by version
 	 * @throws FileNotFoundException the file not found exception
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
 	public static Workbook getWorkbookByVersion(String url) throws Exception{
		VERSION=url.endsWith(".xls")?V_2003:V_2007;
		switch(VERSION)
		{
			case V_2003: return (Workbook) new HSSFWorkbook(new FileInputStream(url));
			case V_2007: return (Workbook) new XSSFWorkbook(new FileInputStream(url));
			default	   : return (Workbook) new HSSFWorkbook(new FileInputStream(url));
		}
	}
 	public static Workbook getWorkbook(InputStream stream) throws Exception{
		try
		{
			return (Workbook) new HSSFWorkbook(stream);
		}
		catch(Exception e)
		{
			return (Workbook) new XSSFWorkbook(stream);
		}
	}
 	
}
