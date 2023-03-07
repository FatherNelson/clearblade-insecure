package com.clearblade.cloud.iot.v1.utils;

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