package com.clearblade.cloud.iot.v1.samples.listdeviceconfigversions;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.listdeviceconfigversions.ListDeviceConfigVersionsRequest;
import com.clearblade.cloud.iot.v1.listdeviceconfigversions.ListDeviceConfigVersionsResponse;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class AsyncListDeviceConfigVersions {

	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String DEVICE = "";
	public static String NUMVERSION = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();
	
	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		NUMVERSION = System.getProperty("numVersion");
		if(REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		} 
		if(LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		asyncDevicesConfigVersionsList();
	}

	public static void asyncDevicesConfigVersionsList() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		ListDeviceConfigVersionsRequest request = ListDeviceConfigVersionsRequest.Builder.newBuilder()
				.setName(DeviceName
						.of(PROJECT, LOCATION, REGISTRY, DEVICE)
						.toString())
				.setNumVersions(Integer.parseInt(NUMVERSION)).build();
		ListDeviceConfigVersionsResponse response = deviceManagerAsyncClient.listDeviceConfigVersions(request);

		if(response != null) {
			System.out.println("DeviceConfigVersionsList fetch successful");
			for (DeviceConfig element : response.getDeviceConfigList()) {
				System.out.println(element.toString());
			}

		}else {
			System.out.println("DeviceConfigVersionsList fetch failed");			
		}
		
	}

}
