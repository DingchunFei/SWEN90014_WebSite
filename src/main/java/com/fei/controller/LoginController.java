package com.fei.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fei.domain.*;
import com.fei.service.*;
import com.fei.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.DocAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/login/")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShapeService shapeService;

    @Autowired
    private WebAppService webAppService;

    @Autowired
    private TrialService trialService;

    @Autowired
    private ParticipantService ParticipantService;

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
            System.out.println(userInfo.getFavourites());

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


    /**
     *  接收 WebApp 传来的结果
     *  注意一定要加 @RequestBody 来表明传入的是一个Json类型的字符串
     */
    @RequestMapping(value = "/new_web_app_result", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int newWebAppResult(@RequestBody String jsonStr) throws IOException, ParseException {
        System.out.println(jsonStr);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node_total = mapper.readTree(jsonStr);

        String name = node_total.get("name").toString();
        name = name.substring(1,name.length()-1);             //需要截取掉两边的引号

        Double total_accuracy = Double.parseDouble(node_total.get("accuracy").toString())*100;

        String URL = node_total.get("test").toString();
        URL = URL.substring(1,URL.length()-1);             //需要截取掉两边的引号
        String webAppId = webAppService.findWebAppIdByWebAppURL(URL);

        String test_time = node_total.get("test_time").toString();


        Participant participant = new Participant();
        participant.setName(name);

        WebAppResult webAppResult = new WebAppResult();
        webAppResult.setTotal_accuracy(total_accuracy);
        webAppResult.setWeb_app_id(webAppId);

        //字符串转换为日期
        SimpleDateFormat sdf = new SimpleDateFormat("\"dd/MM/yyyy HH:mm:ss\"" );
        webAppResult.setTest_date(sdf.parse(test_time));

        //为WebAppResult设置trialResult
        List<TrialResult> trialResultList = new LinkedList<>();

        JsonNode node_trials = node_total.get("trials");
        for(int i=0;;i++){              //遍历每个trial
            if(node_trials.has(i)){
                JsonNode node_trial = node_trials.get(i);
                Integer round = Integer.parseInt(node_trial.get("trial_number").toString())+1;
                Double trial_accuracy = Double.parseDouble(node_trial.get("accuracy").toString())*100;

                JsonNode matrix = node_trial.get("matrix");

                List<TrialResultShape> trialResultShapeList = new LinkedList<>();
                for(int j=0;;j++) {
                    if (matrix.has(j)) {            //遍历每个trial下的图形
                        JsonNode node_shape = matrix.get(j);

                        String s_name = node_shape.get("shape_name").toString();
                        s_name = s_name.substring(1,s_name.length()-1);             //需要截取掉两边的引号
                        String shapeId = shapeService.findShapeIdByShapeName(s_name);

                        Integer grid_row = Integer.parseInt(node_shape.get("row").toString());
                        Integer grid_column = Integer.parseInt(node_shape.get("column").toString());
                        Integer hit_count = Integer.parseInt(node_shape.get("hits").toString());
                        Integer type = Integer.parseInt(node_shape.get("type").toString())-1;

                        JsonNode touched_in_order = node_shape.get("touched_in_order");
                        List<TouchOrder> touchOrderList = new LinkedList<>();

                        if(touched_in_order == null){       //该图形没有被点击
                            touchOrderList = null;
                        }else{
                            for(int k=0;;k++){              //遍历当前图形的点击时间List
                                if (touched_in_order.has(k)) {
                                    TouchOrder touchOrder = new TouchOrder();
                                    String tmp = touched_in_order.get(k).toString();
                                    tmp = tmp.substring(0,tmp.length()-2);

                                    touchOrder.setTouch_time(Double.parseDouble(tmp)/10);
                                    touchOrderList.add(touchOrder);
                                }else break;
                            }
                        }

                        TrialResultShape trialResultShape = new TrialResultShape(type, hit_count, grid_row, grid_column, shapeId, touchOrderList);
                        System.out.println(trialResultShape);
                        trialResultShapeList.add(trialResultShape);
                    }else break;
                }

                String trialId = trialService.findTrialIdByWebAppIdAndRound(webAppId, round);

                TrialResult trialResult = new TrialResult(trialId,trial_accuracy,round,trialResultShapeList);
                trialResultList.add(trialResult);

            }else break;
        }

        webAppResult.setTrialResultList(trialResultList);


        List<WebAppResult> webAppResultList = new LinkedList<>();
        //将该webApp插入到用户所做的所有webApp里
        webAppResultList.add(webAppResult);
        participant.setWebAppResultList(webAppResultList);

        ParticipantService.insertParticipantByParticipant(participant);

        return 1;
    }
}