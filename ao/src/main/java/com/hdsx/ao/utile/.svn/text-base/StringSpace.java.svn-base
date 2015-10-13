package com.hdsx.ao.util;
/**
 * -----------------------------------------
 * @文件: StringSpace.java
 * @作者: jingzh
 * @邮箱: jzh440@163.com
 * @时间: 2013-4-26
 * @描述: 对读取得excel数据格式处理
 * -----------------------------------------
 */
public class StringSpace {
	/**
	 * 去掉字符串空格
	 * @param str
	 * @return
	 */
	public static Double parseDouble(String str){
		String result="";
		if(true){
			 result=str.replaceAll("E", "");
			 result=result.replaceAll("N", "");
			 result=result.replaceAll("　", "").trim();
		}
		if(result.length()<1){
			result="0.0";
		}
		return Double.parseDouble(result);
		
	}
	/**
	 * 去掉整数“.0”
	 * @param str
	 * @return
	 */
	public static String parseInt(String str){
		
		if(str.contains(".0")){
			str=str.substring(0, str.lastIndexOf("."));
		}
		if(str==null){
			str="";
		}
		return str;
	}
	/**
	 * 去掉整数“.0”
	 * @param str
	 * @return
	 */
	public static String parseString(String str){
		
		if(str.contains(".0")){
			str=str.substring(0, str.lastIndexOf("."));
		}
		if(str==null){
			str="";
		}
		return str;
		
	}
	
}
