package com.fei.service.impl;

import com.fei.domain.*;
import com.fei.mapper.*;
import com.fei.mapper.ShapeMapper;
import com.fei.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantMapper participantMapper;

    @Autowired
    private WebAppResultMapper webAppResultMapper;

    @Autowired
    private TrialResultMapper trialResultMapper;

    @Autowired
    private TrialResultShapeMapper trialResultShapeMapper;

    @Autowired
    private TouchOrderMapper touchOrderMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertParticipantByParticipant(Participant participant) {
        participantMapper.insertParticipantByParticipant(participant);

        //其实插入的话就一个
        WebAppResult webAppResult = participant.getWebAppResultList().get(0);
        //上面插入成功后，mybatis自动给当前participant设置了主键id
        webAppResult.setParticipant_id(participant.getId());

        webAppResultMapper.insertWebAppResultByWebAppResult(webAppResult);

        List<TrialResult> trialResultList = webAppResult.getTrialResultList();

        for (TrialResult trialResult: trialResultList) {
            //设置上面插入后产生的WebAppResultId
            trialResult.setWeb_app_result_id(webAppResult.getId());
            trialResultMapper.insertTrialResultByTrialResult(trialResult);

            List<TrialResultShape> trialResultShapeList = trialResult.getTrialResultShapeList();
            for(TrialResultShape trialResultShape : trialResultShapeList){
                //设置上面插入后产生的TrialResultId
                trialResultShape.setTrial_result_id(trialResult.getId());
                trialResultShapeMapper.insertTrialResultShapeByTrialResultShape(trialResultShape);

                if(trialResultShape.getTouchOrderList()!=null){         //如果该图形没有被点过则不需要插入
                    List<TouchOrder> touchOrderList = trialResultShape.getTouchOrderList();
                    for(TouchOrder touchOrder : touchOrderList){
                        touchOrder.setTrial_result_shape_id(trialResultShape.getId());
                        touchOrderMapper.insertTouchOrderByTouchOrder(touchOrder);
                    }
                }

            }
        }
    }
}
