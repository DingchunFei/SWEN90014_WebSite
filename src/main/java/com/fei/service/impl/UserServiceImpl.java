package com.fei.service.impl;

import java.util.List;


import com.fei.domain.User;
import com.fei.mapper.UserMapper;
import com.fei.service.UserService;
import com.fei.utils.User_favourite_sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
	 private UserMapper userMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer insertUser(User user) {
		return userMapper.insertUser(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteUserById(String id) {
		return userMapper.deleteUserById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer updateUserById(User user) {
		return userMapper.updateUserById(user);
	}

	@Override
	public Integer updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public User getUserInfo(String email, String password) {
		User user = userMapper.getUserInfo(email,password);
		if(user == null){
			return null;
		}
		System.out.println(user);
		if(user.getFavourites()!=null) {
			User_favourite_sort.userFavouriteSort(user.getFavourites());        //更具favourite优先级排序
		}
		return user;
	}

	@Override
	public Long checkEmail(String email) {
		return userMapper.checkEmail(email);
	}

	@Override
	public User refreshUserInfo(String id) {
		User user = userMapper.refreshUserInfo(id);
		if(user.getFavourites()!=null){
			User_favourite_sort.userFavouriteSort(user.getFavourites());		//更具favourite优先级排序
		}
		return user;
	}

	 
/*	@Override
	public int add(User user) {
		userMapper.insert(user);
		int id = user.getId();
		return id;
	}
	
	*/
	

	
	
}
