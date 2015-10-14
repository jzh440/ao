package com.hdsx.ao.parameter;

import java.io.Serializable;

import com.hdsx.ao.parameter.support.DataFormatType;
import com.hdsx.ao.parameter.support.GeometryFormatType;
/**
 * 数据访查询参数
 *   
 * @author jingzh
 * 
 * @createDate 2015-10-14
 * 
 * @email jingzh@hdsxtech.com
 * 
 * @version 1.0
 */
public class QueryParameter implements IParameter,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2660085367798987560L;
	
	private String layerName;
	
	private String where;
	
	private String geometry;
	
	private String outFields;
	
	private boolean returnGeometry;
	
	private String orderByFields;
	
	private String spatialRel;
	
	private boolean returnM;
	
	private boolean returnZ;
	
	private DataFormatType dataType = DataFormatType.JSON;
	
	private GeometryFormatType geoFormatType=GeometryFormatType.WKT;
	
	
	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

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

	public String getGeometry() {
		return geometry;
	}

	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}

	public String getOutFields() {
		return outFields;
	}

	public void setOutFields(String outFields) {
		this.outFields = outFields;
	}

	public boolean isReturnGeometry() {
		return returnGeometry;
	}

	public void setReturnGeometry(boolean returnGeometry) {
		this.returnGeometry = returnGeometry;
	}

	public String getOrderByFields() {
		return orderByFields;
	}

	public void setOrderByFields(String orderByFields) {
		this.orderByFields = orderByFields;
	}

	public String getSpatialRel() {
		return spatialRel;
	}

	public void setSpatialRel(String spatialRel) {
		this.spatialRel = spatialRel;
	}

	public boolean isReturnM() {
		return returnM;
	}

	public void setReturnM(boolean returnM) {
		this.returnM = returnM;
	}

	public boolean isReturnZ() {
		return returnZ;
	}

	public void setReturnZ(boolean returnZ) {
		this.returnZ = returnZ;
	}

	public DataFormatType getDataType() {
		return dataType;
	}

	public void setDataType(DataFormatType dataType) {
		this.dataType = dataType;
	}

	public GeometryFormatType getGeoFormatType() {
		return geoFormatType;
	}

	public void setGeoFormatType(GeometryFormatType geoFormatType) {
		this.geoFormatType = geoFormatType;
	}
	
}
