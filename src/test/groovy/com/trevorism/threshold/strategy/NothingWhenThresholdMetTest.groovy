package com.trevorism.threshold.strategy

import com.trevorism.threshold.model.Threshold
import org.junit.Test

class NothingWhenThresholdMetTest {

    @Test
    void testPerformWork() {
        ThresholdMetStrategy instance = new NothingWhenThresholdMet()
        assert !instance.performWork([], 0)
        assert !instance.performWork([new Threshold()], null)
        assert instance.performWork([new Threshold()], 0)

    }
}
