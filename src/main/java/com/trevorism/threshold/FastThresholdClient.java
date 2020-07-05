package com.trevorism.threshold;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.trevorism.http.headers.HeadersHttpClient;
import com.trevorism.http.headers.HeadersJsonHttpClient;
import com.trevorism.http.util.ResponseUtils;
import com.trevorism.secure.PasswordProvider;
import com.trevorism.threshold.model.Threshold;
import com.trevorism.threshold.strategy.ThresholdMetStrategy;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastThresholdClient implements ThresholdClient {

    private HeadersHttpClient headersClient = new HeadersJsonHttpClient();
    private final PasswordProvider passwordProvider = new PasswordProvider();
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();

    @Override
    public List<Threshold> list() {
        CloseableHttpResponse response = null;
        try {
            Map<String, String> headersMap = createHeaderMap();
            response = headersClient.get(THRESHOLD_BASE_URL + "/threshold", headersMap);
            return getThresholdsListFromResponse(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ResponseUtils.closeSilently(response);
        }
    }

    @Override
    public Threshold get(long id) {
        CloseableHttpResponse response = null;
        try {
            Map<String, String> headersMap = createHeaderMap();
            response = headersClient.get(THRESHOLD_BASE_URL + "/threshold/" + id, headersMap);
            return getThresholdFromResponse(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ResponseUtils.closeSilently(response);
        }
    }

    @Override
    public Threshold create(Threshold threshold) {
        CloseableHttpResponse response = null;
        try {
            Map<String, String> headersMap = createHeaderMap();
            response = headersClient.post(THRESHOLD_BASE_URL + "/threshold", gson.toJson(threshold), headersMap);
            return getThresholdFromResponse(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ResponseUtils.closeSilently(response);
        }
    }

    @Override
    public Threshold update(long id, Threshold threshold) {
        CloseableHttpResponse response = null;
        try {
            Map<String, String> headersMap = createHeaderMap();
            response = headersClient.put(THRESHOLD_BASE_URL + "/threshold/" + id, gson.toJson(threshold), headersMap);
            return getThresholdFromResponse(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ResponseUtils.closeSilently(response);
        }
    }

    @Override
    public Threshold delete(long id) {
        CloseableHttpResponse response = null;
        try {
            Map<String, String> headersMap = createHeaderMap();
            response = headersClient.delete(THRESHOLD_BASE_URL + "/threshold/" + id, headersMap);
            return getThresholdFromResponse(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ResponseUtils.closeSilently(response);
        }
    }

    @Override
    public List<Threshold> getByName(String name) {
        CloseableHttpResponse response = null;
        try {
            Map<String, String> headersMap = createHeaderMap();
            response = headersClient.get(THRESHOLD_BASE_URL + "/evaluation/" + name, headersMap);
            return getThresholdsListFromResponse(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ResponseUtils.closeSilently(response);
        }
    }

    @Override
    public boolean evaluate(String name, Double value, ThresholdMetStrategy strategy) {
        CloseableHttpResponse response = null;
        try {
            Map<String, String> headersMap = createHeaderMap();
            response = headersClient.get(THRESHOLD_BASE_URL + "/evaluation/" + name + "/" + value, headersMap);
            List<Threshold> thresholds = getThresholdsListFromResponse(response);
            return strategy.performWork(thresholds, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ResponseUtils.closeSilently(response);
        }
    }

    private Map<String, String> createHeaderMap() {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put(PasswordProvider.AUTHORIZATION_HEADER, passwordProvider.getPassword());
        return headersMap;
    }

    private List<Threshold> getThresholdsListFromResponse(CloseableHttpResponse response) {
        String json = ResponseUtils.getEntity(response);
        return gson.fromJson(json, TypeToken.getParameterized(List.class, Threshold.class).getType());
    }

    private Threshold getThresholdFromResponse(CloseableHttpResponse response) {
        String json = ResponseUtils.getEntity(response);
        return gson.fromJson(json, Threshold.class);
    }
}
