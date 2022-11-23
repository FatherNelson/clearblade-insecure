package com.clearblade.cloud.iot.v1.samples.listdeviceconfigversions;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.listdeviceconfigversions.ListDeviceConfigVersionsRequest;
import com.clearblade.cloud.iot.v1.listdeviceconfigversions.ListDeviceConfigVersionsResponse;

public class AsyncListDeviceConfigVersions {

	static Logger log = Logger.getLogger(AsyncListDeviceConfigVersions.class.getName());

	public static void main(String[] args) {
		asyncDevicesConfigVersionsList();
	}

	public static void asyncDevicesConfigVersionsList() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		ListDeviceConfigVersionsRequest request = ListDeviceConfigVersionsRequest.Builder.newBuilder()
				.setName(DeviceName
						.of("ingressdevelopmentenv", "us-central1", "Rashmi_Registry_Test", "Rashmi_Device_Test")
						.toString())
				.setNumVersions(2).build();
		ListDeviceConfigVersionsResponse response = deviceManagerAsyncClient.listDeviceConfigVersions(request);
		for (DeviceConfig element : response.getDeviceConfigList()) {
			System.out.println(element.toString());
		}
	}

}
