package com.clearblade.cloud.iot.v1.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.clearblade.cloud.iot.v1.exception.ApplicationException;

public class AuthParams {
	static Logger log = Logger.getLogger(AuthParams.class.getName());
	static ConfigParameters configParameters = ConfigParameters.getInstance();
	private static String adminSystemKey = null;
	private static String project = null;
	private static String baseURL = null;
	private static String adminToken = null;
	private static String userSystemKey = null;
	private static String userToken = null;
	private static String apiBaseURL = null;

	public static String getAdminSystemKey() {
		return adminSystemKey;
	}

	public static String getProject() {
		return project;
	}

	public static String getBaseURL() {
		return baseURL;
	}

	public static String getAdminToken() {
		return adminToken;
	}

	public static String getUserSystemKey() {
		return userSystemKey;
	}

	public static String getUserToken() {
		return userToken;
	}

	public static String getApiBaseURL() {
		return apiBaseURL;
	}

	public static void setAdminCredentials() throws ApplicationException, IOException {

		if (adminSystemKey != null) {
			return;
		}
		try {
			String pathToAuthFile = System.getenv(Constants.AUTH_ACCESS);
			if (pathToAuthFile != null) {
				JSONParser jsonParser = new JSONParser();
				FileReader authReader = new FileReader(pathToAuthFile);
				// Read JSON file
				Object obj = jsonParser.parse(authReader);
				JSONObject authJSONObject = (JSONObject) obj;
				if (authJSONObject != null) {

					adminSystemKey = (authJSONObject.get(Constants.ADMIN_SYSTEM_KEY)).toString();
					adminToken = (authJSONObject.get(Constants.ADMIN_TOKEN)).toString();
					baseURL = (authJSONObject.get(Constants.BASE_URL)).toString();
					project = (authJSONObject.get(Constants.PROJECT_NAME)).toString();
				}
			} else {
				log.log(Level.SEVERE, "CLEARBLADE_CONFIGURATION Enviornment variable not set");
				throw new ApplicationException("CLEARBLADE_CONFIGURATION Enviornment variable not set");
			}

		} catch (FileNotFoundException fe) {
			log.log(Level.SEVERE, "ClearBlade Configuration File not found");
			throw new FileNotFoundException("ClearBlade Configuration File not found");

		} catch (IOException fe) {
			log.log(Level.SEVERE, "Access Denied - ClearBlade Configuration File cannot be read");
			throw new IOException("Access Denied - ClearBlade Configuration File cannot be read");

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}

	private static void setRegistryRegion() throws ApplicationException {
		if(configParameters.getRegistry()==null) {
			String registryName = System.getenv(Constants.AUTH_REGISTRY);
			if(registryName != null) { 
				configParameters.setRegistry(registryName);
			} else {
				log.log(Level.SEVERE, "CLEARBLADE_REGISTRY Enviornment variable not set");
				throw new ApplicationException("CLEARBLADE_REGISTRY Enviornment variable not set");
			}
			String regionName = System.getenv(Constants.AUTH_REGION);
			if(regionName != null) { 
				configParameters.setRegion(regionName);
			} else {
				log.log(Level.SEVERE, "CLEARBLADE_REGION Enviornment variable not set");
				throw new ApplicationException("CLEARBLADE_REGION Enviornment variable not set");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void setRegistryCredentials() throws ApplicationException, IOException {
		if (userSystemKey != null) {
			return;
		}
		setAdminCredentials();
		setRegistryRegion();
		String finalURL = baseURL.concat(configParameters.getGetSystemCredentialsExtension())
				.concat(adminSystemKey)
				.concat("/getRegistryCredentials");
		URL obj = new URL(finalURL);
		SetHttpConnection setCon = new SetHttpConnection();
		HttpsURLConnection con = setCon.getConnection(obj);
		con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, adminToken);
		con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY,
				Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
		con.addRequestProperty(Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY,
				Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
		con.setRequestMethod(Constants.HTTP_REQUEST_METHOD_TYPE_POST);
		con.setDoOutput(true);
		StringBuilder response = new StringBuilder();
		try (OutputStream os = con.getOutputStream()) {
			JSONObject js = new JSONObject();
			js.put("region", configParameters.getRegion());
			js.put("registry", configParameters.getRegistry());
			js.put("project", project);

			byte[] input = js.toString().getBytes(Constants.UTF8);
			os.write(input, 0, input.length);
			os.flush();
			int responseCode = con.getResponseCode();
			BufferedReader in = null;
			if (con.getErrorStream() != null) {
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			} else if (con.getInputStream() != null) {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			JSONObject responseJSONObject = new JSONObject();
			JSONParser responseParser = new JSONParser();
			String inputLine;
			String responseMessage = "";
			if (in != null) {
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				responseMessage = response.toString();
			}
			if (responseCode == 200) {
				if (responseMessage != null && responseMessage.length() > 0) {
					Object responseObj = responseParser.parse(responseMessage);
					if (responseObj != null) {
						responseJSONObject = (JSONObject) responseObj;
						userSystemKey = responseJSONObject.get(Constants.USER_SYSTEM_KEY).toString();
						userToken = responseJSONObject.get(Constants.USER_TOKEN).toString();
						apiBaseURL = responseJSONObject.get(Constants.API_BASE_URL).toString();
					}
				}
			}else {
					log.log(Level.INFO, "Response code " + responseCode + " received with message::" + responseMessage);
				}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

}
