package com.fei.domain;

import java.util.List;

public class TrialResult {

    private String id;

    private String web_app_result_id;

    private String trial_id;

    private Double trial_accuracy;

    private Integer round;


    private WebAppResult webAppResult;

    private Trial trial;

    private List<TrialResultShape> trialResultShapeList;

    @Override
    public String toString() {
        return "TrialResult{" +
                "id='" + id + '\'' +
                ", web_app_result_id='" + web_app_result_id + '\'' +
                ", trial_id='" + trial_id + '\'' +
                ", trial_accuracy=" + trial_accuracy +
                ", round=" + round +
                ", trialResultShapeList=" + trialResultShapeList +
                '}';
    }

    public String getTrial_id() {
        return trial_id;
    }

    public void setTrial_id(String trial_id) {
        this.trial_id = trial_id;
    }

    public Trial getTrial() {
        return trial;
    }

    public void setTrial(Trial trial) {
        this.trial = trial;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeb_app_result_id() {
        return web_app_result_id;
    }

    public void setWeb_app_result_id(String web_app_result_id) {
        this.web_app_result_id = web_app_result_id;
    }

    public Double getTrial_accuracy() {
        return trial_accuracy;
    }

    public void setTrial_accuracy(Double trial_accuracy) {
        this.trial_accuracy = trial_accuracy;
    }

    public WebAppResult getWebAppResult() {
        return webAppResult;
    }

    public void setWebAppResult(WebAppResult webAppResult) {
        this.webAppResult = webAppResult;
    }

    public List<TrialResultShape> getTrialResultShapeList() {
        return trialResultShapeList;
    }

    public void setTrialResultShapeList(List<TrialResultShape> trialResultShapeList) {
        this.trialResultShapeList = trialResultShapeList;
    }
}
