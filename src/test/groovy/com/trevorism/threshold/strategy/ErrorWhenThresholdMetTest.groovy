package com.trevorism.threshold.strategy

import com.trevorism.threshold.EchoSecureHttpClient
import com.trevorism.threshold.model.Threshold
import org.junit.Test

class ErrorWhenThresholdMetTest {

    @Test
    void testPerformWork() {
        ErrorWhenThresholdMet strategy = new ErrorWhenThresholdMet(new EchoSecureHttpClient())
        assert strategy.performWork([new Threshold(name: "testing", description: "threshold-client unit test", operator: ">=", value: 50)], 60)
    }
}
