package com.fei.mapper;


import com.fei.domain.Shape;
import com.fei.domain.TrialShape;
import com.fei.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 功能描述：访问数据库的接口
 */

public interface TrialShapeMapper {

	/**
	 * 查看该webapp下的所有target
	 */

	@Select("SELECT * FROM t_trial_shape WHERE id = #{trial_shape_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "shape_id",property = "shape_id"),
			@Result(column = "trial_id",property = "trial_id"),
			@Result(column = "type",property = "type"),
			@Result(property = "shape", column = "shape_id",
					one = @One(select = "com.fei.mapper.ShapeMapper.findShapeByShapeId"))
	})
	Shape findTrialShapeByTrialShapeId(String trial_shape_id);


	@Select("SELECT * FROM t_trial_shape WHERE trial_id = #{trial_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "shape_id",property = "shape_id"),
			@Result(column = "trial_id",property = "trial_id"),
			@Result(column = "type",property = "type"),
			@Result(property = "shape", column = "shape_id",
					one = @One(select = "com.fei.mapper.ShapeMapper.findShapeByShapeId"))
	})
	List<TrialShape> findTrialShapeByTrialId(String trial_id);


	/**
	 * 插入新trial_shape
	 */
	@SelectKey(keyProperty = "id",resultType = String.class, before = true,
			statement = "select uuid()")
	@Options(useGeneratedKeys=true, keyProperty="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
	@Insert("INSERT INTO t_trial_shape(id,shape_id,trial_id,type)" +
			"VALUES(#{id},#{shape_id}, #{trial_id}, #{type})")
	Integer insertTrialShape(TrialShape trialShape);
}