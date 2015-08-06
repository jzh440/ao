package com.hdsx.ao.bean;

import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The Enum CellType.
 * @author jingzh
 */
public enum CellType {
	
	/** The string. */
	STRING(String.class),
	
	/** The integer. */
	INTEGER(Integer.class),
	
	/** The double. */
	DOUBLE(Double.class),
	
	/** The boolean. */
	BOOLEAN(Boolean.class),
	
	/** The date. */
	DATE(Date.class),
	
	/** The blank. */
	BLANK(null);
	
	/** The clasz. */
	private final Class<?> clasz;
	
	/**
	 * Instantiates a new cell type.
	 *
	 * @param clasz the clasz
	 */
	private CellType(Class<?> clasz){
		this.clasz=clasz;
	}
	
	/**
	 * Gets the clasz.
	 *
	 * @return the clasz
	 */
	public  Class<?> getclasz() {
		return clasz;
	}
	
}
