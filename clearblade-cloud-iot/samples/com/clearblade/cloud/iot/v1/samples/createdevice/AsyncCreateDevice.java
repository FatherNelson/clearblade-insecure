package com.clearblade.cloud.iot.v1.samples.createdevice;

import java.util.ArrayList;
import java.util.HashMap;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.createdevice.CreateDeviceRequest;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayConfig;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayType;
import com.clearblade.cloud.iot.v1.devicetypes.Status;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.utils.LogLevel;

public class AsyncCreateDevice {
	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String DEVICE = "";
	public static String NUMID = "";
	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		NUMID = System.getProperty("numId");
		asyncCreateDevice();
	}

	public static void asyncCreateDevice() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		RegistryName parent = RegistryName.of(PROJECT, LOCATION, REGISTRY);
		GatewayConfig gatewayCfg = new GatewayConfig();
		gatewayCfg.setGatewayType(GatewayType.NON_GATEWAY);
		Device device = Device.newBuilder()
				.setId(DEVICE).setName(DEVICE)
				.setNumId(NUMID).setBlocked(false)
				.setGatewayConfig(gatewayCfg)
				.setLogLevel(LogLevel.ERROR)
				.setCredentials(new ArrayList<>())
				.setLastErrorStatus(new Status())
				.setConfig(new DeviceConfig())
				.setMetadata(new HashMap<>())
				.build();
		CreateDeviceRequest request = CreateDeviceRequest.Builder.newBuilder().setParent(parent).setDevice(device)
				.build();
		Device response = deviceManagerAsyncClient.createDevice(request);
		if(response != null) {
			System.out.println("CreateDevice execution successful");
		}else {
			System.out.println("CreateDevice execution failed");
		}
	}
}
