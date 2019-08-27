package com.fei.controller;

import com.fei.domain.Favourite;
import com.fei.domain.User;
import com.fei.domain.WebApp;
import com.fei.service.UserService;
import com.fei.service.WebAppService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/stimuli/")
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private WebAppService  webAppService;

    //登录页面
    @RequestMapping("home")
    public Object home(){
        return "web_app_list";
    }

    //登录页面
    @RequestMapping("results")
    public Object resultList(){
        return "result_list";
    }


    //请求转发到new webapp界面
    @RequestMapping("newWebApp")
    public Object newWebApp(){
        return "new_web_app";
    }

    //插入一个新的webapp
    @RequestMapping("addNewWebApp")
    public Object addNewWebApp(String [] gender_preferrence, String [] age , WebApp webApp, @RequestParam(name = "Email") String [] email , HttpServletRequest req){
        System.out.println("-------------------------------------------------------");

        if(gender_preferrence.length==2) {        //表示两个性别都可以
            webApp.setGender_preferrence(2);
        }else{
            if(gender_preferrence[0].equals("1")){  //男的
                webApp.setGender_preferrence(1);
            }else if(gender_preferrence[0].equals("2")){  //女的
                webApp.setGender_preferrence(0);
            }
        }

        String str="000";           //String不可变，需要变成StringBuilder
        StringBuilder strBuilder = new StringBuilder(str);

        for(int i=0;i<age.length; i++){
            System.out.println("age =======================>" + age[i]);
            if(age[i].equals("1")){
                strBuilder.setCharAt(0, '1');
            }else if(age[i].equals("2")){
                strBuilder.setCharAt(1, '1');
            }else if(age[i].equals("4")){
                strBuilder.setCharAt(2, '1');
            }
        }
        webApp.setAge(strBuilder.toString());


        if(webApp.getTimed()==null){
            webApp.setTimed(0);
        }

        webApp.setDate(new Date());



        HttpSession session = req.getSession();             //取出session中的用户id，提供后续webapp的时候使用
        User userInfo = (User) session.getAttribute("userInfo");
        webApp.setUser_id(userInfo.getId());

        User user = null;

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(webApp);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        if(webApp.getId()!=null){                                  //通过webappId是否存在来判断 更新 / 插入
            user = webAppService.updateWebApp(webApp,email,userInfo.getId());      //更新WebApp成功，重置session
        }else{
            user = webAppService.insertWebApp(webApp,email);      //新WebApp插入成功，重置session
        }

        User refreshUser = userService.refreshUserInfo(user.getId());       //插入后更新用户信息


        if(refreshUser!=null){
            session.setAttribute("userInfo", refreshUser);
        }


        System.out.println(webApp);

        System.out.println("-------------------------------------------------------");
        return "web_app_list";
    }



    /**
     * 功能描述：根据web_app_id删除一个web_app
     * @return
     */
    @GetMapping("delete_webApp")
    public Object deleteWebAppByWebAppId(String webApp_id, HttpServletRequest req){
        Integer flag = webAppService.deleteWebAppByWebAppId(webApp_id);
        if(flag ==1){
            HttpSession session = req.getSession();
            User userInfo = (User) session.getAttribute("userInfo");

            User newUserInfo = userService.refreshUserInfo(userInfo.getId());			//WebApp删除成功，重置session

            if(newUserInfo!=null){
                session.setAttribute("userInfo", newUserInfo);
            }

            return "web_app_list";
        }
        return "web_app_list";			//有问题 需要改404
    }

    /**
     * 功能描述：根据web_app_id修改一个web_app
     * @return
     */
    @GetMapping("edit_webApp")
    public Object editWebAppByWebAppId(String webApp_id, HttpServletRequest req){

        WebApp webApp = webAppService.findWebAppByWebAppId(webApp_id);
        System.out.println(webApp);
        HttpSession session = req.getSession();
        session.setAttribute("webApp",webApp);

        return "web_app_edit";
    }

    /**
     * 查看webApp的详细信息
     * @param webApp_id
     * @return
     */
    @GetMapping("web_app_detail")
    public Object viewAppDetail(String webApp_id, HttpServletRequest req){
        WebApp webApp = webAppService.findWebAppByWebAppId(webApp_id);
        System.out.println(webApp);
        HttpSession session = req.getSession();
        session.setAttribute("webApp",webApp);

        return "web_app_detail";
    }

    /**
     * 对应不同条件的筛选器
     * @return
     */
    @GetMapping("web_app_list_male")
    public Object WebAppListMale(){
        return "web_app_list_male";
    }
    @GetMapping("web_app_list_female")
    public Object WebAppListFemale(){
        return "web_app_list_female";
    }
    @GetMapping("web_app_list_young")
    public Object WebAppListYoung(){
        return "web_app_list_young";
    }
    @GetMapping("web_app_list_adult")
    public Object WebAppListAdult(){
        return "web_app_list_adult";
    }
    @GetMapping("web_app_list_old")
    public Object WebAppListOld(){
        return "web_app_list_old";
    }
    @GetMapping("web_app_list_favourite")
    public Object WebAppListFavourite(){
        return "web_app_list_favourite";
    }

    /**
     * 对应不同条件的排序器
     * @return
     */
    @GetMapping("web_app_list_S_stimuli")
    public Object WebAppListSortByStimuli(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        List<Favourite> favourites = user.getFavourites();
        System.out.println();
        Collections.sort(favourites,new Comparator<Favourite>(){
            public int compare(Favourite fav1, Favourite fav2) {
                return fav1.getWebApp().getApp_name().compareTo(fav2.getWebApp().getApp_name());
            }
        });
        return "web_app_list";
    }

    @GetMapping("web_app_list_S_age")
    public Object WebAppListSortByAge(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        List<Favourite> favourites = user.getFavourites();
        System.out.println();
        Collections.sort(favourites,new Comparator<Favourite>(){
            public int compare(Favourite fav1, Favourite fav2) {
                return fav2.getWebApp().getAge().compareTo(fav1.getWebApp().getAge());
            }
        });
        return "web_app_list";
    }
    @GetMapping("web_app_list_S_date")
    public Object WebAppListSortByDate(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        List<Favourite> favourites = user.getFavourites();
        System.out.println();
        Collections.sort(favourites,new Comparator<Favourite>(){
            public int compare(Favourite fav1, Favourite fav2) {
                return fav2.getWebApp().getDate().compareTo(fav1.getWebApp().getDate());
            }
        });
        return "web_app_list";
    }
}


