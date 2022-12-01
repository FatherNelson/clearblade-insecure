package com.clearblade.cloud.iot.v1.devicetypes;

public class DeviceName {

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

		return this.device;

	}
}
