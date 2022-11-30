package com.clearblade.cloud.iot.v1.samples.binddevicetogateway;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayRequest;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayResponse;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class AsyncBindDeviceToGateway {
	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String GATEWAY = "";
	public static String DEVICE = "";

	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		GATEWAY = System.getProperty("gatewayName");
		DEVICE = System.getProperty("deviceName");
		asyncBindDeviceToGateway();
	}

	public static void asyncBindDeviceToGateway() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		BindDeviceToGatewayRequest request = BindDeviceToGatewayRequest.Builder.newBuilder()
				.setParent(RegistryName.of(PROJECT, LOCATION, REGISTRY).toString())
				.setGateway(GATEWAY).setDevice(DEVICE).build();
		BindDeviceToGatewayResponse response = deviceManagerAsyncClient.bindDeviceToGateway(request);
		if(response != null) {
			System.out.println("BindDeviceToGateway execution successful");
		}else {
			System.out.println("BindDeviceToGateway execution failed");
		}
	}
}
