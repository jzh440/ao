package com.hdsx.ao.dao;

import com.hdsx.ao.bean.HDFeatures;
import com.hdsx.ao.exception.HDException;
import com.hdsx.ao.parameter.UpdateParameter;
import com.hdsx.ao.parameter.DeleteParameter;
import com.hdsx.ao.parameter.InsertParameter;
import com.hdsx.ao.parameter.QueryParameter;





// TODO: Auto-generated Javadoc

/**
 * 数据访问接口 API
 *   
 * @author jingzh
 * 
 * @createDate 2015-10-14
 * 
 * @email jingzh@hdsxtech.com
 * 
 * @version 1.0
 */
public interface AoDao{
	
	/**
	 * 查询.
	 *
	 * @param QueryParameter parameter
	 * @return the Features
	 */
	HDFeatures query(QueryParameter parameter) throws HDException;
	
	/**
	 * 修改.
	 *
	 * @param QueryParameter parameter
	 * @return the Features
	 */
	int update(UpdateParameter parameter)throws HDException;
	
	/**
	 * 添加.
	 *
	 * @param QueryParameter parameter
	 * @return the Features
	 */
	int insert(InsertParameter parameter)throws HDException;
	
	/**
	 * 删除.
	 *
	 * @param QueryParameter parameter
	 * @return the Features
	 */
	int delete(DeleteParameter parameter)throws HDException;
}
