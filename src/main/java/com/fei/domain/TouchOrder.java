package com.fei.domain;

public class TouchOrder {

    private String id;
    private String trial_result_shape_id;
    private Double touch_time;

    @Override
    public String toString() {
        return "TouchOrder{" +
                "id='" + id + '\'' +
                ", trial_result_shape_id='" + trial_result_shape_id + '\'' +
                ", touch_time=" + touch_time +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrial_result_shape_id() {
        return trial_result_shape_id;
    }

    public void setTrial_result_shape_id(String trial_result_shape_id) {
        this.trial_result_shape_id = trial_result_shape_id;
    }

    public Double getTouch_time() {
        return touch_time;
    }

    public void setTouch_time(Double touch_time) {
        this.touch_time = touch_time;
    }
}
