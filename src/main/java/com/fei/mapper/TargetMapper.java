package com.fei.mapper;


import com.fei.domain.Target;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 功能描述：访问数据库的接口
 */

public interface TargetMapper {

	/**
	 * 查看该webapp下的所有target
	 */

	@Select("SELECT * FROM t_target WHERE id = #{target_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "shape",property = "shape"),
			@Result(column = "curve",property = "curve"),
			@Result(column = "direction",property = "direction"),
	})
	List<Target> findTargetByTargetId(String target_id);

}