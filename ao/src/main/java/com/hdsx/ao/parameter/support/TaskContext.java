package com.hdsx.ao.parameter.support;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TaskContext {
	
	private Map<String,Object> params=new ConcurrentHashMap<String, Object>();
	
	private TaskContext(){
	}
	
	public void setParameter(String name,Object value){
		this.params.put(name, value);
	}
	
	public Map<String,Object> getParams(){
		return this.params;
	}
	
	public static TaskContext fromHashMap(final Map<String,Object> params){
		TaskContext context=new TaskContext();
		Set<Entry<String, Object>> set=params.entrySet();
		Iterator<Entry<String, Object>> iterator=set.iterator();
		while(iterator.hasNext()){
			Entry<String, Object> entry=iterator.next();
			context.setParameter(entry.getKey(), entry.getValue());
		}
		return context;
	}
	
	public String getParameter(String name){
		Object val=this.params.get(name);
		if(val==null){
			return null;
		}
		return String.valueOf(this.params.get(name));
	}
	
	public Object getObjectParameter(String name){
		return  params.get(name);
	}
	
	public double getDoubleParameter(String name,double defaultVal){
		//	    	String val=this.getParameter(name);
		//	    	boolean isNumber=NumberUtils.isNumber(val);
		//	    	if(isNumber){
		//	    		return NumberUtils.toDouble(val);
		//	    	}else{
		//	    		return defaultVal;
		//	    	}
		return 0.0;
	}
	
	public double getDoubleParameter(String name){
		return getDoubleParameter(name,Double.NaN);
	}
	
	public int getIntParameter(String name,int defaultVal){
		//	    	String val=this.getParameter(name);
		//	    	boolean isNumber=NumberUtils.isNumber(val);
		//	    	if(isNumber){
		//	    		return NumberUtils.toInt(val);
		//	    	}else{
		//	    		return defaultVal;
		//	    	}
		return 0;
	}
	
	public int getIntParameter(String name){
		return getIntParameter(name,0);
	}
	
	public  boolean getBooleanParameter(String name){
		String val=this.getParameter(name);
		return "true".equalsIgnoreCase(val);
	}
}
