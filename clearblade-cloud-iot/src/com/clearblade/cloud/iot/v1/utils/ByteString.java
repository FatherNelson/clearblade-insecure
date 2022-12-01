package com.clearblade.cloud.iot.v1.utils;

public class ByteString {

	private String binaryDataStr;
	private ByteString binaryData;
	private byte[] binaryDataArray;
	public static final byte[] EMPTY = new byte[0];

	public ByteString(String str) {	
		this.setBinaryDataArray(str.getBytes());
	}

	public ByteString getBinaryData() {
		return binaryData;
	}

	public void setBinaryData(ByteString binaryData) {
		this.binaryData = binaryData;
	}

	public byte[] getBinaryDataArray() {
		return binaryDataArray;
	}

	public void setBinaryDataArray(byte[] binaryDataArray) {
		this.binaryDataArray = binaryDataArray;
	}

	public String getBinaryDataStr() {
		return binaryDataStr;
	}

	public void setBinaryDataStr(String binaryDataStr) {
		this.binaryDataStr = binaryDataStr;
	}
	
	

}
