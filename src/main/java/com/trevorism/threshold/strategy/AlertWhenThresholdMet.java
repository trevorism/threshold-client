package com.trevorism.threshold.strategy;

import com.trevorism.event.EventProducer;
import com.trevorism.event.PingingEventProducer;
import com.trevorism.threshold.model.Alert;
import com.trevorism.threshold.model.Threshold;

import java.util.List;
import java.util.UUID;

public class AlertWhenThresholdMet implements ThresholdMetStrategy {

    private String correlationId;
    private EventProducer<Alert> producer;

    public AlertWhenThresholdMet() {
        this(UUID.randomUUID().toString());
    }

    public AlertWhenThresholdMet(String correlationId) {
        this.correlationId = correlationId;
        producer = new PingingEventProducer<>();
    }

    @Override
    public boolean performWork(List<Threshold> metThresholds, Double value) {
        if (metThresholds == null || metThresholds.size() == 0 || value == null) {
            return false;
        }

        Alert alert = createAlert(metThresholds, value);
        producer.sendEvent("alert", alert, correlationId);

        correlationId = UUID.randomUUID().toString();
        return true;
    }

    private Alert createAlert(List<Threshold> metThresholds, Double value) {
        Alert alert = new Alert();
        alert.setSubject("Threshold Triggered at " + value);
        alert.setBody(buildBody(metThresholds, value));
        return alert;
    }

    private String buildBody(List<Threshold> metThresholds, Double value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The following thresholds have been triggered by the value: ");
        stringBuilder.append(value);
        stringBuilder.append("\nDetails:\n");
        for (Threshold threshold : metThresholds) {
            stringBuilder.append("id: ");
            stringBuilder.append(threshold.getId());
            stringBuilder.append("; name: ");
            stringBuilder.append(threshold.getName());
            stringBuilder.append("; description: ");
            stringBuilder.append(threshold.getDescription());
            stringBuilder.append("; ");
            stringBuilder.append(value);
            stringBuilder.append(" ");
            stringBuilder.append(threshold.getOperator());
            stringBuilder.append(" ");
            stringBuilder.append(threshold.getValue());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
