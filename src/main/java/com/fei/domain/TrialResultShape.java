package com.fei.domain;

import java.util.List;

public class TrialResultShape {

    private String id;
    private String trial_result_id;
    private String shape_id;
    private Integer type;
    private Integer hit_count;

    private List<TouchOrder> touchOrderList;
    private Shape shape;
    private TrialResult trialResult;

    @Override
    public String toString() {
        return "TrialResultShape{" +
                "id='" + id + '\'' +
                ", trial_result_id='" + trial_result_id + '\'' +
                ", shape_id='" + shape_id + '\'' +
                ", type=" + type +
                ", hit_count=" + hit_count +
                ", touchOrderList=" + touchOrderList +
                ", shape=" + shape +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrial_result_id() {
        return trial_result_id;
    }

    public void setTrial_result_id(String trial_result_id) {
        this.trial_result_id = trial_result_id;
    }

    public String getShape_id() {
        return shape_id;
    }

    public void setShape_id(String shape_id) {
        this.shape_id = shape_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHit_count() {
        return hit_count;
    }

    public void setHit_count(Integer hit_count) {
        this.hit_count = hit_count;
    }

    public List<TouchOrder> getTouchOrderList() {
        return touchOrderList;
    }

    public void setTouchOrderList(List<TouchOrder> touchOrderList) {
        this.touchOrderList = touchOrderList;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public TrialResult getTrialResult() {
        return trialResult;
    }

    public void setTrialResult(TrialResult trialResult) {
        this.trialResult = trialResult;
    }
}
