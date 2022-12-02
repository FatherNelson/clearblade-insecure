package com.clearblade.cloud.iot.v1.samples.createdevice;

import java.util.ArrayList;
import java.util.HashMap;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.createdevice.CreateDeviceRequest;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayConfig;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayType;
import com.clearblade.cloud.iot.v1.devicetypes.Status;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;
import com.clearblade.cloud.iot.v1.utils.LogLevel;

public class SyncCreateDevice {

	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String DEVICE = "";
	public static String NUMID = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();
	
	public static void main(String[] args){
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		NUMID = System.getProperty("numId");
		if(REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		} 
		if(LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		syncCreateDevice();
	}

	public static void syncCreateDevice() {

		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		RegistryName parent = RegistryName.of(PROJECT,LOCATION, REGISTRY);
		GatewayConfig gatewayCfg = new GatewayConfig();
		gatewayCfg.setGatewayType(GatewayType.GATEWAY);
		Device device = Device.newBuilder()
				.setId(DEVICE).setName(DEVICE)
				.setNumId(NUMID).setBlocked(false)
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
		if(response != null) {
			System.out.println("CreateDevice execution successful");
		}else {
			System.out.println("CreateDevice execution failed");
		}
	}
}
