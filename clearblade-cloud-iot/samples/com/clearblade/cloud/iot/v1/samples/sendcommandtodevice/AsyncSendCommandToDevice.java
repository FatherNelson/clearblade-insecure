package com.clearblade.cloud.iot.v1.samples.sendcommandtodevice;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceRequest;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceResponse;
import com.clearblade.cloud.iot.v1.utils.ByteString;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class AsyncSendCommandToDevice {

	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String DEVICE = "";
	public static String BINARYDATA = "";
	public static String SUBFOLDER = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();

	public static void main(String[] args) {
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		BINARYDATA = System.getProperty("binaryData");
		SUBFOLDER = System.getProperty("subFolder");
		if (REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		}
		if (LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		asyncSendCommandToDevice();
	}

	public static void asyncSendCommandToDevice() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		SendCommandToDeviceRequest request = SendCommandToDeviceRequest.Builder.newBuilder()
				.setName(DeviceName.of(PROJECT, LOCATION, REGISTRY, DEVICE).toString())
				.setBinaryData(new ByteString(BINARYDATA)).setSubfolder(SUBFOLDER).build();
		try {
			SendCommandToDeviceResponse response = deviceManagerAsyncClient.sendCommandToDevice(request);
			if (response != null) {
				System.out.println("SendCommandToDevice execution successful");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
