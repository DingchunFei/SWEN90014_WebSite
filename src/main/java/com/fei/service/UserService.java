package com.fei.service;

import com.fei.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

	public List<User> getAllUser();

	public Integer insertUser(User user);

	public Integer deleteUserById(String id);

	public Integer updateUserById(User user);

	public Integer updateUser(User user);

	public User getUserInfo(String email, String password);

	public Long checkEmail(String email);

	public User refreshUserInfo(String id);
	
}
