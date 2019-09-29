package com.fei.mapper;


import com.fei.domain.Shape;
import com.fei.domain.Trial;
import com.fei.domain.TrialShape;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 功能描述：访问数据库的接口
 */

public interface TrialMapper {

	/**
	 * 根据TrialId寻找Trial
	 */
	@Select("SELECT * FROM t_trial WHERE id = #{trial_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "web_app_id",property = "web_app_id"),
			@Result(column = "grid_row",property = "grid_row"),
			@Result(column = "grid_column",property = "grid_column"),
			@Result(column = "round",property = "round"),
			@Result(column = "timed",property = "timed"),
			@Result(column = "target_percentage",property = "target_percentage"),
			@Result(column = "near_distractor_percentage",property = "near_distractor_percentage"),
			@Result(column = "far_distractor_percentage",property = "far_distractor_percentage"),

			@Result(property = "webApp", column = "web_app_id",
					one = @One(select = "com.fei.mapper.WebAppMapper.findWebAppByWebAppIdWithoutList")),
			@Result(property = "trialShapeList" , column="id",
					many = @Many(select ="com.fei.mapper.TrialShapeMapper.findTrialShapeByTrialId")),
	})
	Shape findTrialByTrialId(String trial_id);


	/**
	 * 查看该webapp下的所有trial
	 */

	@Select("SELECT * FROM t_trial WHERE web_app_id = #{web_app_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "web_app_id",property = "web_app_id"),
			@Result(column = "grid_row",property = "grid_row"),
			@Result(column = "grid_column",property = "grid_column"),
			@Result(column = "round",property = "round"),
			@Result(column = "timed",property = "timed"),
			@Result(column = "target_percentage",property = "target_percentage"),
			@Result(column = "near_distractor_percentage",property = "near_distractor_percentage"),
			@Result(column = "far_distractor_percentage",property = "far_distractor_percentage"),

			@Result(property = "webApp", column = "web_app_id",
					one = @One(select = "com.fei.mapper.WebAppMapper.findWebAppByWebAppIdWithoutList")),
			@Result(property = "trialShapeList" , column="id",
					many = @Many(select ="com.fei.mapper.TrialShapeMapper.findTrialShapeByTrialId")),
	})
	List<Trial> findTrialByWebAppId(String web_app_id);

	/**
	 * 插入新trial
	 */
	@SelectKey(keyProperty = "id",resultType = String.class, before = true,
			statement = "select uuid()")
	@Options(useGeneratedKeys=true, keyProperty="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
	@Insert("INSERT INTO t_trial(id,web_app_id,round,grid_row,grid_column,timed,target_percentage,near_distractor_percentage,far_distractor_percentage)" +
			"VALUES(#{id},#{web_app_id}, #{round},#{grid_row},#{grid_column},#{timed},#{target_percentage},#{near_distractor_percentage},#{far_distractor_percentage})")
	Integer insertTrial(Trial trial);
}