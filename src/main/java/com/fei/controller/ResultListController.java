package com.fei.controller;

import com.fei.domain.JsonData;
import com.fei.service.ResultListService;
import com.fei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stimuli/resultList")
public class ResultListController {

    @Autowired
    private ResultListService resultListService;

    /**
     * 功能描述：查找全部用户
     * @return
     */
    @GetMapping("find_resultList_by_webAppId")
    public Object findResultListByWebAppId(String web_app_id){
        return JsonData.buildSuccess(resultListService.findResultListByWebAppId(web_app_id));
    }
}
