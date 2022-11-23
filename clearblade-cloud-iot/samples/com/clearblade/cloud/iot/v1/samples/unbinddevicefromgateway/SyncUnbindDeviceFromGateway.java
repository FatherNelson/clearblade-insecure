package com.clearblade.cloud.iot.v1.samples.unbinddevicefromgateway;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayRequest;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayResponse;

public class SyncUnbindDeviceFromGateway {
	static Logger log = Logger.getLogger(SyncUnbindDeviceFromGateway.class.getName());

	public static void main(String[] args) {
		syncUnbindDeviceFromGateway();
	}

	public static void syncUnbindDeviceFromGateway() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		UnbindDeviceFromGatewayRequest request = UnbindDeviceFromGatewayRequest.Builder.newBuilder()
				.setParent(RegistryName.of("ingressdevelopmentenv", "us-central1", "Rashmi_Registry_Test").toString())
				.setGateway("Rashmi_Gateway_Test").setDevice("Rashmi_Device_Test").build();

		UnbindDeviceFromGatewayResponse response = deviceManagerClient.unbindDeviceFromGateway(request);
		System.out.println(response.toString());
	}
}
