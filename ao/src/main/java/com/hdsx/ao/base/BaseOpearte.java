package com.hdsx.ao.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.arcgis.geometry.IGeometry;
import com.hdsx.ao.dao.AoDao;
import com.hdsx.ao.dao.AoDaoImpl;
import com.hdsx.ao.page.Page;


// TODO: Auto-generated Javadoc
/**
 * 
 * The Class BaseOpearte.
 *
 * @param <T> the generic type
 */
public abstract class BaseOpearte<T> {
	
	/** The log. */
	private static Logger log=LoggerFactory.getLogger(BaseOpearte.class);
	
	/** The dao proxy. */
	private AoDao daoProxy;
	
	/** The table name. */
	private String tableName;
	
	/** The model. */
	private T model;  
	/**
	 * Instantiates a new base opearte.
	 *
	 * @param tableName the table name
	 */
	public BaseOpearte(String tableName){
		this.tableName=tableName;
		//this.model=getModel();
		this.daoProxy = (AoDao) Proxy.newProxyInstance(
				AoDao.class.getClassLoader(),
		        new Class[]{AoDao.class},
		        new DaoInvokeHandler());
	}
	
	/**
	 * 插入.
	 *
	 * @param o the o
	 * @return the int
	 */
	protected int insert(T o) {
		return daoProxy.insert(o, tableName);
	}
	
	/**
	 * 插入.
	 *
	 * @param list the list
	 * @return the int
	 */
	protected int insertBath(List<T> list) {
		return daoProxy.insertBath(list, tableName);
	}

	/**
	 * 修改.
	 *
	 * @param sql the sql
	 * @param o the o
	 * @return the int
	 */
	protected int update(String sql,T o) {
		return daoProxy.update(o, sql, tableName);
	}
	
	/**
	 * 删除.
	 *
	 * @param sql the sql
	 * @return the int
	 */
	protected int delete(String sql) {
		return daoProxy.delete(sql, tableName);
	}

	/**
	 * 批量删除.
	 *
	 * @param sql the sql
	 * @return the int
	 */
	protected int deleteBath(String sql) {
		return daoProxy.deleteBath(sql, tableName);
	}
	
	/**
	 * 单条查询.
	 *
	 * @param sql the sql
	 * @return the t
	 */
	protected T selectOne(String sql) {
		return daoProxy.selectOne(sql, getModel(), tableName);
	}
	/**
	 * 查询shape.
	 *
	 * @param sql the sql
	 * @return the t
	 */
	protected IGeometry selectShape(String sql) {
		return daoProxy.selectShape(sql, tableName);
	}

	/**
	 * 统计.
	 *
	 * @param sql the sql
	 * @return the int
	 */
	protected int selectCount(String sql ) {
		return daoProxy.selectCount(sql, tableName);
	}

	/**
	 * 批量查询.
	 *
	 * @param sql the sql
	 * @return the list
	 */
	protected List<T> selectList(String sql) {
		return daoProxy.selectList(sql, getModel(), tableName);
	}

	/**
	 * 分页查询.
	 *
	 * @param sql the sql
	 * @param page the page
	 * @return the list
	 */
	protected List<T> selectPage(String sql,Page page)
	{
		return daoProxy.selectPage(sql, page, getModel(), tableName);
	}
	
	/**
	 * 批量查询.
	 *
	 * @return the list
	 */
	protected List<T> selectList() {
		return daoProxy.selectList("", getModel(), tableName);
	}
	/**
	 * The Class DaoInvokeHandler.
	 */
	private static class DaoInvokeHandler implements InvocationHandler{
		
		/* (non-Javadoc)
		 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
		 */
		public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
			final AoDao dao = new AoDaoImpl();
			try {
				final Object result = method.invoke(dao, args);
				return result;
			} catch (Throwable t) {
				log.info("dao代理出错");
				t.printStackTrace();
			} 
			return null;
		}
	}
	
	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public T getModel(){
		try {
			// 得到model的类型信息
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[0];
			// 通过反射生成model的实例
			return (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
