package com.hdsx.ao.util;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import com.esri.arcgis.geometry.GeometryEnvironment;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.geometry.IGeometryFactory3;
import com.esri.arcgis.geometry.IPoint;
import com.esri.arcgis.geometry.ITopologicalOperator;
import com.esri.arcgis.geometry.IWkb;
import com.esri.arcgis.geometry.IWkbProxy;
import com.esri.arcgis.geometry.Line;
import com.esri.arcgis.geometry.Path;
import com.esri.arcgis.geometry.Point;
import com.esri.arcgis.geometry.Polyline;
import com.esri.arcgis.geometry.esriShapeType;
import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.arcgis.system.esriLicenseProductCode;
import com.esri.arcgis.system.esriLicenseStatus;
import com.vividsolutions.jts.io.WKBConstants;
/**
 * 字符串工具类
 * @author jingzh
 *
 */
public class GemotryUtil {
	
	public static Polyline createPolyline(List<IPoint> points) 
	{
		Polyline poly=null;
		try
		{
			poly=new Polyline();
			Path[] paths=new Path[1];
			paths[0]=new Path();
			GeometryEnvironment gBridge = new GeometryEnvironment();
			gBridge.addPoints(paths[0], points.toArray(new IPoint[]{}));
			gBridge.addGeometries(poly, paths);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    return poly;
	 }
	public static Point createPoint(double x,double y) 
	{
		Point point=null;
		try
		{
			point=new Point();
			point.setX(x);
			point.setY(y);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    return point;
	}
	// var lines = ["linestring(102.0142 23.078,102.0254 23.0670)"];
	public static String generateLineString(IGeometry geometry){
		if(geometry==null)return "";
		Polyline line=(Polyline)geometry;
		StringBuffer buffer=new StringBuffer("linestring(");
		try
		{
			for(int i=0;i<line.getPointCount();i++)
			{
				IPoint point=line.getPoint(i);
				buffer.append(point.getX()+" "+point.getY());
				if(i!=line.getPointCount()-1)
				buffer.append(",");
			}
			buffer.append(")");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return buffer.toString();
	}
	public static IGeometry ConvertWKBToGeometry(byte[] wkb)
    {
        try
        {
			IGeometry[] geoms = {new Point()};
			int [] tt=new int[wkb.length];
			IGeometryFactory3 factory = new GeometryEnvironment();
			factory.createGeometryFromWkbVariant(wkb,geoms,tt);
			return geoms[0];
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        return null;
    }
	public static IGeometry ConvertWKBToGeometry2(byte[] wkb)
    {
        try
        {
			IGeometry[] geoms = {new Line()};
			Line line=null;
			int [] tt=new int[wkb.length];
			IGeometryFactory3 factory = new GeometryEnvironment();
			factory.createGeometryFromWkbVariant(wkb,geoms,tt);
			return geoms[0];
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        return null;
    }
	public static byte[] convertGeometryToWKB(IGeometry geometry)
    {
		
		byte[] b=null;
		try
		{
			//IWkb wkb = (IWkb) geometry;
	        ITopologicalOperator oper = (ITopologicalOperator)geometry;
	        oper.simplify();
	        IGeometryFactory3 factory = new GeometryEnvironment();
	        b = (byte[]) factory.createWkbVariantFromGeometry(geometry);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return b;
    }

	public static void main(String[] args) {
		IPoint point1;
		IPoint point2;
		try {
			point1 = new Point();
			point1.setX(106.27722);
			point1.setY(28.47292);
			point2 = new Point();
			point2.setX(107.27722);
			point2.setY(29.47292);
			List<IPoint> list=new ArrayList<IPoint>();
			list.add(point1);
			list.add(point2);
			Polyline metry=createPolyline(list);
			System.out.println(generateLineString(metry));
			byte[] byt1=convertGeometryToWKB(point1);
			byte[] byt2=convertGeometryToWKB(metry);
			Polyline line=(Polyline) ConvertWKBToGeometry2(byt2);
			int len=line.getGeometryCount();
			for(int i=0;i<len;i++)
			{
				IGeometry geometry=line.getGeometry(i);
				
				System.out.println(geometry.toString());
			}
			
			ByteBuffer buffer1=ByteBuffer.wrap(byt1);
			ByteBuffer buffer2=ByteBuffer.wrap(byt2);
			System.out.println(buffer1.get(1)+","+buffer2.get(1));
			byte byteOrder = buffer1.get();
			byte byteOrder2 = buffer2.get();
			 // default is big endian
			 if (byteOrder == WKBConstants.wkbNDR)
				 buffer1.order(ByteOrder.LITTLE_ENDIAN);
			 if (byteOrder2 == WKBConstants.wkbNDR)
			 	 buffer2.order(ByteOrder.LITTLE_ENDIAN);
			    int typeInt1 = buffer1.getInt();
			    int typeInt2 = buffer2.getInt();
			    int geometryType1 = typeInt1 & 0xff;
			    int geometryType2 = typeInt2 & 0xff;
			    System.out.println(geometryType1+","+geometryType2);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ConvertWKBToGeometry(wkb);
	}
	
	static
	{
		try 
		{
			EngineInitializer.initializeVisualBeans();
			AoInitialize ao= new AoInitialize();
			if (ao.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeEngine) == esriLicenseStatus.esriLicenseAvailable)
				ao.initialize(esriLicenseProductCode.esriLicenseProductCodeEngine);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
