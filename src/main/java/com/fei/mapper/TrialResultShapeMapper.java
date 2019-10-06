package com.fei.mapper;

import com.fei.domain.AppResult;
import com.fei.domain.Participant;
import com.fei.domain.TrialResultShape;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TrialResultShapeMapper {


    @Select("SELECT * FROM t_trial_result_shape WHERE trial_result_id = #{trial_result_id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "trial_result_id",property = "trial_result_id"),
            @Result(column = "shape_id",property = "shape_id"),
            @Result(column = "type",property = "type"),
            @Result(column = "hit_count",property = "hit_count"),
            @Result(column = "grid_row",property = "grid_row"),
            @Result(column = "grid_column",property = "grid_column"),

            @Result(property = "shape", column = "shape_id",
                    one = @One(select = "com.fei.mapper.ShapeMapper.findShapeByShapeId")),
            @Result(property = "touchOrderList" , column="id",
                    many = @Many(select ="com.fei.mapper.TouchOrderMapper.findTouchOrdersByTrialResultShapeId")),
    })
    List<TrialResultShape> findTrialResultShapesByTrialResultId(String trial_result_id);



    @SelectKey(keyProperty = "id",resultType = String.class, before = true,
            statement = "select uuid()")
    @Options(useGeneratedKeys=true, keyProperty="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    @Insert("INSERT INTO t_trial_result_shape(id,trial_result_id,shape_id,type,hit_count,grid_row,grid_column)" +
            " VALUES(#{id},#{trial_result_id}, #{shape_id}, #{type}, #{hit_count}, #{grid_row}, #{grid_column})")
    void insertTrialResultShapeByTrialResultShape(TrialResultShape trialResultShape);
}
