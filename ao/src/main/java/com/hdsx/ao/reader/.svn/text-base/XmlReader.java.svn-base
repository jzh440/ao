package com.hdsx.ao.reader;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XmlReader {
	 //#region 字段定义
     /// <summary>
     /// XML文件的物理路径
     /// </summary>
     private String _filePath = "";
     /// <summary>
     /// Xml文档
     /// </summary>
     private Document _xml;
     /// <summary>
     /// XML的根节点
     /// </summary>
     private Element _element;
     //#endregion

     //#region 构造方法
     public XmlReader(String xmlFilePath)
     {
         //获取XML文件的绝对路径
         _filePath = xmlFilePath;
     }
     //#endregion

     //#region 创建XML的根节点
     private void createXMLElement()
     {
             //加载XML文件
    	 SAXBuilder sb = new SAXBuilder(); 
    	 try {
    		 //_xml= sb.build(new FileInputStream(_filePath));
    		 _xml = sb.build(getClass().getResourceAsStream("/xls-config.xml"));
    	 } catch (JDOMException e) {
    		 e.printStackTrace();
    	 } catch (IOException e) {
    		 e.printStackTrace();
    	 };
         //指定xml根节点
         _element=_xml.getRootElement();
     }
     //#endregion

    // #region 获取指定XPath表达式的节点对象
     public Element getNode(String node)
     {
         //创建XML的根节点
         createXMLElement();
         //返回XPath节点
         return _element.getChild(node);
     }
     //#endregion

     //#region 获取指定XPath表达式节点的值
     public String getValue(String node)
     {
         //创建XML的根节点
    	 createXMLElement();

         //返回XPath节点的值
         return _element.getChild(node).getValue();
     }
     //#endregion

     //#region 获取指定XPath表达式节点的属性值
     public String getAttributeValue(String node, String attributeName)
     {
         //创建XML的根节点
    	 createXMLElement();
         //返回node节点的属性值
         return _element.getChild(node).getAttributeValue(attributeName);
     }
    
}
