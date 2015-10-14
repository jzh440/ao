package com.hdsx.ao.parameter;
/**
 * 数据访删除参数
 *   
 * @author jingzh
 * 
 * @createDate 2015-10-14
 * 
 * @email jingzh@hdsxtech.com
 * 
 * @version 1.0
 */
public class DeleteParameter implements IParameter {
	
	private String layerName;
	
	private String where;
	
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

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}


}
