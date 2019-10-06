package com.fei.service.impl;

import com.fei.domain.*;
import com.fei.mapper.ResultsMapper;
import com.fei.mapper.WebAppResultMapper;
import com.fei.service.ResultsService;
import com.fei.service.WebAppResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;

@Service
public class WebAppResultServiceImpl implements WebAppResultService {

    @Autowired
    private WebAppResultMapper webAppResultMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public WebAppResult findWebAppResultDetailByWebAppResultId(String web_app_result_id) {
        WebAppResult webAppResult = webAppResultMapper.findWebAppResultDetailByWebAppResultId(web_app_result_id);

        Integer numbers_of_trials = webAppResult.getWebApp().getNumbers_of_trials();

        Collections.sort(webAppResult.getTrialResultList(), new Comparator<TrialResult>() {
            @Override
            public int compare(TrialResult trialResult1, TrialResult trialResult2) {
                if (trialResult1.getRound() > trialResult2.getRound()) {
                    return 1;
                } else{
                    return -1;
                }
            }
        });

        //Sort Shape Order according to coordinator
        for (TrialResult trialResult: webAppResult.getTrialResultList()) {

            Collections.sort(trialResult.getTrialResultShapeList(), new Comparator<TrialResultShape>() {
                @Override
                public int compare(TrialResultShape trialResultShape1, TrialResultShape trialResultShape2) {
                    if(trialResultShape1.getGrid_row() > trialResultShape2.getGrid_row()){
                        return 1;
                    }else if(trialResultShape1.getGrid_row() < trialResultShape2.getGrid_row()){
                        return -1;
                    }else{
                        if(trialResultShape1.getGrid_column() > trialResultShape2.getGrid_column()){
                            return 1;
                        }else{
                            return -1;
                        }
                    }
                }
            });

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            //Sort touch Timeline
            for (TrialResultShape trialResultShape: trialResult.getTrialResultShapeList()){

                Collections.sort(trialResultShape.getTouchOrderList(), new Comparator<TouchOrder>() {
                    @Override
                    public int compare(TouchOrder touchOrder1, TouchOrder touchOrder2) {
                        if(touchOrder1.getTouch_time() > touchOrder2.getTouch_time()){
                            return 1;
                        }else{
                            return -1;
                        }
                    }
                });

                trialResultShape.readyToShow();     //给correct赋值，并且改变对应图片路径的名字
                System.out.println(trialResultShape);
            }
        }


        //Sort webAppResult Order according to coordinator
        Collections.sort(webAppResult.getTrialResultList(), new Comparator<TrialResult>() {
            @Override
            public int compare(TrialResult trialResult1, TrialResult trialResult2) {
                if (trialResult1.getRound() > trialResult2.getRound()) {
                    return 1;
                } else{
                    return -1;
                }
            }
        });


        return webAppResult;
    }
}
