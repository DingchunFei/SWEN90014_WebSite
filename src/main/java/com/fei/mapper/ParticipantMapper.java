package com.fei.mapper;

import com.fei.domain.Participant;
import com.fei.domain.TrialResult;
import com.fei.domain.User;
import org.apache.ibatis.annotations.*;

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



    @SelectKey(keyProperty = "id",resultType = String.class, before = true,
            statement = "select uuid()")
    @Options(useGeneratedKeys=true, keyProperty="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    @Insert("INSERT INTO t_participant(id,name,user_id) VALUES(#{id},#{name}, #{user_id})")
    void insertParticipantByParticipant(Participant participant);
}
