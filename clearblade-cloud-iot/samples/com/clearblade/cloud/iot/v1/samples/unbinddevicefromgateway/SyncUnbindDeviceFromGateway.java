package com.clearblade.cloud.iot.v1.samples.unbinddevicefromgateway;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayRequest;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayResponse;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class SyncUnbindDeviceFromGateway {
	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String GATEWAY = "";
	public static String DEVICE = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();

	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		GATEWAY = System.getProperty("gatewayName");
		DEVICE = System.getProperty("deviceName");
		if(REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		} 
		if(LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		syncUnbindDeviceFromGateway();
	}

	public static void syncUnbindDeviceFromGateway() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		UnbindDeviceFromGatewayRequest request = UnbindDeviceFromGatewayRequest.Builder.newBuilder()
				.setParent(RegistryName.of(PROJECT, LOCATION, REGISTRY).toString())
				.setGateway(GATEWAY).setDevice(DEVICE).build();

		UnbindDeviceFromGatewayResponse response = deviceManagerClient.unbindDeviceFromGateway(request);
		if(response != null) {
			System.out.println("UnbindDeviceFromGateway execution successful");
		}else {
			System.out.println("UnbindDeviceFromGateway execution failed");
		}
	}
}
