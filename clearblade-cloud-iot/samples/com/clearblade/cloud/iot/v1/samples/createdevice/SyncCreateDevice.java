package com.clearblade.cloud.iot.v1.samples.createdevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.createdevice.CreateDeviceRequest;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayConfig;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayType;
import com.clearblade.cloud.iot.v1.devicetypes.Status;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.utils.LogLevel;

public class SyncCreateDevice {
	static Logger log = Logger.getLogger(SyncCreateDevice.class.getName());

	public static void main(String[] args) {
		syncCreateDevice();
	}

	public static void syncCreateDevice() {

		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		RegistryName parent = RegistryName.of("ingressdevelopmentenv", "us-central1", "Rashmi_Registry_Test");
		GatewayConfig gatewayCfg = new GatewayConfig();
		gatewayCfg.setGatewayType(GatewayType.NON_GATEWAY);
		Device device = Device.newBuilder()
				.setId("SyncTest22").setName("SyncTest22")
				.setNumId("2314").setBlocked(false)
				.setGatewayConfig(gatewayCfg)
				.setLogLevel(LogLevel.DEBUG)
				.setCredentials(new ArrayList<>())
				.setLastErrorStatus(new Status())
				.setConfig(new DeviceConfig())
				.setMetadata(new HashMap<>())
				.build();
		CreateDeviceRequest request = CreateDeviceRequest.Builder.newBuilder().setParent(parent).setDevice(device)
				.build();
		Device response = deviceManagerClient.createDevice(request);
		System.out.println(response.toBuilder().getName());

	}
}
