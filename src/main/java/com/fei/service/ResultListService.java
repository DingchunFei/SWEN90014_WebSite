package com.fei.service;

import com.fei.domain.ResultList;
import org.springframework.stereotype.Service;


@Service
public interface ResultListService {

    public ResultList findResultListByWebAppId(String web_app_id);
}
