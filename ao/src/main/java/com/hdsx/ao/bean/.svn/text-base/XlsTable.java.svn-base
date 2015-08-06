package com.hdsx.ao.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.hdsx.ao.util.ReflectUtils;



// TODO: Auto-generated Javadoc
/**
 * The Class XlsTable.
 */
public class XlsTable implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	 //string Table ="GIS_LX";
    //string RowStart="7";
    //string[] PKFieldsShape="LXBM;ZDZH" ;
    //string[] PKFieldsExcel="路线代码;止点桩号"
	/** The Layer. */
 	private String Layer;
	
	/** The Row start. */
	private String RowStart;
   
	private String className;
	
	/** The PK fields shape. */
	private String[] PKFieldsShape;
	
	/** The PK fields excel. */
	private String[] PKFieldsExcel;

	/** The Columns. */
	private List<XlsColumn> Columns;

	
	
	public XlsTable() {
		super();
		// TODO Auto-generated constructor stub
	}


	public XlsTable(String layer, String rowStart, String className,
			String[] pKFieldsShape, String[] pKFieldsExcel,
			List<XlsColumn> columns) {
		super();
		Layer = layer;
		RowStart = rowStart;
		this.className = className;
		PKFieldsShape = pKFieldsShape;
		PKFieldsExcel = pKFieldsExcel;
		Columns = columns;
	}


	/**
	 * Gets the layer.
	 *
	 * @return the layer
	 */
	public String getLayer() {
		return Layer;
	}

	/**
	 * Sets the layer.
	 *
	 * @param layer the new layer
	 */
	public void setLayer(String layer) {
		Layer = layer;
	}

	/**
	 * Gets the row start.
	 *
	 * @return the row start
	 */
	public String getRowStart() {
		return RowStart;
	}

	/**
	 * Sets the row start.
	 *
	 * @param rowStart the new row start
	 */
	public void setRowStart(String rowStart) {
		RowStart = rowStart;
	}

	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	/**
	 * Gets the pK fields shape.
	 *
	 * @return the pK fields shape
	 */
	public String[] getPKFieldsShape() {
		return PKFieldsShape;
	}

	/**
	 * Sets the pK fields shape.
	 *
	 * @param pKFieldsShape the new pK fields shape
	 */
	public void setPKFieldsShape(String[] pKFieldsShape) {
		PKFieldsShape = pKFieldsShape;
	}

	/**
	 * Gets the pK fields excel.
	 *
	 * @return the pK fields excel
	 */
	public String[] getPKFieldsExcel() {
		return PKFieldsExcel;
	}

	/**
	 * Sets the pK fields excel.
	 *
	 * @param pKFieldsExcel the new pK fields excel
	 */
	public void setPKFieldsExcel(String[] pKFieldsExcel) {
		PKFieldsExcel = pKFieldsExcel;
	}

	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public List<XlsColumn> getColumns() {
		return Columns;
	}

	/**
	 * Sets the columns.
	 *
	 * @param columns the new columns
	 */
	public void setColumns(List<XlsColumn> columns) {
		Columns = columns;
	}

	public Object getBean(){
		Object obj=null;
		try 
		{
			obj= Class.forName(className).newInstance();
			ReflectUtils.setValue("id", UUID.randomUUID().toString(), obj);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public Class<?> getBeanClass(){
		try 
		{
			return  Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String toString() {
		return "XlsTable [Layer=" + Layer + ", RowStart=" + RowStart
				+ ", PKFieldsShape=" + Arrays.toString(PKFieldsShape)
				+ ", PKFieldsExcel=" + Arrays.toString(PKFieldsExcel)
				+ ", Columns=" + Columns + "]";
	}
	
	
}
