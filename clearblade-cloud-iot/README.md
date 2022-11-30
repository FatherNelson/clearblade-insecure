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
mvn exec:java -Dexec.cleanupDaemonThreads=false -Dexec.mainClass=<<Name of sample file you want to run>> -DprojectName=<<Your Project Name>>  -Dlocation=<<location name>> -DregistryName=<<Registry Name>> -DdeviceName=<<Device Name>>
Parameters to be sent will change, as the sample changes. One has to refer to list of samples to ensure which sample accepts which parameters.
