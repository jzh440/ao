package com.hdsx.ao.workspace.platform;

import com.esri.arcgis.datasourcesGDB.SdeWorkspaceFactory;
import com.esri.arcgis.geodatabase.IWorkspace;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.PropertySet;
import com.hdsx.ao.workspace.EngineCPConfig;
import com.hdsx.ao.workspace.IHDWorkspace;
/**
 * Oracle空间数据源
 *   
 * @author jingzh
 * 
 * @createDate 2015-10-14
 * 
 * @email jingzh@hdsxtech.com
 * 
 * @version 1.0
 */
public class OracleCPWorkspace implements IHDWorkspace {

	@Override
	public IWorkspace getWorkspace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWorkspace getWorkspace(EngineCPConfig config) throws Exception{
		// TODO Auto-generated method stub
		IPropertySet prop=new PropertySet();
		prop.setProperty(EngineCPConfig.P_INSTANCE,config.getInstance());
		prop.setProperty(EngineCPConfig.P_USER,config.getUser());
		prop.setProperty(EngineCPConfig.P_PASSWORD,config.getPassword());
		prop.setProperty(EngineCPConfig.P_VERSION,config.getVersion());
		SdeWorkspaceFactory sdewks =  new SdeWorkspaceFactory();
		return sdewks.open(prop, 0);
	}

}
