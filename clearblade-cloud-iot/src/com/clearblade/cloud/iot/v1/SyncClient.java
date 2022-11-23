package com.clearblade.cloud.iot.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import com.clearblade.cloud.iot.v1.exception.ApplicationException;
import com.clearblade.cloud.iot.v1.utils.AuthParams;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;
import com.clearblade.cloud.iot.v1.utils.Constants;
import com.clearblade.cloud.iot.v1.utils.SetHttpConnection;

public class SyncClient {

	static Logger log = Logger.getLogger(SyncClient.class.getName());
	private ConfigParameters configParameters = new ConfigParameters();

	/**
	 * Method used to generate URL for apicall
	 * 
	 * @param apiName - path to api
	 * @param params  - parameters to be attached to request
	 * @return URL formed and to be used
	 */
	private String generateURL(String apiName, String params) {

		return AuthParams.getApiBaseURL()
				.concat(configParameters.getEndpointPort())
				.concat(configParameters.getBaseURL())
				.concat(AuthParams.getUserSystemKey())
				.concat(apiName)
				.concat("?" + params);
	}

	/**
	 * Method used to generate URL for apicall
	 * 
	 * @param apiName - path to api
	 * @param params  - parameters to be attached to request
	 * @return URL formed and to be used
	 */
	private String generateAdminURL(String apiName, String params) {

		return AuthParams.getBaseURL()
				.concat(configParameters.getBaseURL())
				.concat(AuthParams.getAdminSystemKey())
				.concat(apiName)
				.concat("?" + params);
	}

	/**
	 * Method used to Calls HTTP Get request
	 * 
	 * @param apiName
	 * @param params
	 * @return String[] containing responseCode, responseMessage and response object
	 * @throws IOException
	 * @throws ApplicationException
	 */
	public String[] get(String apiName, String params, boolean isAdmin) {
		String[] responseArray = new String[3];
		String finalURL = "";
		try {
			if (isAdmin) {
				AuthParams.setAdminCredentials();
				finalURL = generateAdminURL(apiName, params);
			} else {
				AuthParams.setRegistryCredentials();
				finalURL = generateURL(apiName, params);
			}
			URL obj = new URL(finalURL);
			SetHttpConnection setCon = new SetHttpConnection();
			HttpsURLConnection con = setCon.getConnection(obj);
			if (isAdmin)
				con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, AuthParams.getAdminToken());
			else
				con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, AuthParams.getUserToken());

			con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY,
					Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
			con.addRequestProperty(Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY,
					Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
			con.setRequestMethod(Constants.HTTP_REQUEST_METHOD_TYPE_GET);
			StringBuilder response = new StringBuilder();
			responseArray[0] = String.valueOf(con.getResponseCode());
			responseArray[1] = con.getResponseMessage();
			BufferedReader in = null;
			if (con.getErrorStream() != null) {
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			} else if (con.getInputStream() != null) {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String inputLine;
			String responseMessage = "";
			if (in != null) {
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				responseMessage = response.toString();
				responseArray[2] = responseMessage;
			}
		} catch (ApplicationException | IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}

		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

		return responseArray;
	}

	/**
	 * Method used to call HTTP Post request
	 * 
	 * @param apiName
	 * @param params
	 * @param body
	 * @return String[] containing responseCode, responseMessage and response object
	 * @throws IOException
	 * @throws ApplicationException
	 */
	public String[] post(String apiName, String params, String body, boolean isAdmin) {
		String[] responseArray = new String[3];
		String finalURL = "";
		try {
			if (isAdmin) {
				AuthParams.setAdminCredentials();
				finalURL = generateAdminURL(apiName, params);
			} else {
				AuthParams.setRegistryCredentials();
				finalURL = generateURL(apiName, params);
			}

			URL obj = new URL(finalURL);
			SetHttpConnection setCon = new SetHttpConnection();
			HttpsURLConnection con = setCon.getConnection(obj);
			if (isAdmin)
				con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY,
						AuthParams.getAdminToken());
			else
				con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY,
						AuthParams.getUserToken());
			con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY,
					Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
			con.addRequestProperty(Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY,
					Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
			con.setRequestMethod(Constants.HTTP_REQUEST_METHOD_TYPE_POST);
			con.setDoOutput(true);
			StringBuilder response = new StringBuilder();
			OutputStream os = con.getOutputStream();
			byte[] input = body.getBytes(Constants.UTF8);
			os.write(input, 0, input.length);
			os.flush();
			responseArray[0] = String.valueOf(con.getResponseCode());
			responseArray[1] = con.getResponseMessage();
			BufferedReader in = null;
			if (con.getErrorStream() != null) {
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			} else if (con.getInputStream() != null) {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String inputLine;
			String responseMessage = "";
			if (in != null) {
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				responseMessage = response.toString();
				responseArray[2] = responseMessage;
			}

		} catch (ApplicationException | IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

		return responseArray;
	}

	/**
	 * Method used to call HTTP delete request
	 * 
	 * @param apiName
	 * @param params
	 * @return String[] containing responseCode, responseMessage and response object
	 * @throws IOException
	 * @throws ApplicationException
	 */
	public String[] delete(String apiName, String params, boolean isAdmin) {
		String[] responseArray = new String[3];
		String finalURL = "";
		try {
			if (isAdmin) {
				AuthParams.setAdminCredentials();
				finalURL = generateAdminURL(apiName, params);
			} else {
				AuthParams.setRegistryCredentials();
				finalURL = generateURL(apiName, params);
			}
			URL obj = new URL(finalURL);
			SetHttpConnection setCon = new SetHttpConnection();
			HttpsURLConnection con = setCon.getConnection(obj);
			if (isAdmin)
				con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY,
						AuthParams.getAdminToken());
			else
				con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY,
						AuthParams.getUserToken());
			con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY,
					Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
			con.addRequestProperty(Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY,
					Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
			con.setRequestMethod(Constants.HTTP_REQUEST_METHOD_TYPE_DELETE);
			StringBuilder response = new StringBuilder();
			responseArray[0] = String.valueOf(con.getResponseCode());
			responseArray[1] = con.getResponseMessage();
			BufferedReader in = null;
			if (con.getErrorStream() != null) {
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			} else if (con.getInputStream() != null) {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String inputLine;
			String responseMessage = "";
			if (in != null) {
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				responseMessage = response.toString();
				responseArray[2] = responseMessage;
			}
		} catch (ApplicationException | IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

		return responseArray;
	}

	/**
	 * Method used to call HTTP Patch request
	 * 
	 * @param apiName
	 * @param params
	 * @param body
	 * @return String[] containing responseCode, responseMessage and response object
	 */
	public String[] update(String apiName, String params, String body) {
		String[] responseArray = new String[3];
		try {
			AuthParams.setRegistryCredentials();
			String finalURL = generateURL(apiName, params);
			URL obj = new URL(finalURL);
			SetHttpConnection setCon = new SetHttpConnection();
			HttpsURLConnection con = setCon.getConnection(obj);
			con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, AuthParams.getUserToken());
			con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY,
					Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
			con.addRequestProperty(Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY,
					Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
			con.setRequestMethod(Constants.HTTP_REQUEST_METHOD_TYPE_POST);
			con.setRequestProperty("X-HTTP-Method-Override", Constants.HTTP_REQUEST_METHOD_TYPE_PATCH);
			con.setDoOutput(true);
			StringBuilder response = new StringBuilder();
			OutputStream os = con.getOutputStream();
			byte[] input = body.getBytes(Constants.UTF8);
			os.write(input, 0, input.length);
			os.flush();
			responseArray[0] = String.valueOf(con.getResponseCode());
			responseArray[1] = con.getResponseMessage();
			BufferedReader in = null;
			if (con.getErrorStream() != null) {
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			} else if (con.getInputStream() != null) {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String inputLine;
			String responseMessage = "";
			if (in != null) {
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				responseMessage = response.toString();
				responseArray[2] = responseMessage;
			}
		} catch (ApplicationException | IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

		return responseArray;
	}
}
