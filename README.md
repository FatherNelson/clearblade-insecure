# ClearBlade Internet of Things (IoT) Core Client for Java

Java library and samples for [ClearBlade Internet of Things (IoT) Core][product-docs].

- [Product Documentation][product-docs]
- [Client Library Documentation][javasdk]

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

4. Use the HTTP or MQTT samples in the [samples](./clearblade-cloud-iot/samples) folder.

## ClearBlade IoT Core Samples Java

The sample apps demonstrates registry and device creation for ClearBlade IoT Core. The [samples](./clearblade-cloud-iot/samples) folder contains all the Java samples that demonstrate an overview of the ClearBlade IoT Core platform.

Note that before you can run the sample, you must configure your development environment
as described in the Qucikstart above or the [samples](./clearblade-cloud-iot/samples) folder.

Before running the samples, you can set the `CLEARBLADE_CONFIGURATION`, `CLEARBLADE_REGISTRY` and
`CLEARBLADE_REGION` environment variables to avoid passing them to
the sample every time you run it.

## Setup

Run the following command to install the libraries and build the sample with
Maven:

    mvn clean install

## Running the sample

Quick Start
set an environment variable CLEARBLADE_CONFIGURATION which should point to your service account json file.
set an environment variable CLEARBLADE_REGISTRY which should be the name of registry you want to work upon.
set an environment variable CLEARBLADE_REGION which should point to the region.
Installation
Install this library as a MAVEN dependency in your project. Alternatively, you can get jar file of this library and add it as an External Jar in your project’s build path and can start using the same.
Code samples and snippets
Code samples and snippets live in the samples folder.
Test Cases
Test cases live in the test folder.
Supported JAVA Version
Minimum JAVA 11 version is required to use current library version. Clearblade JAVA SDK library is built using MAVEN version 4.0.0 and Junit Jupiter API version 5.8.2 for test cases to run.
Executing Samples
You have to use following maven command from command prompt :-
mvn exec:java -Dexec.cleanupDaemonThreads=false -Dexec.mainClass=<<Name of sample file you want to run>> -DprojectName=<<Your Project Name>> -Dlocation=<<location name>> -DregistryName=<<Registry Name>> -DdeviceName=<<Device Name>>
Parameters to be sent will change, as the sample changes. One has to refer to list of samples to ensure which sample accepts which parameters.

[product-docs]: https://clearblade.atlassian.net/wiki/spaces/IC/overview
[javasdk]: https://clearblade.atlassian.net/wiki/spaces/IC/pages/2231173185/Java
