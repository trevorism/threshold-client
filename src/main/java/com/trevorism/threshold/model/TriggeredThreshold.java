package com.trevorism.threshold.model;

public class TriggeredThreshold extends Threshold {

    private Double triggerValue;

    public static TriggeredThreshold copyFrom(Threshold threshold) {
        TriggeredThreshold tt = new TriggeredThreshold();
        tt.setId(threshold.getId());
        tt.setName(threshold.getName());
        tt.setDescription(threshold.getDescription());
        tt.setOperator(threshold.getOperator());
        tt.setValue(threshold.getValue());
        return tt;
    }

    public Double getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(Double triggerValue) {
        this.triggerValue = triggerValue;
    }
}
