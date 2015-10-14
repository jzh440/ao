package com.hdsx.ao.sql;

import com.hdsx.ao.bean.HDFeature;
import com.hdsx.ao.parameter.DeleteParameter;
import com.hdsx.ao.parameter.InsertParameter;
import com.hdsx.ao.parameter.QueryParameter;
import com.hdsx.ao.parameter.UpdateParameter;
import com.hdsx.ao.utile.StringUtile;
/**
 * 空间数据访问SQL监控
 *   
 * @author jingzh
 * 
 * @createDate 2015-10-14
 * 
 * @email jingzh@hdsxtech.com
 * 
 * @version 1.0
 */
public class SQLRunner {

	public String run(QueryParameter parameter){
		SQL sqlBuilder = new SQL();
		sqlBuilder.SELECT(parameter.getOutFields() == null ? "*" :parameter.getOutFields()).FROM(parameter.getLayerName()).WHERE(parameter.getWhere()).ORDER_BY(parameter.getOrderByFields());
		return sqlBuilder.toString();
	}
	
	public String run(DeleteParameter parameter){
		SQL sqlBuilder = new SQL();
		sqlBuilder.DELETE(parameter.getLayerName()).WHERE(parameter.getWhere());
		return sqlBuilder.toString();
	}
	
	public String run(InsertParameter parameter,HDFeature target){
		SQL sqlBuilder = new SQL();
		String columns = StringUtile.collect2String(target.getAttributes().keySet(), ",");
		String values = StringUtile.collect2String(target.getAttributes().values(), ",");
		sqlBuilder.INSERT(parameter.getLayerName()).VALUES(columns, values);
		return sqlBuilder.toString();
	}
	
	public String run(UpdateParameter parameter,HDFeature target){
		SQL sqlBuilder = new SQL();
		sqlBuilder.UPDATE(parameter.getLayerName()).SET("").WHERE(parameter.getWhere());
		return sqlBuilder.toString();
	}
}
