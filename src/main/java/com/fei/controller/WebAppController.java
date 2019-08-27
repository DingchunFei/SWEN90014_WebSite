package com.fei.controller;

import com.fei.domain.JsonData;
import com.fei.domain.User;
import com.fei.service.UserService;
import com.fei.service.WebAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/stimuli/web_app")
public class WebAppController {

	@Autowired
	private WebAppService webAppService;

	@Autowired
	private UserService userService;

	/**
	 * 功能描述：查看该用户的所有web_app
	 * @return
	 */
	@GetMapping("find_webApp_by_webAppId")
	public Object findWebAppByWebAppId(String user_id){
		return JsonData.buildSuccess(webAppService.findWebAppByWebAppId(user_id));
	}


}
