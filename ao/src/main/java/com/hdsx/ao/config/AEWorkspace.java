package com.hdsx.ao.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.arcgis.datasourcesGDB.AccessWorkspaceFactory;
import com.esri.arcgis.datasourcesGDB.SdeWorkspaceFactory;
import com.esri.arcgis.datasourcesfile.ShapefileWorkspaceFactory;
import com.esri.arcgis.geodatabase.IWorkspace;
import com.esri.arcgis.geodatabase.IWorkspaceFactory;
import com.esri.arcgis.system.IPropertySet;

public class AEWorkspace {
	/** The log. */
	private static Logger log=LoggerFactory.getLogger(AEWorkspace.class);
	/**
	 * 获取sde数据库工作空间.
	 *
	 * @param prop 数据库连接信息
	 * @return the sde work space
	 */
	public static IWorkspace getSdeWorkSpace(){
		IWorkspace wks=null;
		try 
		{
			IWorkspaceFactory wkf = new SdeWorkspaceFactory();
			IPropertySet prop=Config.config.initPropertySet();
			DBHandler.getInstance().initDataSource(prop);
			wks = wkf.open(prop, 0);
		}
		catch(Exception ex){
			log.info("\n"+"获取sde数据源异常,请检查sde服务是否启动！");
			ex.printStackTrace();
		}
		return wks;
	}
	
	/**
	 * 获取sde数据库工作空间.
	 *
	 * @param prop 数据库连接信息
	 * @return the sde work space
	 */
	public static IWorkspace getSdeWorkSpace(Config config){
		IWorkspace wks=null;
		try 
		{
			IWorkspaceFactory wkf = new SdeWorkspaceFactory();
			IPropertySet prop=Config.config.initPropertySet();
			wks = wkf.open(prop, 0);
		}
		catch(Exception ex){
			log.info("\n"+"获取sde数据源异常,请检查sde服务是否启动！");
			ex.printStackTrace();
		}
		return wks;
	}
	/**
	 * 获取sde数据库工作空间.
	 *
	 * @param prop 数据库连接信息
	 * @return the sde work space
	 */
	public static IWorkspace getSdeWorkSpace(IPropertySet prop){
		IWorkspace wks=null;
		try 
		{
			IWorkspaceFactory wkf = new SdeWorkspaceFactory();
			wks = wkf.open(prop, 0);
		}
		catch(Exception ex){
			log.info("\n"+"获取sde数据源异常,请检查sde服务是否启动！");
			ex.printStackTrace();
		}
		return wks;
	}
	
	/**
	 * 获取shape文件工作空间.
	 *
	 * @param shp 文件路径（指向文件夹）
	 * @return the shape work space
	 */
	public static IWorkspace getShapeWorkSpace(String shp){
		IWorkspace wks=null;
		try 
		{
			IWorkspaceFactory wkf = new ShapefileWorkspaceFactory();
			wks = wkf.openFromFile(shp, 0);
		}
		catch(Exception ex){
			log.info("\n"+"获取shape数据源异常["+shp+"]");
			ex.printStackTrace();
		}
		return wks;
	}
	
	/**
	 * 获取mdb文件工作空间.
	 *
	 * @param mdb mdb文件路径
	 * @return the mdb work space
	 */
	public static  IWorkspace getMdbWorkSpace(String mdb)
    {
		IWorkspace wk =null;
        try
        {
            IWorkspaceFactory wkf = new AccessWorkspaceFactory();
            wk = wkf.openFromFile(mdb, 0);
        }
        catch (Exception ex)
        {
        	log.info("\n"+"获取mdb数据源异常["+mdb+"]");
        }
        return wk;
    }
}
