package com.clearblade.cloud.iot.v1.samples.unbinddevicefromgateway;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayRequest;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayResponse;

public class AsyncUnbindDeviceFromGateway {
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
		asyncUnbindDeviceFromGateway();
	}

	public static void asyncUnbindDeviceFromGateway() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		UnbindDeviceFromGatewayRequest request = UnbindDeviceFromGatewayRequest.Builder.newBuilder()
				.setParent(RegistryName.of(PROJECT, LOCATION, REGISTRY).toString())
				.setGateway(GATEWAY).setDevice(DEVICE).build();

		UnbindDeviceFromGatewayResponse response = deviceManagerAsyncClient.unbindDeviceFromGateway(request);
		if(response != null) {
			System.out.println("UnbindDeviceFromGateway execution successful");
		}else {
			System.out.println("UnbindDeviceFromGateway execution failed");
		}

	}
}
