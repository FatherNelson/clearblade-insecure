package com.clearblade.cloud.iot.v1.deviceslist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.clearblade.cloud.iot.v1.devicetypes.Device;

public class DevicesListResponse {

	static Logger log = Logger.getLogger(DevicesListResponse.class.getName());

	private String nextPageToken;
	private List<Device> devicesList;

	protected DevicesListResponse(Builder builder) {
		this.devicesList = builder.devicesList;
		this.nextPageToken = builder.nextPageToken;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public List<Device> getDevicesList() {
		return devicesList;
	}

	public void setDevicesList(List<Device> devicesList) {
		this.devicesList = devicesList;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private String nextPageToken;
		private List<Device> devicesList = new ArrayList<>();

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public Builder buildResponse(String jsonString) {
			try {
				JSONObject jsonObj = new JSONObject();
				JSONParser jsonParser = new JSONParser();
				jsonObj = (JSONObject) (jsonParser.parse(jsonString));

				JSONArray devicesArray = (JSONArray) jsonObj.get("devices");
				@SuppressWarnings("rawtypes")
				Iterator deviceIterate = devicesArray.iterator();
				while (deviceIterate.hasNext()) {
					JSONObject deviceJson = (JSONObject) deviceIterate.next();
					Device deviceObj = Device.newBuilder().build();
					deviceObj.loadFromString(deviceJson.toString());
					devicesList.add(deviceObj);
				}

				if (jsonObj.containsKey("nextPageToken"))
					nextPageToken = (String) jsonObj.get("nextPageToken");

			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public DevicesListResponse build() {
			return new DevicesListResponse(this);
		}
	}

}