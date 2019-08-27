package com.fei.controller;

import com.fei.domain.User;
import com.fei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/login/")
public class LoginController {

    @Autowired
    private UserService userService;

    //登录页面
    @RequestMapping("signin")
    public Object signin() {
        return "signin";
    }

    //提交登录信息
    @PostMapping("signin_check")
    //@ResponseBody
    public Object signinCheck(String email, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User userInfo = userService.getUserInfo(email, password);
        if (userInfo != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", userInfo);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(userInfo.getFavourites());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

            return "redirect:/stimuli/home";
        } else {
            //response.sendRedirect("/stimuli/signin");
            return "redirect:signin";
        }
    }


    /**
     * 测试接口使用
     */
    @PostMapping("test_userinfo")
    @ResponseBody
    public Object signinCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User userInfo = userService.getUserInfo("chun@chun", "111111");
        return userInfo;
    }

    //注册页面
   @RequestMapping("signup")
    public Object signup() {
        return "signup";
    }

    //提交注册信息
    @PostMapping("signup_check")
    //@ResponseBody
    public Object signupCheck(String email, String password, String institution, Integer status, Integer gender, String username,
                              String full_name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User newUser = new User(username, password, email, gender, status);
        if(institution!=null){
            newUser.setInstitution(institution);
        }
        if(full_name!=null){
            newUser.setFull_name(full_name);
        }
        int flag = userService.insertUser(newUser);
        if(flag==1){
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();
            session.setAttribute("userInfo", newUser);
            return "redirect:/stimuli/home";
        }
        return "signup";
    }

    /**
     * 检查邮箱是否被占用
     * @param email
     * @return
     */
    @PostMapping("email_check")
    @ResponseBody
    public Object emailCheck(String email){
        Long exist = userService.checkEmail(email);
        if(exist >0){return 0;}
        else{return 1;}
    }
}