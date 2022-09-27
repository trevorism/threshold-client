# threshold-client
![Build](https://github.com/trevorism/threshold-client/actions/workflows/build.yml/badge.svg)
![GitHub last commit](https://img.shields.io/github/last-commit/trevorism/threshold-client)
![GitHub language count](https://img.shields.io/github/languages/count/trevorism/threshold-client)
![GitHub top language](https://img.shields.io/github/languages/top/trevorism/threshold-client)

Java client for working with threshold to [trevorism threshold](https://github.com/trevorism/threshold)

Latest [Version](https://github.com/trevorism/threshold-client/releases/latest)

## How to Use 

```java
ThresholdClient client = new PingingThresholdClient();
client.evaluate("thresholdName", 2000, new AlertWhenThresholdMet());
```

## How to Build
`gradle clean build`