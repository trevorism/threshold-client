package com.trevorism.threshold.strategy

import com.trevorism.threshold.EchoSecureHttpClient
import com.trevorism.threshold.model.Threshold
import org.junit.Test

class AlertWhenThresholdMetTest {

    @Test
    void testPerformWork() {
        ThresholdMetStrategy strategy = new AlertWhenThresholdMet(new EchoSecureHttpClient())
        assert strategy.performWork([new Threshold(name: "testing", description: "threshold-client unit test", operator: ">=", value: 50)], 60)
    }

    @Test
    void testPerformNoWork() {
        ThresholdMetStrategy strategy = new AlertWhenThresholdMet(new EchoSecureHttpClient())
        assert !strategy.performWork([], 0)
    }
}
