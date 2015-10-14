package com.hdsx.ao.utile;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.esri.arcgis.geometry.GeometryEnvironment;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.geometry.IGeometryFactory3;
import com.esri.arcgis.geometry.ITopologicalOperator;
import com.esri.arcgis.geometry.Point;
import com.esri.arcgis.geometry.Polygon;
import com.esri.arcgis.geometry.Polyline;
import com.esri.arcgis.geometry.wkbByteOrder;
import com.esri.arcgis.geometry.wkbGeometryType;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKBWriter;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
/**
 * 空间数据转换工具
 *   
 * @author jingzh
 * 
 * @createDate 2015-10-14
 * 
 * @email jingzh@hdsxtech.com
 * 
 * @version 1.0
 */
public class GeometryConverter {
	
	public static IGeometry esriWKB2Geometry(byte[] wkb){
		IGeometry[] geoms=new IGeometry[1];
        try{
			ByteBuffer buffer=ByteBuffer.wrap(wkb);
			byte byteOrder = buffer.get();
			if (byteOrder == wkbByteOrder.wkbNDR)
				 buffer.order(ByteOrder.LITTLE_ENDIAN);
			else
				 buffer.order(ByteOrder.BIG_ENDIAN);
			int typeInt= buffer.getInt();
			int geometryType= typeInt & 0xff;
			if(geometryType==wkbGeometryType.wkbPoint){
				 geoms[0] = new Point();
			}
			else if(geometryType==wkbGeometryType.wkbLinestring ||geometryType == wkbGeometryType.wkbLinestring){
				 geoms[0] = new Polyline();
			}else if(geometryType == wkbGeometryType.wkbPolygon){
				 geoms[0] = new Polygon();
			}else{
				System.out.println("类型暂不支持！！！");
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
	
	public static byte[] esriGeometry2WKB(IGeometry geometry){
		byte[] b=null;
		try{
	        ITopologicalOperator oper = (ITopologicalOperator)geometry;
	        oper.simplify();
	        IGeometryFactory3 factory = new GeometryEnvironment();
	        b = (byte[]) factory.createWkbVariantFromGeometry(geometry);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return b;
    }
	
	public static Geometry wkb2Geometry(byte[] bytes){
		WKBReader reader = new WKBReader();
		try {
			return reader.read(bytes);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] Geometry2wkb(Geometry geom){
		WKBWriter writer = new WKBWriter();
		return writer.write(geom);
	}
	
	public static String Geometry2wkt(Geometry geom){
		WKTWriter writer = new WKTWriter();
		return writer.write(geom);
	}
	
	public static Geometry wkt2Geometry(String wkt){
		WKTReader reader = new WKTReader();
		try {
			return reader.read(wkt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
