package com.clearblade.cloud.iot.v1.deletedeviceregistry;

public class DeleteDeviceRegistryRequest {
    private String name;

    private DeleteDeviceRegistryRequest(Builder builder) {
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
        public DeleteDeviceRegistryRequest build() {
            return new DeleteDeviceRegistryRequest(this);
        }
    }

    public String getParams() {
        return "name=" + this.name;
    }


}
