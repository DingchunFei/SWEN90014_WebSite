package com.fei.service.impl;

import com.fei.domain.*;
import com.fei.mapper.*;
import com.fei.service.UserService;
import com.fei.service.WebAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class WebAppServiceImpl implements WebAppService {

    @Autowired
    private  WebAppMapper webAppMapper;

    @Autowired
    private FavouriteMapper favouriteMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TrialMapper trialMapper;

    @Autowired
    private TrialShapeMapper trialShapeMapper;

    @Autowired
    private ShapeMapper shapeMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public WebApp findWebAppByWebAppId(String web_app_id) {
        return  webAppMapper.findWebAppByWebAppId(web_app_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteWebAppByWebAppId(String webApp_id) {
        favouriteMapper.deleteFavouriteByWebAppId(webApp_id);
        return webAppMapper.deleteWebAppByWebAppId(webApp_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User insertWebApp(WebApp webApp, String[] emails, Integer round, String[][] targets, String[][] nearDistractors, String[][] farDistractors, Integer[] grid_row, Integer[] grid_column, Integer[] timed,Integer[] target_percentage,Integer[] near_distractor_percentage) {

        //插入webApp
        webAppMapper.insertWebApp(webApp);
        Favourite favourite = new Favourite(webApp.getUser_id(),webApp.getId());
        //插入新的WebApp同时要把favourite表里的内容也插入进去
        favouriteMapper.insertWebFavouriteByNewWebApp(favourite);

        //完成用户id和邮箱映射的转换,并检查是否favourite表中已存在
        for (String email:emails){
            String user_id = userMapper.findUserIdByEmail(email);
            //返回1表示找到了，存在，则不用改变
            Integer flag = favouriteMapper.checkAlreadyExist(user_id, webApp.getId());
            //没找到，需要插入
            if(flag==0){
                //借用之前的一个方法
                favouriteMapper.insertWebFavouriteByNewWebApp(new Favourite(user_id, webApp.getId()));
            }
        }

        for (int i=0;i<round;i++){
            //一个个插入trial i正好是对应的round
            Trial trial = new Trial(webApp.getId(),i+1,grid_row[i],grid_column[i],timed[i],target_percentage[i],near_distractor_percentage[i],100-target_percentage[i]-near_distractor_percentage[i]);
            trialMapper.insertTrial(trial);

            for(int j=0;j<targets[i].length;j++){
                Shape shape = shapeMapper.findShapeByShapeName(targets[i][j]+".png");
                //type=1 表示 target
                TrialShape trialShape = new TrialShape(shape.getId(),trial.getId(),1);
                trialShapeMapper.insertTrialShape(trialShape);
            }

            for(int j=0;j<nearDistractors[i].length;j++){
                Shape shape = shapeMapper.findShapeByShapeName(nearDistractors[i][j]+".png");
                //type=2 表示 nearDistractors
                TrialShape trialShape = new TrialShape(shape.getId(),trial.getId(),2);
                trialShapeMapper.insertTrialShape(trialShape);
            }

            for(int j=0;j<farDistractors[i].length;j++){
                Shape shape = shapeMapper.findShapeByShapeName(farDistractors[i][j]+".png");
                //type=3 表示 farDistractors
                TrialShape trialShape = new TrialShape(shape.getId(),trial.getId(),3);
                trialShapeMapper.insertTrialShape(trialShape);
            }
        }

        return userMapper.refreshUserInfo(webApp.getUser_id());     //返回一个User，因为要更新Session中的内容
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User updateWebApp(WebApp webApp, String[] emails ,String current_user_id) {
        //完成用户id和邮箱映射的转换,并检查是否favourite表中已存在
        List<String> user_ids = new LinkedList<String>();
        for (String email:emails){
            String user_id = userMapper.findUserIdByEmail(email);
            if(user_id!=null){
                user_ids.add(user_id);
                //返回1表示找到了，存在，则不用改变
                Integer flag = favouriteMapper.checkAlreadyExist(user_id, webApp.getId());
                //没找到，需要插入
                if(flag==0){
                    //借用之前的一个方法
                    favouriteMapper.insertWebFavouriteByNewWebApp(new Favourite(user_id, webApp.getId()));
                }
            }
        }
        List<Favourite> favourites = favouriteMapper.findFavouritesByWebAppId(webApp.getId());
        List<String> missing_favourites = new LinkedList<String>();
        for (Favourite favourite: favourites) {             //查找在favourite表中，但不在新的email列表中的email，等待删除
            int flag =0;
            for (String user_id: user_ids) {
                if(favourite.getUser_id().equals(user_id) || favourite.getUser_id().equals(current_user_id)){       //把自己排除，不然把自己也从favourite删了
                    flag=1;                                 //找到了
                    break;
                }
            }
            if(flag==0){        //表示没有在新的email表中找到，需要删除该favourite
                missing_favourites.add(favourite.getId());
            }
        }
        for (String missing_favourite:missing_favourites) {
            favouriteMapper.deleteFavouriteByFavouriteId(missing_favourite);
        }


        webAppMapper.updateWebApp(webApp);
        return userMapper.refreshUserInfo(webApp.getUser_id());     //返回一个User，因为要更新Session中的内容
    }
}
