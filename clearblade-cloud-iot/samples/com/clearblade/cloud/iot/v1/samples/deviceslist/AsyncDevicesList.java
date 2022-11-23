package com.clearblade.cloud.iot.v1.samples.deviceslist;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.deviceslist.DevicesListRequest;
import com.clearblade.cloud.iot.v1.deviceslist.DevicesListResponse;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayListOptions;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayType;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class AsyncDevicesList {

	static Logger log = Logger.getLogger(AsyncDevicesList.class.getName());

	public static void main(String[] args) {
		asyncDevicesList();
	}

	public static void asyncDevicesList() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		RegistryName parent = RegistryName.of("ingressdevelopmentenv", "us-central1", "MandarTest1");
		DevicesListRequest request = DevicesListRequest.Builder.newBuilder().setParent(parent.toString())
				.setGatewayListOptions(GatewayListOptions.newBuilder().setGatewayType(GatewayType.NON_GATEWAY).build())
				.setPageSize(2)
				.build();
		DevicesListResponse response = deviceManagerAsyncClient.listDevices(request);

		for (Device element : response.getDevicesList()) {
			System.out.println(element.toBuilder().getName());

		}
		System.out.println(response.getNextPageToken());
	}
}
