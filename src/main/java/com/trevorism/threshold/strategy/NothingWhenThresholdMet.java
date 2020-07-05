package com.trevorism.threshold.strategy;

import com.trevorism.threshold.model.Threshold;

import java.util.List;

public class NothingWhenThresholdMet implements ThresholdMetStrategy {
    @Override
    public boolean performWork(List<Threshold> metThreshold, Double value) {
        return metThreshold != null && metThreshold.size() != 0 && value != null;
    }
}
