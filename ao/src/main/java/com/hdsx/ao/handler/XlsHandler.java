package com.hdsx.ao.handler;

import java.util.List;


import com.hdsx.ao.request.Request;
import com.hdsx.ao.request.XlsRequest;


public class XlsHandler extends AbstractHandler implements Handler{

	private Handler nextHandler;
	
	private Request request;
	
	public XlsHandler(String layer){
		this.request=new XlsRequest(layer);
	}

	public <T> List<T> handlerRequest(String path) {
		// TODO Auto-generated method stub
		if(request.getType(path))
		{
			return request.getList(path);
		}
		return nextHandler.handlerRequest(path);
	}

	public void addHander(Handler handler) {
		// TODO Auto-generated method stub
		this.nextHandler=handler;
	}

}
