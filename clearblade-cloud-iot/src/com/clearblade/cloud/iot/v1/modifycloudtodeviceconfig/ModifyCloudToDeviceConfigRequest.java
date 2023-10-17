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

package com.clearblade.cloud.iot.v1.modifycloudtodeviceconfig;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.exception.ApplicationException;
import com.clearblade.cloud.iot.v1.registrytypes.LocationName;
import org.json.simple.JSONObject;

import com.clearblade.cloud.iot.v1.utils.ByteString;

public class ModifyCloudToDeviceConfigRequest {

    private String deviceName;
    private String versionToUpdate;
    private ByteString binaryData;
    private byte[] binaryDataByte;

    private ModifyCloudToDeviceConfigRequest(Builder builder) {
        this.deviceName = builder.deviceName;
        this.versionToUpdate = builder.versionToUpdate;
        this.binaryData = builder.binaryData;
        this.binaryDataByte = builder.binaryDataByte;
    }

    // Static class Builder
    public static class Builder {

        /// instance fields
        private String deviceName;
        private String versionToUpdate;
        private ByteString binaryData;
        private byte[] binaryDataByte;

        public static Builder newBuilder() {
            return new Builder();
        }

        private Builder() {
        }

        // Setter methods
        public Builder setName(String deviceName) {
            this.deviceName = deviceName;
            return this;
        }

        public Builder setVersionToUpdate(String versionToUpdate) {
            this.versionToUpdate = versionToUpdate;
            return this;
        }

        public Builder setBinaryData(ByteString binaryData) {
            this.binaryData = binaryData;
            return this;
        }

        public Builder setBinaryData(byte[] binaryDataByte) {
            this.binaryDataByte = binaryDataByte;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public ModifyCloudToDeviceConfigRequest build() {
            return new ModifyCloudToDeviceConfigRequest(this);
        }
    }

    public DeviceName getDeviceName() {
        return DeviceName.parse(this.deviceName);
    }

    @Override
    public String toString() {
        return "name = " + this.deviceName + ", versionToUpdate = " + this.versionToUpdate;
    }

    @SuppressWarnings("unchecked")
    public String[] getBodyAndParams() {
        String[] output = new String[2];
        String params = null;
        try {
            params = "name=" + URLEncoder.encode(this.deviceName,"UTF-8") + "&method=modifyCloudToDeviceConfig";
        } catch (UnsupportedEncodingException e) {
            throw new ApplicationException(e);
        }
        String bData = "EMPTY";
        if (this.binaryData != null) {
            bData = new String(this.binaryData.toByteArray());
        } else if (this.binaryDataByte != null) {
            if (this.binaryDataByte.length == 0) {
                bData = "EMPTY";
            } else {
                bData = Arrays.toString(this.binaryDataByte);
            }
        }
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("binaryData", bData);
        bodyParams.put("versionToUpdate", this.versionToUpdate);

        output[0] = params;
        output[1] = bodyParams.toJSONString();
        return output;
    }
}
