package com.clearblade.cloud.iot.v1.utils;

import com.clearblade.cloud.iot.v1.devicetypes.GatewayAuthMethod;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayConfig;
import com.clearblade.cloud.iot.v1.devicetypes.GatewayType;

import java.sql.Time;

public class Timestamp implements TimestampOrBuilder {

	public static final int SECONDS_FIELD_NUMBER = 1;
	private long seconds_;
	public static final int NANOS_FIELD_NUMBER = 2;
	private int nanos_;
	private byte memoizedIsInitialized;
	private static final Timestamp DEFAULT_INSTANCE;

	private Timestamp() {
		this.seconds_ = 0L;
		this.nanos_ = 0;
		this.memoizedIsInitialized = -1;
	}

	public Timestamp(long seconds, int nanos) {
		this.seconds_ = seconds;
		this.nanos_ = nanos;
		this.memoizedIsInitialized = -1;
	}

	public Timestamp(Builder builder) {
		this.seconds_ = builder.seconds_;
		this.nanos_ = builder.nanos_;
		this.memoizedIsInitialized = builder.memoizedIsInitialized;
	}

	public static Builder newBuilder() {
		return new Timestamp.Builder();
	}

	public Builder toBuilder() {
		return new Timestamp.Builder();
	}


	public static class Builder {
		private long seconds_;
		private int nanos_;
		private byte memoizedIsInitialized;

		protected Builder() {
		}

		private Builder(Timestamp timestamp) {
			this.seconds_ = timestamp.seconds_;
			this.nanos_ = timestamp.nanos_;
			this.memoizedIsInitialized = timestamp.memoizedIsInitialized;
		}

		public Builder setSeconds(long seconds_) {
			this.seconds_ = seconds_;
			return this;
		}

		public Builder setNanos(int nanos_) {
			this.nanos_ = nanos_;
			return this;
		}

		public Builder setMemoizedIsInitialized(byte memoizedIsInitialized) {
			this.memoizedIsInitialized = memoizedIsInitialized;
			return this;
		}

		public Timestamp build() {
			return new Timestamp(this);
		}
	}

	public long getSeconds() {
		return this.seconds_;
	}

	public int getNanos() {
		return this.nanos_;
	}

	public final boolean isInitialized() {
		final byte isInitialized = this.memoizedIsInitialized;
		if (isInitialized == 1) {
			return true;
		}
		if (isInitialized == 0) {
			return false;
		}
		this.memoizedIsInitialized = 1;
		return true;
	}

	public static Timestamp getDefaultInstance() {
		return Timestamp.DEFAULT_INSTANCE;
	}

	public Timestamp getDefaultInstanceForType() {
		return Timestamp.DEFAULT_INSTANCE;
	}

	static {
		DEFAULT_INSTANCE = new Timestamp();
	}
	
	@Override
    public String toString() {
        return "seconds: " + getSeconds() + " nanos: " + getNanos();
    }
}