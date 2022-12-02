package com.clearblade.cloud.iot.v1.samples.devicestateslist;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.devicestateslist.ListDeviceStatesRequest;
import com.clearblade.cloud.iot.v1.devicestateslist.ListDeviceStatesResponse;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceState;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class AsyncDeviceStatesList {

	public static String PROJECT = "";
	public static String  LOCATION = "";
	public static String  REGISTRY = "";
	public static String  DEVICE = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();

	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		if(REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		} 
		if(LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		asyncDeviceStatesList();
	}

	public static void asyncDeviceStatesList() {
		DeviceManagerAsyncClient deviceManagerClient = new DeviceManagerAsyncClient();

		ListDeviceStatesRequest request = ListDeviceStatesRequest.Builder.newBuilder().setName(DeviceName
				.of(PROJECT, LOCATION, REGISTRY, DEVICE)
				.toString()).setNumStates(1643330779).build();
		ListDeviceStatesResponse response = deviceManagerClient.listDeviceStates(request);

		if(response != null) {
			System.out.println("DeviceStatesList fetch successful");
			for (DeviceState element : response.getDeviceStatesList()) {
				System.out.println(element.toString());
			}

		}else {
			System.out.println("DeviceStatesList fetch failed");			
		}

	}

}
