package com.clearblade.cloud.iot.v1.utils;

public class Constants {
	
	private Constants() {}

	//keys used in project
	public static final String AUTH_ACCESS = "CLEARBLADE_CONFIGURATION";
	public static final String AUTH_REGISTRY = "CLEARBLADE_REGISTRY";
	public static final String AUTH_REGION = "CLEARBLADE_REGION";
	 
	public static final String ADMIN_SYSTEM_KEY = "systemKey";
	public static final String ADMIN_TOKEN = "token";
	public static final String BASE_URL = "url";
	public static final String PROJECT_NAME = "project";
	public static final String USER_SYSTEM_KEY = "systemKey";
	public static final String USER_TOKEN = "serviceAccountToken";
	public static final String API_BASE_URL = "url";

	
	// Constants used for setting up http connection - for common use
	public static final String HTTPS_URL_PREFIX = "https://";
	public static final String HTTP_REQUEST_METHOD_TYPE_POST = "POST";
	public static final String HTTP_REQUEST_METHOD_TYPE_DELETE = "DELETE";
	public static final String HTTP_REQUEST_METHOD_TYPE_GET = "GET";
	public static final String HTTP_REQUEST_METHOD_TYPE_PATCH = "PATCH";
	public static final String HTTP_REQUEST_PROPERTY_TOKEN_KEY = "ClearBlade-UserToken";
	public static final String HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY = "Content-Type";
	public static final String HTTP_REQUEST_PROPERTY_ACCEPT_KEY = "Accept";
	public static final String HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE = "application/json";
	
	//Constant values used in project 
	public static final String UTF8 = "utf-8";
	public static final String ENDPOINTPORT=":443";
	public static final String WEBHOOK="/api/v/4/webhook/execute/";
	public static final String DEVICES_URL_EXTENSION="/cloudiot_devices";
	public static final String CLOUDIOT_URL_EXTENSION="/cloudiot";
	public static final String DEVICES_STATES_URL_EXTENSION="/cloudiot_devices_states";
	public static final String CLOUDIOT_DEVICES_URL_EXTENSION="/cloudiotdevice_devices";
	public static final String CLOUDIOT_DEVICE_CONFIG_URL_EXTENSION="/cloudiot_devices_configVersions";
	public static final String GET_SYSTEM_CREDENTIALS_EXTENSION="/api/v/1/code/";
	
}
