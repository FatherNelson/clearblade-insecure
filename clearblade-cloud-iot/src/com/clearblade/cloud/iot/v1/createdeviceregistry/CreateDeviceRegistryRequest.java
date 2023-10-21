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

package com.clearblade.cloud.iot.v1.createdeviceregistry;

import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.registrytypes.LocationName;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CreateDeviceRegistryRequest {
    private final String parent;
    private final DeviceRegistry deviceRegistry;

    private CreateDeviceRegistryRequest(Builder builder) {
        this.parent = builder.parent;
        this.deviceRegistry = builder.deviceRegistry;
    }

    // Static class Builder
    public static class Builder {

        /// instance fields
        private String parent;
        private DeviceRegistry deviceRegistry;

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

        public Builder setDeviceRegistry(DeviceRegistry deviceRegistry) {
            this.deviceRegistry = deviceRegistry;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public CreateDeviceRegistryRequest build() {
            return new CreateDeviceRegistryRequest(this);
        }
    }

    public LocationName getParent() {
        return LocationName.parse(this.parent);
    }

    public String[] getBodyAndParams() {
        String[] output = new String[2];
        output[0] = "parent=" + URLEncoder.encode(this.parent, StandardCharsets.UTF_8);
        output[1] = this.deviceRegistry.createDeviceJSONObject(parent);
        return output;
    }
}
