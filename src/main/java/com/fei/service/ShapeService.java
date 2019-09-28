package com.fei.service;

import com.fei.domain.Shape;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ShapeService {


    public Shape findShapeByShapeName(String shape_name);

}
