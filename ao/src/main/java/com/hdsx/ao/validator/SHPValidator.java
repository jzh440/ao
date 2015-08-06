package com.hdsx.ao.validator;

import java.io.IOException;
import java.io.InputStream;

import com.esri.arcgis.geodatabase.IFeatureClass;
import com.esri.arcgis.geodatabase.IFeatureWorkspace;
import com.esri.arcgis.geodatabase.IField;
import com.esri.arcgis.geodatabase.IFields;
import com.esri.arcgis.interop.AutomationException;
import com.hdsx.ao.config.AEWorkspace;

public class SHPValidator {

	private IFeatureClass templete;
	private IFeatureClass target;
	
	public SHPValidator(IFeatureClass templete,IFeatureClass target){
		this.templete=templete;
		this.target=target;
	}
	
	public SHPValidator (InputStream templeteStream,InputStream targetStream)
	{
		
	}
	public SHPValidator(String templteDirectory,String targetDirectory){
		String directory1=templteDirectory.substring(0, templteDirectory.lastIndexOf("/"));
		String name1=templteDirectory.substring(templteDirectory.lastIndexOf("/")+1, templteDirectory.length());
		String directory2=targetDirectory.substring(0, targetDirectory.lastIndexOf("/"));
		String name2=targetDirectory.substring(targetDirectory.lastIndexOf("/")+1, targetDirectory.length());
		IFeatureWorkspace workspace1=(IFeatureWorkspace) AEWorkspace.getShapeWorkSpace(directory1);
		IFeatureWorkspace workspace2=(IFeatureWorkspace) AEWorkspace.getShapeWorkSpace(directory2);
		try {
			templete=workspace1.openFeatureClass(name1);
			target=workspace2.openFeatureClass(name2);
		} catch (AutomationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean validate(){
		return equals(templete,target);
	}
	public boolean validate(IFeatureClass templete,IFeatureClass target){
		return equals(templete,target);
	}
	protected boolean equals(IFeatureClass templete,IFeatureClass target){
		try {
			if(!(templete.getFeatureType()==target.getFeatureType()))
			{
				return false;
			}
			IFields templeteFields=templete.getFields();
			IFields targetFields=templete.getFields();
			if(!(templeteFields.getFieldCount()==targetFields.getFieldCount()))
			{
				return false;
			}
			for(int i=0,len=templeteFields.getFieldCount();i<len;i++)
			{
				IField templeteField=templeteFields.getField(i);
				IField targetField=targetFields.getField(i);
				if(! equals(templeteField,targetField))
				{
					return false;
				}
			}
		} catch (AutomationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	protected boolean equals(IField templeteField,IField targetField){
		try {
			if(templeteField.getName().equals(targetField.getName())&&
					templeteField.getType()==targetField.getType()	)
			{
				return true;
			}
		} catch (AutomationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
