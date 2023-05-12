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

package com.clearblade.cloud.iot.v1.listdeviceregistries;

import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.registrytypes.LocationName;

import java.util.Locale;

public class ListDeviceRegistriesRequest {

    private String parent;
    private int pageSize = -1;
    private String pageToken;

    private ListDeviceRegistriesRequest(Builder builder) {

        this.parent = builder.parent;
        this.pageSize = builder.pageSize;
        this.pageToken = builder.pageToken;
    }

    // Static class Builder
    public static class Builder {

        /// instance fields
        private String parent;
        private int pageSize;
        private String pageToken;

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

        public Builder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder setPageToken(String pageToken) {
            this.pageToken = pageToken;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public ListDeviceRegistriesRequest build() {
            return new ListDeviceRegistriesRequest(this);
        }

    }

    public LocationName getParent() {
        return LocationName.parse(this.parent);
    }

    @Override
    public String toString() {
        return this.parent.toString();
    }

    public String getParamsForList() {
        String params = "";
        params = "parent=" + this.parent;
        if (this.pageSize > 0)
            params += "&pageSize=" + String.valueOf(this.pageSize);
        if (this.pageToken != null)
            params += "&pageToken=" + this.pageToken;
        return params;
    }


}
