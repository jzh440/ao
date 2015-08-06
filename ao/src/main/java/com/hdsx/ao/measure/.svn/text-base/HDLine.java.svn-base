package com.hdsx.ao.measure;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.geometry.IPoint;
import com.esri.arcgis.geometry.Polyline;
import com.esri.arcgis.interop.AutomationException;

// TODO: Auto-generated Javadoc
/**
 * 路段.
 */
public class HDLine implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 起点桩号. */
	private double startPos;
	
	/** 止点桩号. */
	private double endPos;
	
	/** 路段长度. */
	private double length;
	
	/** 空间对象. */
	private IGeometry geometry;
	
	/** 路段点集合. */
	private List<IPoint> points;
	
		
	/**
	 * Gets the start pos.
	 *
	 * @return the start pos
	 */
	public double getStartPos() {
		return startPos;
	}
	
	/**
	 * Sets the start pos.
	 *
	 * @param startPos the new start pos
	 */
	public void setStartPos(double startPos) {
		this.startPos = startPos;
	}
	
	/**
	 * Gets the end pos.
	 *
	 * @return the end pos
	 */
	public double getEndPos() {
		return endPos;
	}
	
	/**
	 * Sets the end pos.
	 *
	 * @param endPos the new end pos
	 */
	public void setEndPos(double endPos) {
		this.endPos = endPos;
	}
	
	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public double getLength() {
		return length;
	}
	
	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(double length) {
		this.length = length;
	}
	
	/**
	 * Gets the geometry.
	 *
	 * @return the geometry
	 */
	public IGeometry getGeometry() {
		return geometry;
	}
	
	/**
	 * Sets the geometry.
	 *
	 * @param geometry the new geometry
	 */
	public void setGeometry(IGeometry geometry) {
		this.geometry = geometry;
		if(geometry!=null)
		{
			this.points=convertLineToPoints(geometry);
		}
	}
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public List<IPoint> getPoints() {
		return points;
	}
	
	/**
	 * Sets the points.
	 *
	 * @param points the new points
	 */
	public void setPoints(List<IPoint> points) {
		this.points = points;
	}
	
	/**
	 * 将线空间对象转化为点集合.
	 *
	 * @param geometry the geometry
	 * @return the list
	 */
	public List<IPoint> convertLineToPoints(IGeometry geometry){
		List<IPoint> points=new ArrayList<IPoint>();
		Polyline line=(Polyline) geometry;
		try 
		{
			int count=line.getPointCount();
			for(int i=0;i<count;i++)
			{
				IPoint point=line.getPoint(i);
				points.add(point);
			}
		} catch (AutomationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return points;
	}
	
	/**
	 * 自动转化为点集合.
	 *
	 * @return the list
	 */
	public List<IPoint> convertLineToPoints(){
		List<IPoint> points=new ArrayList<IPoint>();
		Polyline line=(Polyline) geometry;
		try 
		{
			int count=line.getPointCount();
			for(int i=0;i<count;i++)
			{
				IPoint point=line.getPoint(i);
				points.add(point);
			}
		} catch (AutomationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return points;
	}

	@Override
	public String toString() {
		return "HDLine [startPos=" + startPos + ", endPos=" + endPos
				+ ", length=" + length + ", geometry=" + geometry + ", points="
				+ points + "]";
	}
	
}
