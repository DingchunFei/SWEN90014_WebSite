package com.fei.mapper;


import com.fei.domain.Favourite;
import com.fei.domain.WebAppTarget;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 功能描述：访问数据库的接口
 */

public interface WebAppTargetMapper {

	/**
	 * 根据web_app_id查看由该web_app下的所有测试信息
	 */
	@Select("SELECT * FROM t_web_app_target WHERE web_app_id = #{web_app_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "web_app_id",property = "web_app_id"),
			@Result(column = "target_id",property = "target_id"),
			@Result(column = "round",property = "round"),
			@Result(property = "target", column = "target_id",
					one = @One(select = "com.fei.mapper.TargetMapper.findTargetByTargetId"))
	})
	List<WebAppTarget> findWebAppTargetByWebAppIdWithoutWebApp(String web_app_id);


}