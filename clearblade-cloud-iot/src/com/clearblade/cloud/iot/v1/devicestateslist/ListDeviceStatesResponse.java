package com.clearblade.cloud.iot.v1.devicestateslist;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.clearblade.cloud.iot.v1.devicetypes.DeviceState;

public class ListDeviceStatesResponse {

	static Logger log = Logger.getLogger(ListDeviceStatesResponse.class.getName());

	private List<DeviceState> deviceStatesList = new ArrayList<>();

	protected ListDeviceStatesResponse(Builder builder) {
		this.deviceStatesList = builder.deviceStatesList;
	}

	// Static class Builder
	public static class Builder {

		private List<DeviceState> deviceStatesList = new ArrayList<>();

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// build method to deal with outer class
		// to return outer instance
		public ListDeviceStatesResponse build() {
			return new ListDeviceStatesResponse(this);
		}

		public Builder buildResponse(String stateList) {
			try {
				JSONObject jsonObj = new JSONObject();
				JSONParser jsonParser = new JSONParser();
				jsonObj = (JSONObject) (jsonParser.parse(stateList));

				JSONArray stateArray = (JSONArray) jsonObj.get("deviceStates");
				@SuppressWarnings("rawtypes")
				Iterator stateIterator = stateArray.iterator();
				while (stateIterator.hasNext()) {
					JSONObject stateJson = (JSONObject) stateIterator.next();
					DeviceState stateObj = DeviceState.newBuilder().setBinaryData((String) stateJson.get("binaryData"))
							.setUpdateTime((String) stateJson.get("updateTime"))
							.build();
					deviceStatesList.add(stateObj);
				}

			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
			return this;

		}

	}

	@Override
	public String toString() {
		return "";
	}

	public List<DeviceState> getDeviceStatesList() {
		return deviceStatesList;
	}

	public void setDeviceStatesList(List<DeviceState> deviceStatesList) {
		this.deviceStatesList = deviceStatesList;
	}
}
