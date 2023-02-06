package com.clearblade.cloud.iot.v1.samples.updatedevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.Device.Builder;
import com.clearblade.cloud.iot.v1.exception.ApplicationException;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceCredential;
import com.clearblade.cloud.iot.v1.registrytypes.PublicKeyCredential;
import com.clearblade.cloud.iot.v1.registrytypes.PublicKeyFormat;
import com.clearblade.cloud.iot.v1.updatedevice.UpdateDeviceRequest;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;
import com.clearblade.cloud.iot.v1.utils.LogLevel;

public class AsyncUpdateDevice {
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String DEVICE = "";
	public static String UPDATEMASK = "";
	public static String ARG = "";
	public static String[] NEWARGS = null;
	public static String KEYFORMAT = PublicKeyFormat.RSA_PEM.name();
	public static String KEYVAL = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();

	public static void main(String[] args) {
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		UPDATEMASK = System.getProperty("updateMask");
		if (REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		}
		if (LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		if (!(System.getProperty("arg") == null || System.getProperty("arg").isBlank()
				|| System.getProperty("arg").isEmpty()))
			ARG = System.getProperty("arg");
		if (System.getProperty("newArgs") != null)
			NEWARGS = System.getProperty("newArgs").split(",");
		if (System.getProperty("keyFormat") != null)
			KEYFORMAT = System.getProperty("keyFormat");
		if (System.getProperty("keyVal") != null)
			KEYVAL = System.getProperty("keyVal");
		asyncUpdateDevice();
	}

	public static void asyncUpdateDevice() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		Builder device = Device.newBuilder().setId(DEVICE).setName(DEVICE);
		if (UPDATEMASK.equals("logLevel")) {
			device.setLogLevel(LogLevel.valueOf(ARG));
		} else if (UPDATEMASK.equals("blocked")) {
			device.setBlocked(Boolean.valueOf(ARG));
		} else if (UPDATEMASK.equals("metadata")) {
			Map<String, String> metadata = new HashMap<>();
			for (int i = 0; i < NEWARGS.length; i++) {
				String key = NEWARGS[i];
				String val = NEWARGS[i + 1];
				metadata.put(key, val);
			}
		} else if (UPDATEMASK.equals("credentials")) {
			List<DeviceCredential> listCredentials = new ArrayList<>();
			PublicKeyCredential publicKeyCredential = new PublicKeyCredential();
			publicKeyCredential.setFormat(PublicKeyFormat.valueOf(KEYFORMAT));
			publicKeyCredential.setKey(KEYVAL);
			DeviceCredential credential = new DeviceCredential();
			credential.setPublicKey(publicKeyCredential);
			listCredentials.add(credential);
			device.setCredentials(listCredentials);
		}
		UpdateDeviceRequest request = UpdateDeviceRequest.Builder.newBuilder().setName(DEVICE).setDevice(device.build())
				.setUpdateMask(UPDATEMASK).build();
		Device response = null;
		try {
			response = deviceManagerAsyncClient.updateDevice(request);
			System.out.println("Response: " + response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Ex: " + e.getMessage());
		}
	}
}
