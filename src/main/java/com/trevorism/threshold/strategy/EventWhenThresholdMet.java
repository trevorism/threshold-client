package com.trevorism.threshold.strategy;

import com.trevorism.event.EventProducer;
import com.trevorism.event.PingingEventProducer;
import com.trevorism.threshold.model.Threshold;
import com.trevorism.threshold.model.TriggeredThreshold;

import java.util.List;
import java.util.UUID;

public class EventWhenThresholdMet implements ThresholdMetStrategy {

    private String correlationId;
    private EventProducer<TriggeredThreshold> producer;

    public EventWhenThresholdMet() {
        this(UUID.randomUUID().toString());
    }

    public EventWhenThresholdMet(String correlationId) {
        this.correlationId = correlationId;
        producer = new PingingEventProducer<>();
    }

    @Override
    public boolean performWork(List<Threshold> metThresholds, Double value) {
        if (metThresholds == null || metThresholds.size() == 0 || value == null) {
            return false;
        }

        for (Threshold threshold : metThresholds) {
            TriggeredThreshold tt = TriggeredThreshold.copyFrom(threshold);
            tt.setTriggerValue(value);

            producer.sendEvent("threshold", tt, correlationId);
        }
        correlationId = UUID.randomUUID().toString();
        return true;
    }
}
