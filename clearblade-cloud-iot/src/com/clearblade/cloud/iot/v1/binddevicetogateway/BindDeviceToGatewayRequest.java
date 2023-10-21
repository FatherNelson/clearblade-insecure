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

package com.clearblade.cloud.iot.v1.binddevicetogateway;

import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import org.json.simple.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BindDeviceToGatewayRequest {
    private final String parent;
    private final String gateway;
    private final String device;
    JSONObject requestParams;
    JSONObject bodyParams;

    private BindDeviceToGatewayRequest(Builder builder) {
        this.parent = builder.parent;
        this.gateway = builder.gateway;
        this.device = builder.device;
    }

    // Static class Builder
    public static class Builder {

        /// instance fields
        private String parent;
        private String gateway;
        private String device;

        public static Builder newBuilder() {
            return new Builder();
        }

        private Builder() {
        }

        // Setter methods
        public Builder setParent(String parent) {
            this.parent = parent;
            return this;
        }

        public Builder setGateway(String gateway) {
            this.gateway = gateway;
            return this;
        }

        public Builder setDevice(String device) {
            this.device = device;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public BindDeviceToGatewayRequest build() {
            return new BindDeviceToGatewayRequest(this);
        }
    }

    public void setRequestParams(JSONObject requestParams) {
        this.requestParams = requestParams;
    }

    public void setBodyParams(JSONObject bodyParams) {
        this.bodyParams = bodyParams;
    }

    public RegistryName getParent() {
        return RegistryName.parse(this.parent);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        requestParams = new JSONObject();
        bodyParams = new JSONObject();

        String method = "bindDeviceToGateway";
        requestParams.put("method", method);
        requestParams.put("parent", this.parent);

        bodyParams.put("gatewayId", this.gateway);
        bodyParams.put("deviceId", this.device);

        this.setRequestParams(requestParams);
        this.setBodyParams(bodyParams);

        return "parent=" + this.parent + ",gateway=" + this.gateway + ", device= " + this.device;
    }

    @SuppressWarnings("unchecked")
    public String[] getBodyAndParams() {
        String[] output = new String[2];
        String params = "parent=" + URLEncoder.encode(this.parent, StandardCharsets.UTF_8) + "&method=" + "bindDeviceToGateway";
        bodyParams = new JSONObject();
        bodyParams.put("gatewayId", this.gateway);
        bodyParams.put("deviceId", this.device);

        output[0] = params;
        output[1] = bodyParams.toJSONString();
        return output;
    }

}
