package com.fei.utils;

import com.fei.domain.Favourite;
import com.fei.domain.ResultList;
import com.fei.domain.WebAppTarget;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WebApp2Json {


    private Integer row;

    private Integer column;

    private double nearPercentage;

    private double farPercentage;
    //倒计时时间
    private Boolean timed;
    //是否是模板
    private Integer time;

    private String target;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public double getNearPercentage() {
        return nearPercentage;
    }

    public void setNearPercentage(double nearPercentage) {
        this.nearPercentage = nearPercentage;
    }

    public double getFarPercentage() {
        return farPercentage;
    }

    public void setFarPercentage(double farPercentage) {
        this.farPercentage = farPercentage;
    }

    public Boolean getTimed() {
        return timed;
    }

    public void setTimed(Boolean timed) {
        this.timed = timed;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "WebApp2Json{" +
                "row=" + row +
                ", column=" + column +
                ", nearPercentage=" + nearPercentage +
                ", farPercentage=" + farPercentage +
                ", timed=" + timed +
                ", time=" + time +
                ", target='" + target + '\'' +
                '}';
    }
}
