package com.hdsx.ao.api.annotation;

public @interface ServiceAPI {

	
	String name() default "";
	
	String describe() default "";
}
