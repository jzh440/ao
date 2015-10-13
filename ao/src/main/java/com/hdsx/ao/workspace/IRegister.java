package com.hdsx.ao.workspace;

public interface IRegister {

	 void regist(String sourceName,EngineCPWorkspace workspace);
	 
	 EngineCPWorkspace get(String sourceName);
}
