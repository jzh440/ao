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
import com.esri.arcgis.geometry.Path;
import com.esri.arcgis.geometry.Point;
import com.esri.arcgis.geometry.Polyline;
import com.esri.arcgis.geometry.wkbByteOrder;
import com.esri.arcgis.geometry.wkbGeometryType;
import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.arcgis.system.esriLicenseProductCode;
import com.esri.arcgis.system.esriLicenseStatus;

public class GeometryUtil {
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
	public static IGeometry convertWKBToGeometry(byte[] wkb)
    {
		IGeometry[] geoms=new IGeometry[1];
        try
        {
			ByteBuffer buffer=ByteBuffer.wrap(wkb);
			byte byteOrder = buffer.get();
			if (byteOrder == wkbByteOrder.wkbNDR)
				 buffer.order(ByteOrder.LITTLE_ENDIAN);
			else
				 buffer.order(ByteOrder.BIG_ENDIAN);
			int typeInt= buffer.getInt();
			int geometryType= typeInt & 0xff;
			
			
			if(geometryType==wkbGeometryType.wkbPoint)
			{
				 geoms[0] = new Point();
			}
			else if(geometryType==wkbGeometryType.wkbLinestring)
			{
				 geoms[0] = new Polyline();
			}
			int [] size=new int[wkb.length];
			IGeometryFactory3 factory = new GeometryEnvironment();
			factory.createGeometryFromWkbVariant(wkb,geoms,size);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        return geoms[0];
    }
	public static byte[] convertGeometryToWKB(IGeometry geometry)
    {
		
		byte[] b=null;
		try
		{
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
		IPoint point;
		try {
			List<IPoint> list=new ArrayList<IPoint>();
			point = new Point();
			point.setX(106.27722);
			point.setY(28.47292);
			list.add(point);
			point = new Point();
			point.setX(107.27722);
			point.setY(29.47292);
			list.add(point);
			Polyline metry=createPolyline(list);
			System.out.println(generateLineString(metry));
			byte[] byt=convertGeometryToWKB(metry);
			Polyline line=(Polyline) convertWKBToGeometry(byt);
			System.out.println(generateLineString(line));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void test(IGeometry shape){
		//IWkb wkb = (IWkb) shape; //(Where geometry is an instance of IGeometry)
		//int byte_count = wkb.getWkbSize();
		//byte[] wkb_bytes = new byte[byte_count];
		//wkb.exportToWkb(arg0, wkb_bytes);
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
