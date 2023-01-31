package com.clearblade.cloud.iot.v1;

public interface ResponseCallback {

	void onSuccess(String message);

	void onFail(String message);
}
