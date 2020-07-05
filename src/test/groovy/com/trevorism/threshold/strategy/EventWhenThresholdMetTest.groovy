package com.trevorism.threshold.strategy

import com.trevorism.event.EventProducer
import com.trevorism.threshold.model.Threshold
import org.junit.Test

class EventWhenThresholdMetTest {

    @Test
    void testPerformWork() {
        ThresholdMetStrategy strategy = new EventWhenThresholdMet()
        strategy.producer = [sendEvent:{ s, r, t -> }] as EventProducer
        assert strategy.performWork([new Threshold(name: "testing", description: "threshold-client unit test", operator: "=", value: 0)], 0)
    }

    @Test
    void testPerformNoWork() {
        ThresholdMetStrategy strategy = new EventWhenThresholdMet()
        assert !strategy.performWork([], 0)
    }
}
