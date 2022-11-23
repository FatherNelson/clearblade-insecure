package com.clearblade.cloud.iot.v1.samples.binddevicetogateway;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayRequest;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayResponse;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class SyncBindDeviceToGateway {
	static Logger log = Logger.getLogger(SyncBindDeviceToGateway.class.getName());

	public static void main(String[] args) {
		syncBindDeviceToGateway();
	}

	public static void syncBindDeviceToGateway() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		BindDeviceToGatewayRequest request = BindDeviceToGatewayRequest.Builder.newBuilder()
				.setParent(RegistryName.of("ingressdevelopmentenv", "us-central1", "Rashmi_Registry_Test").toString())
				.setGateway("Rashmi_Gateway_Test").setDevice("Rashmi_Device_Test").build();
		BindDeviceToGatewayResponse response = deviceManagerClient.bindDeviceToGateway(request);
		System.out.println(response.toString());
	}
}
