package com.hdsx.ao.measure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.esri.arcgis.geometry.IPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class MeasureUtil.
 */
public class MeasureUtil {
	
	/**
	 * 合并路段.
	 *
	 * @param roads the roads
	 * @return the list
	 */
	public static List<HDLine> mergeLine(List<HDLine> roads)
	{
		List<HDLine> lines=getLinesHasM(roads);//设置M值
		Comparator<HDLine> comparator=new ComparatorLines();
		Collections.sort(lines, comparator);
		List<HDLine> routes=new ArrayList<HDLine>();
		HDLine line=lines.get(0);
		for(int i=1;i<lines.size();i++)
		{
			if(isMerge(line,lines.get(i)))
			{
				line=mergeLine(line,lines.get(i));
			}
			else
			{
				routes.add(line);
				line=lines.get(i);
			}
		}
		routes.add(line);
		return routes;
	}
	
	/**
	 * 合并两条路段.
	 *
	 * @param line1 the line1
	 * @param line2 the line2
	 * @return the hD line
	 */
	public static HDLine mergeLine(HDLine line1,HDLine line2)
	{
		line1.setEndPos(line2.getEndPos());
		List<IPoint> points=new ArrayList<IPoint>();
		points.addAll(line1.getPoints());
		points.addAll(line2.getPoints());
		line1.setPoints(points);
		return line1;
	}
	
	/**
	 * 检查是否可以合并.
	 *
	 * @param line1 the line1
	 * @param line2 the line2
	 * @return true, if is merge
	 */
	public static boolean isMerge(HDLine line1,HDLine line2){
		if(line1.getEndPos()==line2.getStartPos())
			return true;
		else
			return false;
	}
	/**
	 * 设置坐标M值.
	 *
	 * @param lines the lines
	 * @return the lines has m
	 */
	public static List<HDLine> getLinesHasM(List<HDLine> lines){
         try
         {
        	 double dDist = 0;
             double dLength = getLengthByXY(lines);//得到长度
        	 for(HDLine line:lines)
             {
            	  double posLength = line.getEndPos() - line.getStartPos();//止点-起点=桩号里程
            	  List<IPoint> list=line.getPoints();
            	  IPoint first=list.get(0);
            	  first.setM(line.getStartPos());
            	  list.add(0, first);
            	  for(int i=1;i<list.size();i++)
            	  {
	            		IPoint p1 = list.get(i-1);
	                  	IPoint p2 = list.get(i);
	                  	dDist = getSubLen(p1.getX(), p1.getY(),p2.getX(), p2.getY());//得到两点间的实际距离
	                  	double m=line.getStartPos() +(dDist * posLength / dLength);//得到容差后的距离：即桩号
	                  	p2.setM(m) ;//得到新点
	                  	list.remove(i);
	                  	list.add(i, p2);
            	  }
             }
         }
         catch(Exception ex)
         {
        	 ex.printStackTrace();
         }
         return lines;
	}
	 /**
	 * 获得整个线的坐标距离.
	 *
	 * @param lines the lines
	 * @return the length by xy
	 */
	public static double getLengthByXY(List<HDLine> lines)
     {
         double dDist = 0;
         try 
         {
	         for (HDLine line : lines)
	         {
	        	 List<IPoint> points=line.getPoints();
	             for (int i = 1; i < points.size(); i++)
	             {
					dDist += getSubLen(points.get(i - 1).getX(), points.get(i - 1).getY(), points.get(i).getX(),  points.get(i).getY());
	             }
	         }
         } 
         catch (Exception e) {
				e.printStackTrace();
         }
         return dDist;
     }
	 /**
 	 * 获得两个坐标的距离.
 	 *
 	 * @param x1 the x1
 	 * @param y1 the y1
 	 * @param x2 the x2
 	 * @param y2 the y2
 	 * @return the sub len
 	 */
 	public static double getSubLen(double x1, double y1, double x2, double y2)
     {
         double radLat1 = (y1 * 3.1415927 / 180.0);
         double radLat2 = (y2 * 3.1415927 / 180.0);

         double a = (radLat1 - radLat2) / 2;

         double b = ((x1 * 3.1415927 / 180.0) - (x2 * 3.1415927 / 180.0)) / 2;//               (dLongitudel1-dLongitude2)/2;
         double sina = Math.sin(a);
         double sinb = Math.sin(b);

         double dLen = 2 * Math.asin(Math.sqrt((sina * sina + Math.cos(radLat1) * Math.cos(radLat2) * sinb * sinb))) * 6378.137;
         dLen = Math.round(dLen * 10000) / 10000;
         return dLen;
     }
}
