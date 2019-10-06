package com.fei.service;

import com.fei.domain.AppResult;
import com.fei.domain.WebAppResult;
import org.springframework.stereotype.Service;


@Service
public interface WebAppResultService {

    public WebAppResult findWebAppResultDetailByWebAppResultId(String web_app_result_id);
}
