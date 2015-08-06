package com.hdsx.ao.config;

import java.io.IOException;
import java.util.Stack;

import com.esri.arcgis.geodatabase.IFeatureWorkspace;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.PropertySet;

public class DBHandler  {

	private static DBHandler handler;
	private Stack<IPropertySet> connStack=new Stack<IPropertySet>();
	private DBHandler(){
		
	}
	public synchronized static DBHandler getInstance(){
		if(handler==null)
		{
			 handler=new DBHandler();
		}
		return handler;
	}
	
	public synchronized  IPropertySet  initDataSource(String server,String instance,String database,String user,String password,String version){
		IPropertySet prop=null;
		try 
		{
			if(!(instance.isEmpty()||user.isEmpty()||password.isEmpty()||version.isEmpty()))
			{
				prop=new PropertySet();
				prop.setProperty("server",server);
				prop.setProperty("instance",instance);
				prop.setProperty("database",database);
				prop.setProperty("user",user);
				prop.setProperty("password",password);
				prop.setProperty("version",version);
				initDataSource(prop);
			}
		} catch (AutomationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	protected synchronized void  initDataSource(IPropertySet prop){
		try
		{
			if(connStack.size()<=50)
			{
				connStack.add(prop);
			}
			else
			{
				if(prop.isEqual(connStack.get(0)))
				{
					connStack.add(prop);
				}
				else
				{
					connStack.clear();
					connStack.add(prop);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public synchronized IFeatureWorkspace getWorkSpace(){
		try
		{
			if(!connStack.isEmpty())
			{
				return WorkspaceFactory.getWorkspace(connStack.get(0));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("error");
		return null;
	}
}
