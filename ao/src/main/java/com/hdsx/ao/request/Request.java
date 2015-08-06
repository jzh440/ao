package com.hdsx.ao.request;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface Request.
 */
public interface Request {
	
	/**
	 * 读取文件.
	 *
	 * @param <T> the generic type
	 * @param path the path
	 * @return the list
	 */
	<T> List<T> getList(String path);
	
	/**
	 * 检查类型是否正确.
	 *
	 * @param path the path
	 * @return the type
	 */
	boolean getType(String path);
}
