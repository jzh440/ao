package com.hdsx.ao.util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFeatureBuffer;
import com.esri.arcgis.geodatabase.IField;
import com.esri.arcgis.geodatabase.IFields;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.interop.AutomationException;

public class ArcGISUtil {

	private static  Logger log=LoggerFactory.getLogger(ArcGISUtil.class); 
	/**
	 * 将几何对象转化为实体.
	 *
	 * @param <T> the generic type
	 * @param feature 几何对象
	 * @param bean 实体类型
	 * @return the bean
	 */
	public static <T> T getBean(IFeature feature,T bean){
		try 
		{
			if(bean instanceof Map)
			{
				 FeatureToMap(feature,bean);
			}
			else
			{
				 FeatureToBean(feature,bean);
			}
		} catch (Exception e) {
			log.debug("\n"+"featureToBean为字段赋值时出错");
			e.printStackTrace();
		}
		return bean;
	}
	/**
	 * 将实体加载到几何对象缓冲区.
	 *
	 * @param buffer 几何对象缓冲区
	 * @param bean 实体对象
	 * @return the buffer
	 */
	public static IFeatureBuffer getBuffer(IFeatureBuffer buffer,Object bean){
		try 
		{
			if(bean instanceof Map)
			{
				 mapToBuffer(buffer,bean);
			}
			else
			{
				beanToBuffer(buffer,bean);	
			}
		} catch (Exception e) {
			log.debug("\n"+"beanToFeature为字段赋值时出错");
			e.printStackTrace();
		}
		return buffer;
	}
	protected static void beanToBuffer(IFeatureBuffer buffer,Object bean){
		try
		{
			IFields iFields=buffer.getFields();
			for(int i=0,size=iFields.getFieldCount();i<size;i++)
			{
				IField iField=iFields.getField(i);
				String name=iField.getName();
				int index=iFields.findField(name);
				if(ReflectUtil.isContain(name.toLowerCase(), bean))
				{
					 Object value=ReflectUtil.getValue(name.toLowerCase(), bean);
					 if(value!=null)
					 {
						 TypeConvert.convertArcObject(buffer, index, value);
					 }
				}
				else
				{
					log.debug("attribute:\t'"+name+"' 在实体中找不到对应");continue;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	protected static void mapToFeature(IFeature feature,Object bean){
		Map<String,Object> map=(Map)bean;
		try
		{
			IFields iFields=feature.getFields();
			for(int i=0,len=iFields.getFieldCount();i<len;i++)
			{
				IField field=iFields.getField(i);
				String name=field.getName();
				if(map.containsKey(name))
				{
					if(map.get(name) instanceof IGeometry)
					{
						feature.setShapeByRef((IGeometry) map.get(name));
					}
					feature.setValue(i, map.get(name));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void mapToBuffer(IFeatureBuffer buffer,Object bean){
		try
		{
			IFields iFields=buffer.getFields();
			for(int i=0,count=iFields.getFieldCount();i<count;i++)
			{
				IField field=iFields.getField(i);
				int index=iFields.findField(field.getName());
				Map<String,Object> map=(Map)bean;
				Set<String> keys=map.keySet();
				if(keys.contains(field.getName()))
				{
					buffer.setValue(index, map.get(field.getName()));
				}
				else
				{
					log.info("\tPhysical Name : " + field.getName()+
							"\tAlias Name    : " + field.getAliasName()+
							"\tType          : " + field.getType()+
							"\tLength        : " + field.getLength()+
							"\tPrecision     : " + field.getPrecision()+
							"\tEditable      : " + field.isEditable()+
							"\tdefaultValue      : " + field.isEditable());
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询空间对象转化为实体bean.
	 *
	 * @param feature the feature
	 * @param bean the bean
	 * @return the bean
	 */
	protected static <T> T FeatureToBean(IFeature feature,T bean)
	{
		try 
		{
			IFields iFields=feature.getFields();
			for(int i=0,size=iFields.getFieldCount();i<size;i++)
			{
				IField field=iFields.getField(i);
				String name=field.getName().toLowerCase();
				Object value=feature.getValue(i);
				if(value!=null&&ReflectUtil.isContain(name, bean))
				{
					value=TypeConvert.convertJava(field.getType(), value, feature);
					ReflectUtil.setValue(name, value, bean);
				}
				else
				{
					log.debug("attribute:\t'"+name+"'在实体中 找不到对应属性");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	@SuppressWarnings("unchecked")
	protected  static <T> T FeatureToMap (IFeature feature,T bean)
	{
		Map<String,Object> map=(Map<String,Object>)bean;
		IFields fields;
		try {
			fields = feature.getFields();
			for(int i=0;i<fields.getFieldCount();i++)
			{
				IField field=fields.getField(i);
				String name=field.getName();
				int index=fields.findField(name);
				Object value=feature.getValue(index);
				map.put(name, value);
			}
		} catch (AutomationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (T) map;
	}
}
