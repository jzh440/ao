package com.hdsx.ao.config;

import com.esri.arcgis.geodatabase.IFeatureWorkspace;
import com.esri.arcgis.system.IPropertySet;

public interface IDatemineDataStore {
	/**
	  * 注册数据源.
	  *
	  * @param <T> the generic type
	  * @param num :数据源KEY
	  * @param prop : 数据源连接信息
	  */
	void regist(int num,IPropertySet prop);
	/**
	  * 获取默认数据源.
	  *
	  * @param <T> the generic type
	  * @param 
	  * @param 
	  */
	IFeatureWorkspace defaultDataStore();
	/**
	  * 根据KEY获取数据源.
	  *
	  * @param <T> the generic type
	  * @param num
	  * @param 
	  */
	IFeatureWorkspace detemineDataStore(int num);
	
	IFeatureWorkspace openWorkspace(String path);
}
