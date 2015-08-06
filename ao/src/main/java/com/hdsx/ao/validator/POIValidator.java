package com.hdsx.ao.validator;

import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.hdsx.ao.reader.XlsReader;

public class POIValidator {

	 /** 模板文件 */
	private Workbook templete;
	
	 /** 目标文件*/
	private Workbook target;
	
	public POIValidator(){}
	/**
	 * 构造模板文件验证器
	 *
	 * @param templetePath 模板文件路径
	 * @param targetPath 目标文件路径
	 */
	public POIValidator(String templetePath,String targetPath) throws Exception{
		this.templete=XlsReader.getWorkbookByVersion(templetePath);
		this.target=XlsReader.getWorkbookByVersion(targetPath);
	}
	
	/**
	 * 构造模板文件验证器
	 *
	 * @param templete 模板流
	 * @param target 目标流
	 * @throws Exception 
	 */
	public POIValidator(InputStream templeteStream,InputStream targetStream) throws Exception{
		Workbook templete=XlsReader.getWorkbook(templeteStream);
		Workbook target=XlsReader.getWorkbook(targetStream);
		new POIValidator(templete,target);
	}
	/**
	 * 构造模板文件验证器
	 *
	 * @param templete 模板文件
	 * @param target 目标文件
	 */
	public POIValidator(Workbook templete,Workbook target){
		this.templete=templete;
		this.target=target;
	}
	
	
	/**
     * 验证文件格式是否一致
     *
     * @return true,若成功返回true，若失败返回false
     */
	public boolean validate() throws POIException{
		if(templete==null||target==null)
		{
			return false;
		}
		return validate(templete,target);
	}

	/**
     * 验证文件格式是否一致
     * 
     * @param templete the templete
     * @param target the request
     * @return true,若成功返回true，若失败返回false
     */
	public boolean validate(Workbook templete,Workbook target) throws POIException
	{
		try
		{
			if(templete.getNumberOfSheets()!=target.getNumberOfSheets())
			{
				return false;
			}
			for(int i=0,len=templete.getNumberOfSheets();i<len;i++)
			{
				Sheet templeteSheet= templete.getSheetAt(i);
				Sheet targetSheet= target.getSheetAt(i);
				if(!validate(templeteSheet,targetSheet))
				{
					return false;
				}
			}
		}
		catch(Exception e)
		{
			throw new POIException(ExceptionMsg.TARGET_SHEET_NUMS);
		}
		return true;
	}
	/**
     * 验证文件格式是否一致
     * 
     * @param templete the templeteSheet
     * @param target the taregtSheet
     * @return true,若成功返回true，若失败返回false
     */
	protected boolean validate(Sheet templeteSheet,Sheet taregtSheet) throws POIException
	{
		if (null == templeteSheet || null == taregtSheet) 
		{System.out.println("111");
			return false;
		}
		if(templeteSheet.getLastRowNum()!=taregtSheet.getLastRowNum())
		{System.out.println("222");
			return false;
		}
		for(int i=0;i<8;i++)
		{
			Row row1=templeteSheet.getRow(i);
			Row row2=taregtSheet.getRow(i);
			if(!validate(row1,row2))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
     * 验证文件格式是否一致
     * 
     * @param templete the templateRow
     * @param target the targetRow
     * @return true,若成功返回true，若失败返回false
     */
	protected boolean validate(Row templateRow, Row targetRow) {
		if (null == templateRow || null == targetRow) {
			System.out.println("333");
			return true;
		}
		int cells=templateRow.getLastCellNum();
		// 遍历出该行的每一个CELL,进行验证是否一致
		for (int i = 0; i < cells; i++) {
			// 取模板cell
			Cell tCell = templateRow.getCell((short) i);
			// 取文件cell
			Cell fCell = targetRow.getCell((short) i);
			// 判断模板cell 与 文件cell是否一则,不一致则返回
			if (!this.validate(tCell, fCell)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 验证cell的合法性,一般分为合并后的cell与有值的cell
	 * 
	 * @param templateCell
	 *            模板cell
	 * @param fileCell
	 *            文件cell
	 * @return 返回boolean值,两cell是否一致
	 */
	protected boolean validate(Cell templateCell, Cell fileCell) {

		// 若模板cell为空且文件cell为空,返回true
		if (null == templateCell && null == fileCell) {
			return true;
		}
		// 若模板cell不为空且文件cell为空,返回false
		if (null != templateCell && null == fileCell) {
			System.out.println("--------------"+templateCell.getRowIndex()+","+fileCell.getColumnIndex());
			return false;
		}
		// 若模板cell为空且文件cell不为空,返回false
		if (null == templateCell && null != fileCell) {
			System.out.println("--------------"+templateCell.getRowIndex()+","+fileCell.getColumnIndex());
			return false;
		}
		// 若模板cell为合并的单元格,而文件cell不为合并的单元格,则不一致,返回false
		if (Cell.CELL_TYPE_BLANK == templateCell.getCellType()
				&& Cell.CELL_TYPE_BLANK != fileCell.getCellType()) {
			System.out.println("--------------"+templateCell.getRowIndex()+","+fileCell.getColumnIndex());
			return false;
		}
		//判断单元格值是否相等
		if(!cellEquals(templateCell,fileCell))
		{
			return false;
		}
		return true;
	}
	protected  static boolean cellEquals(Cell tepCell,Cell tarCell){
		Object tepValue=getCellValue(tepCell);
		Object tarValue=getCellValue(tarCell);
		if(tepValue==null&&tarValue==null)
		{
			System.out.println(tepCell+","+tarCell);
			return true;
		}
		if(tepValue!=null&&tarValue==null)
		{
			System.out.println(tepCell+","+tarCell);
			return false;
		}
		if(tepValue==null&&tarValue!=null)
		{
			System.out.println(tepCell+","+tarCell);
			return false;
		}
		System.out.println(tepValue+"|"+tarValue);
		return tepValue.equals(tarValue);
	}
	protected  static Object getCellValue(Cell cell){
		switch(cell.getCellType())
		{
			case Cell.CELL_TYPE_STRING:  return cell.getStringCellValue().replaceAll("\n", "").trim(); 
			case Cell.CELL_TYPE_NUMERIC: return isDate(cell);
			case Cell.CELL_TYPE_FORMULA: return cell.getCellFormula();
			case Cell.CELL_TYPE_BOOLEAN: return cell.getBooleanCellValue() ;
			case Cell.CELL_TYPE_BLANK:   return "";					   
			case Cell.CELL_TYPE_ERROR:   return new POIException("错误单元格位置:("+cell.getRowIndex()+","+cell.getColumnIndex()+")");
			default: 					 return new POIException(ExceptionMsg.CELL_TYPE_ERROR);
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
}
