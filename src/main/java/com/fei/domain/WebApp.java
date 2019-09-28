package com.fei.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WebApp {

    private String id;

    private String user_id;

    private String app_name;

    private String URL;

    private Date date;

    private String Date_string;

    //倒计时时间
    private Integer timed;
    //是否是模板
    private Boolean template;

    private String age;

    private Integer numbers_of_trials;

    private List<AppResult> resultLists;

    //private List <WebAppTarget> webAppTargets;

    private String notes;

    private List <Favourite> practitioners;

    private Integer practitioners_Num;

    private String test="10";           //作为一个检测工具

    private List<Trial> trials;



    @Override
    public String toString() {
        return "WebApp{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", app_name='" + app_name + '\'' +
                ", URL='" + URL + '\'' +
                ", date=" + date +
                ", Date_string='" + Date_string + '\'' +
                ", timed=" + timed +
                ", template=" + template +
                ", age='" + age + '\'' +
                ", numbers_of_trials=" + numbers_of_trials +
                ", resultLists=" + resultLists +
                ", notes='" + notes + '\'' +
                ", practitioners=" + practitioners +
                ", practitioners_Num=" + practitioners_Num +
                ", trials=" + trials +
                '}';
    }

    public List<Trial> getTrials() {
        return trials;
    }

    public void setTrials(List<Trial> trials) {
        this.trials = trials;
    }

    public Integer getNumbers_of_trials() {
        return numbers_of_trials;
    }

    public void setNumbers_of_trials(Integer numbers_of_trials) {
        this.numbers_of_trials = numbers_of_trials;
    }

    public Integer getPractitioners_Num() {
        return practitioners_Num;
    }

    public void setPractitioners_Num(Integer practitioners_Num) {
        this.practitioners_Num = practitioners_Num;
    }

    public List<Favourite> getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(List<Favourite> practitioners) {
        this.practitioners = practitioners;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
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

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        setDate_string(ft.format(date));
    }

    public String getDate_string() {
        return Date_string;
    }

    public void setDate_string(String date_string) {
        Date_string = date_string;
    }

    public Integer getTimed() {
        return timed;
    }

    public void setTimed(Integer timed) {
        this.timed = timed;
    }

    public Boolean getTemplate() {
        return template;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<AppResult> getResultLists() {
        return resultLists;
    }

    public void setResultLists(List<AppResult> resultLists) {
        this.resultLists = resultLists;
    }
}
