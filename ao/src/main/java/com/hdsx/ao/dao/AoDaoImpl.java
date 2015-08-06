package com.hdsx.ao.dao;
//批量查询反射时注意while循环bean每次都要从新获取新的
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
import com.esri.arcgis.geodatabase.IQueryFilter;
import com.esri.arcgis.geodatabase.QueryFilter;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.system.Cleaner;
import com.esri.arcgis.system.IPropertySet;
import com.hdsx.ao.config.AEWorkspace;
import com.hdsx.ao.config.DBHandler;
import com.hdsx.ao.page.Page;
import com.hdsx.ao.util.ArcGISUtil;
import com.hdsx.ao.util.FeatureUtil;


public class AoDaoImpl implements AoDao {

	private Logger log=LoggerFactory.getLogger(AoDaoImpl.class);

	private IFeatureWorkspace workspace;
	public AoDaoImpl(){
		this.workspace= DBHandler.getInstance().getWorkSpace();
	}
	public AoDaoImpl(IPropertySet prop){
		this.workspace= (IFeatureWorkspace) AEWorkspace.getSdeWorkSpace(prop);
	}
	public <T> T selectOne(String sql, T o, String tableName) {
		//T bean=null;
		try 
		{
			IFeatureClass featureClass=workspace.openFeatureClass(tableName);
			IQueryFilter filter=new QueryFilter();
			filter.setWhereClause(sql);
			IFeatureCursor featureCursor= featureClass.search(filter, true);
			IFeature feature=featureCursor.nextFeature();
			if(feature!=null)
			{
				//bean=AOUtil.getBean(feature, o);
				ArcGISUtil.getBean(feature, o);
			}
			Cleaner.release(featureCursor);
			featureCursor = null;  
		} catch (AutomationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return o;
	}
	public IGeometry selectShape(String sql, String tableName) {
		IGeometry shape=null;
		try 
		{
			IFeatureClass featureClass=workspace.openFeatureClass(tableName);
			IQueryFilter filter=new QueryFilter();
			filter.setWhereClause(sql);
			IFeatureCursor featureCursor= featureClass.search(filter, true);
			IFeature feature=featureCursor.nextFeature();
			if(feature!=null)
			{
				shape=feature.getShapeCopy();
			}
			Cleaner.release(featureCursor);
			featureCursor = null;  
		} catch (AutomationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return shape;
	} 
	public <T> List<T> selectList(String sql, T o, String tableName) {
		List<T> list=new ArrayList<T>();
		try 
		{
			IFeatureClass featureClass=workspace.openFeatureClass(tableName);
			IQueryFilter filter=new QueryFilter();
			filter.setWhereClause(sql);
			IFeatureCursor featureCursor= featureClass.search(filter, true);
			IFeature feature = (IFeature) featureCursor.nextFeature();
			int featureCtr = 0;
		    while (feature != null) {
		    	//T bean=AOUtil.getBean(feature, o);
		    	T bean=ArcGISUtil.getBean(feature, o);
		    	list.add(bean);
		    	feature = (IFeature) featureCursor.nextFeature();
		    	featureCtr++;
		    	o=(T) o.getClass().newInstance();
		    }
			log.info("Features added to list: " + featureCtr + " out of " + featureClass.featureCount(null));
			Cleaner.release(featureCursor);
			featureCursor = null;  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public <T> int insert(T o, String tableName) {
		int count=0;
		try 
		{
			IFeatureClass featureClass=workspace.openFeatureClass(tableName);
			IFeatureCursor featureCursor= featureClass.IFeatureClass_insert(true);
			IFeatureBuffer buffer = featureClass.createFeatureBuffer();
			//AOUtil.setBuffer(buffer, o);
			buffer=ArcGISUtil.getBuffer(buffer, o);
			featureCursor.insertFeature(buffer);
			featureCursor.flush();
			Cleaner.release(featureCursor);
			featureCursor = null;  
			count=1;
		} catch (Exception e) {
			e.printStackTrace();
			count=-1;
		}
		return count;
	}

	public <T> int insertBath(List<T> list, String tableName) {
		int count=0;
		try 
		{
			IFeatureClass featureClass=workspace.openFeatureClass(tableName);
			IFeatureCursor featureCursor= featureClass.IFeatureClass_insert(true);
			IFeatureBuffer buffer = featureClass.createFeatureBuffer();
			for(T o:list)
			{
				//AOUtil.setBuffer(buffer,o);
				buffer=ArcGISUtil.getBuffer(buffer, o);
				featureCursor.insertFeature(buffer);
			}
			featureCursor.flush();
			Cleaner.release(featureCursor);
			featureCursor = null;  
			count=1;
		} catch (Exception e) {
			e.printStackTrace();
			count=-1;
		}
		return count;
	}

	public <T> int update(T o, String sql, String tableName) {
		int count=0;
		try 
		{
			IFeatureClass featureClass=workspace.openFeatureClass(tableName);
			IQueryFilter filter=new QueryFilter();
			filter.setWhereClause(sql);
			IFeatureCursor featureCursor= featureClass.IFeatureClass_update(filter,true);
			IFeature feature=featureCursor.nextFeature();
			if(feature!=null)
			{
				//feature=AOUtil.getFeature(featureClass, feature, o);
				FeatureUtil.getFeature(feature, o);
				featureCursor.updateFeature(feature);
			}
			Cleaner.release(featureCursor);
			featureCursor = null;  
			count=1;
		} catch (Exception e) {
			e.printStackTrace();
			count=-1;
		}
		return count;
	}

	public int delete(String sql, String tableName) {
		int count=0;
		try 
		{
			IFeatureClass featureClass=workspace.openFeatureClass(tableName);
			IQueryFilter filter=new QueryFilter();
			filter.setWhereClause(sql);
			IFeatureCursor featureCursor= featureClass.IFeatureClass_update(filter, true);
			if(featureCursor.nextFeature()!=null)
			{
				featureCursor.deleteFeature();
			}
			Cleaner.release(featureCursor);
			featureCursor = null;  
			count=1;
		} catch (Exception e) {
			e.printStackTrace();
			count=-1;
		}
		return count;
	}

	public int deleteBath(String sql, String tableName) {
		int count=0;
		try 
		{
			IFeatureClass featureClass=workspace.openFeatureClass(tableName);
			IQueryFilter filter=new QueryFilter();
			filter.setWhereClause(sql);
			IFeatureCursor featureCursor= featureClass.IFeatureClass_update(filter, true);
			while(featureCursor.nextFeature()!=null)
			{
				featureCursor.deleteFeature();
			}
			Cleaner.release(featureCursor);
			featureCursor = null;  
			count=1;
		} catch (Exception e) {
			e.printStackTrace();
			count=-1;
		}
		return count;
	}

	public int selectCount(String sql, String tableName) {
		int count=0;
		try 
		{
			IFeatureClass featureClass=workspace.openFeatureClass(tableName);
			IQueryFilter filter=new QueryFilter();
			filter.setWhereClause(sql);
			count=featureClass.featureCount(filter);
		} catch (AutomationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}

	public <T> List<T> selectPage(String sql, Page page, T o, String tableName) {
		List<T> list=new ArrayList<T>();
		try 
		{
			IFeatureClass featureClass=workspace.openFeatureClass(tableName);
			IQueryFilter filter=new QueryFilter();
			filter.setWhereClause(sql);
			IFeatureCursor featureCursor= featureClass.search(filter, true);
			IFeature feature=null;
			do
			{
				feature=featureCursor.nextFeature();
				if(feature==null)continue;
				o=(T) o.getClass().newInstance();
				//T bean=AOUtil.getBean(feature, o);
				T bean=ArcGISUtil.getBean(feature, o);
				list.add(bean);
			}while(feature!=null);
			//总记录数
			int totalRows = list.size();
			page.setTotalRows(totalRows);
			int temp1 = totalRows%page.getPageSize();
			int temp2 = totalRows/page.getPageSize();
			int totalPages = temp1 == 0 ? temp2 : temp2 + 1;
			//设置总页数
			page.setTotalPages(totalPages);
			int fromIndex = page.getStartRow()-1;
			int end = page.getStartRow() + page.getPageSize()-1; 
			int toIndex = end - totalRows > 0 ? totalRows : end;
			list=list.subList(fromIndex, toIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
