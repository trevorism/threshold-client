package com.trevorism.threshold


import com.trevorism.https.SecureHttpClient
import com.trevorism.threshold.model.Threshold
import com.trevorism.threshold.strategy.NothingWhenThresholdMet
import org.junit.Test

class FastThresholdClientTest {

    private final pingResponse = "pong"
    private final listResponse = '[{"id":"1"}]'
    private final singleResponse = '{"id":"1"}'


    @Test
    void testList() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        defaultThresholdClient.secureHttpClient = [get: {url -> return listResponse}] as SecureHttpClient
        def list = defaultThresholdClient.list()
        assert list
    }

    @Test
    void testGet() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.secureHttpClient = [get: {url -> return singleResponse}] as SecureHttpClient
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
        defaultThresholdClient.secureHttpClient = [post: {url, obj -> return singleResponse}] as SecureHttpClient

        def result = defaultThresholdClient.create(threshold)
        assert result
    }

    @Test
    void testUpdate() {
        Threshold threshold = new Threshold()
        threshold.name = "ggggg"

        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        defaultThresholdClient.secureHttpClient = [put: {url, obj -> return singleResponse}] as SecureHttpClient

        def result = defaultThresholdClient.update(5072080529784832, threshold)
        assert result
    }

    @Test
    void testDelete() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.secureHttpClient = [delete: {url -> return singleResponse}] as SecureHttpClient
        assert result
    }

    @Test
    void testGetByName() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        defaultThresholdClient.secureHttpClient = [get: {url -> return listResponse}] as SecureHttpClient
        def result = defaultThresholdClient.getByName("test")
        assert result
    }

    @Test
    void testEvaluate() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        defaultThresholdClient.secureHttpClient = [get: {url -> return listResponse}] as SecureHttpClient
        def result = defaultThresholdClient.evaluate("test", 7, new NothingWhenThresholdMet())
        assert result
    }

}
