package com.hdsx.ao.parameter.support;

/**
 * 空间数据返回格式参数
 *   
 * @author jingzh
 * 
 * @createDate 2015-10-14
 * 
 * @email jingzh@hdsxtech.com
 * 
 * @version 1.0
 */
public enum GeometryFormatType {

	WKT (1,"WKT"),
	GJSON (2,"GJSON");
	
	private int type;
	
	private String name;
	
	GeometryFormatType(int type,String name){
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
