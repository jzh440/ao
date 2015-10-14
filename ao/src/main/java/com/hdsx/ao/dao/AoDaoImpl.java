package com.hdsx.ao.dao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFeatureBuffer;
import com.esri.arcgis.geodatabase.IFeatureClass;
import com.esri.arcgis.geodatabase.IFeatureCursor;
import com.esri.arcgis.geodatabase.IFeatureWorkspace;
import com.esri.arcgis.geodatabase.IFields;
import com.esri.arcgis.geodatabase.IQueryFilter;
import com.esri.arcgis.geodatabase.ISpatialFilter;
import com.esri.arcgis.geodatabase.QueryFilter;
import com.esri.arcgis.geodatabase.SpatialFilter;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.system.Cleaner;
import com.hdsx.ao.bean.HDFeature;
import com.hdsx.ao.bean.HDFeatures;
import com.hdsx.ao.bean.HDField;
import com.hdsx.ao.exception.HDException;
import com.hdsx.ao.parameter.DeleteParameter;
import com.hdsx.ao.parameter.InsertParameter;
import com.hdsx.ao.parameter.QueryParameter;
import com.hdsx.ao.parameter.UpdateParameter;
import com.hdsx.ao.utile.FeatureConverter;
import com.hdsx.ao.workspace.IHDWorkspace;


public class AoDaoImpl implements AoDao {

	private Logger log=LoggerFactory.getLogger(AoDaoImpl.class);

	private IFeatureWorkspace workspace;
	
	public AoDaoImpl(IHDWorkspace workspace){
		this.workspace= (IFeatureWorkspace) workspace.getWorkspace() ;
	}

	@Override
	public HDFeatures query(QueryParameter parameter) throws HDException{
		// TODO Auto-generated method stub
		int count = 0;
		HDFeatures hdfeatures = null;
		IFeatureClass featureClass;
		IFeatureCursor featureCursor;
		try {
			featureClass = workspace.openFeatureClass(parameter.getLayerName());
			ISpatialFilter filter = new SpatialFilter();
			filter.setWhereClause(parameter.getWhere());
			filter.setSubFields(parameter.getOutFields());
			featureCursor = featureClass.search(filter, true);
			HDFeature hdfeature ;
			hdfeatures = new HDFeatures ();
			hdfeatures.setGeometryType(featureClass.getFeatureType()+"");
			//查询元数据
			HDField field ;
			IFields iFields = featureCursor.getFields();
			List<HDField> hdFields = new ArrayList<HDField>();
			for(int i=0,size=iFields.getFieldCount();i<size;i++){
				field = new HDField();
				field.setAlias(iFields.getField(i).getAliasName());
				field.setName(iFields.getField(i).getName());
				field.setType(iFields.getField(i).getType()+"");
				hdFields.add(field);
			}
			hdfeatures.setFields(hdFields);
			//查询结果集
			IFeature feature = featureCursor.nextFeature();
			while(feature != null){
				hdfeature = new HDFeature();
				FeatureConverter.convert(feature, hdfeature);
				hdfeatures.addFeature(hdfeature);
				feature = featureCursor.nextFeature();
				count += 1;
				System.out.println("已查询数据"+count+"条，当前执行到:"+hdfeature.toString());
			}
			Cleaner.release(featureCursor);
			//featureCursor.flush();
			featureCursor = null;   
		} catch (AutomationException e) {
			throw new HDException(e.getDescription(),e.getCause());
		} catch (IOException e) {
			throw new HDException(e.getMessage(),e.getCause());
		}
		return hdfeatures;
	}

	@Override
	public int update(UpdateParameter parameter) throws HDException{
		// TODO Auto-generated method stub
		int count = 0;
		IFeatureClass featureClass;
		IFeatureCursor featureCursor;
		try {
			featureClass=workspace.openFeatureClass(parameter.getLayerName());
			ISpatialFilter filter = new SpatialFilter();
			filter.setWhereClause(parameter.getWhere());
			featureCursor = featureClass.IFeatureClass_update(filter,true);
			IFeature feature = featureCursor.nextFeature();
			FeatureConverter.convert(feature, parameter.getFeature());
			while(feature != null){
				featureCursor.updateFeature(feature);
				count+=1;
			}
			featureCursor.flush();
			Cleaner.release(featureCursor);
			featureCursor = null;  
		} catch (AutomationException e) {
			count = -1;
			throw new HDException(e.getDescription(),e.getCause());
		} catch (IOException e) {
			count = -1;
			throw new HDException(e.getMessage(),e.getCause());
		}
		return count;
	}

	@Override
	public int insert(InsertParameter parameter) throws HDException{
		// TODO Auto-generated method stub
		int count = 0;
		IFeatureBuffer buffer;
		IFeatureClass featureClass;
		IFeatureCursor featureCursor;
		try {
			featureClass = workspace.openFeatureClass(parameter.getLayerName());
			featureCursor = featureClass.IFeatureClass_insert(true);
			buffer = featureClass.createFeatureBuffer();
			for(HDFeature feature:parameter.getFeatures()){
				FeatureConverter.convert(feature, buffer);
				featureCursor.insertFeature(buffer);
				count+=1;
			}
			featureCursor.flush();
			Cleaner.release(featureCursor);
			featureCursor = null;   
		} catch (AutomationException e) {
			count = -1;
			throw new HDException(e.getDescription(),e.getCause());
		} catch (IOException e) {
			count = -1;
			throw new HDException(e.getMessage(),e.getCause());
		}
		return count;
	}

	@Override
	public int delete(DeleteParameter parameter) throws HDException{
		// TODO Auto-generated method stub
		int count = 0;
		IFeatureClass featureClass;
		IFeatureCursor featureCursor;
		try {
			featureClass = workspace.openFeatureClass(parameter.getLayerName());
			IQueryFilter filter = new QueryFilter();
			filter.setWhereClause(parameter.getWhere());
			featureCursor = featureClass.IFeatureClass_update(filter, true);
			while(featureCursor.nextFeature()!=null){
				featureCursor.deleteFeature();
				count+=1;
			}
			featureCursor.flush();
			Cleaner.release(featureCursor);
			featureCursor = null;  
		} catch (AutomationException e) {
			count = -1;
			throw new HDException(e.getDescription(),e.getCause());
		} catch (IOException e) {
			count = -1;
			throw new HDException(e.getMessage(),e.getCause());
		}
		return count;
	}

	
}
