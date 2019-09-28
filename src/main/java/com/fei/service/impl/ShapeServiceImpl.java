package com.fei.service.impl;

import com.fei.domain.Shape;
import com.fei.mapper.ShapeMapper;
import com.fei.service.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShapeServiceImpl implements ShapeService {

    @Autowired
    private ShapeMapper shapeMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Shape findShapeByShapeName(String shape_name){
        return shapeMapper.findShapeByShapeName(shape_name);
    }

}
