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

package com.clearblade.cloud.iot.v1.registrytypes;

import com.clearblade.cloud.iot.v1.utils.PathTemplate;

import java.util.Map;

public class RegistryName {

    private static final PathTemplate PROJECT_LOCATION_REGISTRY = PathTemplate.createWithoutUrlEncoding("projects/{project}/locations/{location}/registries/{registry}");
    private final String project;
    private final String location;
    private final String registry;

    protected RegistryName() {
        project = null;
        location = null;
        registry = null;
    }

    private RegistryName(Builder builder) {
        project = builder.getProject();
        location = builder.getLocation();
        registry = builder.getRegistry();
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

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static RegistryName of(String project, String location, String registry) {
        return newBuilder().setProject(project).setLocation(location).setRegistry(registry).build();
    }

    public static String format(String project, String location, String registry) {
        return newBuilder().setProject(project).setLocation(location).setRegistry(registry).build()
                .toString();
    }

    /**
     * Builder for
     * projects/{project}/locations/{location}/registries/{registry}.
     */
    public static class Builder {
        private String project;
        private String location;
        private String registry;

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

        private Builder(RegistryName registryName) {
            this.project = registryName.project;
            this.location = registryName.location;
            this.registry = registryName.registry;
        }

        public RegistryName build() {
            return new RegistryName(this);
        }
    }

    @Override
    public String toString() {
        return PROJECT_LOCATION_REGISTRY.instantiate(new String[]{"project", this.project, "location", this.location, "registry", this.registry});
    }

    public static RegistryName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        } else {
            Map<String, String> matchMap = PROJECT_LOCATION_REGISTRY.validatedMatch(formattedString, "RegistryName.parse: formattedString not in valid format");
            return of((String) matchMap.get("project"), (String) matchMap.get("location"), (String) matchMap.get("registry"));
        }
    }

    public String getRegistryFullName() {
        return PROJECT_LOCATION_REGISTRY.instantiate(new String[]{"project", this.project, "location", this.location, "registry", this.registry});
    }
}
