package com.fei.service.impl;

import com.fei.mapper.TrialMapper;
import com.fei.service.TrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrialServiceImpl implements TrialService {

    @Autowired
    private TrialMapper trialMapper;

    @Override
    public String findTrialIdByWebAppIdAndRound(String web_app_id, Integer round) {
        return trialMapper.findTrialIdByWebAppIdAndRound(web_app_id,round);
    }
}
