package com.trevorism.threshold;

import com.trevorism.http.headers.HeadersHttpClient;
import com.trevorism.http.headers.HeadersJsonHttpClient;
import com.trevorism.http.util.ResponseUtils;
import com.trevorism.threshold.model.Threshold;
import com.trevorism.threshold.strategy.ThresholdMetStrategy;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.List;

public class PingingThresholdClient implements ThresholdClient {

    private static final long DEFAULT_TIMEOUT_MILLIS = 15000;

    private final FastThresholdClient delegate;
    private final long pingTimeout;
    private final HeadersHttpClient headersClient = new HeadersJsonHttpClient();

    public PingingThresholdClient() {
        this(DEFAULT_TIMEOUT_MILLIS);
    }

    public PingingThresholdClient(long pingTimeout) {
        delegate = new FastThresholdClient();
        this.pingTimeout = pingTimeout;
    }

    @Override
    public List<Threshold> list() {
        ping();
        return delegate.list();
    }

    @Override
    public Threshold get(long id) {
        ping();
        return delegate.get(id);
    }

    @Override
    public Threshold create(Threshold threshold) {
        ping();
        return delegate.create(threshold);
    }

    @Override
    public Threshold update(long id, Threshold threshold) {
        ping();
        return delegate.update(id, threshold);
    }

    @Override
    public Threshold delete(long id) {
        ping();
        return delegate.delete(id);
    }

    @Override
    public List<Threshold> getByName(String name) {
        ping();
        return delegate.getByName(name);
    }

    @Override
    public boolean evaluate(String name, Double value, ThresholdMetStrategy strategy) {
        ping();
        return delegate.evaluate(name, value, strategy);
    }

    public void ping() {
        try {
            //ping the API to wake it up since it is not always on
            sendPingRequest();
        } catch (Exception e) {
            try {
                Thread.sleep(pingTimeout);
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
