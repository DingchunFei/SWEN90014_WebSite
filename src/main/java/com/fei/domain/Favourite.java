package com.fei.domain;

public class Favourite{

    private String id;

    private String user_id;

    private String web_app_id;

    private Boolean flag;

    private User user;

    private WebApp webApp;

    public Favourite() {
    }

    public Favourite(String user_id, String web_app_id) {
        this.user_id = user_id;
        this.web_app_id = web_app_id;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", web_app_id='" + web_app_id + '\'' +
                ", flag=" + flag +
                ", user=" + user +
                ", webApp=" + webApp +
                '}';
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

    public String getWeb_app_id() {
        return web_app_id;
    }

    public void setWeb_app_id(String web_app_id) {
        this.web_app_id = web_app_id;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WebApp getWebApp() {
        return webApp;
    }

    public void setWebApp(WebApp webApp) {
        this.webApp = webApp;
    }

}
