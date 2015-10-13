package com.hdsx.ao.parameter;

import com.hdsx.ao.bean.HDFeature;

public class UpdateParameter implements IParameter {

	private String layerName;
	
	private String where;
	
	private HDFeature feature;
	
	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}
	
	public HDFeature getFeature() {
		return feature;
	}

	public void setFeature(HDFeature feature) {
		this.feature = feature;
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

}
