package com.hdsx.ao.workspace;

import java.lang.reflect.Field;

import com.esri.arcgis.geodatabase.IWorkspace;
import com.hdsx.ao.workspace.platform.PlatformWorkspaceFactory;
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
public class EngineCPWorkspace extends EngineCPConfig implements IHDWorkspace{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2891179990699655568L;

	public EngineCPWorkspace(){
		super();
	}
	
	public EngineCPWorkspace(EngineCPConfig config){
		Field[] fields = EngineCPConfig.class.getDeclaredFields();
		for (Field field: fields){
			try {
				field.setAccessible(true);
				field.set(this, field.get(config));
			} catch (Exception e) {
				// should never happen
			}
		}
	}
	
	@Override
	public IWorkspace getWorkspace() {
		// TODO Auto-generated method stub
		try {
			EngineCPConfig config=clone();
			return PlatformWorkspaceFactory.createPlatformWorkspace(config);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public EngineCPConfig clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		EngineCPConfig clone = new EngineCPConfig();
		clone.setDatabase(getDatabase());
		clone.setInstance(getInstance());
		clone.setUser(getUser());
		clone.setPassword(getPassword());
		clone.setVersion(getVersion());
		clone.setPlatform(getPlatform());
		return clone;
	}

	@Override
	public IWorkspace getWorkspace(EngineCPConfig config) {
		// TODO Auto-generated method stub
		try {
			return PlatformWorkspaceFactory.createPlatformWorkspace(config);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public String toString() {
		return "EngineCPWorkspace [getPlatform()=" + getPlatform() + ", getVersion()=" + getVersion()
				+ ", getInstance()=" + getInstance() + ", getUser()=" + getUser() + ", getPassword()=" + getPassword()
				+ ", getDatabase()=" + getDatabase() + "]";
	}
	
}
