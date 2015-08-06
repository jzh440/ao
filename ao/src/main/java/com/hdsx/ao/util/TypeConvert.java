package com.hdsx.ao.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFeatureBuffer;
import com.esri.arcgis.geodatabase.esriFieldType;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.system.IMemoryBlobStream;
import com.esri.arcgis.system.IMemoryBlobStreamVariant;
import com.esri.arcgis.system.MemoryBlobStream;

public class TypeConvert {
	/**
	   * Get field type description.
	   *
	   * @param geometryType The value representing the geometry type
	   * @return String representing The description of the geometry type.
	 * @throws IOException 
	 * @throws AutomationException 
	   */
	public static Object convertJava(int fieldType,Object value,IFeature feature) throws Exception {
	    switch (fieldType) {
		    case esriFieldType.esriFieldTypeSmallInteger:
		    	return new Short(value+"");
		      
		    case esriFieldType.esriFieldTypeInteger:
		    	return new Integer(value+"");
		      
		    case esriFieldType.esriFieldTypeSingle:
		    	return new Float(value+"");
		      
		    case esriFieldType.esriFieldTypeDouble:
		    	DecimalFormat df = new DecimalFormat("########.000");
		    	return new Double(df.format(value));
		      
		    case esriFieldType.esriFieldTypeString:
		    	return new String(value+"");
		      
		    case esriFieldType.esriFieldTypeDate:
		    	return value;
		      
		    case esriFieldType.esriFieldTypeOID:
		    	return new Long(value+"");
		      
		    case esriFieldType.esriFieldTypeGeometry:
		    	return feature.getShapeCopy();
		      
		    case esriFieldType.esriFieldTypeBlob:
		    	MemoryBlobStream blob = (MemoryBlobStream) value;
				IMemoryBlobStreamVariant varBlobStream = (IMemoryBlobStreamVariant)blob;
				Object[] bts=null;
				try {
					bts=new Object[1];
					varBlobStream.exportToVariant(bts);
				} catch (AutomationException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return bts[0];
		    }
	    return value;
	  }
	  
	 protected static void convertArcObject(IFeatureBuffer buffer,int index,Object value){
			Class<?> clas=value.getClass();
			try
			{
				if(clas==short.class)
				{
					buffer.setValue(index, new Short(value+""));
				}
				else if(clas==int.class)
				{
					buffer.setValue(index, new Integer(value+""));
				}
				else if(clas==long.class)
				{
					buffer.setValue(index, new Long(value+""));
				}
				else if(clas==char.class)
				{
					buffer.setValue(index, value);
				}
				else if(clas==float.class)
				{
					buffer.setValue(index, new Float(value+""));
				}
				else if(clas==double.class)
				{
					buffer.setValue(index, new Double(value+""));
				}
				else if(clas==String.class)
				{
					buffer.setValue(index, new String(value+""));
				}
				else if(clas==IGeometry.class)
				{
					IGeometry geometry=(IGeometry)value;
					buffer.setShapeByRef(geometry);
				}
				else if(clas==Date.class)
				{
					buffer.setValue(index, value);
				}
				else if(clas==byte[].class)
				{
					IMemoryBlobStream pMBS = new MemoryBlobStream();
					IMemoryBlobStreamVariant varBlobStream = (IMemoryBlobStreamVariant)pMBS;
					varBlobStream.importFromVariant(value);
					buffer.setValue(index, varBlobStream);
				}
				else
				{
					buffer.setValue(index, value);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	  protected static void convertArcObject(IFeature feature,int index,Object value){
			Class<?> clas=value.getClass();
			try
			{
				if(clas==short.class)
				{
					feature.setValue(index, new Short(value+""));
				}
				else if(clas==int.class)
				{
					feature.setValue(index, new Integer(value+""));
				}
				else if(clas==long.class)
				{
					feature.setValue(index, new Long(value+""));
				}
				else if(clas==char.class)
				{
					feature.setValue(index, value);
				}
				else if(clas==float.class)
				{
					feature.setValue(index, new Float(value+""));
				}
				else if(clas==double.class)
				{
					feature.setValue(index, new Double(value+""));
				}
				else if(clas==String.class)
				{
					feature.setValue(index, new String(value+""));
				}
				else if(clas==Date.class)
				{
					feature.setValue(index, value);
				}
				else if(clas==IGeometry.class)
				{
					IGeometry geometry=(IGeometry)value;
					feature.setShapeByRef(geometry);
				}
				else if(clas==byte[].class)
				{
					IMemoryBlobStream pMBS = new MemoryBlobStream();
					IMemoryBlobStreamVariant varBlobStream = (IMemoryBlobStreamVariant)pMBS;
					varBlobStream.importFromVariant(value);
					feature.setValue(index, varBlobStream);
				}
				else
				{
					feature.setValue(index, value);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
}
