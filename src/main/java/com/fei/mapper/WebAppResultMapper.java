package com.fei.mapper;

import com.fei.domain.WebAppResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface WebAppResultMapper {

    @Select("SELECT * FROM t_web_app_result WHERE web_app_id = #{web_app_id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "web_app_id",property = "web_app_id"),
            @Result(column = "participant_id",property = "participant_id"),
            @Result(column = "total_accuracy",property = "total_accuracy"),
            @Result(column = "test_date",property = "test_date"),


            @Result(property = "webApp", column = "web_app_id",
                    one = @One(select = "com.fei.mapper.WebAppMapper.findWebAppByWebAppIdWithoutList")),
            @Result(property = "participant", column = "participant_id",
                    one = @One(select = "com.fei.mapper.ParticipantMapper.findParticipantByParticipantId"))
    })
    List<WebAppResult> findWebAppResultByWebAppIdWithoutDetail(String web_app_id);


    @Select("SELECT * FROM t_web_app_result WHERE participant_id = #{participant_id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "web_app_id",property = "web_app_id"),
            @Result(column = "participant_id",property = "participant_id"),
            @Result(column = "total_accuracy",property = "total_accuracy"),
            @Result(column = "test_date",property = "test_date"),

            @Result(property = "webApp", column = "web_app_id",
                    one = @One(select = "com.fei.mapper.WebAppMapper.findWebAppByWebAppIdWithoutList"))
    })
    List<WebAppResult> findWebAppResultByParticipantId(String participant_id);


    @Select("SELECT * FROM t_web_app_result WHERE id = #{web_app_result_id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "web_app_id",property = "web_app_id"),
            @Result(column = "participant_id",property = "participant_id"),
            @Result(column = "total_accuracy",property = "total_accuracy"),
            @Result(column = "test_date",property = "test_date"),


            @Result(property = "webApp", column = "web_app_id",
                    one = @One(select = "com.fei.mapper.WebAppMapper.findWebAppByWebAppIdWithoutList")),
            @Result(property = "participant", column = "participant_id",
                    one = @One(select = "com.fei.mapper.ParticipantMapper.findParticipantByParticipantId")),
            @Result(property = "trialResultList" , column="id",
                    many = @Many(select ="com.fei.mapper.TrialResultMapper.findTrialResultsByWebAppResultId")),
    })
    WebAppResult findWebAppResultDetailByWebAppResultId(String web_app_result_id);



    @SelectKey(keyProperty = "id",resultType = String.class, before = true,
            statement = "select uuid()")
    @Options(useGeneratedKeys=true, keyProperty="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    @Insert("INSERT INTO t_web_app_result(id,web_app_id,participant_id,total_accuracy,test_date) " +
            "VALUES(#{id},#{web_app_id}, #{participant_id}, #{total_accuracy}, #{test_date})")
    void insertWebAppResultByWebAppResult(WebAppResult webAppResult);

    @Delete("DELETE FROM t_web_app_result WHERE id =#{web_app_result_id}")
    void deleteWebAppResultByWebAppResultId(String web_app_result_id);

/*
    @Select("SELECT * FROM t_web_app_result WHERE web_app_id = #{web_app_id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "web_app_id",property = "web_app_id"),
            @Result(column = "participant_id",property = "participant_id"),
            @Result(column = "total_accuracy",property = "total_accuracy"),
            @Result(column = "test_date",property = "test_date"),
    })
    List<WebAppResult> findResultsByWebAppIdWithoutDetail(String web_app_id);*/

    /**
     * 根据result_id查看该result的详细信息
     */
    /*@Select("SELECT * FROM t_web_app_result WHERE id = #{result_id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "web_app_id",property = "web_app_id"),
            @Result(column = "user_id",property = "user_id"),
            @Result(column = "test_code",property = "test_code"),
            @Result(column = "p_gender",property = "p_gender"),
            @Result(column = "p_username",property = "p_username"),
            @Result(column = "p_birthday",property = "p_birthday"),
            @Result(column = "test_date",property = "test_date"),
            @Result(column = "right",property = "right"),
            @Result(column = "wrong",property = "wrong"),
            @Result(column = "wrongOrder",property = "wrongOrder"),
            @Result(column = "accuracy",property = "accuracy"),
            @Result(column = "totalShapes",property = "totalShapes"),
            @Result(column = "targetAmount",property = "targetAmount"),
            @Result(column = "nearDistractorAmount",property = "nearDistractorAmount"),
            @Result(column = "farDistractorAmount",property = "farDistractorAmount"),

            @Result(property = "webApp", column = "web_app_id",
                    one = @One(select = "com.fei.mapper.WebAppMapper.findWebAppByWebAppIdWithoutList"))
    })
    AppResult findResultDetailByResultId(String result_id);*/

}
