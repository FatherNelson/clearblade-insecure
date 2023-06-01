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

package com.clearblade.cloud.iot.v1.updatedevice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceCredential;

public class UpdateDeviceRequest {
    private final String name;
    private final Device device;
    private final String updateMask;
    JSONObject requestParams;
    JSONObject bodyParams;

    private UpdateDeviceRequest(Builder builder) {
        this.name = builder.name;
        this.updateMask = builder.updateMask;
        this.device = builder.device;
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
        private Device device;
        private String updateMask;

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

        public Builder setDevice(Device device) {
            this.device = device;
            return this;
        }

        public Builder setUpdateMask(String updateMask) {
            this.updateMask = updateMask;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public UpdateDeviceRequest build() {
            return new UpdateDeviceRequest(this);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        requestParams.put("name", this.name);
        requestParams.put("updateMask", this.updateMask);

        bodyParams.put("id", this.device.toBuilder().getId());
        bodyParams.put("name", this.device.toBuilder().getName());
        bodyParams.put("logLevel", this.device.toBuilder().getLogLevel());
        bodyParams.put("blocked", this.device.toBuilder().isBlocked());

        return "name=" + this.name + ",updateMask=" + this.updateMask + ", logLevel= "
                + this.device.toBuilder().getLogLevel();
    }

    public DeviceName getDeviceName() {
        return DeviceName.parse(this.name);
    }
  
    @SuppressWarnings("unchecked")
    public String[] getBodyAndParams() {
        String[] output = new String[2];

        String params = "name=" + this.name + "&updateMask=" + this.updateMask;
        bodyParams = new JSONObject();

        bodyParams.put("id", this.device.toBuilder().getId());
        bodyParams.put("name", this.device.toBuilder().getName());
        if (this.device.toBuilder().getLogLevel() != null) {
            bodyParams.put("logLevel", this.device.toBuilder().getLogLevel().toString());
        }
        bodyParams.put("blocked", this.device.toBuilder().isBlocked());
        if (this.device.toBuilder().getCredentials() != null && this.device.toBuilder().getCredentials().size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < this.device.toBuilder().getCredentials().size(); i++) {
                if (!this.device.toBuilder().getCredentials().get(i).isEmpty()) {
                    DeviceCredential credentialObj = this.device.toBuilder().getCredentials().get(i);
                    jsonArray.add(credentialObj.toJSONObject());
                }
            }
            bodyParams.put("credentials", jsonArray);
        }
        if (this.device.toBuilder().getMetadata() != null && this.device.toBuilder().getMetadata().size() > 0) {
            Set metaSet = this.device.toBuilder().getMetadata().keySet();
            Iterator itr = metaSet.iterator();
            JSONObject jsonObject = new JSONObject();
            while (itr.hasNext()) {
                String key = (String) itr.next();
                String value = this.device.toBuilder().getMetadata().get(key).toString();
                jsonObject.put(key, value);
            }
            bodyParams.put("metadata", jsonObject);
        }

        output[0] = params;
        output[1] = bodyParams.toString();
        return output;
    }

}
