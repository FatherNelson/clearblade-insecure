package com.clearblade.cloud.iot.v1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.exception.ApplicationException;
import com.clearblade.cloud.iot.v1.utils.AuthParams;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;
import com.clearblade.cloud.iot.v1.utils.Constants;

public class AsyncClient {

	static Logger log = Logger.getLogger(AsyncClient.class.getName());

	private ConfigParameters configParameters = ConfigParameters.getInstance();
	private String[] responseArray = new String[3];
	private boolean isAdmin = false;

	/**
	 * Method used to generate URL for apicall
	 * @param apiName - path to api
	 * @param params  - parameters to be attached to request
	 * @return URL formed and to be used
	 */
	private String generateURL(String apiName, String params) {

		return AuthParams.getApiBaseURL().concat(configParameters.getEndpointPort())
				.concat(configParameters.getWebhook()).concat(AuthParams.getUserSystemKey()).concat(apiName)
				.concat("?" + params);
	}

	/**
	 * Method used to generate URL for apicall
	 * @param apiName - path to api
	 * @param params  - parameters to be attached to request
	 * @return URL formed and to be used
	 */
	private String generateAdminURL(String apiName, String params) {

		return AuthParams.getBaseURL().concat(configParameters.getWebhook()).concat(AuthParams.getAdminSystemKey())
				.concat(apiName).concat("?" + params);
	}

	/**
	 * Method used to Calls HTTP Get request
	 * @param apiName
	 * @param params
	 * @return String[] containing responseCode, responseMessage and response object
	 * @throws IOException
	 * @throws ApplicationException
	 */
	public String[] get(String apiName, String params) {
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
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(finalURL))
					.headers(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY,
							Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE,
							Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token,
							Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY,
							Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE)
					.GET().build();

			CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder().build()
																		 .sendAsync(request, HttpResponse.BodyHandlers.ofString());

			HttpResponse<String> httpResponse = response.get();
			responseArray[0] = String.valueOf(httpResponse.statusCode());
			responseArray[1] = "";
			responseArray[2] = httpResponse.body();

		} catch (ApplicationException | IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (InterruptedException ex) {
			log.log(Level.SEVERE, ex.getMessage());
			Thread.currentThread().interrupt();
		} catch (Exception ec) {
			log.log(Level.SEVERE, ec.getMessage());
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
	public String[] post(String apiName, String params, String body) {
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

			CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder()
					  .version(HttpClient.Version.HTTP_2)
					  .connectTimeout(Duration.ofSeconds(20))
					  .build()					  
					  .sendAsync(request, HttpResponse.BodyHandlers.ofString());
			
			HttpResponse<String> httpResponse = response.get();
			responseArray[0] = String.valueOf(httpResponse.statusCode());
			responseArray[1] = "";
			responseArray[2] = httpResponse.body();
			
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
	public String[] delete(String apiName, String params) {
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

			CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder()
					  .build()
					  .sendAsync(request, HttpResponse.BodyHandlers.ofString());
			
			HttpResponse<String> httpResponse = response.get();
			responseArray[0] = String.valueOf(httpResponse.statusCode());
			responseArray[1] = "";
			responseArray[2] = httpResponse.body();
			
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
	 * Method used to call HTTP Patch request
	 * @param apiName
	 * @param params
	 * @param body
	 * @return String[] Object containing responseCode, responseMessage and response
	 *         object
	 */
	public String[] update(String apiName, String params, String body) {
		try {
			AuthParams.setRegistryCredentials();
			String finalURL = generateURL(apiName, params);

			BodyPublisher jsonPayload = BodyPublishers.ofString(body);
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(finalURL))
					.method(Constants.HTTP_REQUEST_METHOD_TYPE_PATCH, jsonPayload)
					.headers(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY,
							Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE,
							Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, AuthParams.getUserToken(),
							Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY,
							Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE)
					.build();

			CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder().build().sendAsync(request,
					HttpResponse.BodyHandlers.ofString());

			HttpResponse<String> httpResponse = response.get();
			responseArray[0] = String.valueOf(httpResponse.statusCode());
			responseArray[1] = "";
			responseArray[2] = httpResponse.body();

		} catch (ApplicationException | IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (InterruptedException ex) {
			log.log(Level.SEVERE, ex.getMessage());
			Thread.currentThread().interrupt();
		} catch (Exception ec) {
			log.log(Level.SEVERE, ec.getMessage());
		}

		return responseArray;
	}

	// Registry Apis

	public String[] asyncCreateDeviceRegistry(String apiName, String params, String body, boolean isAdmin) {
		this.isAdmin = isAdmin;
		return this.post(apiName, params, body);
	}


	public String[] asyncDeleteDeviceRegistry(String apiName, String params, boolean isAdmin){
		this.isAdmin = isAdmin;
		return this.delete(apiName, params);
	}


	public String[] asyncListDeviceRegistries(String apiName, String params, boolean isAdmin) {
		this.isAdmin = isAdmin;
		return this.get(apiName, params);
	}

}
