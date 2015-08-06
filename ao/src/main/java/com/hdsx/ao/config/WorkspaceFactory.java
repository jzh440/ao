package com.hdsx.ao.config;


import java.io.IOException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.arcgis.datasourcesGDB.SdeWorkspaceFactory;
import com.esri.arcgis.datasourcesfile.ShapefileWorkspaceFactory;
import com.esri.arcgis.geodatabase.IFeatureWorkspace;
import com.esri.arcgis.geodatabase.IWorkspace;
import com.esri.arcgis.geodatabase.IWorkspaceFactory;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.PropertySet;
import com.vividsolutions.jts.awt.ShapeWriter;

public class WorkspaceFactory {
	private static Logger log=LoggerFactory.getLogger(WorkspaceFactory.class);
	
	private WorkspaceFactory(){}
	public static IFeatureWorkspace getWorkspace(String server,String instance,String database,String user,String password,String version){
		try 
		{
			IPropertySet prop=getPropertySet(server, instance, database, user, password, version);
			return getWorkspace(prop);
		} catch (UnknownHostException e) {
			log.error("\n 数据库连接信息{}","server="+server+" instance="+instance+ " database="+database+ " user="+ user+" password"+password+" version="+ version);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static IPropertySet getPropertySet(String server,String instance,String database,String user,String password,String version) throws UnknownHostException, IOException{
		IPropertySet prop=new PropertySet();
		prop.setProperty("server",server);
		prop.setProperty("instance",instance);
		prop.setProperty("database",database);
		prop.setProperty("user",user);
		prop.setProperty("password",password);
		prop.setProperty("version",version);
		return prop;
	}
	public static IFeatureWorkspace getWorkspace(IPropertySet prop) throws UnknownHostException, IOException{
		SdeWorkspaceFactory sdewks =  new SdeWorkspaceFactory();
		IFeatureWorkspace wks=(IFeatureWorkspace)sdewks.open(prop, 0);
		return wks;
	}
	/**
	 * 获取shape文件工作空间.
	 *
	 * @param shp 文件路径（指向文件夹）
	 * @return the shape work space
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static IWorkspace getShapeWorkSpace(String shp) throws UnknownHostException, IOException{
		IWorkspace wks=null;
		IWorkspaceFactory wkf = new ShapefileWorkspaceFactory();
		wks = wkf.openFromFile(shp, 0);
		return wks;
	}
}
