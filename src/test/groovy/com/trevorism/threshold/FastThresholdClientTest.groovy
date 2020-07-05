package com.trevorism.threshold

import com.trevorism.threshold.model.Threshold
import org.junit.Test

class FastThresholdClientTest {

    @Test
    void testList() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def list = defaultThresholdClient.list()
        println list
        assert list
    }

    @Test
    void testGet() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.get(5680933952815104)
        assert result
    }

    @Test
    void testCreate() {
        Threshold threshold = new Threshold()
        threshold.name = "asdf"
        threshold.operator = "="
        threshold.value = 2020
        threshold.description = "test"

        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.create(threshold)
        println result
    }

    @Test
    void testUpdate() {
        Threshold threshold = new Threshold()
        threshold.name = "ggggg"

        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.update(5072080529784832, threshold)
        println result
    }

    @Test
    void testDelete() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.delete(5072080529784832)
        println result
    }

    @Test
    void testGetByName() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.getByName("bitcoin")
        println result

    }

    @Test
    void testEvaluate() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.evaluate("bitcoin", 9003)
        println result
    }
}
