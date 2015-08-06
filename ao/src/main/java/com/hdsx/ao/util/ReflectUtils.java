package com.hdsx.ao.util;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * 反射对象
 * @author jingzh
 *
 */
public class ReflectUtils {
	
	private static Logger log=LoggerFactory.getLogger(ReflectUtils.class);
	/**
	 * 反射得到类字段值
	 * @param <T>
	 * @param name
	 * @param obj
	 * @return
	 */
	public static Object getValue(String name,Object obj)
	{
		String getAttribute=StringUtile.getInitial(name.toLowerCase());
		Method[] methods=obj.getClass().getMethods();//getDeclaredMethods();默认覆盖与超类相同的方法
		label:for(Method method:methods)
		{
			if(method.getName().equals(getAttribute))
			{
				try 
				{	
					method=obj.getClass().getMethod(getAttribute);
					return method.invoke(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break label;
			}
		}
		return null;
	}
	public static  void setValue(String word,Object value,Object o){
		if(value==null||word==null||o==null)return;
		Method[] methods=o.getClass().getMethods();
		word=StringUtile.setInitial(word);
		label:for(Method method:methods)
		{
			if(method.getName().equals(word))
			{
				try 
				{	
					value=convertValueType(method.getParameterTypes()[0],value);
					method.invoke(o,value);
				} catch (Exception e) {
					log.error("字段 ["+word+"] bean定义 类型："+method.getParameterTypes()[0]+",实际值类型"+value.getClass()+",实际值："+value);
					e.printStackTrace();
				}
				break label;
			}
		}
	}
	protected static Object convertValueType(Class<?> type,Object value){
		if(type.getName().equals("java.lang.String"))
		{
			if(value==null)
				return "";
			else
				return value+"";
		}
		else if(type.getName().equals("java.lang.Double")||type.getName().equals("double"))
		{
			DecimalFormat df = new DecimalFormat("########.000");
			if(value==null||value.equals(""))
				return 0.0;
			else
				 return new Double(df.format(value));
		}
		else if(type.getName().equals("java.lang.Integer")||type.getName().equals("int"))
		{
			if(value==null||value.equals(""))
				return 0;
			else
				return new Integer(value+"");
		}
		else if(type.getName().equals("java.util.Date")&&!value.getClass().equals(type))
		{
			if(value==null||value.equals(""))
				return "";
			DateFormat df=null;
			if(value.toString().length()==4)
				df=new SimpleDateFormat("yyyy");
			else
				df=new SimpleDateFormat("yyyyMMdd");
			Date date=null;
			try 
			{
				date=df.parse(value+"");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
		else
			return value;
	}
	
}
