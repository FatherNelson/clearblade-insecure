package com.clearblade.cloud.iot.v1.samples.updatedevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceCredential;
import com.clearblade.cloud.iot.v1.registrytypes.PublicKeyCredential;
import com.clearblade.cloud.iot.v1.registrytypes.PublicKeyFormat;
import com.clearblade.cloud.iot.v1.updatedevice.UpdateDeviceRequest;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;
import com.clearblade.cloud.iot.v1.utils.LogLevel;

public class AsyncUpdateDevice {
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String  DEVICE = "";
	public static String  UPDATEMASK = "";
	public static String  ARG="";
	public static String[] NEWARGS = null;
	public static String KEYFORMAT ="";
	public static String KEYVAL = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();

	public static void main(String[] args) {		
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		UPDATEMASK = System.getProperty("updateMask");
		if(REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		} 
		if(LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		if(!(System.getProperty("arg").isBlank() || System.getProperty("arg").isEmpty()|| System.getProperty("arg")==null))
			ARG = System.getProperty("arg");
		if(System.getProperty("newArgs") != null)
			NEWARGS = System.getProperty("newArgs").split(","); 
		asyncUpdateDevice();
	}

	public static void asyncUpdateDevice() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();		
		Device device = new Device();
		device.toBuilder().setId(DEVICE).setName(DEVICE).build();
		if(UPDATEMASK .equals("logLevel")) {
			device.toBuilder().setLogLevel(LogLevel.valueOf(ARG));
		}else if(UPDATEMASK .equals("blocked")) {
			device.toBuilder().setBlocked(Boolean.valueOf(ARG));
		}else if(UPDATEMASK .equals("metadata")) {
			Map<String, String> metadata = new HashMap<>();
			for(int i=0;i<NEWARGS.length;i++) {
				String key = NEWARGS[i];
				String val = NEWARGS[i+1];
				metadata.put(key,val);
			}
			device.toBuilder().setMetadata(metadata);
		}else if(UPDATEMASK.equals("credentials")) {
			List<DeviceCredential> listCredentials = new ArrayList<>();
			PublicKeyCredential publicKeyCredential = new PublicKeyCredential();
			publicKeyCredential.setFormat(PublicKeyFormat.valueOf(KEYFORMAT));
			publicKeyCredential.setKey(KEYVAL);
			DeviceCredential credential = new DeviceCredential();
			credential.setPublicKey(publicKeyCredential);
			listCredentials.add(credential);
			device.toBuilder().setCredentials(listCredentials);
		}
		UpdateDeviceRequest request = UpdateDeviceRequest.Builder.newBuilder().setName(DEVICE).setDevice(device)
				.setUpdateMask(UPDATEMASK).build();
		Device response = deviceManagerAsyncClient.updateDevice(request);
		if(response != null) {
			System.out.println("UpdateDevice execution successful");
		}else {
			System.out.println("UpdateDevice execution failed");
		}

	}
}
