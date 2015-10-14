package com.hdsx.ao.workspace;

import com.esri.arcgis.geodatabase.IWorkspace;
/**
 * 空间数据源连接池
 *   
 * @author jingzh
 * 
 * @createDate 2015-10-14
 * 
 * @email jingzh@hdsxtech.com
 * 
 * @version 1.0
 */
public interface IHDWorkspace {

	
	IWorkspace getWorkspace();
	
	
	IWorkspace getWorkspace(EngineCPConfig config) throws Exception;
	
}
