package com.trevorism.threshold

import com.trevorism.http.HttpClient
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
        defaultThresholdClient.httpClient = [get: {url -> return listResponse}] as HttpClient
        def list = defaultThresholdClient.list()
        assert list
    }

    @Test
    void testGet() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.httpClient = [get: {url -> return singleResponse}] as HttpClient
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
        defaultThresholdClient.httpClient = [post: {url, obj -> return singleResponse}] as HttpClient

        def result = defaultThresholdClient.create(threshold)
        assert result
    }

    @Test
    void testUpdate() {
        Threshold threshold = new Threshold()
        threshold.name = "ggggg"

        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        defaultThresholdClient.httpClient = [put: {url, obj -> return singleResponse}] as HttpClient

        def result = defaultThresholdClient.update(5072080529784832, threshold)
        assert result
    }

    @Test
    void testDelete() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.httpClient = [delete: {url -> return singleResponse}] as HttpClient
        assert result
    }

    @Test
    void testGetByName() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        defaultThresholdClient.httpClient = [get: {url -> return listResponse}] as HttpClient
        def result = defaultThresholdClient.getByName("test")
        assert result
    }

    @Test
    void testEvaluate() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        defaultThresholdClient.httpClient = [get: {url -> return listResponse}] as HttpClient
        def result = defaultThresholdClient.evaluate("test", 7, new NothingWhenThresholdMet())
        assert result
    }

}
