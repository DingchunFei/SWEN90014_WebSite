package com.fei.mapper;

import com.fei.domain.TouchOrder;
import com.fei.domain.TrialResultShape;
import com.fei.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TouchOrderMapper {

    @Select("SELECT * FROM t_touch_order WHERE trial_result_shape_id = #{trial_result_shape_id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "trial_result_shape_id",property = "trial_result_shape_id"),
            @Result(column = "touch_time",property = "touch_time")
    })
    List<TouchOrder> findTouchOrdersByTrialResultShapeId(String trial_result_shape_id);



    @SelectKey(keyProperty = "id",resultType = String.class, before = true,
            statement = "select uuid()")
    @Options(useGeneratedKeys=true, keyProperty="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    @Insert("INSERT INTO t_touch_order(id,trial_result_shape_id,touch_time)" +
            "VALUES(#{id},#{trial_result_shape_id}, #{touch_time})")
    void insertTouchOrderByTouchOrder(TouchOrder touchOrder);

}
