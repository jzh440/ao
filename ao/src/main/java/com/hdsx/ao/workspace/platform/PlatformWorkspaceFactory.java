package com.hdsx.ao.workspace.platform;

import com.esri.arcgis.geodatabase.IWorkspace;
import com.hdsx.ao.workspace.EngineCPConfig;

public class PlatformWorkspaceFactory {

	public static IWorkspace createPlatformWorkspace(EngineCPConfig config) throws Exception{
		switch(config.getPlatform()){
			case Oracle:
				return new OracleCPWorkspace().getWorkspace(config);
			case SQLServer:
				return null;
			case PostgreSQL:
				return null;
			default:
				return null;
		}
	}
}
