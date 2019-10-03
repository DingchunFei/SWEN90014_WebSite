package com.fei.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Trial {
    @JsonIgnore
    private String id;

    @JsonIgnore
    private String web_app_id;

    @JsonProperty("numberOfTrials")
    private Integer round;
    @JsonProperty("row")
    private Integer grid_row;
    @JsonProperty("column")
    private Integer grid_column;
    @JsonProperty("time")
    private Integer timed;

    private Integer target_percentage;

    private Integer near_distractor_percentage;

    @JsonIgnore
    private Integer far_distractor_percentage;

    private List<TrialShape> trialShapeList;
    @JsonIgnore
    private WebApp webApp;

    public Trial() {
    }

    public Trial(Integer round, Integer grid_row, Integer grid_column, Integer timed, Integer target_percentage, Integer near_distractor_percentage) {
        this.round = round;
        this.grid_row = grid_row;
        this.grid_column = grid_column;
        this.timed = timed;
        this.target_percentage = target_percentage;
        this.near_distractor_percentage = near_distractor_percentage;
    }


    public Trial(String web_app_id, Integer round, Integer grid_row, Integer grid_column, Integer timed, Integer target_percentage, Integer near_distractor_percentage, Integer far_distractor_percentage) {
        this.web_app_id = web_app_id;
        this.round = round;
        this.grid_row = grid_row;
        this.grid_column = grid_column;
        this.timed = timed;
        this.target_percentage = target_percentage;
        this.near_distractor_percentage = near_distractor_percentage;
        this.far_distractor_percentage = far_distractor_percentage;
    }

    @Override
    public String toString() {
        return "Trial{" +
                "id='" + id + '\'' +
                ", web_app_id='" + web_app_id + '\'' +
                ", round=" + round +
                ", grid_row=" + grid_row +
                ", grid_column=" + grid_column +
                ", timed=" + timed +
                ", target_percentage=" + target_percentage +
                ", near_distractor_percentage=" + near_distractor_percentage +
                ", far_distractor_percentage=" + far_distractor_percentage +
                ", trialShapeList=" + trialShapeList +
                '}';
    }

    public List<TrialShape> getTrialShapeList() {
        return trialShapeList;
    }

    public void setTrialShapeList(List<TrialShape> trialShapeList) {
        this.trialShapeList = trialShapeList;
    }

    public WebApp getWebApp() {
        return webApp;
    }

    public void setWebApp(WebApp webApp) {
        this.webApp = webApp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeb_app_id() {
        return web_app_id;
    }

    public void setWeb_app_id(String web_app_id) {
        this.web_app_id = web_app_id;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getGrid_row() {
        return grid_row;
    }

    public void setGrid_row(Integer grid_row) {
        this.grid_row = grid_row;
    }

    public Integer getGrid_column() {
        return grid_column;
    }

    public void setGrid_column(Integer grid_column) {
        this.grid_column = grid_column;
    }

    public Integer getTimed() {
        return timed;
    }

    public void setTimed(Integer timed) {
        this.timed = timed;
    }

    public Integer getTarget_percentage() {
        return target_percentage;
    }

    public void setTarget_percentage(Integer target_percentage) {
        this.target_percentage = target_percentage;
    }

    public Integer getNear_distractor_percentage() {
        return near_distractor_percentage;
    }

    public void setNear_distractor_percentage(Integer near_distractor_percentage) {
        this.near_distractor_percentage = near_distractor_percentage;
    }

    public Integer getFar_distractor_percentage() {
        return far_distractor_percentage;
    }

    public void setFar_distractor_percentage(Integer far_distractor_percentage) {
        this.far_distractor_percentage = far_distractor_percentage;
    }
}
