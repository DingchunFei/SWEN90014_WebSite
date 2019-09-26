/*
package com.fei.service.impl;

import com.fei.domain.AppResult;
import com.fei.mapper.ResultsMapper;
import com.fei.service.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResultsServiceImpl implements ResultsService {

    @Autowired
    private ResultsMapper resultsMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AppResult findResultListByWebAppId(String web_app_id) {
        return resultsMapper.findResultListByWebAppId(web_app_id);
    }
}
*/
