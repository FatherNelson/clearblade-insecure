# Java Samples

This folder contains Java samples that demonstrate an overview of the
ClearBlade IoT Core platform.

## Quickstart

1. From the [ClearBlade Migration from Google IoT Core section](https://clearblade.atlassian.net/wiki/spaces/IC/pages/2202664969/Migration+from+Google+IoT+Core)
   migrate your existing Google IoT Core registries and/or devices into ClearBlade IoT Core or using the
   ClearBlade IoT Core Console, [create a device registry and devices](https://clearblade.atlassian.net/wiki/spaces/IC/pages/2202206388/Creating+registries+and+devices).

2. [Add service accounts to a project](https://clearblade.atlassian.net/wiki/spaces/IC/pages/2240675843/Add+service+accounts+to+a+project) and download the json file with your service
   account’s credentials.

3. Use the following to set your environment variables keys:

   ```
    export CLEARBLADE_CONFIGURATION=/path/to/file.json
    export CLEARBLADE_REGISTRY=[your-registry]
    export CLEARBLADE_REGION=[your-region]
   ```
