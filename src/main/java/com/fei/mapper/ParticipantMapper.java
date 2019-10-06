package com.fei.mapper;

import com.fei.domain.Participant;
import com.fei.domain.TrialResult;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ParticipantMapper {

    @Select("SELECT * FROM t_participant WHERE id = #{participaint_id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "user_id",property = "user_id"),
            @Result(column = "name",property = "name"),
/*
            @Result(property = "webAppResultList" , column="id",
                    many = @Many(select ="com.fei.mapper.webAppResultMapper.findWebAppResultByParticipantId")),*/
    })
    Participant findParticipantByParticipantId(String participaint_id);

}
