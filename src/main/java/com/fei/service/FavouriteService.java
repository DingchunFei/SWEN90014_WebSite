package com.fei.service;

import com.fei.domain.Favourite;
import com.fei.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface FavouriteService {

	public Integer updateFavourite(Favourite favourite);

}
