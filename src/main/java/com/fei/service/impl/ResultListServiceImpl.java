package com.fei.service.impl;

import com.fei.domain.ResultList;
import com.fei.mapper.ResultListMapper;
import com.fei.service.ResultListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResultListServiceImpl implements ResultListService {

    @Autowired
    private ResultListMapper resultListMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultList findResultListByWebAppId(String web_app_id) {
        return resultListMapper.findResultListByWebAppId(web_app_id);
    }
}
