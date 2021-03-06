package com.hdsx.ao.request;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.ao.parse.XlsParse;
import com.hdsx.ao.reader.XlsReader;

public class XlsRequest extends AbstractRequest implements Request {

	private Logger log=LoggerFactory.getLogger(XlsRequest.class);
	
	public XlsRequest(String layer) {
		super(layer);
	}

	public <T> List<T> getList(String path) {
		List<T> list=null;
		try 
		{
			log.debug("------------开始解析Excel------------");
			Workbook workbook=XlsReader.getWorkbookByVersion(path);
			list=XlsParse.parseExcel(workbook,getTable());
			log.debug("------------结束解析Excel------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list.size()==0)
		{
			System.out.println("---------------:Excel数据条数为0,请检查Excel!");
		}
		return list;
	}

	public boolean getType(String path) {
		// TODO Auto-generated method stub
		return path.endsWith(TYPE.XLS);
	}

}
