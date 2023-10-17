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

package com.clearblade.cloud.iot.v1.listdeviceconfigversions;

import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.exception.ApplicationException;
import com.clearblade.cloud.iot.v1.registrytypes.LocationName;
import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ListDeviceConfigVersionsRequest {
    private final String name;
    private final String numVersions;
    JSONObject requestParams;

    private ListDeviceConfigVersionsRequest(Builder builder) {
        this.name = builder.name;
        this.numVersions = builder.numVersions;
    }

    // Static class Builder
    public static class Builder {

        /// instance fields
        private String name;
        private String numVersions;

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

        public Builder setNumVersions(int numVersions) {
            this.numVersions = String.valueOf(numVersions);
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public ListDeviceConfigVersionsRequest build() {
            return new ListDeviceConfigVersionsRequest(this);
        }

    }

    public JSONObject getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(JSONObject requestParams) {
        this.requestParams = requestParams;
    }

    public DeviceName getName() {
        return DeviceName.parse(this.name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        requestParams = new JSONObject();
        requestParams.put("name", this.name);
        requestParams.put("numVersions", this.numVersions);
        this.setRequestParams(requestParams);
        return "name=" + this.name + ",numVersions=" + this.numVersions;
    }

    public String getParamsForList() {
        String params = "";
        try {
            params = "name=" + URLEncoder.encode(this.name,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ApplicationException(e);
        }
        params += "&numVersions=" + this.numVersions;

        return params;
    }
}
