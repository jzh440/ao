package com.hdsx.ao.dao;

import java.util.List;

import com.esri.arcgis.geometry.IGeometry;
import com.hdsx.ao.page.Page;





// TODO: Auto-generated Javadoc
/**
 * The Interface SdeDao.
 */
public interface AoDao{
	
	/**
	 * 单条查询.
	 *
	 * @param <T> the generic type
	 * @param sql : LXDM ='G210'
	 * @param o the o
	 * @param tableName the table name
	 * @return the t
	 */
	<T> T selectOne(String sql, T o,String tableName);
	/**
	 * 只查询shape字段.
	 *
	 * @param <T> the generic type
	 * @param sql : LXDM ='G210'
	 * @param tableName the table name
	 * @return the t
	 */
	IGeometry selectShape(String sql,String tableName);
	
	/**
	 * 批量查询.
	 *
	 * @param <T> the generic type
	 * @param sql : LXDM LIKE '%'||'G'||'%'
	 * @param o the o
	 * @param tableName the table name
	 * @return the t
	 */
	 <T>List<T> selectList(String sql,T o,String tableName);
		
	/**
	 * 插入.
	 *
	 * @param <T> the generic type
	 * @param o the o
	 * @param tableName the table name
	 * @return the int
	 */
	<T> int insert(T o,String tableName);
	
	/**
	 * 批量插入.
	 *
	 * @param <T> the generic type
	 * @param list the list
	 * @param tableName the table name
	 * @return the int
	 */
	<T> int insertBath(List<T> list,String tableName);
	
	/**
	 * 修改.
	 *
	 * @param <T> the generic type
	 * @param o the o
	 * @param sql the sql
	 * @param tableName the table name
	 * @return the int
	 */
	<T> int update(T o,String sql,String tableName);
	
	/**
	 * 删除.
	 *
	 * @param sql the sql
	 * @param tableName the table name
	 * @return the int
	 */
	int delete(String sql,String tableName);
	/**
	 * 批量删除.
	 *
	 * @param sql the sql
	 * @param tableName the table name
	 * @return the int
	 */
	int deleteBath(String sql,String tableName);
	
	/**
	 * 计量.
	 *
	 * @param sql the sql
	 * @param tableName the table name
	 * @return the int
	 */
	int selectCount(String sql,String tableName);
	
	/**
	 * 分页查询.
	 *
	 * @param <T> the generic type
	 * @param sql the sql
	 * @param page the page
	 * @param o the o
	 * @param tableName the table name
	 * @return the list
	 */
	<T> List<T> selectPage(String sql,Page page,T o,String tableName);
	
	
	
	
}
