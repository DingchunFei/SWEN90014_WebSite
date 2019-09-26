/*
package com.fei.mapper;


import com.fei.domain.ResultList;
import com.fei.domain.WebApp;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

*/
/**
 * 功能描述：访问数据库的接口
 *//*


public interface ResultListMapper {

	*/
/**
	 * 根据web_app_id查看由该web_app下的所有测试信息
	 *//*

	@Select("SELECT id, web_app_id,p_username,p_age,p_gender,touch_history,accuracy,time_consumption,DATE_FORMAT(DATE, '%Y-%m-%d %H:%i:%s') `date`  FROM t_result_list WHERE web_app_id = #{web_app_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "web_app_id",property = "web_app_id"),
			@Result(column = "p_username",property = "p_username"),
			@Result(column = "p_age",property = "p_age"),
			@Result(column = "p_gender",property = "p_gender"),
			@Result(column = "date",property = "date"),
			@Result(column = "touch_history",property = "touch_history"),
			@Result(column = "accuracy",property = "accuracy"),
			@Result(column = "time_consumption",property = "time_consumption"),
			@Result(property = "webApp", column = "web_app_id",
					one = @One(select = "com.fei.mapper.WebAppMapper.findWebAppByWebAppIdWithoutList"))
	})
	ResultList findResultListByWebAppId(String web_app_id);


	*/
/**
	 * 根据web_app_id查看由该web_app下的所有测试信息,去除webApp字段
	 *//*

	@Select("SELECT id, web_app_id,p_username,p_age,p_gender,touch_history,accuracy,time_consumption,DATE_FORMAT(DATE, '%Y-%m-%d %H:%i:%s') `date`  FROM t_result_list WHERE web_app_id = #{web_app_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "web_app_id",property = "web_app_id"),
			@Result(column = "p_username",property = "p_username"),
			@Result(column = "p_age",property = "p_age"),
			@Result(column = "p_gender",property = "p_gender"),
			@Result(column = "date",property = "date"),
			@Result(column = "touch_history",property = "touch_history"),
			@Result(column = "accuracy",property = "accuracy"),
			@Result(column = "time_consumption",property = "time_consumption"),
	})
	ResultList findResultListByWebAppIdWithoutWebApp(String web_app_id);
}*/
