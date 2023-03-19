package com.trevorism.threshold;

import com.trevorism.http.HttpClient;
import com.trevorism.https.DefaultSecureHttpClient;

public class PingingThresholdClient extends FastThresholdClient {

    public PingingThresholdClient() {
        this(new DefaultSecureHttpClient());
    }

    public PingingThresholdClient(HttpClient httpClient) {
        super(httpClient);
        ping();
    }

}
