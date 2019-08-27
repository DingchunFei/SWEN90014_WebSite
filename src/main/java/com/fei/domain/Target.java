package com.fei.domain;

import java.util.List;

public class Target {

    private String id;
    private Integer shape;
    private Boolean curve;
    private Integer direction;

    @Override
    public String toString() {
        return "Target{" +
                "id='" + id + '\'' +
                ", shape=" + shape +
                ", curve=" + curve +
                ", direction=" + direction +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getShape() {
        return shape;
    }

    public void setShape(Integer shape) {
        this.shape = shape;
    }

    public Boolean getCurve() {
        return curve;
    }

    public void setCurve(Boolean curve) {
        this.curve = curve;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }


}
