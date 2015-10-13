package com.hdsx.ao.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.vividsolutions.jts.geom.Geometry;

public class HDFeature implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6691581144387552898L;

	private Map<String,Object> attributes = new LinkedHashMap<String,Object>();
	
	private Geometry geometry;
	
	public void addAttribute(String attribute,Object value) {
		attributes.put(attribute, value);
	}
	
	public Object getAttribute(String attribute) {
		return attributes.get(attribute);
	}
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		return "HDFeature [attributes=" + attributes + ", geometry=" + geometry + "]";
	}
	
	
}
