package com.hdsx.ao.utile;

import java.io.IOException;

import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFeatureBuffer;
import com.esri.arcgis.geodatabase.IField;
import com.esri.arcgis.geodatabase.IFields;
import com.esri.arcgis.geodatabase.esriFieldType;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.system.IMemoryBlobStream;
import com.esri.arcgis.system.IMemoryBlobStreamVariant;
import com.esri.arcgis.system.MemoryBlobStream;
import com.hdsx.ao.bean.HDFeature;

public class FeatureConverter {

	public static HDFeature convert(IFeature feature,HDFeature hdfeature){
		try {
			IFields iFields = feature.getFields();
			for(int i=0,size=iFields.getFieldCount();i<size;i++){
				IField field = iFields.getField(i);
				if(field.getType() == esriFieldType.esriFieldTypeGeometry){
					byte[] bytes = GeometryConverter.esriGeometry2WKB(feature.getShapeCopy());
					hdfeature.setGeometry(GeometryConverter.wkb2Geometry(bytes));
					continue;
				}
				if(field.getType() == esriFieldType.esriFieldTypeBlob){
					MemoryBlobStream blob = (MemoryBlobStream) feature.getValue(i);
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
				}else{
					hdfeature.setAttribute(field.getName(), feature.getValue(i));	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hdfeature;
	}
	
	public static IFeatureBuffer convert(HDFeature hdfeature,IFeatureBuffer buffer){
		try{
			if(hdfeature ==  null) return buffer;
			//属性
			IFields iFields = buffer.getFields();
			for(int i=0,count=iFields.getFieldCount();i<count;i++){
				IField field = iFields.getField(i);
				int index=iFields.findField(field.getName());
				if(hdfeature.getAttributes().containsKey(field.getName())){
					if(field.getType() == esriFieldType.esriFieldTypeBlob){
						IMemoryBlobStream pMBS = new MemoryBlobStream();
						IMemoryBlobStreamVariant varBlobStream = (IMemoryBlobStreamVariant)pMBS;
						varBlobStream.importFromVariant(hdfeature.getAttribute(field.getName()));
						buffer.setValue(index, varBlobStream);
						continue;
					}else{
						buffer.setValue(index, hdfeature.getAttribute(field.getName()));
						continue;
					}
				}
			}
			//空间
			if(hdfeature.getGeometry()!=null && !hdfeature.getGeometry().isEmpty()){
				byte[] bytes = GeometryConverter.Geometry2wkb(hdfeature.getGeometry());
				IGeometry geom = GeometryConverter.esriWKB2Geometry(bytes);
				buffer.setShapeByRef(geom);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		} 
		return buffer;
	}
	
}
