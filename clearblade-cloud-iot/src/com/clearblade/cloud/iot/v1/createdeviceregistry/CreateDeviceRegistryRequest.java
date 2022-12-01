package com.clearblade.cloud.iot.v1.createdeviceregistry;

import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;

public class CreateDeviceRegistryRequest {
    private String parent;
    private DeviceRegistry deviceRegistry;

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

    public String[] getBodyAndParams() {
        String[] output = new String[2];
        output[0] = "parent=" + this.parent;
        output[1] = this.deviceRegistry.createDeviceJSONObject(parent);
        return output;
    }
}
