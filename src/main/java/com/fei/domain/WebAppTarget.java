package com.fei.domain;

public class WebAppTarget {

    private String id;
    private String web_app_id;
    private String target_id;
    private Integer round;

    private WebApp webApp;
    private Target target;

    @Override
    public String toString() {
        return "WebAppTarget{" +
                "id='" + id + '\'' +
                ", web_app_id='" + web_app_id + '\'' +
                ", target_id='" + target_id + '\'' +
                ", round=" + round +
                ", webApp=" + webApp +
                ", target=" + target +
                '}';
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

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public WebApp getWebApp() {
        return webApp;
    }

    public void setWebApp(WebApp webApp) {
        this.webApp = webApp;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
