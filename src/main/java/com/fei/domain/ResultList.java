package com.fei.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultList {

    private String id;

    private String web_app_id;

    private String p_username;

    private Integer p_age;

    private Integer p_gender;
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;

    private String touch_history;

    private Double accuracy;

    private Integer time_consumption;

    private WebApp webApp;

    private String  date_string;

    @Override
    public String toString() {
        return "ResultList{" +
                "id='" + id + '\'' +
                ", web_app_id='" + web_app_id + '\'' +
                ", p_username='" + p_username + '\'' +
                ", p_age=" + p_age +
                ", p_gender=" + p_gender +
                ", date=" + date +
                ", touch_history='" + touch_history + '\'' +
                ", accuracy=" + accuracy +
                ", time_consumption=" + time_consumption +
                ", webApp=" + webApp +
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

    public String getP_username() {
        return p_username;
    }

    public void setP_username(String p_username) {
        this.p_username = p_username;
    }

    public Integer getP_age() {
        return p_age;
    }

    public void setP_age(Integer p_age) {
        this.p_age = p_age;
    }

    public Integer getP_gender() {
        return p_gender;
    }

    public void setP_gender(Integer p_gender) {
        this.p_gender = p_gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        setDate_string(ft.format(date));
    }

    public String getTouch_history() {
        return touch_history;
    }

    public void setTouch_history(String touch_history) {
        this.touch_history = touch_history;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getTime_consumption() {
        return time_consumption;
    }

    public void setTime_consumption(Integer time_consumption) {
        this.time_consumption = time_consumption;
    }

    public WebApp getWebApp() {
        return webApp;
    }

    public void setWebApp(WebApp webApp) {
        this.webApp = webApp;
    }

    public String getDate_string() {
        return date_string;
    }

    public void setDate_string(String date_string) {
        this.date_string = date_string;
    }
}
