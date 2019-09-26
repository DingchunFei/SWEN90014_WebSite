package com.fei.service.impl;

import com.fei.domain.Favourite;
import com.fei.mapper.FavouriteMapper;
import com.fei.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouriteMapper favouriteMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateFavourite(Favourite favourite) {
        return favouriteMapper.updateFavourite(favourite);
    }
}
