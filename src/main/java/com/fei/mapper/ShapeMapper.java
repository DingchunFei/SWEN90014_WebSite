package com.fei.mapper;


import com.fei.domain.Shape;
import com.fei.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 功能描述：访问数据库的接口
 */

public interface ShapeMapper {

	/**
	 * 根据id查看shape
	 */
	@Select("SELECT * FROM t_shape WHERE id = #{shape_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "s_name",property = "s_name"),
	})
	Shape findShapeByShapeId(String shape_id);

	/**
	 * 根据name查看shape
	 */
	@Select("SELECT * FROM t_shape WHERE s_name = #{shape_name}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "s_name",property = "s_name"),
	})
	Shape findShapeByShapeName(String shape_name);
}