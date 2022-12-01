package com.clearblade.cloud.iot.v1.samples.listdeviceconfigversions;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.listdeviceconfigversions.ListDeviceConfigVersionsRequest;
import com.clearblade.cloud.iot.v1.listdeviceconfigversions.ListDeviceConfigVersionsResponse;

public class SyncListDeviceConfigVersions {

	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String DEVICE = "";
	public static String NUMVERSION = "";
	
	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		NUMVERSION = System.getProperty("numVersion");
		syncDevicesConfigVersionsList();
	}

	public static void syncDevicesConfigVersionsList() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		ListDeviceConfigVersionsRequest request = ListDeviceConfigVersionsRequest.Builder.newBuilder()
				.setName(DeviceName
						.of(PROJECT, LOCATION, REGISTRY, DEVICE)
						.toString())
				.setNumVersions(Integer.parseInt(NUMVERSION)).build();
		ListDeviceConfigVersionsResponse response = deviceManagerClient.listDeviceConfigVersions(request);
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
