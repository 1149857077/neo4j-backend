package com.zju.neo4jbackend.service.Impl;

import com.zju.neo4jbackend.common.ServerResponse;
import com.zju.neo4jbackend.entity.User;
import com.zju.neo4jbackend.repository.UserRepository;
import com.zju.neo4jbackend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public ServerResponse login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user==null){
            return ServerResponse.createByErrorMessage("用户不存在");
        }else if(user.getPassword().equals(password)) {
            //user.setFriends(null);
            return ServerResponse.createBySuccess("登录成功",user);
        }
        return ServerResponse.createByErrorMessage("密码错误");
    }

    public ServerResponse register(User user){
        if(user.getUsername()==null||user.getPassword()==null){
            return ServerResponse.createByErrorMessage("用户名或密码为空");
        }
        User u = userRepository.findByUsername(user.getUsername());
        if(u!=null){
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        user.setName("保密");
        user.setSex("保密");
        User save = userRepository.save(user);
        if(save!=null) {
            return ServerResponse.createBySuccessMessage("注册成功");
        }
        return ServerResponse.createByErrorMessage("注册失败");
    }

    public ServerResponse becomeFriend(String username,String friend_username){
        if(username.equals(friend_username)){
            return ServerResponse.createByErrorMessage("不能添加自己为好友");
        }
        User user2 = userRepository.findByUsername(friend_username);
        if(user2==null){
            return ServerResponse.createByErrorMessage("添加的好友用户名不存在");
        }

        userRepository.becomeFriend(username,friend_username);
        return ServerResponse.createBySuccessMessage("好友添加成功");
    }


    public ServerResponse delFriend(String username1,String username2){
        User user2 = userRepository.findByUsername(username2);
        if(user2==null){
            return ServerResponse.createByErrorMessage("删除的好友用户名不存在");
        }
        userRepository.delFriend(username1,username2);
        return ServerResponse.createBySuccessMessage("好友删除成功");
    }

    public ServerResponse getAllFriends(String username) {
        Collection<User> userCollection = userRepository.getAllFriends(username);
        for (User u : userCollection) {
            u.emptyPassword();
        }
        return ServerResponse.createBySuccess("好友列表", userCollection);
    }

    public ServerResponse getFriendsOfFriend(String username){
        Collection<User> userCollection = userRepository.getAllFriends(username);
        for (User u : userCollection) {
            u.emptyPassword();
        }
        return ServerResponse.createBySuccess("朋友的朋友", userCollection);
    }
}
