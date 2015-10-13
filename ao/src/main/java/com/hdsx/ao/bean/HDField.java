package com.hdsx.ao.bean;

import java.io.Serializable;

public class HDField implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4454548344945945105L;

	private String name;
	
	private String type;
	
	private String alias;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "HDField [name=" + name + ", type=" + type + ", alias=" + alias + "]";
	}
	
	
}
