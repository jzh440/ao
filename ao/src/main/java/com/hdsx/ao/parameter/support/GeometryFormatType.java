package com.hdsx.ao.parameter.support;

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
