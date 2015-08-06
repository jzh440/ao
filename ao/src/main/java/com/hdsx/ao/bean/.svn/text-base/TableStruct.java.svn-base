package com.hdsx.ao.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TableStruct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,XlsTable> tables=new HashMap<String,XlsTable>();
	
	private String name;
	
	private XlsTable table;

	
	
	public TableStruct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TableStruct(String name, XlsTable table) {
		super();
		this.name = name;
		this.table = table;
		add(name,table);
	}

	public void add(String name,XlsTable table)
	{
		tables.put(name, table);
	}
	public XlsTable get(String name){
		return tables.get(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public XlsTable getTable() {
		return table;
	}

	public void setTable(XlsTable table) {
		this.table = table;
	}
	
	
}
