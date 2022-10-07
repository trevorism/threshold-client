package com.trevorism.threshold.strategy;

import com.trevorism.TestErrorClient;
import com.trevorism.model.TestError;
import com.trevorism.threshold.model.Threshold;

import java.util.List;

public class ErrorWhenThresholdMet implements ThresholdMetStrategy{

    TestErrorClient testErrorClient = new TestErrorClient();

    @Override
    public boolean performWork(List<Threshold> metThresholds, Double value) {
        if (metThresholds == null || metThresholds.size() == 0 || value == null) {
            return false;
        }

        testErrorClient.addTestError(createTestError(metThresholds, value));

        return true;
    }

    private TestError createTestError(List<Threshold> metThresholds, Double value) {
        TestError testError = new TestError();
        testError.setSource("threshold");

        StringBuilder sb = new StringBuilder("The following thresholds were met:");
        for (Threshold metThreshold : metThresholds) {
            sb.append("\n").append(metThreshold);
        }

        testError.setMessage(sb.toString());
        return testError;
    }
}
