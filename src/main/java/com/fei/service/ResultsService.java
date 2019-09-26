package com.fei.service;

import com.fei.domain.AppResult;
import org.springframework.stereotype.Service;


@Service
public interface ResultsService {

    public AppResult findResultDetailByResultId(String result_id);
}
