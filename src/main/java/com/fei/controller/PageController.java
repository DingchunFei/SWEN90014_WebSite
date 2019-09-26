package com.fei.controller;

import com.fei.domain.Favourite;
import com.fei.domain.User;
import com.fei.domain.WebApp;
import com.fei.service.UserService;
import com.fei.service.WebAppService;
import com.fei.utils.HttpUtils;
import com.fei.utils.JsonUtils;
import com.fei.utils.WebApp2Json;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/stimuli/")
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private WebAppService  webAppService;

    //登录页面
    @RequestMapping("home")
    public Object home(HttpServletRequest req){

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        //每次使用前刷新一次
        user = userService.refreshUserInfo(user.getId());

        session.setAttribute("userInfo",user);

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

    //请求转发到new webapp界面
    @RequestMapping("editProfile")
    public Object editProfile(){
        return "profile";
    }

    @RequestMapping("uploadImg")
    public Object uploadImg(@RequestParam("image_upload")MultipartFile file, HttpServletRequest request) throws FileNotFoundException {

        String userId = request.getParameter("userId");
        System.out.println("图片上传的userid为"+userId);

        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        fileName = UUID.randomUUID() + suffixName;
        //获取项目的绝对路径 ResourceUtils.getURL()
        File dest = new File(ResourceUtils.getURL("classpath:").getPath()+"/static/images/"+fileName);
        System.out.println("static/images/"+fileName);

        User user = new User();
        user.setId(userId);
        user.setImg_src("/images/"+fileName);

        try{
            file.transferTo(dest);
            userService.updateUser(user);
            //刷新用戶信息
            user = userService.refreshUserInfo(user.getId());
            request.getSession().setAttribute("userInfo",user);
            return "profile";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "profile";
    }

   /* //插入一个新的webapp
    @RequestMapping("addNewWebApp")
    public Object addNewWebApp(String [] age , WebApp webApp, @RequestParam(name = "Email") String [] email , HttpServletRequest req){
        System.out.println("-------------------------------------------------------");

        String str="0000000";           //String不可变，需要变成StringBuilder
        StringBuilder strBuilder = new StringBuilder(str);

        for(int i=0;i<age.length; i++){
            System.out.println("age =======================>" + age[i]);
            if(age[i].equals("1")){
                strBuilder.setCharAt(0, '1');
            }else if(age[i].equals("2")){
                strBuilder.setCharAt(1, '1');
            }else if(age[i].equals("3")){
                strBuilder.setCharAt(2, '1');
            }else if(age[i].equals("4")){
                strBuilder.setCharAt(3, '1');
            }else if(age[i].equals("5")){
                strBuilder.setCharAt(4, '1');
            }else if(age[i].equals("6")){
                strBuilder.setCharAt(5, '1');
            }else if(age[i].equals("7")){
                strBuilder.setCharAt(6, '1');
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
    }*/



    //插入一个新的webapp
    @RequestMapping("addNewWebApp")
    public Object addNewWebApp(String [] age , WebApp webApp, @RequestParam(name = "Email") String [] email , HttpServletRequest req, HttpServletResponse resp){



        HttpSession session = req.getSession();             //取出session中的用户id，提供后续webapp的时候使用
        User userInfo = (User) session.getAttribute("userInfo");
        webApp.setUser_id(userInfo.getId());


        User user = null;

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(webApp);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        if(webApp.getId()!=null){                                  //通过webappId是否存在来判断 更新

            user = webAppService.updateWebApp(webApp,email,userInfo.getId());      //更新WebApp成功，重置session
        }else{

            String str="0000000";           //String不可变，需要变成StringBuilder
            StringBuilder strBuilder = new StringBuilder(str);

            for(int i=0;i<age.length; i++){
                System.out.println("age =======================>" + age[i]);
                if(age[i].equals("1")){
                    strBuilder.setCharAt(0, '1');
                }else if(age[i].equals("2")){
                    strBuilder.setCharAt(1, '1');
                }else if(age[i].equals("3")){
                    strBuilder.setCharAt(2, '1');
                }else if(age[i].equals("4")){
                    strBuilder.setCharAt(3, '1');
                }else if(age[i].equals("5")){
                    strBuilder.setCharAt(4, '1');
                }else if(age[i].equals("6")){
                    strBuilder.setCharAt(5, '1');
                }else if(age[i].equals("7")){
                    strBuilder.setCharAt(6, '1');
                }
            }
            webApp.setAge(strBuilder.toString());


            if(webApp.getTimed()==null){
                webApp.setTimed(0);
            }

            webApp.setDate(new Date());


            WebApp2Json webApp2Json = new WebApp2Json();
            webApp2Json.setColumn(webApp.getGrid_column());
            webApp2Json.setRow(webApp.getGrid_row());
            webApp2Json.setFarPercentage(webApp.getFar_percentage());
            webApp2Json.setNearPercentage(webApp.getNear_percentage());
            webApp2Json.setTarget("T_270_round.png");
            if(webApp.getTimed()!=0){
                webApp2Json.setTimed(true);
                webApp2Json.setTime(webApp.getTimed());
            }else{
                webApp2Json.setTimed(false);
            }

            //新的WebApp
            //设置URL
            String jsonStrReq = JsonUtils.obj2String(webApp2Json);
            jsonStrReq="jsonData="+jsonStrReq;
            System.out.println(jsonStrReq);

            String jsonStrResp = HttpUtils.sendPost("http://101.116.96.228:10000/stimuli/data", jsonStrReq);

            Integer jsonStrRespLength = jsonStrResp.length();
            //截取URL
            String url = jsonStrResp.substring(9,jsonStrRespLength-2);

            System.out.println(url);

            webApp.setURL(url);

            user = webAppService.insertWebApp(webApp,email);      //新WebApp插入成功，重置session
        }

        User refreshUser = userService.refreshUserInfo(user.getId());       //插入后更新用户信息


        if(refreshUser!=null){
            session.setAttribute("userInfo", refreshUser);
        }


        System.out.println(webApp);

        System.out.println("-------------------------------------------------------");


        try {
            resp.sendRedirect("web_app_detail?webApp_id="+webApp.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }




    /**
     * 功能描述：根据web_app_id删除一个web_app
     * @return
     */
    @GetMapping("delete_webApp")
    public Object deleteWebAppByWebAppId(String webApp_id, HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Integer flag = webAppService.deleteWebAppByWebAppId(webApp_id);
        if(flag ==1){
            HttpSession session = req.getSession();
            User userInfo = (User) session.getAttribute("userInfo");

            User newUserInfo = userService.refreshUserInfo(userInfo.getId());			//WebApp删除成功，重置session

            if(newUserInfo!=null){
                session.setAttribute("userInfo", newUserInfo);
            }

            //resp.sendRedirect("web_app_list");
            //return "web_app_list";
        }
        resp.sendRedirect("home");
        return null;
        //return "web_app_list";			//有问题 需要改404
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
        Collections.sort(favourites,new Comparator<Favourite>(){
            public int compare(Favourite fav1, Favourite fav2) {
                return fav2.getWebApp().getDate().compareTo(fav1.getWebApp().getDate());
            }
        });
        return "web_app_list";
    }

    @GetMapping("web_app_list_F_0-5")
    public Object WebAppListFilterBy0_5(HttpServletRequest req){


        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        //每次使用前刷新一次
        user = userService.refreshUserInfo(user.getId());

        List<Favourite> favourites = user.getFavourites();

        Iterator<Favourite> iterator = favourites.iterator();
        while(iterator.hasNext()){
            Favourite favourite = iterator.next();
            if(favourite.getWebApp().getAge().charAt(0)=='0'){      //如果发现第一位不是0，说明不是这个年龄段
                iterator.remove();
            }
        }

        session.setAttribute("userInfo",user);


        return "web_app_list";
    }

    @GetMapping("web_app_list_F_5-10")
    public Object WebAppListFilterBy5_10(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        //每次使用前刷新一次
        user = userService.refreshUserInfo(user.getId());

        List<Favourite> favourites = user.getFavourites();

        Iterator<Favourite> iterator = favourites.iterator();
        while(iterator.hasNext()){
            Favourite favourite = iterator.next();
            if(favourite.getWebApp().getAge().charAt(1)=='0'){      //如果发现第一位不是0，说明不是这个年龄段
                iterator.remove();
            }
        }

        session.setAttribute("userInfo",user);

        return "web_app_list";
    }

    @GetMapping("web_app_list_F_10-15")
    public Object WebAppListFilterBy10_15(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        //每次使用前刷新一次
        user = userService.refreshUserInfo(user.getId());

        List<Favourite> favourites = user.getFavourites();

        Iterator<Favourite> iterator = favourites.iterator();
        while(iterator.hasNext()){
            Favourite favourite = iterator.next();
            if(favourite.getWebApp().getAge().charAt(2)=='0'){      //如果发现第一位不是0，说明不是这个年龄段
                iterator.remove();
            }
        }
        session.setAttribute("userInfo",user);


        return "web_app_list";
    }

    @GetMapping("web_app_list_F_15-18")
    public Object WebAppListFilterBy15_18(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        //每次使用前刷新一次
        user = userService.refreshUserInfo(user.getId());

        List<Favourite> favourites = user.getFavourites();

        Iterator<Favourite> iterator = favourites.iterator();
        while(iterator.hasNext()){
            Favourite favourite = iterator.next();
            if(favourite.getWebApp().getAge().charAt(3)=='0'){      //如果发现第一位不是0，说明不是这个年龄段
                iterator.remove();
            }
        }
        session.setAttribute("userInfo",user);

        return "web_app_list";
    }

    @GetMapping("web_app_list_F_18-45")
    public Object WebAppListFilterBy18_45(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        //每次使用前刷新一次
        user = userService.refreshUserInfo(user.getId());

        List<Favourite> favourites = user.getFavourites();

        Iterator<Favourite> iterator = favourites.iterator();
        while(iterator.hasNext()){
            Favourite favourite = iterator.next();
            if(favourite.getWebApp().getAge().charAt(4)=='0'){      //如果发现第一位不是0，说明不是这个年龄段
                iterator.remove();
            }
        }

        session.setAttribute("userInfo",user);

        return "web_app_list";
    }

    @GetMapping("web_app_list_F_45-60")
    public Object WebAppListFilterBy45_60(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        //每次使用前刷新一次
        user = userService.refreshUserInfo(user.getId());

        List<Favourite> favourites = user.getFavourites();

        Iterator<Favourite> iterator = favourites.iterator();
        while(iterator.hasNext()){
            Favourite favourite = iterator.next();
            if(favourite.getWebApp().getAge().charAt(5)=='0'){      //如果发现第一位不是0，说明不是这个年龄段
                iterator.remove();
            }
        }

        session.setAttribute("userInfo",user);

        return "web_app_list";
    }

    @GetMapping("web_app_list_F_60+")
    public Object WebAppListFilterBy60(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        //每次使用前刷新一次
        user = userService.refreshUserInfo(user.getId());

        List<Favourite> favourites = user.getFavourites();

        Iterator<Favourite> iterator = favourites.iterator();
        while(iterator.hasNext()){
            Favourite favourite = iterator.next();
            if(favourite.getWebApp().getAge().charAt(6)=='0'){      //如果发现第一位不是0，说明不是这个年龄段
                iterator.remove();
            }
        }

        session.setAttribute("userInfo",user);

        return "web_app_list";
    }


    @GetMapping("web_app_list_F_favourite")
    public Object WebAppListFilterByFavourite(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        //每次使用前刷新一次
        user = userService.refreshUserInfo(user.getId());

        List<Favourite> favourites = user.getFavourites();

        Iterator<Favourite> iterator = favourites.iterator();
        while(iterator.hasNext()){
            Favourite favourite = iterator.next();
            if(!favourite.getFlag()){      //如果发现第一位不是0，说明不是这个年龄段
                iterator.remove();
            }
        }

        session.setAttribute("userInfo",user);

        return "web_app_list";
    }


}


