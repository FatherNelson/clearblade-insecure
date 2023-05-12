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

package com.clearblade.cloud.iot.v1.sendcommandtodevice;

import java.util.Arrays;

import org.json.simple.JSONObject;

import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.utils.ByteString;

public class SendCommandToDeviceRequest {

    private final DeviceName name;
    private final ByteString binaryData;
    private final byte[] binaryDataByte;
    private final String subfolder;
    private final String deviceName;
    public JSONObject requestParams;
    public JSONObject bodyParams;

    private SendCommandToDeviceRequest(Builder builder) {
        this.name = builder.name;
        this.binaryData = builder.binaryData;
        this.subfolder = builder.subfolder;
        this.binaryDataByte = builder.binaryDataByte;
        this.deviceName = builder.deviceName;
    }

    // Static class Builder
    public static class Builder {

        /// instance fields
        private DeviceName name;
        private ByteString binaryData;
        private String subfolder;
        private byte[] binaryDataByte;
        private String deviceName;

        public static Builder newBuilder() {
            return new Builder();
        }

        private Builder() {
        }

        // Setter methods
        public Builder setName(DeviceName name) {
            this.name = name;
            return this;
        }

        public Builder setBinaryData(ByteString binaryData) {
            this.binaryData = binaryData;
            return this;
        }

        public Builder setBinaryDataByte(byte[] binaryDataByte) {
            this.binaryDataByte = binaryDataByte;
            return this;
        }

        public Builder setSubfolder(String subfolder) {
            this.subfolder = subfolder;
            return this;
        }

        public Builder setName(String deviceName) {
            this.deviceName = deviceName;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public SendCommandToDeviceRequest build() {
            return new SendCommandToDeviceRequest(this);
        }
    }

    public DeviceName getDeviceName() {
        if (name != null) {
            return name;
        } else {
            return DeviceName.parse(this.deviceName);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        requestParams = new JSONObject();
        bodyParams = new JSONObject();

        String dName = "";
        String bData = null;

        if (this.name != null) {
            dName = this.name.getDevice();
        } else if (this.deviceName != null) {
            dName = this.deviceName;
        }
        if (this.binaryData != null) {
            bData = new String(this.binaryData.toByteArray());
        } else if (this.binaryDataByte != null) {
            if (this.binaryDataByte.length == 0) {
                bData = "EMPTY";
            } else {
                bData = Arrays.toString(this.binaryDataByte);
            }
        }

        requestParams.put("name", dName);
        bodyParams.put("binaryData", bData);
        bodyParams.put("subfolder", subfolder);

        return "name = " + dName + ", binaryData = " + bData + ", subfolder = " + subfolder;

    }

    @SuppressWarnings("unchecked")
    public String[] getBodyAndParams() {
        String[] output = new String[2];
        String params = "name=" + this.deviceName + "&method=sendCommandToDevice";
        String bData = null;
        if (this.binaryData != null) {
            bData = new String(this.binaryData.toByteArray());
        } else if (this.binaryDataByte != null) {
            if (this.binaryDataByte.length == 0) {
                bData = "EMPTY";
            } else {
                bData = Arrays.toString(this.binaryDataByte);
            }
        }
        bodyParams = new JSONObject();
        bodyParams.put("binaryData", bData);
        if (this.subfolder != null) {
            bodyParams.put("subfolder", this.subfolder);
        }

        output[0] = params;
        output[1] = bodyParams.toJSONString();
        return output;
    }
}
