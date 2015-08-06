package com.hdsx.ao.request;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esri.arcgis.geodatabase.IFeatureCursor;
import com.esri.arcgis.geometry.IGeometry;
import com.hdsx.ao.bean.TableStruct;
import com.hdsx.ao.bean.XlsColumn;
import com.hdsx.ao.bean.XlsTable;
import com.hdsx.ao.parse.XmlParse;
import com.hdsx.ao.util.AOUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractRequest.
 */
public abstract class AbstractRequest {
	
	/** The structs. */
	private static TableStruct structs;
	
	/** The table. */
	private XlsTable table;
	
	/**
	 * Instantiates a new abstract request.
	 *
	 * @param layer the layer
	 */
	public AbstractRequest(String layer){
		this.table=structs.get(layer)	;
	}
	
	/**
	 *获取空间属性名称.
	 *
	 * @return the geometry
	 */
	public String getGeometry(){
		String name="";
		Field[] child=table.getBeanClass().getDeclaredFields();
		Field[] parent=table.getBeanClass().getSuperclass().getDeclaredFields();
		Field[] fields=AOUtil.concat(child, parent);
		for(Field attr:fields)
		{
			if(attr.getType().equals(IGeometry.class))
			{
				name= attr.getName();
			}
		}
		if(name.equals(""))
		{
			System.out.println("-----------------：实体bean找不到空间类型'IGeometry'");
			return null;
		}
		return name;
	}
	
	/**
	 * 获取映射关系.
	 *
	 * @return the mapping
	 */
	public Map<String,String> getMapping(){
		String [] EKFields=getTable().getPKFieldsExcel();
		String [] SKFields=getTable().getPKFieldsShape();
		Map<String,String> relationShip=new HashMap<String,String>();
		for(int i=0;i<EKFields.length;i++)
		{
			for(XlsColumn column:getTable().getColumns())
			{
				if(column.getCellName().equals(EKFields[i]))
				{
					relationShip.put(SKFields[i], column.getField());break;
				}
			}
		}
		if(relationShip.isEmpty())
		{
			System.out.println("-----------------：找不到对应关系！");
			return null;
		}
		return relationShip;
	}
	
	/**
	 * 获取空间关联主键.
	 *
	 * @param cursor the cursor
	 * @return the pk shape index
	 */
	public List<Integer> getPkShapeIndex(IFeatureCursor cursor){
		List<Integer> pkIndexs=new ArrayList<Integer>();
		try
		{
			for(String field:getTable().getPKFieldsShape())
			{	
				pkIndexs.add(cursor.findField(field));
			}
			if(pkIndexs.contains(-1))
			{
				System.out.println("---------------：找不到PKFieldsShape主键！不能继续导入了");
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pkIndexs;
	}
	
	/**
	 * 获取单表xml结构.
	 *
	 * @return the table
	 */
	public XlsTable getTable(){
		return table;
	}
	
	static
	{
		structs=XmlParse.parseXml("xls-config.xml");
	}
}
