package com.zju.neo4jbackend.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class User {


    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String name;
    private String sex;
    private String desc;

    //@Relationship(type = "Friend")
    //private List<Friend> friends;


    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User emptyPassword() {
        this.password = null;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //public List<Friend> getFriends() {
    //    return friends;
    //}

    //public void setFriends(List<Friend> friends) {
    //    this.friends = friends;
    //}
}
