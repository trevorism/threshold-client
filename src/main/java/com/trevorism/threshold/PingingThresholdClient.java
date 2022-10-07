package com.trevorism.threshold;

import com.trevorism.http.headers.HeadersHttpClient;
import com.trevorism.http.headers.HeadersJsonHttpClient;
import com.trevorism.http.util.ResponseUtils;
import com.trevorism.https.SecureHttpClient;
import com.trevorism.threshold.model.Threshold;
import com.trevorism.threshold.strategy.ThresholdMetStrategy;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.List;

public class PingingThresholdClient implements ThresholdClient {

    private static final long DEFAULT_TIMEOUT_MILLIS = 15000;

    private final FastThresholdClient delegate;
    private final HeadersHttpClient headersClient = new HeadersJsonHttpClient();

    public PingingThresholdClient() {
        delegate = new FastThresholdClient();
    }

    public PingingThresholdClient(SecureHttpClient secureHttpClient) {
        delegate = new FastThresholdClient(secureHttpClient);
        ping();
    }

    @Override
    public List<Threshold> list() {
        return delegate.list();
    }

    @Override
    public Threshold get(long id) {
        return delegate.get(id);
    }

    @Override
    public Threshold create(Threshold threshold) {
        return delegate.create(threshold);
    }

    @Override
    public Threshold update(long id, Threshold threshold) {
        return delegate.update(id, threshold);
    }

    @Override
    public Threshold delete(long id) {
        return delegate.delete(id);
    }

    @Override
    public List<Threshold> getByName(String name) {
        return delegate.getByName(name);
    }

    @Override
    public boolean evaluate(String name, Double value, ThresholdMetStrategy strategy) {
        return delegate.evaluate(name, value, strategy);
    }

    public void ping() {
        try {
            //ping the API to wake it up since it is not always on
            sendPingRequest();
        } catch (Exception e) {
            try {
                Thread.sleep(DEFAULT_TIMEOUT_MILLIS);
                sendPingRequest();
            } catch (Exception ie) {
                throw new RuntimeException("Interrupted failure", ie);
            }
        }
    }

    private void sendPingRequest() {
        CloseableHttpResponse response = headersClient.get(THRESHOLD_BASE_URL + "/ping", null);
        String pong = ResponseUtils.getEntity(response);
        ResponseUtils.closeSilently(response);
        throwPingExceptionIfResponseNotPong(pong);
    }

    private void throwPingExceptionIfResponseNotPong(String pong) {
        if (!"pong".equals(pong))
            throw new RuntimeException("Unable to ping " + THRESHOLD_BASE_URL);
    }
}
