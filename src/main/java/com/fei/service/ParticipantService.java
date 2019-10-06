package com.fei.service;

import com.fei.domain.Participant;
import org.springframework.stereotype.Service;

@Service
public interface ParticipantService {

    public void insertParticipantByParticipant(Participant participant);

}
