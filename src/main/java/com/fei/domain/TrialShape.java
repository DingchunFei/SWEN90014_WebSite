package com.fei.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TrialShape {
    @JsonIgnore
    private String id;
    @JsonIgnore
    private String shape_id;
    @JsonIgnore
    private String trial_id;
    private Integer type;

    private Shape shape;
    @JsonIgnore
    private Trial trial;


    public TrialShape() {
    }

    public TrialShape(Integer type, Shape shape) {
        this.type = type;
        this.shape = shape;
    }

    public TrialShape(String shape_id, String trial_id, Integer type) {
        this.shape_id = shape_id;
        this.trial_id = trial_id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "TrialShape{" +
                "id='" + id + '\'' +
                ", shape_id='" + shape_id + '\'' +
                ", trial_id='" + trial_id + '\'' +
                ", type=" + type +
                ", shape=" + shape +
                ", trial=" + trial +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Trial getTrial() {
        return trial;
    }

    public void setTrial(Trial trial) {
        this.trial = trial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShape_id() {
        return shape_id;
    }

    public void setShape_id(String shape_id) {
        this.shape_id = shape_id;
    }

    public String getTrial_id() {
        return trial_id;
    }

    public void setTrial_id(String trial_id) {
        this.trial_id = trial_id;
    }
}
