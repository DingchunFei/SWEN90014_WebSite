package com.fei.controller;


import com.fei.domain.Favourite;
import com.fei.domain.User;
import com.fei.mapper.UserMapper;
import com.fei.service.FavouriteService;
import com.fei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/stimuli/favourite")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @Autowired
    private UserService userService;

    //登录页面
    @RequestMapping("favourite_ajax")
    @ResponseBody
    public Object favourite_ajax(Favourite favourite,HttpServletRequest req) {
        favouriteService.updateFavourite(favourite);
        HttpSession session = req.getSession();
        User user = userService.refreshUserInfo(favourite.getUser_id());

        session.setAttribute("userInfo",user);

        return 1;
    }
}
