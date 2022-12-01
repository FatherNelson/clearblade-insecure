package com.clearblade.cloud.iot.v1.samples.deletedevice;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.deletedevice.DeleteDeviceRequest;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;

public class SyncDeleteDevice {

	public static String PROJECT = "";
	public static String  LOCATION = "";
	public static String  REGISTRY = "";
	public static String  DEVICE = "";

	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		syncDeleteDevice();
	}

	public static void syncDeleteDevice() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		DeviceName deviceName = DeviceName.of(PROJECT, LOCATION, REGISTRY, DEVICE);
		DeleteDeviceRequest request = DeleteDeviceRequest.Builder.newBuilder().setName(deviceName).build();
		deviceManagerClient.deleteDevice(request);
	}

}
