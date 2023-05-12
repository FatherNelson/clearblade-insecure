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

public class LocationName {

    private static final PathTemplate PROJECT_LOCATION = PathTemplate.createWithoutUrlEncoding("projects/{project}/locations/{location}");

    private final String project;
    private final String location;

    protected LocationName() {
        project = null;
        location = null;
    }

    private LocationName(Builder builder) {
        project = builder.getProject();
        location = builder.getLocation();
    }

    public String getProject() {
        return project;
    }

    public String getLocation() {
        return location;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static LocationName of(String project, String location) {
        return newBuilder().setProject(project).setLocation(location).build();
    }

    public static String format(String project, String location) {
        return newBuilder().setProject(project).setLocation(location).build()
                .toString();
    }

    public static LocationName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        } else {
            Map<String, String> matchMap = PROJECT_LOCATION.validatedMatch(formattedString, "LocationName.parse: formattedString not in valid format");
            return of((String) matchMap.get("project"), (String) matchMap.get("location"));
        }
    }


    /**
     * Builder for
     * projects/{project}/locations/{location}/registries/{registry}.
     */
    public static class Builder {
        private String project;
        private String location;

        protected Builder() {
        }

        public String getProject() {
            return project;
        }

        public String getLocation() {
            return location;
        }

        public Builder setProject(String project) {
            this.project = project;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        private Builder(LocationName locationName) {
            this.project = locationName.project;
            this.location = locationName.location;
        }

        public LocationName build() {
            return new LocationName(this);
        }
    }

    @Override
    public String toString() {
        return PROJECT_LOCATION.instantiate(new String[]{"project", this.project, "location", this.location});
    }

    public String getLocationFullName() {
        return PROJECT_LOCATION.instantiate(new String[]{"project", this.project, "location", this.location});
    }
}
