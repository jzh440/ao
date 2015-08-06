/*
 * 
 */
package com.hdsx.ao.parse;


import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.ao.bean.TableStruct;
import com.hdsx.ao.bean.XlsColumn;
import com.hdsx.ao.bean.XlsTable;
import com.hdsx.ao.reader.XmlReader;
import com.hdsx.ao.request.XlsRequest;



// TODO: Auto-generated Javadoc
/**
 * -----------------------------------------.
 *
 * @文件: XmlParse.java
 * @作者: jingzh
 * @邮箱: jzh440@163.com
 * @时间: 2014-1-24
 * @描述: 创建、读取、解析、输出xml
 * -----------------------------------------
 */
public class XmlParse {
	private static Logger log=LoggerFactory.getLogger(XlsRequest.class);
	@SuppressWarnings("unchecked")
	public static TableStruct parseXml(String xml)
    {
       TableStruct configs=new TableStruct();
        try
        {
            XmlReader x = new XmlReader(xml);
            XlsTable config ;
            Element node = x.getNode("tables");
            List<Element> children=node.getChildren();
            for (Element item : children)
            {
	          log.debug(   
		        		  item.getAttributeValue("id") + " ; " +
		             	  item.getAttributeValue("rowStart")  + " ; " +
		             	  item.getAttributeValue("PKFieldsShape")  + " ; " +
		             	  item.getAttributeValue("PKFieldsExcel") );
                config = new XlsTable();
                config.setLayer(item.getAttributeValue("id").trim());
                config.setRowStart(item.getAttributeValue("rowStart").trim());
                config.setClassName(item.getAttributeValue("className").trim());
                config.setPKFieldsExcel(item.getAttributeValue("PKFieldsExcel").trim().split(";"));
                config.setPKFieldsShape(item.getAttributeValue("PKFieldsShape").trim().split(";"));
                config.setColumns(new ArrayList<XlsColumn>());
                configs.add(item.getAttributeValue("id").trim(), config);
                try
                {
             	   List<Element> nodes=item.getChildren();
                    for (Element item2 :nodes)
                    {
                        XlsColumn column = new XlsColumn();
                        column.setCellName(item2.getAttributeValue("cellName").trim()) ;
                        column.setCellCode(item2.getAttributeValue("cellCode").trim());
                        column.setField(item2.getAttributeValue("field").trim());
                        config.getColumns().add(column);
                    }
                }
                catch (Exception ex)
                {
             	   System.out.println("------xml配置文件读取时发生错误：");
                }
            }
        }
        catch (Exception ex)
        {
     	   System.out.println("------xml配置文件读完了！发生错误：");
        }
        return configs;
    }
}
