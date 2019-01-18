package com.zju.neo4jbackend.service;

import com.zju.neo4jbackend.common.ServerResponse;
import com.zju.neo4jbackend.entity.User;

public interface IUserService {

    ServerResponse login(String username,String password);

    ServerResponse register(User user);

    ServerResponse becomeFriend(String username,String friend_username);

    ServerResponse delFriend(String username,String friend_username);

    ServerResponse getAllFriends(String username);

    ServerResponse getFriendsOfFriend(String username);

}
