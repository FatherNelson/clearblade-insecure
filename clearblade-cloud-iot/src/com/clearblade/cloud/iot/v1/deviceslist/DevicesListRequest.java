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

package com.clearblade.cloud.iot.v1.deviceslist;

import com.clearblade.cloud.iot.v1.devicetypes.GatewayListOptions;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class DevicesListRequest {

    private String parent;
    private String deviceNumIds;
    private String deviceIds;
    private String fieldMask;
    private int pageSize = -1;
    private String pageToken;
    private GatewayListOptions gatewayListOptions;

    private DevicesListRequest(Builder builder) {
        this.parent = builder.parent;
        this.deviceNumIds = builder.deviceNumIds;
        this.deviceIds = builder.deviceIds;
        this.fieldMask = builder.fieldMask;
        this.pageSize = builder.pageSize;
        this.pageToken = builder.pageToken;
        this.gatewayListOptions = builder.gatewayListOptions;
    }

    // Static class Builder
    public static class Builder {

        /// instance fields
        private String parent;
        private String deviceNumIds;
        private String deviceIds;
        private String fieldMask;
        private int pageSize;
        private String pageToken;
        private GatewayListOptions gatewayListOptions;

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

        public Builder setDeviceNumIds(String deviceNumIds) {
            this.deviceNumIds = deviceNumIds;
            return this;
        }

        public Builder setDeviceIds(String deviceIds) {
            this.deviceIds = deviceIds;
            return this;
        }

        public Builder setFieldMask(String fieldMask) {
            this.fieldMask = fieldMask;
            return this;
        }

        public Builder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder setPageToken(String pageToken) {
            this.pageToken = pageToken;
            return this;
        }

        public Builder setGatewayListOptions(GatewayListOptions gatewayListOptions) {
            this.gatewayListOptions = gatewayListOptions;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public DevicesListRequest build() {
            return new DevicesListRequest(this);
        }

    }

    public RegistryName getParent() {
        return RegistryName.parse(this.parent);
    }

    @Override
    public String toString() {
        return this.parent.toString();
    }

    public String getParamsForList() {
        String params = "";
        params = "parent=" + this.parent;
        if (this.deviceNumIds != null)
            params += "&deviceNumIds=" + this.deviceNumIds;
        if (this.deviceIds != null)
            params += "&deviceIds=" + this.deviceIds;
        if (this.fieldMask != null)
            params += "&fieldMask=" + this.fieldMask;
        if (this.pageSize > 0)
            params += "&pageSize=" + String.valueOf(this.pageSize);
        if (this.pageToken != null)
            params += "&pageToken=" + this.pageToken;
        if (this.gatewayListOptions != null) {
            if (this.gatewayListOptions.getGatewayType() != null) {
                params += "&gatewayListOptions.gatewayType=" + this.gatewayListOptions.getGatewayType().name();
            }
            if (this.gatewayListOptions.getAssociationsDeviceId() != null) {
                params += "&gatewayListOptions.associationsDeviceId="
                        + this.gatewayListOptions.getAssociationsDeviceId();
            }
            if (this.gatewayListOptions.getAssociationsGatewayId() != null) {
                params += "&gatewayListOptions.associationsGatewayId="
                        + this.gatewayListOptions.getAssociationsGatewayId();
            }
        }
        return params;
    }

}
