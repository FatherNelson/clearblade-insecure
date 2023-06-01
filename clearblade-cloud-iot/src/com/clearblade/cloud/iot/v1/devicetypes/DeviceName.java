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

package com.clearblade.cloud.iot.v1.devicetypes;

import com.clearblade.cloud.iot.v1.utils.PathTemplate;

import java.util.Map;

public class DeviceName {

    private static final PathTemplate PROJECT_LOCATION_REGISTRY_DEVICE = PathTemplate.createWithoutUrlEncoding("projects/{project}/locations/{location}/registries/{registry}/devices/{device}");
    private final String project;
    private final String location;
    private final String registry;
    private final String device;

    protected DeviceName() {
        project = null;
        location = null;
        registry = null;
        device = null;
    }

    private DeviceName(Builder builder) {
        project = builder.getProject();
        location = builder.getLocation();
        registry = builder.getRegistry();
        device = builder.getDevice();
    }

    public String getProject() {
        return project;
    }

    public String getLocation() {
        return location;
    }

    public String getRegistry() {
        return registry;
    }

    public String getDevice() {
        return device;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static DeviceName of(String project, String location, String registry, String device) {
        return newBuilder().setProject(project).setLocation(location).setRegistry(registry).setDevice(device).build();
    }

    public static String format(String project, String location, String registry, String device) {
        return newBuilder().setProject(project).setLocation(location).setRegistry(registry).setDevice(device).build()
                .toString();
    }

    public static DeviceName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        } else {
            Map<String, String> matchMap = PROJECT_LOCATION_REGISTRY_DEVICE.validatedMatch(formattedString, "DeviceName.parse: formattedString not in valid format");
            return of((String) matchMap.get("project"), (String) matchMap.get("location"), (String) matchMap.get("registry"), (String) matchMap.get("device"));
        }
    }

    /**
     * Builder for
     * projects/{project}/locations/{location}/registries/{registry}/devices/{device}.
     */
    public static class Builder {
        private String project;
        private String location;
        private String registry;
        private String device;

        protected Builder() {
        }

        public String getProject() {
            return project;
        }

        public String getLocation() {
            return location;
        }

        public String getRegistry() {
            return registry;
        }

        public String getDevice() {
            return device;
        }

        public Builder setProject(String project) {
            this.project = project;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setRegistry(String registry) {
            this.registry = registry;
            return this;
        }

        public Builder setDevice(String device) {
            this.device = device;
            return this;
        }

        private Builder(DeviceName deviceName) {
            this.project = deviceName.project;
            this.location = deviceName.location;
            this.registry = deviceName.registry;
            this.device = deviceName.device;
        }

        public DeviceName build() {
            return new DeviceName(this);
        }
    }

    @Override
    public String toString() {
        return PROJECT_LOCATION_REGISTRY_DEVICE.instantiate(new String[]{"project", this.project, "location", this.location, "registry", this.registry, "device", this.device});
    }
}
