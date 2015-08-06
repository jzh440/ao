package com.hdsx.ao.util;
/**
 * Method[] methods=cla.getMethods();
 * cla.getMethods()包含父类的方法
 * cla.getDeclaredMethods()只有当前类方法
 */
import java.lang.reflect.Method;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.geometry.Point;
import com.esri.arcgis.geometry.Polyline;

public class ReflectUtil {
	private static final String REGEX = "[a-zA-Z]";
	public static  Object getValue(String attribute,Object bean){
		String methodName=convertToMethodName(attribute,bean.getClass(),false);
		Object value=null;
		try 
		{
			Method method = bean.getClass().getMethod(methodName);
			value=method.invoke(bean);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	public static void setValue(String attribute,Object value,Object bean)
	{
		String methodName=convertToMethodName(attribute,bean.getClass(),true);
		try 
		{
			Class<?> clas=basicTypeTOReference(value.getClass());
			Method method = bean.getClass().getMethod(methodName,clas);
			method.invoke(bean, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected static Class<?> basicTypeTOReference (Class<?> clas){
		if(clas==Byte.class)
		{
			return byte.class;
		}
		else if(clas==Short.class)
		{
			return short.class;
		}
		else if(clas==Integer.class)
		{
			return int.class;
		}
		else if(clas==Long.class)
		{
			return long.class;
		}
		else if(clas==Character.class)
		{
			return char.class;
		}
		else if(clas==Float.class)
		{
			return float.class;
		}
		else if(clas==Double.class)
		{
			return double.class;
		}
		else if(clas==String.class)
		{
			return String.class;
		}
		else if(clas==Date.class)
		{
			return Date.class;
		}
		else if(clas==Byte[].class)
		{
			return byte[].class;
		}
		else if(clas==Point.class||clas==Polyline.class)
		{
			return IGeometry.class;
		}
		else
		{
			return clas;
		}
	}
	
	public static String convertToAttribute(String methodName,String type){
		String attribute=methodName.substring(type.length(),methodName.length());
		return attribute.toLowerCase();
	}
	private static String convertToMethodName(String attribute,Class<?> cla,boolean isSet)
     {
		/** 通过正则表达式来匹配第一个字符 **/
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(attribute);
		String afertInitial=attribute.substring(0,1).toUpperCase();
		String afertStr=afertInitial+attribute.substring(1);
		StringBuilder sb = new StringBuilder();
		if(isSet)
		{
			sb.append("set");
		}
		else 
		{
			try {
				Class<?> parameterT=cla.getMethod("get"+afertStr).getReturnType();
				/** 如果类型为boolean **/
				if(parameterT == boolean.class||parameterT == Boolean.class)
				{
					sb.append("is");
				}
				else
				{
					sb.append("get");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/** 针对以下划线开头的属性 **/
		sb.append(afertStr);
		return sb.toString();
     }

	public static boolean isContain(String attribute,Object bean){
		try 
		{
			bean.getClass().getDeclaredField(attribute);
		} catch (Exception e) {
			//e.printStackTrace();
			return isContain(attribute,bean.getClass());
		}
		return true;
	}
	public static boolean isContain(String attribute,Class<?> cla){
		Class<?> parCla=cla.getSuperclass();
		try 
		{
			parCla.getDeclaredField(attribute);
		} catch (Exception e) {
			if(parCla.getSuperclass()!=null&&!parCla.getSuperclass().equals(Object.class))
			{
				return isContain(attribute,parCla);
			}
			return false;
		}
		return true;
	}
}
