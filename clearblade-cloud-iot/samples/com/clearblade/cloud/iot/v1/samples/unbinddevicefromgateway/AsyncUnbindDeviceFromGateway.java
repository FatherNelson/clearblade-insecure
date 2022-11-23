package com.clearblade.cloud.iot.v1.samples.unbinddevicefromgateway;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayRequest;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayResponse;

public class AsyncUnbindDeviceFromGateway {
	static Logger log = Logger.getLogger(AsyncUnbindDeviceFromGateway.class.getName());

	public static void main(String[] args) {
		asyncUnbindDeviceFromGateway();
	}

	public static void asyncUnbindDeviceFromGateway() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		UnbindDeviceFromGatewayRequest request = UnbindDeviceFromGatewayRequest.Builder.newBuilder()
				.setParent(RegistryName.of("ingressdevelopmentenv", "us-central1", "Rashmi_Registry_Test").toString())
				.setGateway("Rashmi_Gateway_Test").setDevice("myOldDevice").build();

		UnbindDeviceFromGatewayResponse response = deviceManagerAsyncClient.unbindDeviceFromGateway(request);
		System.out.println(response.toString());

	}
}
