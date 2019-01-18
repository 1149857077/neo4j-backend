package com.zju.neo4jbackend.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;


@NodeEntity
public class Dynamic {

    @Id
    @GeneratedValue
    private Long id;

    private Long createTime;
    private String createUsername;
    private String message;
    private String imgUri;

    public Dynamic(String createUsername,String message, String imgUri) {
        this.createTime = new Date().getTime();
        this.message = message;
        this.imgUri = imgUri;
        this.createUsername = createUsername;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public Long getId() {
        return id;
    }
}
