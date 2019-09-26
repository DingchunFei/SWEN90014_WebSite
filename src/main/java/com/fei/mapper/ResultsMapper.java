package com.fei.mapper;

import com.fei.domain.AppResult;
import com.fei.domain.WebApp;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;



import java.util.List;

public interface ResultsMapper {

/*    @Select("SELECT id, web_app_id,user_id,test_code,p_username,DATE_FORMAT(DATE, '%Y-%m-%d %H:%i:%s') `p_birthday`,DATE_FORMAT(DATE, '%Y-%m-%d %H:%i:%s') `test_date`," +
            "right,wrong,wrongOrder,accuracy,totalShapes,targetAmount,nearDistractorAmount,farDistractorAmount  FROM t_results WHERE web_app_id = #{web_app_id}")*/

    @Select("SELECT * FROM t_results WHERE web_app_id = #{web_app_id}")
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
    List<AppResult> findResultsByWebAppIdWithoutWebApp(String web_app_id);


    /**
     * 根据result_id查看该result的详细信息
     */
    @Select("SELECT * FROM t_results WHERE id = #{result_id}")
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
    AppResult findResultDetailByResultId(String result_id);

}
