package com.fei.domain;

import java.util.List;

public class Participant {

    private String id;
    private String user_id;
    private String name;

    private List<WebAppResult> webAppResultList;

    public Participant(String id, String user_id, String name, List<WebAppResult> webAppResultList) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.webAppResultList = webAppResultList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WebAppResult> getWebAppResultList() {
        return webAppResultList;
    }

    public void setWebAppResultList(List<WebAppResult> webAppResultList) {
        this.webAppResultList = webAppResultList;
    }
}
