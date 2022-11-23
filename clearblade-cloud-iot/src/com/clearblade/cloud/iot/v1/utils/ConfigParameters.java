package com.clearblade.cloud.iot.v1.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigParameters {
	static Logger log = Logger.getLogger(ConfigParameters.class.getName());

	// Fields used to set values from properties file

	private String project;
	private String region;
	private String registry;
	private String endpointPort;

	private String getSystemCredentialsExtension;

	private String baseURL;
	private String cloudiotURLExtension;
	private String devicesURLExtension;
	private String devicesStatesURLExtension;
	private String cloudiotdevicesURLExtension;
	private String cloudiotDeviceConfigURLExtension;
	private String cloudiotConfigURLExtension;

	// Fields used to fetch values from Properties file
	Properties properties;
	InputStream input;

	public ConfigParameters() {
		properties = null;
		input = null;
		setValues();
	}

	/**
	 * Method used to read values form config.properties file and setting in field
	 * setters
	 */
	public void getConfigurations() {
		// Read inputs from properties file
		properties = new Properties();
		input = getClass().getClassLoader().getResourceAsStream("config.properties");
		try {
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}

	/**
	 * Method used to set values in Constants setter method
	 */
	public void setValues() {
		// get Configurations uploaded from config.properties file
		getConfigurations();
		this.setBaseURL(properties.getProperty("baseURL"));
		this.setCloudiotURLExtension(properties.getProperty("cloudiotURLExtension"));
		this.setDevicesURLExtension(properties.getProperty("devicesURLExtension"));
		this.setCloudiotdevicesURLExtension(properties.getProperty("cloudiotdevicesURLExtension"));
		this.setDevicesStatesURLExtension(properties.getProperty("devicesStatesURLExtension"));
		this.setCloudiotdevicesURLExtension(properties.getProperty("cloudiotDeviceConfigURLExtension"));
		this.setCloudiotConfigURLExtension(properties.getProperty("cloudiotConfigURLExtension"));
		this.setProject(properties.getProperty("project"));
		this.setRegion(properties.getProperty("region"));
		this.setRegistry(properties.getProperty("registry"));
		this.setGetSystemCredentialsExtension(properties.getProperty("getSystemCredentialsExtension"));
		this.setEndpointPort(properties.getProperty("endPointPort"));
	}

	public String getCloudiotURLExtension() {
		return cloudiotURLExtension;
	}

	public void setCloudiotURLExtension(String cloudiotURLExtension) {
		this.cloudiotURLExtension = cloudiotURLExtension;
	}

	public String getDevicesURLExtension() {
		return devicesURLExtension;
	}

	public void setDevicesURLExtension(String devicesURLExtension) {
		this.devicesURLExtension = devicesURLExtension;
	}

	public String getDevicesStatesURLExtension() {
		return devicesStatesURLExtension;
	}

	public void setDevicesStatesURLExtension(String devicesStatesURLExtension) {
		this.devicesStatesURLExtension = devicesStatesURLExtension;
	}

	public String getCloudiotdevicesURLExtension() {
		return cloudiotdevicesURLExtension;
	}

	public void setCloudiotdevicesURLExtension(String cloudiotdevicesURLExtension) {
		this.cloudiotdevicesURLExtension = cloudiotdevicesURLExtension;
	}

	public String getCloudiotDeviceConfigURLExtension() {
		return cloudiotDeviceConfigURLExtension;
	}

	public void setCloudiotDeviceConfigURLExtension(String cloudiotDeviceConfigURLExtension) {
		this.cloudiotDeviceConfigURLExtension = cloudiotDeviceConfigURLExtension;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}

	public String getGetSystemCredentialsExtension() {
		return getSystemCredentialsExtension;
	}

	public void setGetSystemCredentialsExtension(String getSystemCredentialsExtension) {
		this.getSystemCredentialsExtension = getSystemCredentialsExtension;
	}

	public String getEndpointPort() {
		return endpointPort;
	}

	public void setEndpointPort(String endpointPort) {
		this.endpointPort = endpointPort;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getCloudiotConfigURLExtension() {
		return cloudiotConfigURLExtension;
	}

	public void setCloudiotConfigURLExtension(String cloudiotConfigURLExtension) {
		this.cloudiotConfigURLExtension = cloudiotConfigURLExtension;
	}

}
