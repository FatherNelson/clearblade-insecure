package com.clearblade.cloud.iot.v1.samples.createdevice;

import java.awt.Taskbar.State;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.ResponseCallback;
import com.clearblade.cloud.iot.v1.createdevice.CreateDeviceRequest;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceCredential;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceState;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayAuthMethod;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayConfig;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayType;
import com.clearblade.cloud.iot.v1.devicetypes.Status;
import com.clearblade.cloud.iot.v1.registrytypes.PublicKeyCredential;
import com.clearblade.cloud.iot.v1.registrytypes.PublicKeyFormat;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;
import com.clearblade.cloud.iot.v1.utils.LogLevel;

public class AsyncCreateDevice {
	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String DEVICE = "";
	public static String NUMID = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();

	public static void main(String[] args) {
//		PROJECT = System.getProperty("projectName");
//		LOCATION = System.getProperty("location");
//		REGISTRY = System.getProperty("registryName");
//		DEVICE = System.getProperty("deviceName");
//		NUMID = System.getProperty("numId");
		PROJECT = "api-project-320446546234";
		LOCATION = "asia-east1";
		REGISTRY = "test-asia-east1";
		DEVICE = "Java_SDK_Updated";
		NUMID = null;
		if (REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		}
		if (LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		asyncCreateDevice();
	}

	public static void asyncCreateDevice() {
		String KEYVAL = "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5P0Z4OUD5PSjri8xexGo\n6eQ39NGyQbXamIgWAwvnAs/oDRVqEejE2nwDhnpykaCGLkuDEN0LPd2wF+vC2Cq3\nY3YvkJh71IkjuAjMZQ+00CXdezfCjmTtEpMCNA3cV+G1g6uIcdEpHKs0YHfC9CFQ\nrjkc7tl3idmcQLngIov/gsFY7D1pbOgkCVVcZCRLgsdFfhCUYwYCvdEVJP3w+5mG\nybvmhNRbbFG7eG3+hmZoOg0h3f6r2fqgSx6l0+Z3D77SRT6lBEHvGDlxb08ASeuE\n0SJAc6PdAKd3FDqdZok4z1qJsgMqtU/ZGJJG54pNECWmhoOar+aQmmqnZ6kGQ5cn\nEwIDAQAB\n-----END PUBLIC KEY-----\n";

		PublicKeyCredential publicKeyCredential = new PublicKeyCredential();
	    publicKeyCredential.setKey(KEYVAL);
	    publicKeyCredential.setFormat(PublicKeyFormat.ES256_PEM);

	    DeviceCredential devCredential = new DeviceCredential();
	    devCredential.setPublicKey(publicKeyCredential);   
		
		
		
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		
		RegistryName parent = RegistryName.of(PROJECT, LOCATION, REGISTRY);
		GatewayConfig gatewayCfg = new GatewayConfig();
		gatewayCfg.setGatewayAuthMethod(GatewayAuthMethod.GATEWAY_AUTH_METHOD_UNSPECIFIED);
		gatewayCfg.setGatewayType(GatewayType.NON_GATEWAY);

		Status status = new Status();
		status.setCode(200);
		status.setMessage("Testing status code");
		
		DeviceConfig deviceConfig = new DeviceConfig();
		deviceConfig.setVersion("3");
		deviceConfig.setBinaryData("U3RhdGVPZkdyYWNl");
		
		HashMap<String, String> metadata = new HashMap<>();
		metadata.put("test", "value");
		
		DeviceState deviceState = new DeviceState();
		deviceState.setBinaryData("VGVzdGluZy4h");
		deviceState.setUpdateTime(String.valueOf(System.currentTimeMillis()));
		
		Device device = Device.newBuilder()
				.setId(DEVICE)
				.setName(DEVICE)
				.setNumId(NUMID)
				.setBlocked(false)
				.setGatewayConfig(gatewayCfg)
				.setLogLevel(LogLevel.ERROR)
				.setCredentials(List.of(devCredential))
				.setLastErrorStatus(status)
				.setConfig(deviceConfig)
				.setState(deviceState)
				.setLastConfigAckTime(String.valueOf(System.currentTimeMillis()))
				.setLastConfigSendTime(String.valueOf(System.currentTimeMillis()))
				.setLastErrorTime(String.valueOf(System.currentTimeMillis()))
				.setMetadata(metadata)				
				.build();

		CreateDeviceRequest request = CreateDeviceRequest.Builder.newBuilder().setParent(parent).setDevice(device)
				.build();
		deviceManagerAsyncClient.createDevice(request, new ResponseCallback() {
			
			@Override
			public void onSuccess(String message) {
				// TODO Auto-generated method stub
				System.out.println("onSuccess: " + message);
			}
			
			@Override
			public void onFail(String message) {
				// TODO Auto-generated method stub
				System.out.println("onFail: " + message);
			}
		});
	}
}
