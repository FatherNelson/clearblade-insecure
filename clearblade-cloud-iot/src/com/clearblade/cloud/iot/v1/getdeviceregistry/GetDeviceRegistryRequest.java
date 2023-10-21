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

package com.clearblade.cloud.iot.v1.getdeviceregistry;

import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GetDeviceRegistryRequest {

    private final String name;

    private GetDeviceRegistryRequest(Builder builder) {
        this.name = builder.name;
    }

    // Static class Builder
    public static class Builder {

        /// instance fields
        private String name;

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

        // build method to deal with outer class
        // to return outer instance
        public GetDeviceRegistryRequest build() {
            return new GetDeviceRegistryRequest(this);
        }
    }

    public RegistryName getName() {
        return RegistryName.parse(this.name);
    }

    @Override
    public String toString() {
        return "name=" + URLEncoder.encode(this.name, StandardCharsets.UTF_8);
    }

}
