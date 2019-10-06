package com.fei.service;

import org.springframework.stereotype.Service;

@Service
public interface TrialService {

    public String findTrialIdByWebAppIdAndRound(String web_app_id, Integer round);

}
