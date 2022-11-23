package com.clearblade.cloud.iot.v1.samples.binddevicetogateway;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayRequest;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayResponse;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class AsyncBindDeviceToGateway {
	static Logger log = Logger.getLogger(AsyncBindDeviceToGateway.class.getName());

	public static void main(String[] args) {
		asyncBindDeviceToGateway();
	}

	public static void asyncBindDeviceToGateway() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		BindDeviceToGatewayRequest request = BindDeviceToGatewayRequest.Builder.newBuilder()
				.setParent(RegistryName.of("ingressdevelopmentenv", "us-central1", "Rashmi_Registry_Test").toString())
				.setGateway("Rashmi_Gateway_Test").setDevice("myOldDevice").build();
		BindDeviceToGatewayResponse response = deviceManagerAsyncClient.bindDeviceToGateway(request);
		System.out.println(response.toString());

	}
}
