package com.trevorism.threshold;

import com.trevorism.threshold.model.Threshold;
import com.trevorism.threshold.strategy.ThresholdMetStrategy;

import java.util.List;

public interface ThresholdClient {

    String THRESHOLD_BASE_URL = "https://threshold.datastore.trevorism.com";

    List<Threshold> list();
    Threshold get(long id);
    Threshold create(Threshold threshold);
    Threshold update(long id, Threshold threshold);
    Threshold delete(long id);

    List<Threshold> getByName(String name);
    boolean evaluate(String name, Double value, ThresholdMetStrategy strategy);

}
