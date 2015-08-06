package com.hdsx.ao.parse;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.ao.bean.XlsColumn;
import com.hdsx.ao.bean.XlsTable;
import com.hdsx.ao.util.ReflectUtils;
import com.hdsx.ao.util.StringSpace;



/**
 * -----------------------------------------.
 * @param <T>
 *
 * @文件: XmlParse.java
 * @作者: jingzh
 * @邮箱: jzh440@163.com
 * @时间: 2014-1-24
 * @描述: 创建、读取、解析、输出xml
 * -----------------------------------------
 */
public class XlsParse {
	
	private static Logger log=LoggerFactory.getLogger(XlsParse.class);
	/** The error. */
	private static Map<String,Object> error=new HashMap<String,Object>();
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> parseExcel(Workbook workbook,XlsTable table)throws Exception {
		List<T> resultList=new ArrayList<T>();
		for(int sheetIndex=0;sheetIndex<workbook.getNumberOfSheets();sheetIndex++)
		{
			Sheet sheet=workbook.getSheetAt(sheetIndex);
			int ignoreRows=Integer.valueOf(table.getRowStart());
			for(int rowIndex=ignoreRows;rowIndex<sheet.getLastRowNum();rowIndex++)
			{
				Row row=sheet.getRow(rowIndex);
				Object bean=table.getBean();
				reflectBean(bean,row,table.getColumns());
				resultList.add((T) bean);
			}
		}
		return resultList;
	}
 	protected  static void reflectBean(Object bean,Row row,List<XlsColumn> columns){
 		for(int cellIndex=0;cellIndex<row.getLastCellNum();cellIndex++)
		{
			Cell cell=row.getCell(cellIndex);
			if(cell==null)continue;
			for(XlsColumn column:columns)
	 		{
	 			if(column.getCellCode().equals("cell_"+cell.getColumnIndex()))
	 			{
	 				Object value=getCellValue(cell);
	 				ReflectUtils.setValue(column.getField().toLowerCase(),value ,bean);break;
	 			}
	 		}
		}
 		
 	}
 	/**
	 * Gets the cell value.
	 *
	 * @param cell the cell
	 * @param map the map
	 * @return the cell value
	 */
	protected  static Object getCellValue(Cell cell){
		StringBuffer errorInfo=null;
		switch(cell.getCellType())
		{
			case Cell.CELL_TYPE_STRING:  return cell.getStringCellValue().replaceAll("\n", "").trim(); 
			case Cell.CELL_TYPE_NUMERIC: return isDate(cell);
			case Cell.CELL_TYPE_FORMULA: return cell.getCellFormula();
			case Cell.CELL_TYPE_BOOLEAN: return cell.getBooleanCellValue() ;
			case Cell.CELL_TYPE_BLANK:   return "";					   
			case Cell.CELL_TYPE_ERROR:   errorInfo=new StringBuffer(); errorInfo.append("错误单元格位置:(x,y)---("+cell.getRowIndex()+","+cell.getColumnIndex()+")");error.put("cell_"+cell.getColumnIndex(),errorInfo);    return "";
			default: 					 errorInfo=new StringBuffer(); errorInfo.append("错误单元格位置:(x,y)---("+cell.getRowIndex()+","+cell.getColumnIndex()+")");error.put("cell_"+cell.getColumnIndex(),errorInfo);    return "";
		}
	}
	protected static  Object isDate(Cell cell){
		if (HSSFDateUtil.isCellDateFormatted(cell)) {
			return HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
        }
		else{
        	 return cell.getNumericCellValue();
       }
	}
	/**
	 * 根据xml文件映射类型来校验数据格式.
	 *
	 * @param column xml一行
	 * @param value 一个单元格值
	 * @return the object
	 */
	protected  static Object convertValueType(XlsColumn column,Object value){
		if(value==null||value.equals(""))return "";
		String strValue=value.toString().trim();
		switch(column.getCellType())
		{
			case  STRING:
				return new String(StringSpace.parseString(strValue));
			case  INTEGER:
				return new Integer(StringSpace.parseInt(strValue));
			case  DOUBLE:
				return new Double(StringSpace.parseDouble(strValue));
			case  BOOLEAN:
				return new Boolean(strValue);
			case  DATE:
				DateFormat df=null;
				if(value.toString().length()==4) df=new SimpleDateFormat("yyyy");
				else df=new SimpleDateFormat("yyyyMMdd");
				Date date=null;
				try 
				{
					date=df.parse(StringSpace.parseString(value.toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return date;
			case  BLANK:
				return "";
			default:System.out.println("你是神马类型！！！"+value);return "";
		}
	}
}
