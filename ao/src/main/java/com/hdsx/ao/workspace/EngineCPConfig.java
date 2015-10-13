package com.hdsx.ao.workspace;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Properties;

import com.hdsx.ao.utile.StringUtile;

public class EngineCPConfig implements Cloneable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3413043646409831278L;

	public static Platform DEFAULT_PLATFORM = Platform.Oracle;
	
	public static String DEFAULT_VERSION = "sde.DEFAULT";
	
	public static String P_PLATFORM = "platform";
	
	public static String P_VERSION = "version";
	
	public static String P_INSTANCE = "instance";
	
	public static String P_USER = "user";
	
	public static String P_PASSWORD = "password";
	
	public static String P_DATABASE = "database";
	
	private Platform platform = DEFAULT_PLATFORM;
	
	private String version = DEFAULT_VERSION;
	
	private String instance;
	
	private String user;
	
	private String password;
	
	private String database;

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
	/**
	 * Loads the given properties file using the classloader.
	 * @param filename Config filename to load
	 * 
	 */
	protected void loadProperties(String filename) {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource(filename);
		if (url != null){
			try {
				Properties dbProps = new Properties();
				dbProps.load(url.openStream());
				platform = StringUtile.isEmpty(dbProps.getProperty(P_PLATFORM))?DEFAULT_PLATFORM:Platform.valueOf(dbProps.getProperty(P_PLATFORM));
				instance = dbProps.getProperty(P_INSTANCE);
				database = dbProps.getProperty(P_DATABASE);
				user     = dbProps.getProperty(P_USER);
				password = dbProps.getProperty(P_PASSWORD);
				version  = StringUtile.isEmpty(dbProps.getProperty(P_VERSION))?DEFAULT_VERSION:dbProps.getProperty(P_VERSION);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public EngineCPConfig clone() throws CloneNotSupportedException {
		EngineCPConfig clone = new EngineCPConfig();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field: fields){
			try {
				field.set(clone, field.get(this));
			} catch (Exception e) {
				// should never happen
			}
		}
		return clone;
	}

	@Override
	public String toString() {
		return "EngineCPConfig [platform=" + platform + ", version=" + version + ", instance=" + instance + ", user="
				+ user + ", password=" + password + ", database=" + database + "]";
	}
	
	
}
