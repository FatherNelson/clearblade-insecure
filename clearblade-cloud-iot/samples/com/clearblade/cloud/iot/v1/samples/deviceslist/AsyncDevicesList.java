package com.clearblade.cloud.iot.v1.samples.deviceslist;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.deviceslist.DevicesListRequest;
import com.clearblade.cloud.iot.v1.deviceslist.DevicesListResponse;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayListOptions;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayType;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class AsyncDevicesList {

	public static String PROJECT = "";
	public static String  LOCATION = "";
	public static String  REGISTRY = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();
	
	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		if(REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		} 
		if(LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		asyncDevicesList();
	}

	public static void asyncDevicesList() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		RegistryName parent = RegistryName.of(PROJECT, LOCATION, REGISTRY);
		DevicesListRequest request = DevicesListRequest.Builder.newBuilder().setParent(parent.toString())
				.setGatewayListOptions(GatewayListOptions.newBuilder().setGatewayType(GatewayType.NON_GATEWAY).build())
				.setPageSize(2)
				.build();
		DevicesListResponse response = deviceManagerAsyncClient.listDevices(request);

		if(response != null) {
			System.out.println("DeviceList fetch successful");
			for (Device element : response.getDevicesList()) {
				System.out.println(element.toBuilder().getName());

			}
			System.out.println(response.getNextPageToken());

		}else {
			System.out.println("DeviceList fetch failed");			
		}
	}
}
