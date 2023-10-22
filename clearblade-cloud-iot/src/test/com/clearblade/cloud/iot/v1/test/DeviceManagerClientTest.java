/*
 * Copyright 2023 ClearBlade Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.com.clearblade.cloud.iot.v1.test;


import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
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
    private final String project = System.getenv("PROJECT_ID");
    private final String location = System.getenv("REGION");
    private final String registryId = System.getenv("REGISTRY");
    private final String failedRegistryId = System.getenv("FAILED_REGISTRY");
    private final String deviceId = System.getenv("DEVICE");
    private final String gatewayId = System.getenv("GATEWAY_ID");
    private final String numDeviceId = System.getenv("NUM_DEVICE_ID");
    private final String numGatewayId = System.getenv("NUM_GATEWAY_ID");

    @BeforeAll
    public static void setUp() {
        client = new DeviceManagerClient();
        testResponse = new ExpectedResponseTest();
    }

    @Test
    @Order(1)
    public void testCreateRegistrySuccess() {
        System.out.println("ENV VARIABLE : " + project);
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
        GetDeviceRegistryRequest request = GetDeviceRegistryRequest.Builder.newBuilder()
                .setName(RegistryName.of(project, location, failedRegistryId).getRegistryFullName())
                .build();
        DeviceRegistry actualResponse = null;
        try {
            actualResponse = client.getDeviceRegistry(request);
        } catch (Exception e) {
            testResponse.assertException(e);
            testResponse.assertContains(e.getMessage(), "No systems found matching that combination of project, region and registry:");
        }
        testResponse.assertEqualsNull(actualResponse);
    }

    @Test
    @Order(4)
    public void testUpdateDeviceRegistrySuccess() {
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
        RegistryName parent = RegistryName.of(project, location, registryId);
        Device expectedResponse = testResponse.getResponseTest7(deviceId, numDeviceId);
        GatewayConfig gatewayCfg = GatewayConfig.newBuilder().setGatewayType(GatewayType.NON_GATEWAY).build();
        Device device = Device.newBuilder()
                .setId(deviceId)
                .setName(deviceId)
                .setNumId(numDeviceId)
                .setBlocked(false)
                .setGatewayConfig(gatewayCfg)
                .setLogLevel(LogLevel.DEBUG)
                .setCredentials(new ArrayList<>())
                .setLastErrorStatus(new Status())
                .setConfig(new DeviceConfig())
                .setMetadata(new HashMap<>())
                .build();
        CreateDeviceRequest request = CreateDeviceRequest.Builder.newBuilder().setParent(parent.toString()).setDevice(device)
                .build();
        Device actualResponse = client.createDevice(request);
        testResponse.assertEqual(expectedResponse, actualResponse);

    }

    @Test
    @Order(6)
    public void testCreateDeviceAsAGatewaySuccess() {
        RegistryName parent = RegistryName.of(project, location, registryId);
        Device expectedResponse = testResponse.getResponseTest8(gatewayId, numGatewayId);
        GatewayConfig gatewayCfg = GatewayConfig.newBuilder().setGatewayType(GatewayType.GATEWAY).build();
        Device device = Device.newBuilder()
                .setId(gatewayId)
                .setName(gatewayId)
                .setNumId(numGatewayId)
                .setBlocked(false)
                .setGatewayConfig(gatewayCfg)
                .setLogLevel(LogLevel.DEBUG)
                .setCredentials(new ArrayList<>())
                .setLastErrorStatus(new Status())
                .setConfig(new DeviceConfig())
                .setMetadata(new HashMap<>())
                .build();
        CreateDeviceRequest request = CreateDeviceRequest.Builder.newBuilder().setParent(parent.toString()).setDevice(device)
                .build();
        Device actualResponse = client.createDevice(request);
        testResponse.assertEqual(expectedResponse, actualResponse);

    }

    @Test
    @Order(7)
    public void testGetDeviceFail() {
        DeviceName name = DeviceName.of(project, location, registryId, "test_device02");
        GetDeviceRequest request = GetDeviceRequest.Builder.newBuilder().setName(name)
                .setFieldMask(FieldMask.newBuilder().build()).build();
        Device actualResponse = null;
        try {
            actualResponse = client.getDevice(request);
        } catch (Exception e) {
            testResponse.assertException(e);
            testResponse.assertContains(e.getMessage(), "doesn't exist");
        }
        testResponse.assertEqualsNull(actualResponse);
    }

    @Test
    @Order(8)
    public void testGetDeviceSuccess() {
        DeviceName name = DeviceName.of(project, location, registryId, deviceId);
        Device expectedResponse = testResponse.getResponseTest10(deviceId, numDeviceId);
        GetDeviceRequest request = GetDeviceRequest.Builder.newBuilder().setName(name)
                .setFieldMask(FieldMask.newBuilder().build()).build();
        Device actualResponse = client.getDevice(request);
        testResponse.assertEqual(expectedResponse, actualResponse);
    }

    @Test
    @Order(9)
    public void testGetDeviceAsAGatewaySuccess() {
        DeviceName name = DeviceName.of(project, location, registryId, gatewayId);
        Device expectedResponse = testResponse.getResponseTest11(gatewayId, numGatewayId);
        GetDeviceRequest request = GetDeviceRequest.Builder.newBuilder().setName(name)
                .setFieldMask(FieldMask.newBuilder().build()).build();
        Device actualResponse = client.getDevice(request);
        testResponse.assertEqual(expectedResponse, actualResponse);
    }

    @Test
    @Order(10)
    public void testUpdateDevice() {
        Device expectedResponse = testResponse.getResponseTest12(deviceId);
        Device device = Device.patch(deviceId, deviceId, LogLevel.ERROR, true);
        DeviceName deviceName = DeviceName.of(project, location, registryId, deviceId);
        String updateMask = "logLevel";
        UpdateDeviceRequest request = UpdateDeviceRequest.Builder.newBuilder().setName(deviceName.toString()).setDevice(device)
                .setUpdateMask(updateMask).build();
        Device actualResponse = client.updateDevice(request);
        testResponse.assertEqual(expectedResponse, actualResponse);

    }

    @Test
    @Order(11)
    public void testBindDeviceToGateway() {
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
        String byteData = "c2VuZEZ1bm55TWVzc2FnZVRvRGV2aWNl";
        SendCommandToDeviceRequest request = SendCommandToDeviceRequest.Builder.newBuilder()
                .setName(DeviceName
                        .of(project, location, registryId, deviceId)
                        .toString())
                .setBinaryData(new ByteString(byteData)).build();
        SendCommandToDeviceResponse actualResponse = null;
        try {
            actualResponse = client.sendCommandToDevice(request);
        } catch (Exception e) {
            testResponse.assertException(e);
            testResponse.assertContains(e.getMessage(), " is not connected.");
        }
        testResponse.assertEqualsNull(actualResponse);
    }

    @Test
    @Order(14)
    public void testDeleteDevice() {
        DeviceName deviceName = DeviceName.of(project, location, registryId, deviceId);
        DeleteDeviceRequest request = DeleteDeviceRequest.Builder.newBuilder().setName(deviceName).build();
        client.deleteDevice(request);
    }

    @Test
    @Order(15)
    public void testDeleteDeviceAsAGateway() {
        DeviceName deviceName = DeviceName.of(project, location, registryId, gatewayId);
        DeleteDeviceRequest request = DeleteDeviceRequest.Builder.newBuilder().setName(deviceName).build();
        client.deleteDevice(request);
    }

    @Test
    @Order(16)
    public void testDeleteDeviceRegistry() {
        DeleteDeviceRegistryRequest request = DeleteDeviceRegistryRequest.Builder.newBuilder()
                .setName(RegistryName.of(project, location, registryId)
                        .getRegistryFullName())
                .build();
        client.deleteDeviceRegistry(request);
    }

}
