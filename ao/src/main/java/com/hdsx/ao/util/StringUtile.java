package com.hdsx.ao.util;

import java.util.Collection;
import java.util.Map;

/**
 * 字符串工具类
 * @author jingzh
 *
 */
public class StringUtile {
	/**
	 * 格式化首字母
	 * @return
	 */
	public static String lowerInitial(String word)
	{
		if(word==null)
		{
			return null;
		}
		String afertInitial=word.substring(0,1).toLowerCase();
		String afertStr=afertInitial+word.substring(1);
		return afertStr;
	}

	/**
	 * 格式化首字母
	 * @return
	 */
	public static String upperInitial(String word)
	{
		if(word==null)
		{
			return null;
		}
		String afertInitial=word.substring(0,1).toUpperCase();
		String afertStr=afertInitial+word.substring(1);
		return afertStr;
	}
	public static String setInitial(String word)
	{
		if(word==null)
		{
			return null;
		}
		word=word.toLowerCase();
		String afertInitial=word.substring(0,1).toUpperCase();
		String afertStr="set"+afertInitial+word.substring(1);
		return afertStr;
	}
	public static String getInitial(String word)
	{
		if(word==null)
		{
			return null;
		}
		word=word.toLowerCase();
		String afertInitial=word.substring(0,1).toUpperCase();
		String afertStr="get"+afertInitial+word.substring(1);
		return afertStr;
	}
	public static String getAttribute(String attribute){
		String afertInitial=attribute.substring(0,1).toUpperCase();
		String afertStr=afertInitial+attribute.substring(1);
		return afertStr;
	}
	//判断对象是否为空
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj){
		if (obj == null) { return true;}
		else if (obj instanceof String && (obj.equals(""))) {return true;}
		else if (obj instanceof Number && ((Number) obj).doubleValue() == 0) {return true; }
		else if (obj instanceof Boolean && !((Boolean) obj)) {return true;}
		else if (obj instanceof Collection && ((Collection) obj).isEmpty()){return true;}
		else if (obj instanceof Map && ((Map) obj).isEmpty()) {return true;}
		else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {return true;}
		return false;
	}
	//判断字符串是否为空
	public static boolean isEmptyStr(String str){
		return str == null||"null".equalsIgnoreCase(str.trim())||str.trim().length() == 0 ? true:false;
	}
}
