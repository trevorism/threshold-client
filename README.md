# threshold-client
![Jenkins](https://img.shields.io/jenkins/build/http/trevorism-build.eastus.cloudapp.azure.com/threshold-client)
![Jenkins Coverage](https://img.shields.io/jenkins/coverage/jacoco/http/trevorism-build.eastus.cloudapp.azure.com/threshold-client)
![GitHub last commit](https://img.shields.io/github/last-commit/trevorism/threshold-client)
![GitHub language count](https://img.shields.io/github/languages/count/trevorism/threshold-client)
![GitHub top language](https://img.shields.io/github/languages/top/trevorism/threshold-client)


Java client for working with threshold to [trevorism threshold](https://github.com/trevorism/threshold)

Current version: 0.2.0

## How to Use 

```java
ThresholdClient client = new PingingThresholdClient();
client.evaluate("thresholdName", 2000, new AlertWhenThresholdMet());
```

## How to Build
`gradle clean build`