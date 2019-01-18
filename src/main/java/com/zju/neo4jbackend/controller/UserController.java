package com.zju.neo4jbackend.controller;

import com.zju.neo4jbackend.common.Const;
import com.zju.neo4jbackend.common.ServerResponse;
import com.zju.neo4jbackend.entity.User;
import com.zju.neo4jbackend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/user/register")
    public ServerResponse register(User user){
        return iUserService.register(user);
    }

    @PostMapping("/user/login")
    public ServerResponse login(String username, String password, HttpSession session){
        ServerResponse response = iUserService.login(username,password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @PostMapping("/user/become_friends")
    public ServerResponse becomeFriend(String friend_username,HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(friend_username==null){
            return ServerResponse.createByErrorMessage("未指定好友名");
        }
        return iUserService.becomeFriend(user.getUsername(),friend_username);
    }

    @PostMapping("/user/del_friend")
    public ServerResponse delFriend(String friend_username,HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(friend_username==null){
            return ServerResponse.createByErrorMessage("未指定好友名");
        }
        return iUserService.delFriend(user.getUsername(),friend_username);
    }

    @GetMapping("/user/get_all_friends")
    public ServerResponse findAllFriends(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.getAllFriends(user.getUsername());
    }

    @PostMapping("/user/get_friends_of_friend")
    public ServerResponse getFriendsOfFriend(String friend_username,HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.getAllFriends(friend_username);
    }
}
