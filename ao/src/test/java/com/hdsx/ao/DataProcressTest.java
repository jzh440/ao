package com.hdsx.ao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hdsx.ao.bean.HDFeature;
import com.hdsx.ao.bean.HDFeatures;
import com.hdsx.ao.dao.AoDao;
import com.hdsx.ao.dao.AoDaoImpl;
import com.hdsx.ao.exception.HDException;
import com.hdsx.ao.init.InitLicenses;
import com.hdsx.ao.parameter.DeleteParameter;
import com.hdsx.ao.parameter.InsertParameter;
import com.hdsx.ao.parameter.QueryParameter;
import com.hdsx.ao.workspace.EngineCPWorkspace;

public class DataProcressTest {

	private AoDao dao;
	private AoDao daoTmp;
	private String[] layerNames={
			"GIS_CDSL","GIS_CFLD",
			"GIS_CRK","GIS_DMDX",
			"GIS_FWQ","GIS_JKSSSB",
			"GIS_JSDJ","GIS_JTLGCZ",
			"GIS_KBQBB","GIS_LDMCLX",
			"GIS_LDSF","GIS_LDSS",
			"GIS_LJLM","GIS_LX",
			"GIS_NCLX","GIS_QL",
			"GIS_SD","GIS_SFZ",
			"GIS_XZQH","GIS_YFGS",
			"GIS_ZCZ"};
	@Before
	public void before(){
		InitLicenses.init();
		EngineCPWorkspace workspace = new EngineCPWorkspace();
		workspace.setInstance("sde:oracle11g:orcl");
		workspace.setPassword("GISDB");
		workspace.setUser("GISDB");
		dao =new AoDaoImpl(workspace);
		System.out.println("GISDB数据源初始化连接成功");
		EngineCPWorkspace workspaceTmp = new EngineCPWorkspace();
		workspaceTmp.setInstance("sde:oracle11g:orcl");
		workspaceTmp.setPassword("GISDBTEMP");
		workspaceTmp.setUser("GISDBTEMP");
		daoTmp =new AoDaoImpl(workspaceTmp);
		System.out.println("GISDBTEMP数据源初始化连接成功");
	}
	
	@Test
	public void testClear() {
		DeleteParameter parameter ;
		for(String layerName :layerNames){
			parameter = new DeleteParameter();
			parameter.setLayerName(layerName);
			try {
 				daoTmp.delete(parameter);
				System.out.println(layerName+"数据清空成功");
			} catch (HDException e) {
				e.printStackTrace();
				System.exit(-1);
				
			}
		}
		
	}
	
	@Test
	public void testCopy(){
		QueryParameter qpram = new QueryParameter();
		InsertParameter ipram = null;
		for(String layerName :layerNames){
			qpram.setLayerName(layerName);
			try {
				HDFeatures fetures = dao.query(qpram);
				ipram = new InsertParameter();
				for(HDFeature feature :fetures.getFeatures()){
					feature.setAttribute("DWMC", "cdeb153d7d07432b937221b722611cb2");
					feature.setAttribute("SBZT", "1");
					feature.setAttribute("SHZT", "1");
					feature.setAttribute("OPERATIONTYPE", "1");
					feature.setAttribute("EXIST", "1");
					feature.setAttribute("CJZT", "0");
				}
				ipram.setFeatures(fetures.getFeatures());
				ipram.setLayerName(layerName);
				daoTmp.insert(ipram);
			} catch (HDException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
	
	@After
	public void after(){
		System.out.println("after");
		InitLicenses.init();
	}

}
