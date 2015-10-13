package com.hdsx.ao.bean;

import java.util.ArrayList;
import java.util.List;

public class HDFeatures {

	private String geometryType;
	
	private SpatialReference spatialReference;
	
	private List<HDFeature> features = new ArrayList<HDFeature>();

	private List<HDField> fields = new ArrayList<HDField>();
	
	
	public String getGeometryType() {
		return geometryType;
	}

	public void setGeometryType(String geometryType) {
		this.geometryType = geometryType;
	}

	public SpatialReference getSpatialReference() {
		return spatialReference;
	}

	public void setSpatialReference(SpatialReference spatialReference) {
		this.spatialReference = spatialReference;
	}

	public List<HDFeature> getFeatures() {
		return features;
	}

	public void setFeatures(List<HDFeature> features) {
		this.features = features;
	}
	
	public void addFeature(HDFeature feature) {
		features.add(feature);
	}

	public List<HDField> getFields() {
		return fields;
	}

	public void setFields(List<HDField> fields) {
		this.fields = fields;
	}
	
	public void addField(HDField field) {
		fields.add(field);
	}

	@Override
	public String toString() {
		return "HDFeatures [geometryType=" + geometryType + ", spatialReference=" + spatialReference + ", features="
				+ features + ", fields=" + fields + "]";
	}
	
}
