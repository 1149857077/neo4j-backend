package com.zju.neo4jbackend.controller;


import com.zju.neo4jbackend.common.Const;
import com.zju.neo4jbackend.common.ServerResponse;
import com.zju.neo4jbackend.entity.User;
import com.zju.neo4jbackend.service.IDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class DynamicController {

    @Autowired
    private IDynamicService iDynamicService;

    @PostMapping("/dynamic/release")
    public ServerResponse releaseDynamic(String message,String imgUri,HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iDynamicService.releaseDynamic(user.getUsername(),message,imgUri);
    }

    @PostMapping("/dynamic/list")
    public ServerResponse releaseDynamic(String username){
        return iDynamicService.getDynamicList(username);
    }

    @PostMapping("/dynamic/delete")
    public ServerResponse deleteDynamic(Long dynamicId,HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iDynamicService.deleteDynamic(user.getUsername(),dynamicId);
    }

}
