package com.fei.service;

import com.fei.domain.User;
import com.fei.domain.WebApp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WebAppService {

    public  WebApp findWebAppByWebAppId(String web_app_id);

    public Integer deleteWebAppByWebAppId(String webApp_id);

    public User insertWebApp(WebApp webApp,String[] emails);                    //由于每次插入新的webApp URL也需要检查，因此要多一个URL参数

    public User updateWebApp(WebApp webApp,String[] emails, String user_id);                    //由于每次更新webApp URL也需要检查，因此要多一个URL参数; 因为防止把自己删掉，所以加一个用户id
}
