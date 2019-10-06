package com.fei.service;

import com.fei.domain.Shape;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Service
public interface ShapeService {

    public String findShapeIdByShapeName(String shape_name);

    public Shape findShapeByShapeName(String shape_name);

}
