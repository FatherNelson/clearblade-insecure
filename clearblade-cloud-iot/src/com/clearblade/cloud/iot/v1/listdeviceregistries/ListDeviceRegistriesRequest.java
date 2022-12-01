package com.clearblade.cloud.iot.v1.listdeviceregistries;

public class ListDeviceRegistriesRequest {
	
	private String parent;
	private int pageSize = -1;
	private String pageToken;

	private ListDeviceRegistriesRequest(Builder builder) {

		this.parent = builder.parent;
		this.pageSize = builder.pageSize;
		this.pageToken = builder.pageToken;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private String parent;
		private int pageSize;
		private String pageToken;

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

		public Builder setPageSize(int pageSize) {
			this.pageSize = pageSize;
			return this;
		}

		public Builder setPageToken(String pageToken) {
			this.pageToken = pageToken;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public ListDeviceRegistriesRequest build() {
			return new ListDeviceRegistriesRequest(this);
		}

	}

	@Override
	public String toString() {
		return this.parent.toString();
	}

	public String getParamsForList() {
		String params = "";
		params = "parent=" + this.parent;
		if (this.pageSize > 0)
			params += "&pageSize=" + String.valueOf(this.pageSize);
		if (this.pageToken != null)
			params += "&pageToken=" + this.pageToken;
		return params;
	}


}
