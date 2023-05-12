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

package com.clearblade.cloud.iot.v1.createdevice;

import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class CreateDeviceRequest {
    private final RegistryName parent;
    private final Device device;

    private CreateDeviceRequest(Builder builder) {
        this.parent = builder.parent;
        this.device = builder.device;
    }

    // Static class Builder
    public static class Builder {

        /// instance fields
        private RegistryName parent;
        private Device device;

        public static Builder newBuilder() {
            return new Builder();
        }

        private Builder() {
        }

        // Setter methods
        public Builder setParent(RegistryName parent) {
            this.parent = parent;
            return this;
        }

        public Builder setDevice(Device device) {
            this.device = device;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public CreateDeviceRequest build() {
            return new CreateDeviceRequest(this);
        }
    }

    public RegistryName getParent() {
        return parent;
    }

    public String[] getParams() {
        String[] params = new String[2];
        params[0] = "parent=" + this.parent.getRegistryFullName();
        params[1] = this.device.createDeviceJSONObject();
        return params;
    }
}
