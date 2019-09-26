package com.fei.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AppResult {

    private String id;

    private String web_app_id;

    private String user_id;

    private String test_code;

    private String p_username;

    //@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date p_birthday;

    private String p_birthday_string;

    private Date test_date;

    private String test_date_string;

    private Integer right;

    private Integer wrong;

    private Boolean wrongOrder;

    private Double accuracy;

    private Integer totalShapes;

    private Integer targetAmount;

    private Integer nearDistractorAmount;

    private Integer farDistractorAmount;

    private WebApp webApp;

    private Integer age;

    @Override
    public String toString() {
        return "AppResult{" +
                "id='" + id + '\'' +
                ", web_app_id='" + web_app_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", test_code='" + test_code + '\'' +
                ", p_username='" + p_username + '\'' +
                ", p_birthday=" + p_birthday +
                ", test_date=" + test_date +
                ", right=" + right +
                ", wrong=" + wrong +
                ", wrongOrder=" + wrongOrder +
                ", accuracy=" + accuracy +
                ", totalShapes=" + totalShapes +
                ", targetAmount=" + targetAmount +
                ", nearDistractorAmount=" + nearDistractorAmount +
                ", farDistractorAmount=" + farDistractorAmount +
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTest_code() {
        return test_code;
    }

    public void setTest_code(String test_code) {
        this.test_code = test_code;
    }

    public String getP_username() {
        return p_username;
    }

    public void setP_username(String p_username) {
        this.p_username = p_username;
    }

    public Date getP_birthday() {
        return p_birthday;
    }

    public void setP_birthday(Date p_birthday) {
        this.p_birthday = p_birthday;

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        String str = ft.format(p_birthday);
        setP_birthday_string(str);

        if(test_date_string!=null){
            LocalDate date1 = LocalDate.of(Integer.parseInt(str.substring(0,4)), Integer.parseInt(str.substring(5,7)), Integer.parseInt(str.substring(8,10)));
            LocalDate date2 = LocalDate.of(Integer.parseInt(test_date_string.substring(0,4)), Integer.parseInt(test_date_string.substring(5,7)), Integer.parseInt(test_date_string.substring(8,10)));
            this.age = date1.until(date2).getYears();
            System.out.println(Integer.parseInt(str.substring(0,4)) +"    "+ Integer.parseInt(str.substring(5,7)) +"    "+ Integer.parseInt(str.substring(8,10)));
            System.out.println(Integer.parseInt(test_date_string.substring(0,4)) +"    " + Integer.parseInt(test_date_string.substring(5,7))+"    " + Integer.parseInt(test_date_string.substring(8,10)));

        }
    }

    public Date getTest_date() {
        return test_date;
    }

    public void setTest_date(Date test_date) {
        this.test_date = test_date;

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        String str = ft.format(test_date);
        setTest_date_string(str);

        if(p_birthday_string!=null){
            LocalDate date1 = LocalDate.of(Integer.parseInt(str.substring(0,4)), Integer.parseInt(str.substring(5,7)), Integer.parseInt(str.substring(8,10)));
            LocalDate date2 = LocalDate.of(Integer.parseInt(p_birthday_string.substring(0,4)), Integer.parseInt(p_birthday_string.substring(5,7)), Integer.parseInt(p_birthday_string.substring(8,10)));
            this.age = date2.until(date1).getYears();
            System.out.println(Integer.parseInt(str.substring(0,4)) +"    "+ Integer.parseInt(str.substring(5,7)) +"    "+ Integer.parseInt(str.substring(8,10)));
            System.out.println(Integer.parseInt(p_birthday_string.substring(0,4)) +"    " + Integer.parseInt(p_birthday_string.substring(5,7))+"    " + Integer.parseInt(p_birthday_string.substring(8,10)));

        }
    }

    public String getP_birthday_string() {
        return p_birthday_string;
    }

    public void setP_birthday_string(String p_birthday_string) {
        this.p_birthday_string = p_birthday_string;
    }

    public String getTest_date_string() {
        return test_date_string;
    }

    public void setTest_date_string(String test_date_string) {
        this.test_date_string = test_date_string;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getWrong() {
        return wrong;
    }

    public void setWrong(Integer wrong) {
        this.wrong = wrong;
    }

    public Boolean getWrongOrder() {
        return wrongOrder;
    }

    public void setWrongOrder(Boolean wrongOrder) {
        this.wrongOrder = wrongOrder;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getTotalShapes() {
        return totalShapes;
    }

    public void setTotalShapes(Integer totalShapes) {
        this.totalShapes = totalShapes;
    }

    public Integer getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Integer targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Integer getNearDistractorAmount() {
        return nearDistractorAmount;
    }

    public void setNearDistractorAmount(Integer nearDistractorAmount) {
        this.nearDistractorAmount = nearDistractorAmount;
    }

    public Integer getFarDistractorAmount() {
        return farDistractorAmount;
    }

    public void setFarDistractorAmount(Integer farDistractorAmount) {
        this.farDistractorAmount = farDistractorAmount;
    }

    public WebApp getWebApp() {
        return webApp;
    }

    public void setWebApp(WebApp webApp) {
        this.webApp = webApp;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
