package com.clearblade.cloud.iot.v1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
import com.clearblade.cloud.iot.v1.utils.Constants;
import org.json.simple.parser.ParseException;

public class AsyncClient {

    static Logger log = Logger.getLogger(AsyncClient.class.getName());
    private String[] responseArray = new String[3];
    private boolean isAdmin = false;
    private AuthParams authParams = new AuthParams();
    private ExecutorService executor = Executors.newFixedThreadPool(5);

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
        try {
            authParams.setRegistryCredentials(request.getName().getProject(), request.getName().getRegistry(), request.getName().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateURL(authParams, apiName, request.toString());
        String token = authParams.getUserToken();
        return get(finalURL, token);
    }

    public String[] get(String apiName, String params, DevicesListRequest request) throws IOException, ParseException {
        try {
            authParams.setRegistryCredentials(request.getParent().getProject(), request.getParent().getRegistry(), request.getParent().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return get(finalURL, token);
    }

    public String[] get(String apiName, String params, ListDeviceStatesRequest request) throws IOException, ParseException {
        try {
            authParams.setRegistryCredentials(request.getName().getProject(), request.getName().getRegistry(), request.getName().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return get(finalURL, token);
    }

    public String[] get(String apiName, String params, GetDeviceRegistryRequest request) {
        try {
            authParams.setRegistryCredentials(request.getName().getProject(), request.getName().getRegistry(), request.getName().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return get(finalURL, token);
    }

    public String[] get(String apiName, String params, ListDeviceConfigVersionsRequest request) throws IOException, ParseException {
        try {
            authParams.setRegistryCredentials(request.getName().getProject(), request.getName().getRegistry(), request.getName().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return get(finalURL, token);
    }

    private String[] get(String finalURL, String token) {
        try {
            URL url = new URL(finalURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
            connection.setRequestMethod("GET");
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token);
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);

            Future<String[]> future = executor.submit(() -> {
                int responseCode = connection.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                responseArray[0] = String.valueOf(responseCode);
                responseArray[1] = "";
                responseArray[2] = content.toString();
                return responseArray;
            });

            return future.get();

        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            throw new ApplicationException(e);
        }
    }

    public String[] post(String apiName, String params, String body, CreateDeviceRequest request) throws IOException, ParseException {
        try {
            authParams.setRegistryCredentials(request.getParent().getProject(), request.getParent().getRegistry(), request.getParent().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return post(finalURL, body, token);
    }

    public String[] post(String apiName, String params, String body, SendCommandToDeviceRequest request) throws IOException, ParseException {
        try {
            authParams.setRegistryCredentials(request.getDeviceName().getProject(), request.getDeviceName().getRegistry(), request.getDeviceName().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return post(finalURL, body, token);
    }

    public String[] post(String apiName, String params, String body, BindDeviceToGatewayRequest request) throws IOException, ParseException {
        try {
            authParams.setRegistryCredentials(request.getParent().getProject(), request.getParent().getRegistry(), request.getParent().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return post(finalURL, body, token);
    }

    public String[] post(String apiName, String params, String body, ModifyCloudToDeviceConfigRequest request) throws IOException, ParseException {
        try {
            authParams.setRegistryCredentials(request.getDeviceName().getProject(), request.getDeviceName().getRegistry(), request.getDeviceName().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return post(finalURL, body, token);
    }

    public String[] post(String apiName, String params, String body, UnbindDeviceFromGatewayRequest request) throws IOException, ParseException {
        try {
            authParams.setRegistryCredentials(request.getParent().getProject(), request.getParent().getRegistry(), request.getParent().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
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
    private String[] post(String finalURL, String body, String token) {
        try {
            URL url = new URL(finalURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
            connection.setRequestMethod("POST");
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token);
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY, Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
            connection.setDoOutput(true);

            Future<String[]> future = executor.submit(() -> {
                try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
                    out.writeBytes(body);
                    out.flush();
                }
                int responseCode = connection.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                responseArray[0] = String.valueOf(responseCode);
                responseArray[1] = "";
                responseArray[2] = content.toString();
                return responseArray;
            });

            return future.get();

        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            throw new ApplicationException(e);
        }
    }

    public String[] delete(String apiName, String params, DeleteDeviceRequest request) throws IOException {
        String finalURL = "";
        String token = "";
        if (isAdmin) {
            try {
                authParams.setAdminCredentials();
            } catch (Exception e) {
                throw new ApplicationException(e);
            }
            finalURL = generateAdminURL(authParams, apiName, params);
            token = authParams.getAdminToken();
        } else {
            try {
                authParams.setRegistryCredentials(request.getName().getProject(), request.getName().getRegistry(), request.getName().getLocation());
            } catch (Exception e) {
                throw new ApplicationException(e);
            }
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
        String[] responseArray = new String[3];
        HttpURLConnection connection = null;
        try {
            URL url = new URL(finalURL);
            connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY,
                    Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token);
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY,
                    Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
    
            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
    
            responseArray[0] = String.valueOf(responseCode);
            responseArray[1] = "";
            responseArray[2] = content.toString();
    
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
            throw new ApplicationException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return responseArray;
    }

    public String[] update(String apiName, String params, String body, UpdateDeviceRegistryRequest request) throws IOException, ParseException {
        try {
            authParams.setRegistryCredentials(request.getParent().getProject(), request.getParent().getRegistry(), request.getParent().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
        return update(finalURL, body, token);
    }

    public String[] update(String apiName, String params, String body, UpdateDeviceRequest request) throws IOException, ParseException {
        try {
            authParams.setRegistryCredentials(request.getDeviceName().getProject(), request.getDeviceName().getRegistry(), request.getDeviceName().getLocation());
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateURL(authParams, apiName, params);
        String token = authParams.getUserToken();
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
        String[] responseArray = new String[3];
        HttpURLConnection connection = null;
        try {
            URL url = new URL(finalURL);
            connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
            connection.setRequestMethod("PATCH");
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY,
                    Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_TOKEN_KEY, token);
            connection.setRequestProperty(Constants.HTTP_REQUEST_PROPERTY_ACCEPT_KEY,
                    Constants.HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE);
            connection.setDoOutput(true);
    
            try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
                out.writeBytes(body);
                out.flush();
            }
    
            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
    
            responseArray[0] = String.valueOf(responseCode);
            responseArray[1] = "";
            responseArray[2] = content.toString();
    
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
            throw new ApplicationException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return responseArray;
    }

    // Registry Apis

    public String[] asyncCreateDeviceRegistry(String apiName, String params, String body, boolean isAdmin) throws IOException {
        String finalURL = "";
        String token = "";
        if (isAdmin) {
            try {
                authParams.setAdminCredentials();
            } catch (Exception e) {
                throw new ApplicationException(e);
            }
            finalURL = generateAdminURL(authParams, apiName, params);
            token = authParams.getAdminToken();
        }
        return post(finalURL, body, token);
    }


    public String[] asyncDeleteDeviceRegistry(String apiName, String params, boolean isAdmin) throws IOException {
        try {
            authParams.setAdminCredentials();
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        String finalURL = generateAdminURL(authParams, apiName, params);
        String token = authParams.getAdminToken();
        return this.delete(finalURL, token);
    }


    public String[] asyncListDeviceRegistries(String apiName, String params, boolean isAdmin) throws IOException {
        String finalURL = "";
        String token = "";
        if (isAdmin) {
            try {
                authParams.setAdminCredentials();
            } catch (Exception e) {
                throw new ApplicationException(e);
            }
            finalURL = generateAdminURL(authParams, apiName, params);
            token = authParams.getAdminToken();
        }
        return this.get(finalURL, token);
    }

}
