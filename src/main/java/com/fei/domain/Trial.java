package com.fei.domain;

import java.util.List;

public class Trial {

    private String id;
    private String web_app_id;
    private Integer round;
    private Integer grid_row;
    private Integer grid_column;

    private List<TrialShape> trialShapeList;
    private WebApp webApp;

    public Trial() {
    }

    public Trial(String web_app_id, Integer round, Integer grid_row, Integer grid_column) {
        this.web_app_id = web_app_id;
        this.round = round;
        this.grid_column = grid_column;
        this.grid_row = grid_row;
    }

    @Override
    public String toString() {
        return "Trial{" +
                "id='" + id + '\'' +
                ", web_app_id='" + web_app_id + '\'' +
                ", round=" + round +
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
}
