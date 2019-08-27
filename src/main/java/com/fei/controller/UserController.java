package com.fei.controller;

import com.fei.domain.Favourite;
import com.fei.domain.JsonData;
import com.fei.domain.User;
import com.fei.mapper.UserMapper;
import com.fei.service.UserService;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/stimuli/user")
public class UserController {

	@Autowired
	private UserService userService;


	/**
	 * 功能描述：查找全部用户
	 * @return
	 */
	@GetMapping("find_all_user")
	@ResponseBody
	public Object findAll(){
		return JsonData.buildSuccess(userService.getAllUser());
	}

	/**
	 * 功能描述：插入一个新用户
	 * @return
	 */
	@PostMapping("insert_user")
	@ResponseBody
	public Object insertUser(@ModelAttribute User user){
		return JsonData.buildSuccess(userService.insertUser(user));
	}

	/**
	 * 功能描述：删除一个用户
	 * @return
	 */
	@GetMapping("del_user_by_id")
	@ResponseBody
	public Object delUserById(String id){
		return JsonData.buildSuccess(userService.deleteUserById(id));
	}

	/**
	 * 功能描述：修改一个用户
	 * @return
	 */
	@RequestMapping("update_user_by_id")
	@ResponseBody
	public Object updateUserById(User user){
		return JsonData.buildSuccess(userService.updateUserById(user));
	}

	/**
	 * 功能描述：用户登录
	 * 根据email，password查找该用户的详细信息
	 * @return
	 */
	@PostMapping("get_user_info")
	@ResponseBody
	public Object getUserInfo(String email, String password){
		return JsonData.buildSuccess(userService.getUserInfo(email, password));
	}
}
