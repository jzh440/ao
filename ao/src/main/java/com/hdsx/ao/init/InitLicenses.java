package com.hdsx.ao.init;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.arcgis.system.esriLicenseProductCode;
import com.esri.arcgis.system.esriLicenseStatus;
/**
 * ArcGIS Engine 授权认证 
 *   
 * @author jingzh
 * 
 * @createDate 2015-10-14
 * 
 * @email jingzh@hdsxtech.com
 * 
 * @version 1.0
 */
public class InitLicenses {
	
	private static Logger log=LoggerFactory.getLogger(InitLicenses.class);
	
	public static void init(){
		try {
			EngineInitializer.initializeVisualBeans();
			AoInitialize ao= new AoInitialize();
			initializeArcGISLicenses(ao);
		} catch (Exception e) {
			log.error("ArcGis Engine 环境初始化失败，请检查ArcGIS Engine 是否安装或 ArcGIS Liense是否授权并且启动!");
			System.exit(-1);
		}
	}
	
	private static void initializeArcGISLicenses(AoInitialize aoInit) throws AutomationException, IOException {
		if (aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeEngine) == esriLicenseStatus.esriLicenseAvailable){
			aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeEngine);
			log.trace("本机器已安装产品许可证号"+esriLicenseProductCode.esriLicenseProductCodeEngine);
		} 
		else if(aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeAdvanced)==esriLicenseStatus.esriLicenseAvailable){
			aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeAdvanced);
			log.debug("本机器已安装产品许可证号"+esriLicenseProductCode.esriLicenseProductCodeAdvanced);
		}	
		else if(aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeBasic)==esriLicenseStatus.esriLicenseAvailable) {
			aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeBasic);
			log.debug("本机器已安装产品许可证号"+esriLicenseProductCode.esriLicenseProductCodeBasic);
		} 	
		else if(aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeEngineGeoDB)==esriLicenseStatus.esriLicenseAvailable){
			aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeEngineGeoDB);
			log.debug("本机器已安装产品许可证号"+esriLicenseProductCode.esriLicenseProductCodeEngineGeoDB);
		}	
		else if(aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeStandard)==esriLicenseStatus.esriLicenseAvailable) {
			aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeStandard);
			log.debug("本机器已安装产品许可证号"+esriLicenseProductCode.esriLicenseProductCodeStandard);
		}
		else {
			throw new IOException("Could not initialize an Engine or Basic License. Exiting application.");
		}
	}
}
