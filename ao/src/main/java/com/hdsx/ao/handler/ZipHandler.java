package com.hdsx.ao.handler;


import java.util.List;

import com.hdsx.ao.request.Request;
import com.hdsx.ao.request.ZipRequest;



public class ZipHandler extends AbstractHandler implements Handler{

	private Handler nextHandler;
	private Request request;
	public ZipHandler(String layer){
		this.request=new ZipRequest(layer);
	}
	public <T> List<T> handlerRequest(String path) {
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
