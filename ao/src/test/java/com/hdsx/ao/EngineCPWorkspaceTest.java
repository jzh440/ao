package com.hdsx.ao;

import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hdsx.ao.bean.HDFeature;
import com.hdsx.ao.bean.HDFeatures;
import com.hdsx.ao.dao.AoDao;
import com.hdsx.ao.dao.AoDaoImpl;
import com.hdsx.ao.exception.HDException;
import com.hdsx.ao.init.InitLicenses;
import com.hdsx.ao.parameter.InsertParameter;
import com.hdsx.ao.parameter.QueryParameter;
import com.hdsx.ao.utile.GeometryConverter;
import com.hdsx.ao.workspace.EngineCPWorkspace;
import com.vividsolutions.jts.geom.Geometry;

public class EngineCPWorkspaceTest {

	private AoDao dao;
	@Before
	public void before(){
		InitLicenses.init();
		EngineCPWorkspace workspace = new EngineCPWorkspace();
		workspace.setInstance("sde:oracle11g:vm_jxgis");
		workspace.setPassword("GISDB");
		workspace.setUser("GISDB");
		dao =new AoDaoImpl(workspace);
		System.out.println("数据源初始化连接成功");
	}
	
	@Test
	public void testDaoQuery() {
		QueryParameter parameter = new QueryParameter();
		parameter.setLayerName("GIS_GK");
		parameter.setWhere("1=1");
		parameter.setOutFields("GKMC,GKDM,SHAPE");
		//parameter.setOutFields("SUM(ZRAXCD) AS T_SUM");
		try {
			HDFeatures features=dao.query(parameter);
			System.out.println(features.toString());
		} catch (HDException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDaoInsert() {
		InsertParameter parameter = new InsertParameter();
		parameter.setLayerName("GIS_GK");
		try {
			HDFeature feature =new HDFeature();
			feature.setAttribute("ID", UUID.randomUUID().toString());
			feature.setAttribute("GKDM", "HLLY360102");
			feature.setAttribute("GKMC", "哈利路亚");
			feature.setAttribute("ZRAXCD", 123.34);
			feature.setAttribute("GKMJ", "123.56");
			feature.setAttribute("DRSJ", new Date());
			Geometry geom=GeometryConverter.wkt2Geometry("POINT(128.2332323 28.2324324)");
			feature.setGeometry(geom);
			parameter.addFeature(feature);
			long start=System.currentTimeMillis();
			for(int i=0;i<10;i++){
				int count=dao.insert(parameter);
			}
			long end=System.currentTimeMillis();
			System.out.println(end-start);
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
