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

    /**
     * 发布动态
     *
     * @param message  动态消息
     * @param imgUrl   图片链接
     * @param session
     * @return
     */
    @PostMapping("/dynamic/release")
    public ServerResponse releaseDynamic(String message,String imgUrl,HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iDynamicService.releaseDynamic(user.getUsername(),message,imgUrl);
    }

    /**
     * 查询动态列表
     *
     * @param username  要查询的用户
     * @return
     */
    @PostMapping("/dynamic/list")
    public ServerResponse releaseDynamic(String username){
        return iDynamicService.getDynamicList(username);
    }


    /**
     * 删除动态
     *
     * @param dynamicId  要删除的动态id
     * @param session
     * @return
     */
    @PostMapping("/dynamic/delete")
    public ServerResponse deleteDynamic(Long dynamicId,HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iDynamicService.deleteDynamic(user.getUsername(),dynamicId);
    }

}
