package com.hdsx.ao.parameter;

import java.util.ArrayList;
import java.util.List;

import com.hdsx.ao.bean.HDFeature;

public class InsertParameter implements IParameter {

	private String layerName;
	
	private List<HDFeature> features = new  ArrayList<HDFeature>();
	
	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}
	
	public List<HDFeature> getFeatures() {
		return features;
	}

	public void setFeatures(List<HDFeature> features) {
		this.features = features;
	}

	public void addFeature(HDFeature feature) {
		this.features.add(feature);
	}
	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

}
