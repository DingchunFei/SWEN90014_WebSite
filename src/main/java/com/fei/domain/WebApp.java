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

    private Integer gender_preferrence;

    private Integer grid_row;

    private Integer grid_column;

    private double near_percentage;

    private double far_percentage;
    //倒计时时间
    private Integer timed;
    //是否是模板
    private Boolean template;

    private String age;

    private Integer numbers_of_targets;

    private Integer numbers_of_trials;

    private List<ResultList> resultLists;

    private List <WebAppTarget> webAppTargets;

    private String notes;

    private List <Favourite> practitioners;

    private Integer practitioners_Num;

    private String test="10";           //作为一个检测工具

    @Override
    public String toString() {
        return "WebApp{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", app_name='" + app_name + '\'' +
                ", URL='" + URL + '\'' +
                ", date=" + date +
                ", gender_preferrence=" + gender_preferrence +
                ", grid_row=" + grid_row +
                ", grid_column=" + grid_column +
                ", near_percentage='" + near_percentage + '\'' +
                ", far_percentage='" + far_percentage + '\'' +
                ", timed=" + timed +
                ", template=" + template +
                ", age=" + age +
                ", notes=" + notes +
                ", numbers_of_targets=" + numbers_of_targets +
                ", numbers_of_trials=" + numbers_of_trials +
                ", practitioners=" + practitioners +
                ", practitioners_Num=" + practitioners_Num +
                ", resultLists=" + resultLists +
                ", webAppTargets=" + webAppTargets +
                '}';
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

    public Integer getNumbers_of_targets() {
        return numbers_of_targets;
    }

    public void setNumbers_of_targets(Integer numbers_of_targets) {
        this.numbers_of_targets = numbers_of_targets;
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

    public Integer getGender_preferrence() {
        return gender_preferrence;
    }

    public void setGender_preferrence(Integer gender_preferrence) {
        this.gender_preferrence = gender_preferrence;
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

    public double getNear_percentage() {
        return near_percentage;
    }

    public void setNear_percentage(double near_percentage) {
        this.near_percentage = near_percentage;
    }

    public double getFar_percentage() {
        return far_percentage;
    }

    public void setFar_percentage(double far_percentage) {
        this.far_percentage = far_percentage;
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

    public List<ResultList> getResultLists() {
        return resultLists;
    }

    public void setResultLists(List<ResultList> resultLists) {
        this.resultLists = resultLists;
    }

    public List<WebAppTarget> getWebAppTargets() {
        return webAppTargets;
    }

    public void setWebAppTargets(List<WebAppTarget> webAppTargets) {
        this.webAppTargets = webAppTargets;
    }
}
