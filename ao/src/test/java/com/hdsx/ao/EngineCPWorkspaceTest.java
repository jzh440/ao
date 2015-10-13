package com.hdsx.ao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.esri.arcgis.geodatabase.IWorkspace;
import com.hdsx.ao.bean.HDFeatures;
import com.hdsx.ao.dao.AoDao;
import com.hdsx.ao.dao.AoDaoImpl;
import com.hdsx.ao.exception.HDException;
import com.hdsx.ao.init.InitLicenses;
import com.hdsx.ao.parameter.QueryParameter;
import com.hdsx.ao.workspace.EngineCPWorkspace;

import junit.framework.Assert;

public class EngineCPWorkspaceTest {

	@Before
	public void before(){
		System.out.println("before");
		InitLicenses.init();
	}
	
	@Test
	public void test() {
		EngineCPWorkspace workspace = new EngineCPWorkspace();
		workspace.setInstance("sde:oracle11g:vm_jxgis");
		workspace.setPassword("GISDB");
		workspace.setUser("GISDB");
		System.out.println(workspace.toString());
		IWorkspace ws = workspace.getWorkspace();
		
	}
	
	@Test
	public void testDao() {
		EngineCPWorkspace workspace = new EngineCPWorkspace();
		workspace.setInstance("sde:oracle11g:vm_jxgis");
		workspace.setPassword("GISDB");
		workspace.setUser("GISDB");
		AoDao dao =new AoDaoImpl(workspace);
		QueryParameter parameter = new QueryParameter();
		parameter.setLayerName("GIS_LX");
		try {
			HDFeatures features=dao.query(parameter);
			System.out.println(features.getFeatures().size());
		} catch (HDException e) {
			//e.printStackTrace();
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	@After
	public void after(){
		System.out.println("after");
		InitLicenses.init();
	}

}
