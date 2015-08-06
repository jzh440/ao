package com.hdsx.ao.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.arcgis.system.esriLicenseProductCode;
import com.esri.arcgis.system.esriLicenseStatus;

public class EsriLicenseListener implements  ServletContextListener{
	/** The log. */
	private static Logger log=LoggerFactory.getLogger(EsriLicenseListener.class);
	private AoInitialize ao;
	public void contextInitialized(ServletContextEvent sce) {
		try 
		{
			EngineInitializer.initializeVisualBeans();
			 ao= new AoInitialize();
			initializeArcGISLicenses(ao);
		} catch (Exception e) {
			log.debug("\n"+"ArcGis Engine 环境初始化失败，请检查ArcGIS Liense是否授权并且启动!");
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		try 
		{
			ao.shutdown();
		} catch (AutomationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void initializeArcGISLicenses(AoInitialize aoInit) throws AutomationException, IOException {
			if (aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeEngine) == esriLicenseStatus.esriLicenseAvailable)
            {
            	aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeEngine);
            	log.debug("\n"+"产品许可证号"+esriLicenseProductCode.esriLicenseProductCodeEngine);
            } 
			else if(aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeAdvanced)==esriLicenseStatus.esriLicenseAvailable)
            {
            	aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeAdvanced);
            	log.debug("\n"+"产品许可证号"+esriLicenseProductCode.esriLicenseProductCodeAdvanced);
            }	
			else if(aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeBasic)==esriLicenseStatus.esriLicenseAvailable)
            {
            	aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeBasic);
            	log.debug("\n"+"产品许可证号"+esriLicenseProductCode.esriLicenseProductCodeBasic);
            } 	
			else if(aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeEngineGeoDB)==esriLicenseStatus.esriLicenseAvailable)
            {
            	aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeEngineGeoDB);
            	log.debug("\n"+"产品许可证号"+esriLicenseProductCode.esriLicenseProductCodeEngineGeoDB);
            }	
			else if(aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeStandard)==esriLicenseStatus.esriLicenseAvailable)
            {
            	aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeStandard);
            	log.debug("\n"+"产品许可证号"+esriLicenseProductCode.esriLicenseProductCodeStandard);
            }
			else
			{
				log.error("Could not initialize an Engine or Basic License. Exiting application.");
				System.exit(-1);
			}
	}
}
