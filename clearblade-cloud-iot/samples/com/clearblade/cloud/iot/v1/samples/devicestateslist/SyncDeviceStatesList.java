package com.clearblade.cloud.iot.v1.samples.devicestateslist;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.devicestateslist.ListDeviceStatesRequest;
import com.clearblade.cloud.iot.v1.devicestateslist.ListDeviceStatesResponse;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceState;

public class SyncDeviceStatesList {
	public static String PROJECT = "";
	public static String  LOCATION = "";
	public static String  REGISTRY = "";
	public static String  DEVICE = "";

	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		syncDeviceStatesList();
	}

	public static void syncDeviceStatesList() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();

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
