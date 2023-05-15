/*
 * Copyright 2023 ClearBlade Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayRequest;
import com.clearblade.cloud.iot.v1.createdevice.CreateDeviceRequest;
import com.clearblade.cloud.iot.v1.deletedevice.DeleteDeviceRequest;
import com.clearblade.cloud.iot.v1.deviceslist.DevicesListRequest;
import com.clearblade.cloud.iot.v1.devicestateslist.ListDeviceStatesRequest;
import com.clearblade.cloud.iot.v1.exception.ApplicationException;
import com.clearblade.cloud.iot.v1.getdevice.GetDeviceRequest;
import com.clearblade.cloud.iot.v1.getdeviceregistry.GetDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.listdeviceconfigversions.ListDeviceConfigVersionsRequest;
import com.clearblade.cloud.iot.v1.modifycloudtodeviceconfig.ModifyCloudToDeviceConfigRequest;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceRequest;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayRequest;
import com.clearblade.cloud.iot.v1.updatedevice.UpdateDeviceRequest;
import com.clearblade.cloud.iot.v1.updatedeviceregistry.UpdateDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.utils.AuthParams;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;
import com.clearblade.cloud.iot.v1.utils.Constants;
import org.json.simple.parser.ParseException;

public class AsyncClient {

    static Logger log = Logger.getLogger(AsyncClient.class.getName());
    private String[] responseArray = new String[3];
    private boolean isAdmin = false;
    private AuthParams authParams = new AuthParams();

    /**
     * Method used to generate URL for apicall
     *
     * @param apiName - path to api
     * @param params  - parameters to be attached to request
     * @return URL formed and to be used
     */
    private String generateURL(AuthParams authParams, String apiName, String params) {

        return authParams.getApiBaseURL().concat(Constants.ENDPOINTPORT).concat(Constants.WEBHOOK).concat(authParams.getUserSystemKey()).concat(apiName).concat("?" + params);
    }

    /**
     * Method used to generate URL for apicall
     *
     * @param apiName - path to api
     * @param params  - parameters to be attached to request
     * @return URL formed and to be used
     */
    private String generateAdminURL(AuthParams authParams, String apiName, String params) {

        return authParams.getBaseURL().concat(Constants.WEBHOOK).concat(authParams.getAdminSystemKey()).concat(apiName).concat("?" + params);
    }

    /**
     * Method used to Calls HTTP Get request
     *
     * @param apiName
     * @return String[] containing responseCode, responseMessage and response object
     * @throws IOException
     * @throws ApplicationException
     */
    public String[] get(String apiName, GetDeviceRequest request) throws IOException, ParseException {
        authParams.setRegistryCredentials(request.getName().getProject(), request.getName().getRegistry(), request.getName().getLocation());
        String finalURL = generateURL(authParams, apiName, request.toString());
        String token = authParams.getUserToken();
        return get(finalURL, token);
    }

    public String[] get(String apiName, String params, DevicesListRequest request) throws IOException, ParseException {
        authParams.setRegistryCredentials(request.getParent().getProject(), request.getParent().getRegistry(), request.getParent().getLocation());
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return get(finalURL, token);
    }

    public String[] get(String apiName, String params, ListDeviceStatesRequest request) throws IOException, ParseException {
        authParams.setRegistryCredentials(request.getName().getProject(), request.getName().getRegistry(), request.getName().getLocation());
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return get(finalURL, token);
    }

    public String[] get(String apiName, String params, GetDeviceRegistryRequest request) {
        authParams.setRegistryCredentials(request.getName().getProject(), request.getName().getRegistry(), request.getName().getLocation());
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return get(finalURL, token);
    }

    public String[] get(String apiName, String params, ListDeviceConfigVersionsRequest request) throws IOException, ParseException {
        authParams.setRegistryCredentials(request.getName().getProject(), request.getName().getRegistry(), request.getName().getLocation());
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return get(finalURL, token);
    }

    public String[] get(String finalURL, String token) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(finalURL)).headers(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE, Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token, Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE).GET().build();

            CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder().build().sendAsync(request, HttpResponse.BodyHandlers.ofString());

            HttpResponse<String> httpResponse = response.get();
            responseArray[0] = String.valueOf(httpResponse.statusCode());
            responseArray[1] = "";
            responseArray[2] = httpResponse.body();

        } catch (ApplicationException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (InterruptedException ex) {
            log.log(Level.SEVERE, ex.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception ec) {
            log.log(Level.SEVERE, ec.getMessage());
        }
        return responseArray;
    }

    public String[] post(String apiName, String params, String body, CreateDeviceRequest request) throws IOException, ParseException {
        authParams.setRegistryCredentials(request.getParent().getProject(), request.getParent().getRegistry(), request.getParent().getLocation());
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return post(finalURL, body, token);
    }

    public String[] post(String apiName, String params, String body, SendCommandToDeviceRequest request) throws IOException, ParseException {
        String finalURL = "";
        String token = "";
        authParams.setRegistryCredentials(request.getDeviceName().getProject(), request.getDeviceName().getRegistry(), request.getDeviceName().getLocation());
        finalURL = generateURL(authParams, apiName, params);
        token = authParams.getUserToken();
        return post(finalURL, body, token);
    }

    public String[] post(String apiName, String params, String body, BindDeviceToGatewayRequest request) throws IOException, ParseException {
        authParams.setRegistryCredentials(request.getParent().getProject(), request.getParent().getRegistry(), request.getParent().getLocation());
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return post(finalURL, body, token);
    }

    public String[] post(String apiName, String params, String body, ModifyCloudToDeviceConfigRequest request) throws IOException, ParseException {
        authParams.setRegistryCredentials(request.getDeviceName().getProject(), request.getDeviceName().getRegistry(), request.getDeviceName().getLocation());
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return post(finalURL, body, token);
    }

    public String[] post(String apiName, String params, String body, UnbindDeviceFromGatewayRequest request) throws IOException, ParseException {
        authParams.setRegistryCredentials(request.getParent().getProject(), request.getParent().getRegistry(), request.getParent().getLocation());
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return post(finalURL, body, token);
    }

    /**
     * Method used to call HTTP Post request
     *
     * @param body
     * @return String[] containing responseCode, responseMessage and response object
     * @throws IOException
     * @throws ApplicationException
     */
    public String[] post(String finalURL, String body, String token) {
        try {
            BodyPublisher jsonPayload = BodyPublishers.ofString(body);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(finalURL)).method(Constants.HTTP_REQUEST_METHOD_TYPE_POST, jsonPayload).headers(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE, Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token, Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE).build();

            CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).connectTimeout(Duration.ofSeconds(20)).build().sendAsync(request, HttpResponse.BodyHandlers.ofString());

            HttpResponse<String> httpResponse = response.get();
            responseArray[0] = String.valueOf(httpResponse.statusCode());
            responseArray[1] = "";
            responseArray[2] = httpResponse.body();

        } catch (ApplicationException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (InterruptedException ex) {
            log.log(Level.SEVERE, ex.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception ec) {
            log.log(Level.SEVERE, ec.getMessage());
        }

        return responseArray;

    }

    public String[] delete(String apiName, String params, DeleteDeviceRequest request) throws IOException, ParseException {
        String finalURL = "";
        String token = "";
        if (isAdmin) {
            authParams.setAdminCredentials();
            finalURL = generateAdminURL(authParams, apiName, params);
            token = authParams.getAdminToken();
        } else {
            authParams.setRegistryCredentials(request.getName().getProject(), request.getName().getRegistry(), request.getName().getLocation());
            finalURL = generateURL(authParams, apiName, params);
            token = authParams.getUserToken();
        }
        return delete(finalURL, token);
    }

    /**
     * Method used to call HTTP delete request
     *
     * @return String[] containing responseCode, responseMessage and response object
     * @throws IOException
     * @throws ApplicationException
     */
    public String[] delete(String finalURL, String token) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(finalURL)).headers(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE, Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token, Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE).DELETE().build();

            CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder().build().sendAsync(request, HttpResponse.BodyHandlers.ofString());

            HttpResponse<String> httpResponse = response.get();
            responseArray[0] = String.valueOf(httpResponse.statusCode());
            responseArray[1] = "";
            responseArray[2] = httpResponse.body();

        } catch (ApplicationException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (InterruptedException ex) {
            log.log(Level.SEVERE, ex.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception ec) {
            log.log(Level.SEVERE, ec.getMessage());
        }

        return responseArray;

    }

    public String[] update(String apiName, String params, String body, UpdateDeviceRegistryRequest request) throws IOException, ParseException {
        authParams.setRegistryCredentials(request.getParent().getProject(), request.getParent().getRegistry(), request.getParent().getLocation());
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return update(finalURL, body, token);
    }

    public String[] update(String apiName, String params, String body, UpdateDeviceRequest request) throws IOException, ParseException {
        String finalURL = "";
        String token = "";
        authParams.setRegistryCredentials(request.getDeviceName().getProject(), request.getDeviceName().getRegistry(), request.getDeviceName().getLocation());
        finalURL = generateURL(authParams, apiName, params);
        token = authParams.getUserToken();
        return update(finalURL, body, token);
    }

    /**
     * Method used to call HTTP Patch request
     *
     * @param body
     * @return String[] Object containing responseCode, responseMessage and response
     * object
     */
    public String[] update(String finalURL, String body, String token) {
        try {
            BodyPublisher jsonPayload = BodyPublishers.ofString(body);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(finalURL)).method(Constants.HTTP_REQUEST_METHOD_TYPE_PATCH, jsonPayload).headers(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE, Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token, Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE).build();

            CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder().build().sendAsync(request, HttpResponse.BodyHandlers.ofString());

            HttpResponse<String> httpResponse = response.get();
            responseArray[0] = String.valueOf(httpResponse.statusCode());
            responseArray[1] = "";
            responseArray[2] = httpResponse.body();

        } catch (ApplicationException e) {
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

    public String[] asyncCreateDeviceRegistry(String apiName, String params, String body, boolean isAdmin) throws IOException {
        String finalURL = "";
        String token = "";
        if (isAdmin) {
            authParams.setAdminCredentials();
            finalURL = generateAdminURL(authParams, apiName, params);
            token = authParams.getAdminToken();
        }
        return post(finalURL, body, token);
    }


    public String[] asyncDeleteDeviceRegistry(String apiName, String params, boolean isAdmin) throws IOException {
        authParams.setAdminCredentials();
        String finalURL = generateAdminURL(authParams, apiName, params);
        String token = authParams.getAdminToken();
        return this.delete(finalURL, token);
    }


    public String[] asyncListDeviceRegistries(String apiName, String params, boolean isAdmin) throws IOException {
        String finalURL = "";
        String token = "";
        if (isAdmin) {
            authParams.setAdminCredentials();
            finalURL = generateAdminURL(authParams, apiName, params);
            token = authParams.getAdminToken();
        }
        return this.get(finalURL, token);
    }

}
