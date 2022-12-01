package com.clearblade.cloud.iot.v1.devicetypes;

public class FieldMask {

	private final String name;

	public FieldMask() {
		name = "";
	}

	private FieldMask(Builder builder) {
		name = builder.getFieldmask();
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private String name;

		protected Builder() {

		}

		public String getFieldmask() {
			return name;
		}

		public Builder setFieldmask(String fieldmask) {
			this.name = fieldmask;
			return this;
		}

		private Builder(FieldMask fieldMask) {
			this.name = fieldMask.name;
		}

		public FieldMask build() {
			return new FieldMask(this);
		}
	}

	@Override
	public String toString() {
		if (this.name != null)
			return this.name;
		else
			return "";
	}
}
