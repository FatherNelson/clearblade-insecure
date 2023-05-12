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

package com.clearblade.cloud.iot.v1.utils;

import java.util.logging.Logger;

public class ConfigParameters {
    static Logger log = Logger.getLogger(ConfigParameters.class.getName());

    private static ConfigParameters single_instance = null;

    private String project;
    private String region;
    private String registry;
    private String getSystemCredentialsExtension;
    private String webhook;
    private String cloudiotURLExtension;
    private String devicesURLExtension;
    private String devicesStatesURLExtension;
    private String cloudiotdevicesURLExtension;
    private String cloudiotConfigURLExtension;
    private String endpointPort;

    public static ConfigParameters getInstance() {
        if (single_instance == null)
            single_instance = new ConfigParameters();

        return single_instance;
    }

    public ConfigParameters() {
        setValues();
    }

    /**
     * Method used to set values in Constants setter method
     */
    public void setValues() {
        this.setWebhook(Constants.WEBHOOK);
        this.setCloudiotURLExtension(Constants.CLOUDIOT_URL_EXTENSION);
        this.setDevicesURLExtension(Constants.DEVICES_URL_EXTENSION);
        this.setCloudiotdevicesURLExtension(Constants.CLOUDIOT_DEVICES_URL_EXTENSION);
        this.setDevicesStatesURLExtension(Constants.DEVICES_STATES_URL_EXTENSION);
        this.setCloudiotConfigURLExtension(Constants.CLOUDIOT_DEVICE_CONFIG_URL_EXTENSION);
        this.setGetSystemCredentialsExtension(Constants.GET_SYSTEM_CREDENTIALS_EXTENSION);
        this.setEndpointPort(Constants.ENDPOINTPORT);
    }

    public String getCloudiotURLExtension() {
        return cloudiotURLExtension;
    }

    public void setCloudiotURLExtension(String cloudiotURLExtension) {
        this.cloudiotURLExtension = cloudiotURLExtension;
    }

    public String getDevicesURLExtension() {
        return devicesURLExtension;
    }

    public void setDevicesURLExtension(String devicesURLExtension) {
        this.devicesURLExtension = devicesURLExtension;
    }

    public String getDevicesStatesURLExtension() {
        return devicesStatesURLExtension;
    }

    public void setDevicesStatesURLExtension(String devicesStatesURLExtension) {
        this.devicesStatesURLExtension = devicesStatesURLExtension;
    }

    public String getCloudiotdevicesURLExtension() {
        return cloudiotdevicesURLExtension;
    }

    public void setCloudiotdevicesURLExtension(String cloudiotdevicesURLExtension) {
        this.cloudiotdevicesURLExtension = cloudiotdevicesURLExtension;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public String getGetSystemCredentialsExtension() {
        return getSystemCredentialsExtension;
    }

    public void setGetSystemCredentialsExtension(String getSystemCredentialsExtension) {
        this.getSystemCredentialsExtension = getSystemCredentialsExtension;
    }

    public String getEndpointPort() {
        return endpointPort;
    }

    public void setEndpointPort(String endpointPort) {
        this.endpointPort = endpointPort;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getCloudiotConfigURLExtension() {
        return cloudiotConfigURLExtension;
    }

    public void setCloudiotConfigURLExtension(String cloudiotConfigURLExtension) {
        this.cloudiotConfigURLExtension = cloudiotConfigURLExtension;
    }

}
