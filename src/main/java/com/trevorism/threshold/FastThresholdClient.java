package com.trevorism.threshold;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.trevorism.https.DefaultSecureHttpClient;
import com.trevorism.https.SecureHttpClient;
import com.trevorism.threshold.model.Threshold;
import com.trevorism.threshold.strategy.ThresholdMetStrategy;

import java.util.List;

public class FastThresholdClient implements ThresholdClient {

    private SecureHttpClient secureHttpClient;
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();

    public FastThresholdClient() {
        secureHttpClient = new DefaultSecureHttpClient();
    }

    public FastThresholdClient(SecureHttpClient secureHttpClient) {
        this.secureHttpClient = secureHttpClient;
    }


    @Override
    public List<Threshold> list() {
        String json = secureHttpClient.get(THRESHOLD_BASE_URL + "/threshold");
        return getThresholdsListFromResponse(json);
    }

    @Override
    public Threshold get(long id) {
        String json = secureHttpClient.get(THRESHOLD_BASE_URL + "/threshold/" + id);
        return getThresholdFromResponse(json);
    }

    @Override
    public Threshold create(Threshold threshold) {
        String json = secureHttpClient.post(THRESHOLD_BASE_URL + "/threshold", gson.toJson(threshold));
        return getThresholdFromResponse(json);
    }

    @Override
    public Threshold update(long id, Threshold threshold) {
        String json = secureHttpClient.put(THRESHOLD_BASE_URL + "/threshold/" + id, gson.toJson(threshold));
        return getThresholdFromResponse(json);
    }

    @Override
    public Threshold delete(long id) {
        String json = secureHttpClient.delete(THRESHOLD_BASE_URL + "/threshold/" + id);
        return getThresholdFromResponse(json);
    }

    @Override
    public List<Threshold> getByName(String name) {
        String json = secureHttpClient.get(THRESHOLD_BASE_URL + "/evaluation/" + name);
        return getThresholdsListFromResponse(json);
    }

    @Override
    public boolean evaluate(String name, Double value, ThresholdMetStrategy strategy) {
        String json = secureHttpClient.get(THRESHOLD_BASE_URL + "/evaluation/" + name + "/" + value);
        List<Threshold> thresholds = getThresholdsListFromResponse(json);
        return strategy.performWork(thresholds, value);
    }

    private List<Threshold> getThresholdsListFromResponse(String json) {
        return gson.fromJson(json, TypeToken.getParameterized(List.class, Threshold.class).getType());
    }

    private Threshold getThresholdFromResponse(String json) {
        return gson.fromJson(json, Threshold.class);
    }
}
