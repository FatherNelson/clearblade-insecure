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

package com.clearblade.cloud.iot.v1.updatedeviceregistry;

import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import org.json.simple.JSONObject;

import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;

public class UpdateDeviceRegistryRequest {

    private String name;
    private String updateMask;
    private DeviceRegistry deviceRegistry;
    JSONObject requestParams;
    JSONObject bodyParams;

    private UpdateDeviceRegistryRequest(Builder builder) {
        this.name = builder.name;
        this.updateMask = builder.updateMask;
        this.deviceRegistry = builder.deviceRegistry;
    }

    public JSONObject getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(JSONObject requestParams) {
        this.requestParams = requestParams;
    }

    public JSONObject getBodyParams() {
        return bodyParams;
    }

    public void setBodyParams(JSONObject bodyParams) {
        this.bodyParams = bodyParams;
    }

    // Static class Builder
    public static class Builder {

        /// instance fields
        private String name;
        private String updateMask;
        private DeviceRegistry deviceRegistry;

        public static Builder newBuilder() {
            return new Builder();
        }

        private Builder() {
        }

        // Setter methods
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDeviceRegistry(DeviceRegistry deviceRegistry) {
            this.deviceRegistry = deviceRegistry;
            return this;
        }

        public Builder setUpdateMask(String updateMask) {
            this.updateMask = updateMask;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public UpdateDeviceRegistryRequest build() {
            return new UpdateDeviceRegistryRequest(this);
        }
    }

    public RegistryName getParent() {
        return RegistryName.parse(this.name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        requestParams.put("name", this.name);
        requestParams.put("updateMask", this.updateMask);

        bodyParams.put("id", this.deviceRegistry.toBuilder().getId());
        bodyParams.put("name", this.deviceRegistry.toBuilder().getName());
        bodyParams.put("logLevel", this.deviceRegistry.toBuilder().getLogLevel());

        return "name=" + this.name + ",updateMask=" + this.updateMask + ", logLevel= "
                + this.deviceRegistry.toBuilder().getLogLevel();
    }

    public String[] getBodyAndParams() {
        String[] output = new String[2];

        String params = "name=" + this.name + "&updateMask=" + this.updateMask;

        output[0] = params;
        output[1] = this.deviceRegistry.createDeviceJSONObject("");
        return output;
    }

}
