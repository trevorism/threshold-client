package com.trevorism.threshold;

import com.trevorism.threshold.model.Threshold;

import java.util.List;

public interface ThresholdClient {

    String THRESHOLD_BASE_URL = "https://threshold-dot-trevorism-gcloud.appspot.com";

    List<Threshold> list();
    Threshold get(long id);
    Threshold create(Threshold threshold);
    Threshold update(long id, Threshold threshold);
    Threshold delete(long id);

    List<Threshold> getByName(String name);
    List<Threshold> evaluate(String name, Double value);

}
