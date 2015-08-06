package com.hdsx.ao.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * 资源读取类.
 */
public class Resources {
	
	/** The class loader wrapper. */
	private static ClassLoaderWapper classLoaderWrapper=new ClassLoaderWapper();
	
	/** The charset. */
	private static Charset charset;
	
	/**
	 * Instantiates a new resources.
	 */
	public Resources() 
	{
		
	}
	//com/hdsx/mybatis/learn/jdbc.properties默认从src下面开始
	/**
	 * Gets the resource url.
	 *
	 * @param resource the resource
	 * @return the resource url
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static URL getResourceURL(String resource) throws IOException {
		return getResourceURL(null, resource); 
	}
	
	/**
	 * Gets the resource url.
	 *
	 * @param loader the loader
	 * @param resource the resource
	 * @return the resource url
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static URL getResourceURL(ClassLoader loader, String resource) throws IOException {
		URL url = classLoaderWrapper.getResourceAsURL(resource, loader);
		if (url == null) throw new IOException("Could not find resource " + resource);
		return url;
	}
	
	/**
	 * Gets the resource as stream.
	 *
	 * @param resource the resource
	 * @return the resource as stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static InputStream getResourceAsStream(String resource) throws IOException {
		return getResourceAsStream(null, resource);
	}
	
	/**
	 * Gets the resource as stream.
	 *
	 * @param loader the loader
	 * @param resource the resource
	 * @return the resource as stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static InputStream getResourceAsStream(ClassLoader loader, String resource) throws IOException {
		InputStream in = classLoaderWrapper.getResourceAsStream(resource, loader);
		if (in == null) throw new IOException("Could not find resource " + resource);
		return in;
	}
	
	/**
	 * Gets the resource as properties.
	 *
	 * @param resource the resource
	 * @return the resource as properties
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Properties getResourceAsProperties(String resource) throws IOException {
	    Properties props = new Properties();
	    InputStream in = getResourceAsStream(resource);
	    props.load(in);
	    in.close();
	    return props;
	  }

	  /*
	   * Returns a resource on the classpath as a Properties object
	   *
	   * @param loader   The classloader used to fetch the resource
	   * @param resource The resource to find
	   * @return The resource
	   * @throws java.io.IOException If the resource cannot be found or read
	   */
	  /**
  	 * Gets the resource as properties.
  	 *
  	 * @param loader the loader
  	 * @param resource the resource
  	 * @return the resource as properties
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
  	public static Properties getResourceAsProperties(ClassLoader loader, String resource) throws IOException {
	    Properties props = new Properties();
	    InputStream in = getResourceAsStream(loader, resource);
	    props.load(in);
	    in.close();
	    return props;
	  }

	  /*
	   * Returns a resource on the classpath as a Reader object
	   *
	   * @param resource The resource to find
	   * @return The resource
	   * @throws java.io.IOException If the resource cannot be found or read
	   */
	  /**
  	 * Gets the resource as reader.
  	 *
  	 * @param resource the resource
  	 * @return the resource as reader
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
  	public static Reader getResourceAsReader(String resource) throws IOException {
	    Reader reader;
	    if (charset == null) {
	      reader = new InputStreamReader(getResourceAsStream(resource));
	    } else {
	      reader = new InputStreamReader(getResourceAsStream(resource), charset);
	    }
	    return reader;
	  }

	  /*
	   * Returns a resource on the classpath as a Reader object
	   *
	   * @param loader   The classloader used to fetch the resource
	   * @param resource The resource to find
	   * @return The resource
	   * @throws java.io.IOException If the resource cannot be found or read
	   */
	  /**
  	 * Gets the resource as reader.
  	 *
  	 * @param loader the loader
  	 * @param resource the resource
  	 * @return the resource as reader
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
  	public static Reader getResourceAsReader(ClassLoader loader, String resource) throws IOException {
	    Reader reader;
	    if (charset == null) {
	      reader = new InputStreamReader(getResourceAsStream(loader, resource));
	    } else {
	      reader = new InputStreamReader(getResourceAsStream(loader, resource), charset);
	    }
	    return reader;
	  }

	  /*
	   * Returns a resource on the classpath as a File object
	   *
	   * @param resource The resource to find
	   * @return The resource
	   * @throws java.io.IOException If the resource cannot be found or read
	   */
	  /**
  	 * Gets the resource as file.
  	 *
  	 * @param resource the resource
  	 * @return the resource as file
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
  	public static File getResourceAsFile(String resource) throws IOException {
	    return new File(getResourceURL(resource).getFile());
	  }

	  /*
	   * Returns a resource on the classpath as a File object
	   *
	   * @param loader   - the classloader used to fetch the resource
	   * @param resource - the resource to find
	   * @return The resource
	   * @throws java.io.IOException If the resource cannot be found or read
	   */
	  /**
  	 * Gets the resource as file.
  	 *
  	 * @param loader the loader
  	 * @param resource the resource
  	 * @return the resource as file
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
  	public static File getResourceAsFile(ClassLoader loader, String resource) throws IOException {
	    return new File(getResourceURL(loader, resource).getFile());
	  }

	  /*
	   * Gets a URL as an input stream
	   *
	   * @param urlString - the URL to get
	   * @return An input stream with the data from the URL
	   * @throws java.io.IOException If the resource cannot be found or read
	   */
	  /**
  	 * Gets the url as stream.
  	 *
  	 * @param urlString the url string
  	 * @return the url as stream
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
  	public static InputStream getUrlAsStream(String urlString) throws IOException {
	    URL url = new URL(urlString);
	    URLConnection conn = url.openConnection();
	    return conn.getInputStream();
	  }

	  /*
	   * Gets a URL as a Reader
	   *
	   * @param urlString - the URL to get
	   * @return A Reader with the data from the URL
	   * @throws java.io.IOException If the resource cannot be found or read
	   */
	  /**
  	 * Gets the url as reader.
  	 *
  	 * @param urlString the url string
  	 * @return the url as reader
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
  	public static Reader getUrlAsReader(String urlString) throws IOException {
	    Reader reader;
	    if (charset == null) {
	      reader = new InputStreamReader(getUrlAsStream(urlString));
	    } else {
	      reader = new InputStreamReader(getUrlAsStream(urlString), charset);
	    }
	    return reader;
	  }

	  /*
	   * Gets a URL as a Properties object
	   *
	   * @param urlString - the URL to get
	   * @return A Properties object with the data from the URL
	   * @throws java.io.IOException If the resource cannot be found or read
	   */
	  /**
  	 * Gets the url as properties.
  	 *
  	 * @param urlString the url string
  	 * @return the url as properties
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
  	public static Properties getUrlAsProperties(String urlString) throws IOException {
	    Properties props = new Properties();
	    InputStream in = getUrlAsStream(urlString);
	    props.load(in);
	    in.close();
	    return props;
	  }

	  /*
	   * Loads a class
	   *
	   * @param className - the class to fetch
	   * @return The loaded class
	   * @throws ClassNotFoundException If the class cannot be found (duh!)
	   */
	  /**
  	 * Class for name.
  	 *
  	 * @param className the class name
  	 * @return the class
  	 * @throws ClassNotFoundException the class not found exception
  	 */
  	public static Class<?> classForName(String className) throws ClassNotFoundException {
	    return classLoaderWrapper.classForName(className);
	  }

	  /**
  	 * Gets the charset.
  	 *
  	 * @return the charset
  	 */
  	public static Charset getCharset() {
	    return charset;
	  }

	  /**
  	 * Sets the charset.
  	 *
  	 * @param charset the new charset
  	 */
  	public static void setCharset(Charset charset) {
	    Resources.charset = charset;
	  }
}
