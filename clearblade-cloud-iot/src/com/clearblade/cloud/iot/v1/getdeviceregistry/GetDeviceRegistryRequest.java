package com.clearblade.cloud.iot.v1.getdeviceregistry;

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

	@Override
	public String toString() {
		return "name="+this.name;
	}

}
