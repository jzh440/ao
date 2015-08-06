package com.hdsx.ao.handler;

import java.util.List;

public interface Handler {
	 <T> List<T> handlerRequest(String path);
	 
	  void addHander(Handler handler);
}
