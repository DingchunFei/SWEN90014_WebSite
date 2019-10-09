package com.fei.service;

import com.fei.domain.WebAppResult;
import org.springframework.stereotype.Service;


@Service
public interface WebAppResultService {

    public WebAppResult findWebAppResultDetailByWebAppResultId(String web_app_result_id);

    public void deleteWebAppResultByWebAppResultId(String web_app_result_id);
}
