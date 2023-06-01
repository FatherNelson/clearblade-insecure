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

import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayResponse;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayConfig;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayType;
import com.clearblade.cloud.iot.v1.devicetypes.Status;
import com.clearblade.cloud.iot.v1.exception.ApplicationException;
import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.registrytypes.HttpConfig;
import com.clearblade.cloud.iot.v1.registrytypes.HttpState;
import com.clearblade.cloud.iot.v1.registrytypes.MqttConfig;
import com.clearblade.cloud.iot.v1.registrytypes.MqttState;
import com.clearblade.cloud.iot.v1.registrytypes.StateNotificationConfig;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceResponse;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayResponse;
import com.clearblade.cloud.iot.v1.utils.LogLevel;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import static org.junit.matchers.JUnitMatchers.containsString;

public class ExpectedResponseTest {

    public boolean assertEquals(DeviceRegistry obj1, DeviceRegistry obj2) {
        boolean flag = true;
        if (!obj1.toBuilder().getId().equals(obj2.toBuilder().getId())
                || obj1.toBuilder().getHttpConfig().equals(obj2.toBuilder().getHttpConfig())
                || obj1.toBuilder().getMqttConfig().equals(obj2.toBuilder().getMqttConfig())
                || obj1.toBuilder().getLogLevel().equals(obj2.toBuilder().getLogLevel())
                || obj1.toBuilder().getStateNotificationConfig().equals(obj2.toBuilder().getStateNotificationConfig()))
            flag = false;
        return flag;
    }

    public boolean assertNotEquals(DeviceRegistry obj1, DeviceRegistry obj2) {
        boolean flag = true;
        if (obj2 == null)
            return flag;
        else if (obj1.toBuilder().getId().equals(obj2.toBuilder().getId())
                || obj1.toBuilder().getHttpConfig().equals(obj2.toBuilder().getHttpConfig())
                || obj1.toBuilder().getMqttConfig().equals(obj2.toBuilder().getMqttConfig())
                || obj1.toBuilder().getLogLevel().equals(obj2.toBuilder().getLogLevel())
                || obj1.toBuilder().getStateNotificationConfig().equals(obj2.toBuilder().getStateNotificationConfig()))
            flag = false;
        return flag;
    }

    public boolean assertEqual(Device obj1, Device obj2) {
        boolean flag = true;
        if (obj2 == null)
            return flag;
        else if (!obj1.toBuilder().getId().equals(obj2.toBuilder().getId())
                || obj1.toBuilder().getLogLevel().equals(obj2.toBuilder().getLogLevel())
                || obj1.toBuilder().getCredentials().equals(obj2.toBuilder().getCredentials()))
            flag = false;
        return flag;
    }

    public void assertException(Throwable e) {
        Assertions.assertInstanceOf(ApplicationException.class, e);
    }

    public void assertContains(String actual, String expected) {
        Assert.assertThat(actual, containsString(expected));
    }

    public void assertEqualsNull(Object obj) {
        Assertions.assertNull(obj);
    }

    public boolean assertNotEqual(Device obj1, Device obj2) {
        boolean flag = true;
        if (obj2 == null)
            return flag;
        else if (obj1.toBuilder().getId().equals(obj2.toBuilder().getId())
                || obj1.toBuilder().getName().equals(obj2.toBuilder().getName())
                || obj1.toBuilder().getNumId().equals(obj2.toBuilder().getNumId())
                || obj1.toBuilder().getGatewayConfig().equals(obj2.toBuilder().getGatewayConfig())
                || obj1.toBuilder().getLogLevel().equals(obj2.toBuilder().getLogLevel())
                || obj1.toBuilder().getCredentials().equals(obj2.toBuilder().getCredentials()))
            flag = false;
        return flag;
    }

    public boolean assertEqualResponse(BindDeviceToGatewayResponse obj1, BindDeviceToGatewayResponse obj2) {
        boolean flag = true;
        if (obj1.getHttpStatusCode() != obj2.getHttpStatusCode() || !(obj1.getHttpStatusResponse().equals(obj2.getHttpStatusResponse())))
            flag = false;
        return flag;
    }

    public boolean assertEqualsResponse(UnbindDeviceFromGatewayResponse obj1, UnbindDeviceFromGatewayResponse obj2) {
        boolean flag = true;
        if (obj1.getHttpStatusCode() != obj2.getHttpStatusCode() || !(obj1.getHttpStatusResponse().equals(obj2.getHttpStatusResponse())))
            flag = false;
        return flag;
    }

    public boolean assertEqualsResponses(SendCommandToDeviceResponse obj1, SendCommandToDeviceResponse obj2) {
        boolean flag = true;
        if (obj1.getHttpStatusCode() != obj2.getHttpStatusCode() || !(obj1.getHttpStatusResponse().equals(obj2.getHttpStatusResponse())))
            flag = false;
        return flag;
    }

    public DeviceRegistry getResponseTest1(String registryId) {

        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setHttpEnabledState(HttpState.HTTP_ENABLED);

        MqttConfig mqttConfig = new MqttConfig();
        mqttConfig.setMqttEnabledState(MqttState.MQTT_ENABLED);

        StateNotificationConfig stateConfig = new StateNotificationConfig();
        stateConfig.setPubsubTopicName("");

        return DeviceRegistry.newBuilder()
                .setId(registryId)
                .setHttpConfig(httpConfig)
                .setMqttConfig(mqttConfig)
                .setLogLevel(LogLevel.DEBUG)
                .setStateNotificationConfig(stateConfig)
                .build();
    }

    public DeviceRegistry getResponseTest2(String registryId) {

        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setHttpEnabledState(HttpState.HTTP_ENABLED);

        MqttConfig mqttConfig = new MqttConfig();
        mqttConfig.setMqttEnabledState(MqttState.MQTT_ENABLED);

        StateNotificationConfig stateConfig = new StateNotificationConfig();
        stateConfig.setPubsubTopicName("");

        return DeviceRegistry.newBuilder()
                .setId(registryId)
                .setCredentials(new ArrayList<>())
                .setHttpConfig(httpConfig)
                .setMqttConfig(mqttConfig)
                .setLogLevel(LogLevel.DEBUG)
                .setEventNotificationConfigs(new ArrayList<>())
                .setStateNotificationConfig(stateConfig)
                .build();
    }


    public DeviceRegistry getResponseTest3(String registryId) {

        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setHttpEnabledState(HttpState.HTTP_ENABLED);

        MqttConfig mqttConfig = new MqttConfig();
        mqttConfig.setMqttEnabledState(MqttState.MQTT_ENABLED);

        StateNotificationConfig stateConfig = new StateNotificationConfig();
        stateConfig.setPubsubTopicName("");

        return DeviceRegistry.newBuilder()
                .setId(registryId)
                .setHttpConfig(httpConfig)
                .setMqttConfig(mqttConfig)
                .setLogLevel(LogLevel.DEBUG)
                .setStateNotificationConfig(stateConfig)
                .build();
    }

    public DeviceRegistry getResponseTest4(String registryId) {

        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setHttpEnabledState(HttpState.HTTP_ENABLED);

        MqttConfig mqttConfig = new MqttConfig();
        mqttConfig.setMqttEnabledState(MqttState.MQTT_ENABLED);

        StateNotificationConfig stateConfig = new StateNotificationConfig();
        stateConfig.setPubsubTopicName("");

        return DeviceRegistry.newBuilder()
                .setId(registryId)
                .setHttpConfig(httpConfig)
                .setMqttConfig(mqttConfig)
                .setLogLevel(LogLevel.DEBUG)
                .setStateNotificationConfig(stateConfig)
                .build();
    }


    public DeviceRegistry getResponseTest5(String registryId) {

        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setHttpEnabledState(HttpState.HTTP_ENABLED);

        MqttConfig mqttConfig = new MqttConfig();
        mqttConfig.setMqttEnabledState(MqttState.MQTT_ENABLED);

        StateNotificationConfig stateConfig = new StateNotificationConfig();
        stateConfig.setPubsubTopicName("");

        return DeviceRegistry.newBuilder()
                .setId(registryId)
                .setHttpConfig(httpConfig)
                .setMqttConfig(mqttConfig)
                .setLogLevel(LogLevel.ERROR)
                .setStateNotificationConfig(stateConfig)
                .build();
    }


    public Device getResponseTest6(String deviceId, String numId) {

        GatewayConfig gatewayConfig = new GatewayConfig();
        gatewayConfig.setGatewayType(GatewayType.NON_GATEWAY);

        return Device.newBuilder()
                .setId(deviceId)
                .setName(deviceId)
                .setNumId(numId)
                .setBlocked(false)
                .setGatewayConfig(gatewayConfig)
                .setLogLevel(LogLevel.DEBUG)
                .setCredentials(new ArrayList<>())
                .setLastErrorStatus(new Status())
                .setConfig(new DeviceConfig())
                .setMetadata(new HashMap<>())
                .build();

    }

    public Device getResponseTest7(String deviceId, String numId) {

        GatewayConfig gatewayConfig = new GatewayConfig();
        gatewayConfig.setGatewayType(GatewayType.NON_GATEWAY);

        return Device.newBuilder()
                .setId(deviceId)
                .setName(deviceId)
                .setNumId(numId)
                .setBlocked(false)
                .setGatewayConfig(gatewayConfig)
                .setLogLevel(LogLevel.DEBUG)
                .setCredentials(new ArrayList<>())
                .setLastErrorStatus(new Status())
                .setConfig(new DeviceConfig())
                .setMetadata(new HashMap<>())
                .build();

    }

    public Device getResponseTest8(String deviceId, String numId) {

        GatewayConfig gatewayConfig = new GatewayConfig();
        gatewayConfig.setGatewayType(GatewayType.GATEWAY);

        return Device.newBuilder()
                .setId(deviceId)
                .setName(deviceId)
                .setNumId(numId)
                .setBlocked(false)
                .setGatewayConfig(gatewayConfig)
                .setLogLevel(LogLevel.DEBUG)
                .setCredentials(new ArrayList<>())
                .setLastErrorStatus(new Status())
                .setConfig(new DeviceConfig())
                .setMetadata(new HashMap<>())
                .build();

    }

    public Device getResponseTest9(String deviceId, String numId) {

        GatewayConfig gatewayConfig = new GatewayConfig();
        gatewayConfig.setGatewayType(GatewayType.NON_GATEWAY);

        return Device.newBuilder()
                .setId(deviceId)
                .setName(deviceId)
                .setNumId(numId)
                .setBlocked(false)
                .setGatewayConfig(gatewayConfig)
                .setLogLevel(LogLevel.DEBUG)
                .setCredentials(new ArrayList<>())
                .setLastErrorStatus(new Status())
                .setConfig(new DeviceConfig())
                .setMetadata(new HashMap<>())
                .build();

    }

    public Device getResponseTest10(String deviceId, String numId) {

        GatewayConfig gatewayConfig = new GatewayConfig();
        gatewayConfig.setGatewayType(GatewayType.NON_GATEWAY);

        return Device.newBuilder()
                .setId(deviceId)
                .setName(deviceId)
                .setNumId(numId)
                .setBlocked(false)
                .setGatewayConfig(gatewayConfig)
                .setLogLevel(LogLevel.DEBUG)
                .setCredentials(new ArrayList<>())
                .setLastErrorStatus(new Status())
                .setConfig(new DeviceConfig())
                .setMetadata(new HashMap<>())
                .build();

    }

    public Device getResponseTest11(String deviceId, String numId) {

        GatewayConfig gatewayConfig = new GatewayConfig();
        gatewayConfig.setGatewayType(GatewayType.GATEWAY);

        return Device.newBuilder()
                .setId(deviceId)
                .setName(deviceId)
                .setNumId(numId)
                .setBlocked(false)
                .setGatewayConfig(gatewayConfig)
                .setLogLevel(LogLevel.DEBUG)
                .setCredentials(new ArrayList<>())
                .setLastErrorStatus(new Status())
                .setConfig(new DeviceConfig())
                .setMetadata(new HashMap<>())
                .build();

    }

    public Device getResponseTest12(String deviceId) {
        GatewayConfig gatewayConfig = new GatewayConfig();
        gatewayConfig.setGatewayType(GatewayType.NON_GATEWAY);
        return Device.newBuilder()
                .setId(deviceId)
                .setName(deviceId)
                .setBlocked(false)
                .setLogLevel(LogLevel.ERROR)
                .build();
    }

    public BindDeviceToGatewayResponse getResponseTest13() {

        BindDeviceToGatewayResponse response = BindDeviceToGatewayResponse.Builder.newBuilder().build();
        response.setHttpStatusCode(200);
        response.setHttpStatusResponse("OK");
        return response;
    }

    public UnbindDeviceFromGatewayResponse getResponseTest14() {

        UnbindDeviceFromGatewayResponse response = UnbindDeviceFromGatewayResponse.Builder.newBuilder().build();
        response.setHttpStatusCode(200);
        response.setHttpStatusResponse("OK");
        return response;
    }

    public SendCommandToDeviceResponse getResponseTest15() {

        SendCommandToDeviceResponse response = SendCommandToDeviceResponse.Builder.newBuilder().build();
        response.setHttpStatusCode(200);
        response.setHttpStatusResponse("OK");
        return response;
    }
}
