package com.hdsx.ao.parameter;

import com.hdsx.ao.bean.HDFeature;
/**
 * 数据访修改参数
 *   
 * @author jingzh
 * 
 * @createDate 2015-10-14
 * 
 * @email jingzh@hdsxtech.com
 * 
 * @version 1.0
 */
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
