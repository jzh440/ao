package com.hdsx.ao.util;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IField;
import com.esri.arcgis.geodatabase.IFields;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.interop.AutomationException;

public class FeatureUtil {
	private static  Logger log=LoggerFactory.getLogger(FeatureUtil.class); 
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
	public static IFeature getFeature(IFeature feature,Object bean){
		if(bean instanceof Map)
		{
			mapToFeature(feature,bean);
		}
		else
		{
			beanToFeature(feature,bean);	
		}
		return feature;
	}
	protected static void beanToFeature(IFeature feature,Object bean){
		try
		{
			IFields iFields=feature.getFields();
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
						 TypeConvert.convertArcObject(feature,index,value);
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
					log.debug("attribute:\t'"+name+"'在实体中 找不到对应属性");continue;
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
