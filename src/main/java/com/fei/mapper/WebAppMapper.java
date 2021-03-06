package com.fei.mapper;


import com.fei.domain.User;
import com.fei.domain.WebApp;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 功能描述：访问数据库的接口
 */

public interface WebAppMapper {

	/**
	 * 根据webapp_id 查看该webapp的详细信息,填充内置数组
	 */
	@Select("SELECT * FROM t_web_app WHERE id = #{web_app_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "app_name",property = "app_name"),
			@Result(column = "URL",property = "URL"),
			@Result(column = "age",property = "age"),
			@Result(column = "date",property = "date"),
			@Result(column = "numbers_of_trials",property = "numbers_of_trials"),
			@Result(column = "notes",property = "notes"),
			@Result(property = "practitioners_Num", column = "id",			//查看一共有几个Practitoner有权限使用这个模板
					one = @One(select = "com.fei.mapper.FavouriteMapper.findPractitionersNumByWebAppId")),
/*			@Result(property = "resultLists" , column="id",
					many = @Many(select ="com.fei.mapper.ResultListMapper.findResultListByWebAppIdWithoutWebApp")),
			@Result(property = "webAppTargets" , column="id",
					many = @Many(select ="com.fei.mapper.WebAppTargetMapper.findWebAppTargetByWebAppIdWithoutWebApp")),*/

			@Result(property = "webAppResultList" , column="id",
					many = @Many(select ="com.fei.mapper.WebAppResultMapper.findWebAppResultByWebAppIdWithoutDetail")),
			@Result(property = "practitioners" , column="id",
					many = @Many(select ="com.fei.mapper.FavouriteMapper.findPractitionersByWebAppId")),
			@Result(property = "trials" , column="id",
					many = @Many(select ="com.fei.mapper.TrialMapper.findTrialByWebAppId")),
	})
	WebApp findWebAppByWebAppId(String web_app_id);


	/**
	 * 根据webapp_id 查看该webapp的详细信息,填充内置数组
	 */
	@Select("SELECT * FROM t_web_app WHERE id = #{web_app_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "user_id",property = "user_id"),
			@Result(column = "app_name",property = "app_name"),
			@Result(column = "URL",property = "URL"),
			@Result(column = "age",property = "age"),
			@Result(column = "date",property = "date"),
			@Result(column = "numbers_of_trials",property = "numbers_of_trials"),
			@Result(column = "notes",property = "notes"),
	})
	WebApp findWebAppByWebAppIdWithoutList(String web_app_id);

	@SelectKey(keyProperty = "id",resultType = String.class, before = true,
			statement = "select uuid()")
	@Options(useGeneratedKeys=true, keyProperty="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
	@Insert("INSERT INTO t_web_app(id,URL,app_name,age,date,notes,numbers_of_trials) " +
			"VALUES(#{id},#{URL},#{app_name},#{age},#{date},#{notes},#{numbers_of_trials})")
	Integer insertWebApp (WebApp webApp);


/*	@Update("UPDATE t_web_app SET URL=#{URL},gender_preferrence=#{gender_preferrence},grid_row=#{grid_row},grid_column=#{grid_column},near_percentage=#{near_percentage},far_percentage=#{far_percentage}," +
			"timed=#{timed},app_name=#{app_name},age=#{age},numbers_of_targets=#{numbers_of_targets},notes=#{notes},numbers_of_trials=#{numbers_of_trials} WHERE id=#{id}")
	Integer updateWebApp (WebApp webApp);*/

	@Update("UPDATE t_web_app SET app_name=#{app_name},age=#{age},notes=#{notes} WHERE id=#{id}")
	Integer updateWebApp (WebApp webApp);

	@Delete("DELETE FROM t_web_app WHERE id =#{webApp_id}")
	Integer deleteWebAppByWebAppId (String webApp_id);

	@Select("SELECT id FROM t_web_app WHERE URL = #{URL}")
	String findWebAppIdByWebAppURL(String URL);
}