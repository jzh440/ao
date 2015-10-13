package com.hdsx.ao.utile;

import java.util.Collection;
import java.util.Map;

/**
 * 字符串工具类
 * @author jingzh
 *
 */
public class StringUtile {
	
	//判断对象是否为空
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj){
		if (obj == null) { return true;}
		else if (obj instanceof String && (obj.equals(""))) {return true;}
		else if (obj instanceof Number && ((Number) obj).doubleValue() == 0) {return true; }
		else if (obj instanceof Boolean && !((Boolean) obj)) {return true;}
		else if (obj instanceof Collection && ((Collection) obj).isEmpty()){return true;}
		else if (obj instanceof Map && ((Map) obj).isEmpty()) {return true;}
		else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {return true;}
		return false;
	}
}
