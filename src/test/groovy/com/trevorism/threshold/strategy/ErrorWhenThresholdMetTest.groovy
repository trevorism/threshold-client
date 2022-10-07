package com.trevorism.threshold.strategy

import com.trevorism.TestErrorClient
import com.trevorism.model.TestError
import com.trevorism.model.response.TestErrorResponse
import com.trevorism.threshold.model.Threshold
import org.junit.Test

class ErrorWhenThresholdMetTest {

    @Test
    void testPerformWork() {
        ErrorWhenThresholdMet strategy = new ErrorWhenThresholdMet()

        strategy.testErrorClient = [addTestError: { err -> new TestErrorResponse()}] as TestErrorClient

        assert strategy.performWork([new Threshold(name: "testing", description: "threshold-client unit test", operator: ">=", value: 50)], 60)
    }
}
