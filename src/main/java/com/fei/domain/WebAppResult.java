package com.fei.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WebAppResult {

    private String id;

    private String web_app_id;

    private String participant_id;

    private Double total_accuracy;

    private Date test_date;

    private String test_date_string;



    private WebApp webApp;

    private List<TrialResult> trialResultList;

    private Participant participant;

    @Override
    public String toString() {
        return "WebAppResult{" +
                "id='" + id + '\'' +
                ", web_app_id='" + web_app_id + '\'' +
                ", participant_id='" + participant_id + '\'' +
                ", total_accuracy=" + total_accuracy +
                ", test_date=" + test_date +
                ", trialResultList=" + trialResultList +
                '}';
    }

    public String getTest_date_string() {
        return test_date_string;
    }

    public void setTest_date_string(String test_date_string) {
        this.test_date_string = test_date_string;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public List<TrialResult> getTrialResultList() {
        return trialResultList;
    }

    public void setTrialResultList(List<TrialResult> trialResultList) {
        this.trialResultList = trialResultList;
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

    public String getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(String participant_id) {
        this.participant_id = participant_id;
    }

    public Double getTotal_accuracy() {
        return total_accuracy;
    }

    public void setTotal_accuracy(Double total_accuracy) {
        this.total_accuracy = total_accuracy;
    }

    public Date getTest_date() {
        return test_date;
    }

    public void setTest_date(Date test_date) {
        this.test_date = test_date;
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        setTest_date_string(ft.format(test_date));
    }
}
