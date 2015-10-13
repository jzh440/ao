package com.hdsx.ao.workspace;

import com.esri.arcgis.geodatabase.IWorkspace;

public interface IHDWorkspace {

	
	IWorkspace getWorkspace();
	
	
	IWorkspace getWorkspace(EngineCPConfig config) throws Exception;
	
}
