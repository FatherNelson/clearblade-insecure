package com.clearblade.cloud.iot.v1.utils;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

public class SetHttpConnection {
	static Logger log = Logger.getLogger(SetHttpConnection.class.getName());
	public HttpsURLConnection con;

	public SetHttpConnection() {
		con = null;
	}

	public HttpsURLConnection getConnection(URL url) {
		try {
			con = (HttpsURLConnection) url.openConnection();
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return con;
	}
}
