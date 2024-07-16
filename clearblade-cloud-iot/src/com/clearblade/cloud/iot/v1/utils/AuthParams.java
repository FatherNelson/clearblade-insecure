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

package com.clearblade.cloud.iot.v1.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.clearblade.cloud.iot.v1.exception.ApplicationException;
import org.json.simple.parser.ParseException;
import org.junit.platform.commons.util.StringUtils;

public class AuthParams {
    static Logger log = Logger.getLogger(AuthParams.class.getName());

    private static HashMap<String, String> cachedResponse = new HashMap<>();

    private String adminSystemKey = null;
    private String project = null;
    private String baseURL = null;
    private String adminToken = null;
    private String userSystemKey = null;
    private String userToken = null;
    private String apiBaseURL = null;

    public String getAdminSystemKey() {
        return adminSystemKey;
    }

    public String getProject() {
        return project;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public String getUserSystemKey() {
        return userSystemKey;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getApiBaseURL() {
        return apiBaseURL;
    }

    public void setAdminCredentials() throws ApplicationException, IOException {
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

    @SuppressWarnings("unchecked")
    public void setRegistryCredentials(String project, String registry, String location) throws ApplicationException {

        if (cachedResponse.containsKey(location + "-" + registry)) {
            String responseMessage = cachedResponse.get(location + "-" + registry);
            JSONParser responseParser = new JSONParser();
            JSONObject responseJSONObject;
            Object responseObj = null;
            try {
                responseObj = responseParser.parse(responseMessage);
            } catch (ParseException e) {
                throw new ApplicationException(e);
            }
            if (responseObj != null) {
                responseJSONObject = (JSONObject) responseObj;
                userSystemKey = responseJSONObject.get(Constants.USER_SYSTEM_KEY).toString();
                userToken = responseJSONObject.get(Constants.USER_TOKEN).toString();
                apiBaseURL = responseJSONObject.get(Constants.API_BASE_URL).toString();
            }
            return;
        } else if(StringUtils.isNotBlank(System.getenv(Constants.REGISTRY_URL)) && StringUtils.isNotBlank(System.getenv(Constants.REGISTRY_URL)) && StringUtils.isNotBlank(System.getenv(Constants.REGISTRY_URL))) {
            apiBaseURL = System.getenv(Constants.REGISTRY_URL);
            userSystemKey = System.getenv(Constants.REGISTRY_SYSKEY);
            userToken = System.getenv(Constants.REGISTRY_TOKEN);
            return;
        }
        try {
            setAdminCredentials();
            String finalURL = baseURL.concat(Constants.GET_SYSTEM_CREDENTIALS_EXTENSION)
                    .concat(adminSystemKey)
                    .concat("/getRegistryCredentials");

            JSONObject js = new JSONObject();
            js.put("region", location);
            js.put("registry", registry);
            js.put("project", project);
            BodyPublisher jsonPayload = BodyPublishers.ofString(js.toString());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(finalURL))
                    .method(Constants.HTTP_REQUEST_METHOD_TYPE_POST, jsonPayload)
                    .headers(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE,
                            Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, adminToken,
                            Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE)
                    .build();

            HttpResponse<String> response = HttpClient.newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .send(request, BodyHandlers.ofString());

            int responseCode = response.statusCode();
            String responseMessage = response.body();
            JSONParser responseParser = new JSONParser();
            JSONObject responseJSONObject;
            if (responseCode == 200) {
                if (responseMessage != null && responseMessage.length() > 0) {
                    Object responseObj = responseParser.parse(responseMessage);
                    if (responseObj != null) {
                        responseJSONObject = (JSONObject) responseObj;
                        userSystemKey = responseJSONObject.get(Constants.USER_SYSTEM_KEY).toString();
                        userToken = responseJSONObject.get(Constants.USER_TOKEN).toString();
                        apiBaseURL = responseJSONObject.get(Constants.API_BASE_URL).toString();
                        cachedResponse.put(location + "-" + registry, responseMessage);
                    }
                }
            } else {
                log.log(Level.INFO, () -> "Response code " + responseCode + " received with message::" + responseMessage);
                throw new ApplicationException(responseMessage);
            }
        } catch (Exception ec) {
            log.log(Level.SEVERE, ec.getMessage());
            throw new ApplicationException(ec.getMessage());
        }
    }
}
