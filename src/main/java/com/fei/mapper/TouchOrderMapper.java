package com.fei.mapper;

import com.fei.domain.TouchOrder;
import com.fei.domain.TrialResultShape;
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
}
