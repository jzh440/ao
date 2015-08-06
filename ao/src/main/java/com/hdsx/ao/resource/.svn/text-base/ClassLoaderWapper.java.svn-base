package com.hdsx.ao.resource;

import java.io.InputStream;
import java.net.URL;

public class ClassLoaderWapper {
	 ClassLoader defaultClassLoader;
	 ClassLoader systemClassLoader;
	 public ClassLoaderWapper()
	 {
		 systemClassLoader=ClassLoader.getSystemClassLoader();
	 }
	 public URL getResourceAsURL(String resource)
	 {
		 return getResourceAsURL(resource,systemClassLoader);
	 }
	 
	 public URL getResourceAsURL(String resource, ClassLoader classLoader) {
		    return getResourceAsURL(resource, getClassLoaders(classLoader));
	 }
	 
	 URL getResourceAsURL(String resource,ClassLoader[] classLoaders)
	 {
		 URL url;
		 for(ClassLoader cl:classLoaders)
		 {		
			 
			 if(cl!=null)
			 {	 
				 url = cl.getResource(resource);
				 if (null == url) url = cl.getResource("/" + resource);
				 if (null != url) return url;
			 }
		 }
		 return null;
	 }
	 
	 public InputStream getResourceAsStream(String resource) {
		 return getResourceAsStream(resource, getClassLoaders(null));
	 }
	 public InputStream getResourceAsStream(String resource, ClassLoader classLoader) {
		 return getResourceAsStream(resource, getClassLoaders(classLoader));
	 }
	 InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
		 for (ClassLoader cl : classLoader) {
			 if (null != cl) {
				 // try to find the resource as passed
				 InputStream returnValue = cl.getResourceAsStream(resource);
				 // now, some class loaders want this leading "/", so we'll add it and try again if we didn't find the resource
				 if (null == returnValue) returnValue = cl.getResourceAsStream("/" + resource);
				 if (null != returnValue) return returnValue;
			 }
		 }
		 return null;
	 }
	 
	 public Class<?> classForName(String name) throws ClassNotFoundException {
		 return classForName(name, getClassLoaders(null));
	 }
	 public Class<?> classForName(String name, ClassLoader classLoader) throws ClassNotFoundException {
		 return classForName(name, getClassLoaders(classLoader));
	 }
	 Class<?> classForName(String name, ClassLoader[] classLoader) throws ClassNotFoundException {
		 for (ClassLoader cl : classLoader) {
			 if (null != cl) { 

				 try 
				 {
					 Class<?> c = Class.forName(name, true, cl);
					 if (null != c) return c;
				 } catch (ClassNotFoundException e) {
					 // we'll ignore this until all classloaders fail to locate the class
				 }
			 }
		 }
		 throw new ClassNotFoundException("Cannot find class: " + name);
	 }
	 
	 
	 ClassLoader[] getClassLoaders(ClassLoader classLoader){
		 return new ClassLoader[]{
				 	classLoader, 
			        defaultClassLoader, 
			        Thread.currentThread().getContextClassLoader(), 
			        getClass().getClassLoader(), 
			        systemClassLoader};
		 }
	 }
