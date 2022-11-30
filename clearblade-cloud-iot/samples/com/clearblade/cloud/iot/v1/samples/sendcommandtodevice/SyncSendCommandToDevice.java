package com.clearblade.cloud.iot.v1.samples.sendcommandtodevice;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceRequest;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceResponse;
import com.clearblade.cloud.iot.v1.utils.ByteString;

public class SyncSendCommandToDevice {

	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String DEVICE = "";
	public static String BINARYDATA = "";
	public static String SUBFOLDER = "";
	
	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		BINARYDATA = System.getProperty("binaryData");
		SUBFOLDER = System.getProperty("subFolder");
		syncSendCommandToDevice();
	}

	public static void syncSendCommandToDevice() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		SendCommandToDeviceRequest request = SendCommandToDeviceRequest.Builder.newBuilder()
				.setName(DeviceName.of(PROJECT, LOCATION, REGISTRY, DEVICE).toString())
				.setBinaryData(new ByteString(BINARYDATA)).setSubfolder(SUBFOLDER).build();
		SendCommandToDeviceResponse response = deviceManagerClient.sendCommandToDevice(request);
		if(response != null) {
			System.out.println("SendCommandToDevice execution successful");
		}else {
			System.out.println("SendCommandToDevice execution failed");
		}
	}
}
