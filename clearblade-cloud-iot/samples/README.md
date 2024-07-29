# ClearBlade IoT Core Platform Java samples

## Quickstart

1. Migrate your Google IoT Core registries and devices into ClearBlade IoT Core from the [ClearBlade Migration from Google IoT Core section](https://clearblade.atlassian.net/wiki/spaces/IC/pages/2202664969/Migration+from+Google+IoT+Core) or
 [Creating registries and devices section](https://clearblade.atlassian.net/wiki/spaces/IC/pages/2202206388/Creating+registries+and+devices).

2. [Add service accounts to a project](https://clearblade.atlassian.net/wiki/spaces/IC/pages/2240675843/Add+service+accounts+to+a+project) and download the JSON file with your service
   account’s credentials.

3. Use the following to set your environment variables keys:

   ```
    export CLEARBLADE_CONFIGURATION=/path/to/configuration_file.json

   ```

   Optional

   ```
    export CLEARBLADE_REGISTRY=[your-registry]
    export CLEARBLADE_REGION=[your-region]
   ```

## Note about performance:
    
By default, calls to some SDK functions cause a REST request to be sent to acquire the registry API keys found on the IoTCore UI Registry Details page. Those keys are cached for subsequent operations to improve performance. However, these caches do not persist if the application is stopped and restarted, as would be the case with typical serverless functions (e.g., Google Cloud Functions, AWS Lambda, etc.). To improve those functions' performance, the REST call can be prevented by passing the API keys as environment variables:
    
    ```
    REGISTRY_URL: string
    REGISTRY_SYSKEY: string
    REGISTRY_TOKEN: string
    ```
