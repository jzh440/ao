package com.hdsx.ao.config;

import java.io.IOException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.arcgis.geodatabase.IFeatureWorkspace;
import com.esri.arcgis.geodatabase.IWorkspace;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.IPropertySetArray;
import com.esri.arcgis.system.PropertySetArray;
public class DetermineDataStore  implements IDatemineDataStore{

	private static Logger log=LoggerFactory.getLogger(DetermineDataStore.class);
	
	private static IPropertySetArray arrays;
	
	private  int currentPropertySet;
	
	public DetermineDataStore(){
		try
		{
			arrays=new PropertySetArray();
		} catch (UnknownHostException e) {
			log.error("the IP address of a host could not be determined");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void regist(int num,IPropertySet prop) {
		try
		{
			if(arrays.getCount()==0||!arrays.getElement(num).equals(prop))
			{
				arrays.insert(num, prop);
			}
		}
		catch(Exception e)
		{
			log.debug("\n 数据库连接信息第num{}个PropertySet{}异常.",num,prop);
			e.printStackTrace();
		}
	}
	public IFeatureWorkspace defaultDataStore() {
		try
		{
			return WorkspaceFactory.getWorkspace(arrays.getElement(currentPropertySet));
		}
		catch(Exception e)
		{
			log.debug("\n currentPropertySet数据源{}获取异常.",currentPropertySet);
			e.printStackTrace();
		}
		return null;
	}
	public IFeatureWorkspace detemineDataStore(int num) {
		try
		{
			if(num>=0&&arrays.getCount()>=num)
			{
				currentPropertySet=num;
				return WorkspaceFactory.getWorkspace(arrays.getElement(num));
			}
		}
		catch(Exception e)
		{
			log.debug("\n 数据源num{}获取异常.",num);
			e.printStackTrace();
		}
		return null;
	}
	
	public IFeatureWorkspace openWorkspace(String path) {
		try
		{
			IWorkspace space=WorkspaceFactory.getShapeWorkSpace(path);
			regist(3,space.getConnectionProperties());
			currentPropertySet=3;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public int getCurrentPropertySet() {
		return currentPropertySet;
	}
	public void setCurrentPropertySet(int currentPropertySet) {
		this.currentPropertySet = currentPropertySet;
	}
	
}
