package com.clearblade.cloud.iot.v1.samples.deletedevice;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.deletedevice.DeleteDeviceRequest;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;

public class AsyncDeleteDevice {

	public static String PROJECT = "";
	public static String  LOCATION = "";
	public static String  REGISTRY = "";
	public static String  DEVICE = "";

	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		asyncDeleteDevice();
	}

	public static void asyncDeleteDevice() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		DeviceName deviceName = DeviceName.of(PROJECT, LOCATION, REGISTRY, DEVICE);
		DeleteDeviceRequest request = DeleteDeviceRequest.Builder.newBuilder().setName(deviceName).build();
		deviceManagerAsyncClient.deleteDevice(request);
	}

}
