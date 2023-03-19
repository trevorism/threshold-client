package com.trevorism.threshold;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.trevorism.http.HttpClient;
import com.trevorism.https.DefaultSecureHttpClient;
import com.trevorism.threshold.model.Threshold;
import com.trevorism.threshold.strategy.ThresholdMetStrategy;

import java.util.List;

public class FastThresholdClient implements ThresholdClient {

    private static final long DEFAULT_TIMEOUT_MILLIS = 15000;
    private HttpClient httpClient;
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();

    public FastThresholdClient() {
        httpClient = new DefaultSecureHttpClient();
    }

    public FastThresholdClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public List<Threshold> list() {
        String json = httpClient.get(THRESHOLD_BASE_URL + "/threshold");
        return getThresholdsListFromResponse(json);
    }

    @Override
    public Threshold get(long id) {
        String json = httpClient.get(THRESHOLD_BASE_URL + "/threshold/" + id);
        return getThresholdFromResponse(json);
    }

    @Override
    public Threshold create(Threshold threshold) {
        String json = httpClient.post(THRESHOLD_BASE_URL + "/threshold", gson.toJson(threshold));
        return getThresholdFromResponse(json);
    }

    @Override
    public Threshold update(long id, Threshold threshold) {
        String json = httpClient.put(THRESHOLD_BASE_URL + "/threshold/" + id, gson.toJson(threshold));
        return getThresholdFromResponse(json);
    }

    @Override
    public Threshold delete(long id) {
        String json = httpClient.delete(THRESHOLD_BASE_URL + "/threshold/" + id);
        return getThresholdFromResponse(json);
    }

    @Override
    public List<Threshold> getByName(String name) {
        String json = httpClient.get(THRESHOLD_BASE_URL + "/evaluation/" + name);
        return getThresholdsListFromResponse(json);
    }

    @Override
    public boolean evaluate(String name, Double value, ThresholdMetStrategy strategy) {
        String json = httpClient.get(THRESHOLD_BASE_URL + "/evaluation/" + name + "/" + value);
        List<Threshold> thresholds = getThresholdsListFromResponse(json);
        return strategy.performWork(thresholds, value);
    }

    @Override
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
        String pong = httpClient.get(THRESHOLD_BASE_URL + "/ping");
        throwPingExceptionIfResponseNotPong(pong);
    }

    private void throwPingExceptionIfResponseNotPong(String pong) {
        if (!"pong".equals(pong))
            throw new RuntimeException("Unable to ping " + THRESHOLD_BASE_URL);
    }

    private List<Threshold> getThresholdsListFromResponse(String json) {
        return gson.fromJson(json, TypeToken.getParameterized(List.class, Threshold.class).getType());
    }

    private Threshold getThresholdFromResponse(String json) {
        return gson.fromJson(json, Threshold.class);
    }
}
