package com.clearblade.cloud.iot.v1.test;


import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayRequest;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayResponse;
import com.clearblade.cloud.iot.v1.createdevice.CreateDeviceRequest;
import com.clearblade.cloud.iot.v1.createdeviceregistry.CreateDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.deletedevice.DeleteDeviceRequest;
import com.clearblade.cloud.iot.v1.deletedeviceregistry.DeleteDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.devicetypes.FieldMask;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayConfig;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayType;
import com.clearblade.cloud.iot.v1.devicetypes.Status;
import com.clearblade.cloud.iot.v1.getdevice.GetDeviceRequest;
import com.clearblade.cloud.iot.v1.getdeviceregistry.GetDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.registrytypes.LocationName;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceRequest;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceResponse;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayRequest;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayResponse;
import com.clearblade.cloud.iot.v1.updatedevice.UpdateDeviceRequest;
import com.clearblade.cloud.iot.v1.updatedeviceregistry.UpdateDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.utils.ByteString;
import com.clearblade.cloud.iot.v1.utils.LogLevel;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeviceManagerClientTest {
	
	private static DeviceManagerClient client;
	private static ExpectedResponseTest testResponse;
	private String project = "ingressdevelopmentenv";
	private String location = "us-central1";
	
	
	@BeforeAll
	public static void setUp() {
		client = new DeviceManagerClient();
		testResponse = new ExpectedResponseTest();
	}

	@Test
	@Order(1)
	public void testCreateRegistrySuccess() {
		String registryId = "testCreate_reg03";		
		DeviceRegistry expectedResponse = testResponse.getResponseTest2(registryId);
        CreateDeviceRegistryRequest request = CreateDeviceRegistryRequest.Builder.newBuilder()
                .setParent(LocationName.of(project, location).toString())
                .setDeviceRegistry(
                        DeviceRegistry.newBuilder().setId(registryId).setLogLevel(LogLevel.DEBUG).build())
                .build();
        DeviceRegistry actualResponse = client.createDeviceRegistry(request);
        testResponse.assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	@Order(2)
	public void testGetRegistrySuccess() {
		String registryId = "testCreate_reg03";		
		DeviceRegistry expectedResponse = testResponse.getResponseTest3(registryId);
		GetDeviceRegistryRequest request = GetDeviceRegistryRequest.Builder.newBuilder()
				.setName(RegistryName.of(project, location, registryId).getRegistryFullName())
				.build();
		DeviceRegistry actualResponse = client.getDeviceRegistry(request);
		testResponse.assertEquals(expectedResponse, actualResponse);
		
	}

	@Test
	@Order(3)
	public void testGetRegistryFail() {
		String registryId = "testCreate_reg01";
		DeviceRegistry expectedResponse = testResponse.getResponseTest4(registryId);
		GetDeviceRegistryRequest request = GetDeviceRegistryRequest.Builder.newBuilder()
				.setName(RegistryName.of(project, location, registryId).getRegistryFullName())
				.build();
		DeviceRegistry actualResponse = client.getDeviceRegistry(request);
		testResponse.assertNotEquals(expectedResponse, actualResponse);
		
	}

	@Test
	@Order(4)
	public void testUpdateDeviceRegistrySuccess() {
		String registryId = "Rashmi_Registry_Test";		
		RegistryName name = RegistryName.of(project, location, registryId);
		DeviceRegistry expectedResponse = testResponse.getResponseTest5(registryId);
		UpdateDeviceRegistryRequest request = UpdateDeviceRegistryRequest.Builder.newBuilder()
				.setDeviceRegistry(DeviceRegistry.newBuilder().setId(registryId)
						.setName(name.getRegistryFullName())
						.setLogLevel(LogLevel.ERROR)
						.build())
				.setName(name.getRegistryFullName()).setUpdateMask("logLevel").build();

		DeviceRegistry actualResponse = client.updateDeviceRegistry(request);
		testResponse.assertEquals(expectedResponse, actualResponse);	
	}
	
	@Test
	@Order(5)
	public void testCreateDeviceSuccess() {
		String registryId = "Rashmi_Registry_Test";
		String deviceId = "test_device01";
		String numId = "1211";
		RegistryName parent = RegistryName.of(project, location, registryId);
		Device expectedResponse = testResponse.getResponseTest7(deviceId,numId);
		GatewayConfig gatewayCfg = new GatewayConfig();
		gatewayCfg.setGatewayType(GatewayType.NON_GATEWAY);
		Device device = Device.newBuilder()
							  .setId(deviceId)
							  .setName(deviceId)
							  .setNumId(numId)
							  .setBlocked(false)
							  .setGatewayConfig(gatewayCfg)
							  .setLogLevel(LogLevel.DEBUG)
							  .setCredentials(new ArrayList<>())
							  .setLastErrorStatus(new Status())
							  .setConfig(new DeviceConfig())
							  .setMetadata(new HashMap<>())
							  .build();
		CreateDeviceRequest request = CreateDeviceRequest.Builder.newBuilder().setParent(parent).setDevice(device)
				.build();
		Device actualResponse = client.createDevice(request);
		testResponse.assertEqual(expectedResponse, actualResponse);	

	}

	@Test
	@Order(6)
	public void testCreateDeviceAsAGatewaySuccess() {
		String registryId = "Rashmi_Registry_Test";
		String deviceId = "test_device03";
		String numId = "1112";
		RegistryName parent = RegistryName.of(project, location, registryId);
		Device expectedResponse = testResponse.getResponseTest8(deviceId,numId);
		GatewayConfig gatewayCfg = new GatewayConfig();
		gatewayCfg.setGatewayType(GatewayType.GATEWAY);
		Device device = Device.newBuilder()
							  .setId(deviceId)
							  .setName(deviceId)
							  .setNumId(numId)
							  .setBlocked(false)
							  .setGatewayConfig(gatewayCfg)
							  .setLogLevel(LogLevel.DEBUG)
							  .setCredentials(new ArrayList<>())
							  .setLastErrorStatus(new Status())
							  .setConfig(new DeviceConfig())
							  .setMetadata(new HashMap<>())
							  .build();
		CreateDeviceRequest request = CreateDeviceRequest.Builder.newBuilder().setParent(parent).setDevice(device)
				.build();
		Device actualResponse = client.createDevice(request);
		testResponse.assertEqual(expectedResponse, actualResponse);	

	}
	
	@Test
	@Order(7)
	public void testGetDeviceFail() {
		String registryId = "Rashmi_Registry_Test";
		String deviceId = "test_device01";		
		String numId= "1211";
		DeviceName name = DeviceName.of(project, location, registryId, "test_device02");
		Device expectedResponse = testResponse.getResponseTest9(deviceId,numId);
		GetDeviceRequest request = GetDeviceRequest.Builder.newBuilder().setName(name)
				.setFieldMask(FieldMask.newBuilder().build()).build();
		Device actualResponse = client.getDevice(request);
		testResponse.assertNotEqual(expectedResponse, actualResponse);
	}

	@Test
	@Order(8)
	public void testGetDeviceSuccess() {
		String registryId = "Rashmi_Registry_Test";
		String deviceId = "test_device01";		
		String numId= "1211";
		DeviceName name = DeviceName.of(project, location, registryId, deviceId);
		Device expectedResponse = testResponse.getResponseTest10(deviceId,numId);
		GetDeviceRequest request = GetDeviceRequest.Builder.newBuilder().setName(name)
				.setFieldMask(FieldMask.newBuilder().build()).build();
		Device actualResponse = client.getDevice(request);
		testResponse.assertEqual(expectedResponse, actualResponse);
	}

	@Test
	@Order(9)
	public void testGetDeviceAsAGatewaySuccess() {
		String registryId = "Rashmi_Registry_Test";
		String deviceId = "test_device03";		
		String numId= "1112";
		DeviceName name = DeviceName.of(project, location, registryId, deviceId);
		Device expectedResponse = testResponse.getResponseTest11(deviceId,numId);
		GetDeviceRequest request = GetDeviceRequest.Builder.newBuilder().setName(name)
				.setFieldMask(FieldMask.newBuilder().build()).build();
		Device actualResponse = client.getDevice(request);
		testResponse.assertEqual(expectedResponse, actualResponse);
	}
	
	@Test
	@Order(10)
	@Disabled
	public void testUpdateDevice() {
		String deviceId = "myOldDevice";		
		Device expectedResponse = testResponse.getResponseTest12(deviceId);
		Device device = Device.patch(deviceId, deviceId, LogLevel.ERROR, true);
		String updateMask = "logLevel";
		UpdateDeviceRequest request = UpdateDeviceRequest.Builder.newBuilder().setName(deviceId).setDevice(device)
				.setUpdateMask(updateMask).build();
		Device actualResponse = client.updateDevice(request);
		testResponse.assertEqual(expectedResponse, actualResponse);

	}
	
	@Test
	@Order(11)
	public void testBindDeviceToGateway() {
		String registryId = "Rashmi_Registry_Test";
		String gatewayId = "Rashmi_Gateway_Test";
		String deviceId = "test_device01";

		BindDeviceToGatewayResponse expectedResponse = testResponse.getResponseTest13();
		BindDeviceToGatewayRequest request = BindDeviceToGatewayRequest.Builder.newBuilder()
				.setParent(RegistryName.of(project, location, registryId).toString())
				.setGateway(gatewayId).setDevice(deviceId).build();
		BindDeviceToGatewayResponse actualResponse = client.bindDeviceToGateway(request);
		testResponse.assertEqualResponse(expectedResponse, actualResponse);
		
	}

	@Test
	@Order(12)
	public void testUnbindDeviceFromGateway() {
		String registryId = "Rashmi_Registry_Test";
		String gatewayId = "Rashmi_Gateway_Test";
		String deviceId = "test_device01";

		UnbindDeviceFromGatewayResponse expectedResponse = testResponse.getResponseTest14();
		UnbindDeviceFromGatewayRequest request = UnbindDeviceFromGatewayRequest.Builder.newBuilder()
				.setParent(RegistryName.of(project, location, registryId).toString())
				.setGateway(gatewayId).setDevice(deviceId).build();
		UnbindDeviceFromGatewayResponse actualResponse = client.unbindDeviceFromGateway(request);
		testResponse.assertEqualsResponse(expectedResponse, actualResponse);
	}
	
	@Test
	@Order(13)
	public void testSendCommandToDevice() {
		String registryId = "Rashmi_Registry_Test";
		String deviceId = "test_device01";
		String byteData = "c2VuZEZ1bm55TWVzc2FnZVRvRGV2aWNl";

		SendCommandToDeviceResponse expectedResponse = testResponse.getResponseTest15();		
		SendCommandToDeviceRequest request = SendCommandToDeviceRequest.Builder.newBuilder()
				.setName(DeviceName
						.of(project, location, registryId, deviceId)
						.toString())
				.setBinaryData(new ByteString(byteData)).build();
		SendCommandToDeviceResponse actualResponse = client.sendCommandToDevice(request);
		testResponse.assertEqualsResponses(expectedResponse, actualResponse);
		
	}

	@Test
	@Order(14)
	public void testDeleteDevice() {
		String registryId = "Rashmi_Registry_Test";
		String deviceId = "test_device01";
		DeviceName deviceName = DeviceName.of(project, location, registryId, deviceId);
		DeleteDeviceRequest request = DeleteDeviceRequest.Builder.newBuilder().setName(deviceName).build();
		client.deleteDevice(request);
	}

	@Test
	@Order(15)
	public void testDeleteDeviceAsAGateway() {
		String registryId = "Rashmi_Registry_Test";
		String deviceId = "test_device03";
		DeviceName deviceName = DeviceName.of(project, location, registryId, deviceId);
		DeleteDeviceRequest request = DeleteDeviceRequest.Builder.newBuilder().setName(deviceName).build();
		client.deleteDevice(request);
	}
	
	@Test
	@Order(16)
	public void testDeleteDeviceRegistry() {
		String registryId = "testCreate_reg03";		
		DeleteDeviceRegistryRequest request = DeleteDeviceRegistryRequest.Builder.newBuilder()
				.setName(RegistryName.of(project, location, registryId)
						.getRegistryFullName())
				.build();
		client.deleteDeviceRegistry(request);
		
	}
	
}
