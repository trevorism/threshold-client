package com.trevorism.threshold;

import com.trevorism.https.AppClientSecureHttpClient;
import com.trevorism.https.SecureHttpClient;

public class PingingThresholdClient extends FastThresholdClient {

    public PingingThresholdClient() {
        this(new AppClientSecureHttpClient());
    }

    public PingingThresholdClient(SecureHttpClient httpClient) {
        super(httpClient);
        ping();
    }

}
