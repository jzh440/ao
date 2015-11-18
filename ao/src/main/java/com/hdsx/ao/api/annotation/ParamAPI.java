package com.hdsx.ao.api.annotation;

public @interface ParamAPI {

	String name()  default "";
	
	String alias() default "";
	
	boolean required() default false;
	
	String defaultvalue() default "";
	
}
