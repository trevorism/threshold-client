package com.trevorism.threshold

import com.trevorism.http.headers.HeadersHttpClient
import com.trevorism.threshold.model.Threshold
import com.trevorism.threshold.strategy.NothingWhenThresholdMet
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.entity.StringEntity
import org.junit.Test

class FastThresholdClientTest {

    private final pingResponse = [getEntity: { new StringEntity("pong") }] as CloseableHttpResponse
    private final listResponse = [getEntity: { new StringEntity('[{"id":"1"}]') }] as CloseableHttpResponse
    private final singleResponse = [getEntity: { new StringEntity('{"id":"1"}') }] as CloseableHttpResponse


    @Test
    void testList() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        defaultThresholdClient.headersClient = [get: {url, headers -> return listResponse}] as HeadersHttpClient
        def list = defaultThresholdClient.list()
        assert list
    }

    @Test
    void testGet() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.headersClient = [get: {url, headers -> return singleResponse}] as HeadersHttpClient
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
        defaultThresholdClient.headersClient = [post: {url, obj, headers -> return singleResponse}] as HeadersHttpClient

        def result = defaultThresholdClient.create(threshold)
        assert result
    }

    @Test
    void testUpdate() {
        Threshold threshold = new Threshold()
        threshold.name = "ggggg"

        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        defaultThresholdClient.headersClient = [put: {url, obj, headers -> return singleResponse}] as HeadersHttpClient

        def result = defaultThresholdClient.update(5072080529784832, threshold)
        assert result
    }

    @Test
    void testDelete() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        def result = defaultThresholdClient.headersClient = [delete: {url, headers -> return singleResponse}] as HeadersHttpClient
        assert result
    }

    @Test
    void testGetByName() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        defaultThresholdClient.headersClient = [get: {url, headers -> return listResponse}] as HeadersHttpClient
        def result = defaultThresholdClient.getByName("test")
        assert result
    }

    @Test
    void testEvaluate() {
        ThresholdClient defaultThresholdClient = new FastThresholdClient()
        defaultThresholdClient.headersClient = [get: {url, headers -> return listResponse}] as HeadersHttpClient
        def result = defaultThresholdClient.evaluate("test", 7, new NothingWhenThresholdMet())
        assert result
    }
}
