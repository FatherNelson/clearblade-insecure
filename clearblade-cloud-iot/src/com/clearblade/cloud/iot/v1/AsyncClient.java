package com.clearblade.cloud.iot.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import com.clearblade.cloud.iot.v1.exception.ApplicationException;
import com.clearblade.cloud.iot.v1.utils.AuthParams;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;
import com.clearblade.cloud.iot.v1.utils.Constants;
import com.clearblade.cloud.iot.v1.utils.SetHttpConnection;

public class AsyncClient {

	static Logger log = Logger.getLogger(AsyncClient.class.getName());

	private ConfigParameters configParameters = ConfigParameters.getInstance();
	private String[] responseArray = new String[3];
	private String apiName;
	private String params;
	private String body;
	private boolean isAdmin = false;

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
				.concat(configParameters.getWebhook())
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
				.concat(configParameters.getWebhook())
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
	private Object get() {
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
	public Object post() {
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
	public Object delete() {
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
	 * @return String[] Object containing responseCode, responseMessage and response
	 *         object
	 */
	public Object update() {
		try {
			AuthParams.setRegistryCredentials();
			String finalURL = generateURL(apiName, params);

			URL obj = new URL(finalURL);
			SetHttpConnection setCon = new SetHttpConnection();
			HttpsURLConnection con = setCon.getConnection(obj);
			con.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY,
					AuthParams.getUserToken());
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

	private Future<Object> getDeviceAsync() {
		return CompletableFuture.supplyAsync(this::get);
	}

	public String[] asyncGetDevice(String apiName, String params) throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		Future<Object> future = getDeviceAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> createObjectAsync() {
		return CompletableFuture.supplyAsync(this::post);
	}

	public String[] asyncCreate(String apiName, String params, String body) throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		this.body = body;
		Future<Object> future = createObjectAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> bindDeviceToGatewayObjectAsync() {
		return CompletableFuture.supplyAsync(this::post);
	}

	public String[] asyncBindDeviceToGateway(String apiName, String params, String body) throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		this.body = body;
		Future<Object> future = bindDeviceToGatewayObjectAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> unBindDeviceFromGatewayObjectAsync() {
		return CompletableFuture.supplyAsync(this::post);
	}

	public String[] asyncUnbindDeviceFromGateway(String apiName, String params, String body)
			throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		this.body = body;
		Future<Object> future = unBindDeviceFromGatewayObjectAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> sendCommandToDeviceAsync() {
		return CompletableFuture.supplyAsync(this::post);
	}

	public String[] asyncSendCommandToDevice(String apiName, String params, String body) throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		this.body = body;
		Future<Object> future = sendCommandToDeviceAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> modifyCloudToDeviceConfigAsync() {
		return CompletableFuture.supplyAsync(this::post);
	}

	public String[] asyncModifyCloudToDeviceConfig(String apiName, String params, String body)
			throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		this.body = body;
		Future<Object> future = modifyCloudToDeviceConfigAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> listDeviceStatesAsync() {
		return CompletableFuture.supplyAsync(this::get);
	}

	public String[] asyncListDeviceStates(String apiName, String params)
			throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		Future<Object> future = listDeviceStatesAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> deleteObjectAsync() {
		return CompletableFuture.supplyAsync(this::delete);
	}

	public String[] asyncDelete(String apiName, String params) throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		Future<Object> future = deleteObjectAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> updateObjectAsync() {
		return CompletableFuture.supplyAsync(this::update);
	}

	public String[] asyncUpdate(String apiName, String params, String body) throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		this.body = body;
		Future<Object> future = updateObjectAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> listObjectAsync() {
		return CompletableFuture.supplyAsync(this::get);
	}

	public String[] asyncListDevices(String apiName, String params) throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		Future<Object> future = listObjectAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> listDeviceConfigVersionsAsync() {
		return CompletableFuture.supplyAsync(this::get);
	}

	public String[] asyncListDeviceConfigVersions(String apiName, String params)
			throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		Future<Object> future = listDeviceConfigVersionsAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	// Registry Apis

	private Future<Object> getRegistryAsync() {
		return CompletableFuture.supplyAsync(this::get);
	}

	public String[] asyncGetRegistry(String apiName, String params) throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		Future<Object> future = getRegistryAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> createRegistryAsync() {
		return CompletableFuture.supplyAsync(this::post);
	}

	public String[] asyncCreateDeviceRegistry(String apiName, String params, String body, boolean isAdmin)
			throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		this.body = body;
		this.isAdmin = isAdmin;
		Future<Object> future = createRegistryAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> updateRegistryAsync() {
		return CompletableFuture.supplyAsync(this::update);
	}

	public String[] asyncUpdateDeviceRegistry(String apiName, String params, String body)
			throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		this.body = body;
		Future<Object> future = updateRegistryAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> deleteRegistryAsync() {
		return CompletableFuture.supplyAsync(this::delete);
	}

	public String[] asyncDeleteDeviceRegistry(String apiName, String params, String body, boolean isAdmin)
			throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		this.body = body;
		this.isAdmin = isAdmin;
		Future<Object> future = deleteRegistryAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

	private Future<Object> listDeviceRegistriesAsync() {
		return CompletableFuture.supplyAsync(this::get);
	}

	public String[] asyncListDeviceRegistries(String apiName, String params, boolean isAdmin)
			throws InterruptedException {
		this.apiName = apiName;
		this.params = params;
		this.isAdmin = isAdmin;
		Future<Object> future = listDeviceRegistriesAsync();
		try {
			future.get(15000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return responseArray;
	}

}
