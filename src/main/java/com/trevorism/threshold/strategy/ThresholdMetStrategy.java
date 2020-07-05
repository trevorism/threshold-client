package com.trevorism.threshold.strategy;

import com.trevorism.threshold.model.Threshold;

import java.util.List;

public interface ThresholdMetStrategy {

    /**
     * Does some work if the threshold has been met
     *
     * @param metThresholds The thresholds met upon which we operate
     * @return true if successful, false otherwise
     */
    boolean performWork(List<Threshold> metThresholds, Double value);

}
