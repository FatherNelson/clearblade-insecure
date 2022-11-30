package com.clearblade.cloud.iot.v1;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.exception.ApplicationException;
import com.clearblade.cloud.iot.v1.utils.AuthParams;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;
import com.clearblade.cloud.iot.v1.utils.Constants;

public class SyncClient {

	static Logger log = Logger.getLogger(SyncClient.class.getName());
	private ConfigParameters configParameters = ConfigParameters.getInstance();
	

	/**
	 * Method used to generate URL for apicall
	 * @param apiName - path to api
	 * @param params  - parameters to be attached to request
	 * @return URL formed and to be used
	 */
	private String generateURL(String apiName, String params) {

		return AuthParams.getApiBaseURL()
				.concat(configParameters.getEndpointPort())
				.concat(configParameters.getWebhook())
				.concat(AuthParams.getUserSystemKey())
				.concat(apiName)
				.concat("?" + params);
	}

	/**
	 * Method used to generate URL for apicall
	 * @param apiName - path to api
	 * @param params  - parameters to be attached to request
	 * @return URL formed and to be used
	 */
	private String generateAdminURL(String apiName, String params) {

		return AuthParams.getBaseURL()
				.concat(configParameters.getWebhook())
				.concat(AuthParams.getAdminSystemKey())
				.concat(apiName)
				.concat("?" + params);
	}

	/**
	 * Method used to Calls HTTP Get request
	 * @param apiName
	 * @param params
	 * @return String[] containing responseCode, responseMessage and response object
	 * @throws IOException
	 * @throws ApplicationException
	 */
	public String[] get(String apiName, String params, boolean isAdmin) {
		String[] responseArray = new String[3];
		String finalURL = "";
		String token = "";
		try {
			if (isAdmin) {
				AuthParams.setAdminCredentials();
				finalURL = generateAdminURL(apiName, params);
				token = AuthParams.getAdminToken();
			} else {
				AuthParams.setRegistryCredentials();
				finalURL = generateURL(apiName, params);
				token = AuthParams.getUserToken();
			}

			HttpRequest request = HttpRequest.newBuilder()					
                    .uri(URI.create(finalURL))                    
                    .headers(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE, 
        					Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token,
        					Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE)
                    .GET()
                    .build();

			HttpResponse<String> response = HttpClient
					  .newBuilder()
					  .proxy(ProxySelector.getDefault())
					  .build()
					  .send(request, BodyHandlers.ofString());
			
			responseArray[0] = String.valueOf(response.statusCode());
			responseArray[1] = "";
			responseArray[2] = response.body();
			

		} catch (InterruptedException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}catch(Exception ex) {
			log.log(Level.SEVERE, ex.getMessage());
		}

		return responseArray;
	}

	/**
	 * Method used to call HTTP Post request
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
		String token = "";
		try {
			if (isAdmin) {
				AuthParams.setAdminCredentials();
				finalURL = generateAdminURL(apiName, params);
				token = AuthParams.getAdminToken();
			} else {
				AuthParams.setRegistryCredentials();
				finalURL = generateURL(apiName, params);
				token = AuthParams.getUserToken();
			}

			BodyPublisher jsonPayload = BodyPublishers.ofString(body);
			HttpRequest request = HttpRequest.newBuilder()					
                    .uri(URI.create(finalURL))
                    .method(Constants.HTTP_REQUEST_METHOD_TYPE_POST, jsonPayload)
                    .headers(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE, 
        					Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token,
        					Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE)
                    .build();

			HttpResponse<String> response = HttpClient
					  .newBuilder()
					  .proxy(ProxySelector.getDefault())
					  .build()
					  .send(request, BodyHandlers.ofString());
			
			responseArray[0] = String.valueOf(response.statusCode());
			responseArray[1] = "";
			responseArray[2] = response.body();
			
		} catch (ApplicationException | IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (InterruptedException ex) {
			log.log(Level.SEVERE, ex.getMessage());
			Thread.currentThread().interrupt();
		}catch(Exception ec) {
			log.log(Level.SEVERE, ec.getMessage());
		}

		return responseArray;
	}

	/**
	 * Method used to call HTTP delete request
	 * @param apiName
	 * @param params
	 * @return String[] containing responseCode, responseMessage and response object
	 * @throws IOException
	 * @throws ApplicationException
	 */
	public String[] delete(String apiName, String params, boolean isAdmin) {
		String[] responseArray = new String[3];
		String finalURL = "";
		String token = "";
		try {
			if (isAdmin) {
				AuthParams.setAdminCredentials();
				finalURL = generateAdminURL(apiName, params);
				token = AuthParams.getAdminToken();
			} else {
				AuthParams.setRegistryCredentials();
				finalURL = generateURL(apiName, params);
				token = AuthParams.getUserToken();
			}
			HttpRequest request = HttpRequest.newBuilder()					
                    .uri(URI.create(finalURL))                    
                    .headers(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE, 
        					Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token,
        					Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE)
                    .DELETE()
                    .build();

			HttpResponse<String> response = HttpClient
					  .newBuilder()
					  .proxy(ProxySelector.getDefault())
					  .build()
					  .send(request, BodyHandlers.ofString());
			
			responseArray[0] = String.valueOf(response.statusCode());
			responseArray[1] = "";
			responseArray[2] = response.body();
			

		} catch (InterruptedException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}catch(Exception ex) {
			log.log(Level.SEVERE, ex.getMessage());
		}

		return responseArray;
	}

	/**
	 * Method used to call HTTP Patch request
	 * @param apiName
	 * @param params
	 * @param body
	 * @return String[] containing responseCode, responseMessage and response object
	 */
	public String[] update(String apiName, String params, String body) throws InterruptedException{
		String[] responseArray = new String[3];
		try {
			AuthParams.setRegistryCredentials();
			String finalURL = generateURL(apiName, params);

			BodyPublisher jsonPayload = BodyPublishers.ofString(body);
			HttpRequest request = HttpRequest.newBuilder()					
                    .uri(URI.create(finalURL))
                    .method(Constants.HTTP_REQUEST_METHOD_TYPE_PATCH, jsonPayload)
                    .headers(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY,
        					Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE, 
        					Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, AuthParams.getUserToken(),Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY,
        					Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE )
                    .build();

			HttpResponse<String> response = HttpClient
					  .newBuilder()
					  .proxy(ProxySelector.getDefault())
					  .build()
					  .send(request, BodyHandlers.ofString());
			
			responseArray[0] = String.valueOf(response.statusCode());
			responseArray[1] = "";
			responseArray[2] = response.body();

		} catch (InterruptedException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}catch(Exception ex) {
			log.log(Level.SEVERE, ex.getMessage());
		}
		return responseArray;
	}
}
