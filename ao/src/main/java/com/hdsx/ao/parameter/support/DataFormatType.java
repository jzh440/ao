package com.hdsx.ao.parameter.support;

public enum DataFormatType {

	JSON (1,"JSON"),
	XML (2,"XML"),
	HTML (3,"HTML");
	
	private int type;
	
	private String name;
	
	DataFormatType(int type,String name){
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
