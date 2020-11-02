package com.fei.controller;

import com.fei.domain.*;
import com.fei.service.*;
import com.fei.utils.HttpUtils;
import com.fei.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


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

    @Autowired
    private WebAppResultService webAppResultService;

    @Autowired
    private ShapeService shapeService;

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
    public Object uploadImg(@RequestParam("image_upload")MultipartFile file,String institution ,HttpServletRequest request) throws FileNotFoundException {

        String userId = request.getParameter("userId");

        if(file.getOriginalFilename().length()!=0){
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

            user.setInstitution(institution);

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
        }else{
            User user = new User();
            user.setId(userId);
            user.setInstitution(institution);

            userService.updateUserInstitution(user);
            //刷新用戶信息
            user = userService.refreshUserInfo(user.getId());
            request.getSession().setAttribute("userInfo",user);
            return "profile";
        }

    }


    //插入一个新的webapp
    @RequestMapping("addNewWebApp")
    public Object addNewWebApp(WebApp webApp, String [] age, @RequestParam(name = "Email") String [] emails, Integer[] near_distractor_percentage, Integer[] target_percentage ,Integer [] timed ,Integer round, String targetArray  ,String farDistractorArray ,String nearDistractorArray, Integer[] grid_row, Integer[] grid_column, HttpServletRequest req, HttpServletResponse resp){

        HttpSession session = req.getSession();             //取出session中的用户id，提供后续webapp的时候使用
        User userInfo = (User) session.getAttribute("userInfo");
        webApp.setUser_id(userInfo.getId());

        webApp.setNumbers_of_trials(round);
        User user = null;

        String str="0000000";           //String不可变，需要变成StringBuilder
        StringBuilder strBuilder = new StringBuilder(str);

        if(age!=null){
            for(int i=0;i<age.length; i++){
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
        }else{
            //不填就代表全选
            webApp.setAge("1111111");
        }
        if(webApp.getId()!=null){                                  //通过webappId是否存在来判断 更新
            user = webAppService.updateWebApp(webApp,emails,userInfo.getId());      //更新WebApp成功，重置session
        }else{

            webApp.setDate(new Date());

            /**
             * 一下三个数组与具体选择target有关
             */
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(round);
            System.out.println(targetArray);
            System.out.println(farDistractorArray);
            System.out.println(nearDistractorArray);


            String[][] targets = JsonUtils.string2Obj(targetArray, String[][].class);                     //重建从JS传来的二维字符串数组
            String[][] farDistractors = JsonUtils.string2Obj(farDistractorArray, String[][].class);       //重建从JS传来的二维字符串数组
            String[][] nearDistractors = JsonUtils.string2Obj(nearDistractorArray, String[][].class);     //重建从JS传来的二维字符串数组

            /**
             * translate into Json format
             */
            List<Trial> trialList = new LinkedList<>();
            for (int i=0;i<round;i++) {
                Trial trial = new Trial(i+1, grid_row[i], grid_column[i], timed[i], target_percentage[i], near_distractor_percentage[i]);
                List<TrialShape> trialShapeList = new LinkedList<>();

                for(int j=0;j<targets[i].length;j++){
                    Shape shape = shapeService.findShapeByShapeName(targets[i][j] + ".png");
                    TrialShape trialShape = new TrialShape(1, shape);
                    trialShapeList.add(trialShape);
                }
                for(int j=0;j<nearDistractors[i].length;j++){
                    Shape shape = shapeService.findShapeByShapeName(nearDistractors[i][j] + ".png");
                    TrialShape trialShape = new TrialShape(2, shape);
                    trialShapeList.add(trialShape);
                }
                for(int j=0;j<farDistractors[i].length;j++){
                    Shape shape = shapeService.findShapeByShapeName(farDistractors[i][j] + ".png");
                    TrialShape trialShape = new TrialShape(3, shape);
                    trialShapeList.add(trialShape);
                }
                trial.setTrialShapeList(trialShapeList);
                trialList.add(trial);
            }
            webApp.setTrials(trialList);


            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(webApp);

            String jsonStr = JsonUtils.obj2String(webApp);
            jsonStr = "jsonData="+jsonStr;
            try{
                System.out.println(jsonStr);
                String URL = HttpUtils.sendPost("https://www.stimulisite.com:10000/stimuli/data", jsonStr);
                Integer jsonStrRespLength = URL.length();
                //截取URL
                String url = URL.substring(9,jsonStrRespLength-2);

                System.out.println(url);

                webApp.setURL(url);

                System.out.println(jsonStr);
            }catch(Exception e){
                System.out.println("Fetching URL failed!");
                e.printStackTrace();
            }

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            user = webAppService.insertWebApp(webApp,emails,round,targets,nearDistractors,farDistractors,grid_row,grid_column,timed,target_percentage,near_distractor_percentage);      //新WebApp插入成功，重置session
        }

        User refreshUser = userService.refreshUserInfo(user.getId());       //插入后更新用户信息


        if(refreshUser!=null){
            session.setAttribute("userInfo", refreshUser);
        }


        System.out.println(webApp);

        System.out.println("-------------------------------------------------------");

        try {
            //resp.sendRedirect("web_app_detail?webApp_id="+webApp.getId());
            resp.sendRedirect("home");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @GetMapping("delete_result")
    public Object deleteResultByResultId(String result_id, HttpServletRequest req,HttpServletResponse resp) throws IOException {
        webAppResultService.deleteWebAppResultByWebAppResultId(result_id);

        HttpSession session = req.getSession();
        User userInfo = (User) session.getAttribute("userInfo");

        User newUserInfo = userService.refreshUserInfo(userInfo.getId());			//WebAppResult删除成功，重置session

        if(newUserInfo!=null){
            session.setAttribute("userInfo", newUserInfo);
        }

        resp.sendRedirect("results");
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
     * 查看result的详细信息
     * @param
     * @return
     */
    @GetMapping("web_app_result_detail")
    public Object webAppResultDetail(String web_app_result_id, HttpServletRequest req){
        WebAppResult webAppResult = webAppResultService.findWebAppResultDetailByWebAppResultId(web_app_result_id);
        System.out.println(webAppResult);
        HttpSession session = req.getSession();
        session.setAttribute("webAppResult",webAppResult);

        return "result_detail";
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


//    @GetMapping("result_list_S_date")
//    public Object ResultListSortByDate(HttpServletRequest req){
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("userInfo");
//        List<Favourite> favourites = user.getFavourites();
//        List<WebAppResult> webAppResults = new LinkedList<WebAppResult>();
//        for (Favourite favourite: favourites) {
//            webAppResults.addAll(favourite.getWebApp().getWebAppResultList());  //将结果全部加入
//        }
//        Collections.sort(webAppResults,new Comparator<WebAppResult>(){
//            public int compare(WebAppResult res1, WebAppResult res2) {
//                return res1.getTest_date().compareTo(res2.getTest_date());
//            }
//        });
//        req.getSession().setAttribute("results",webAppResults);
//        return "web_app_list";
//    }


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


