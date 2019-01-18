package com.zju.neo4jbackend.entity;


import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity("Release")
public class Release {

    @Id
    @GeneratedValue
    private Long id;

    private Date createTime;

    @StartNode
    private User user;

    @EndNode
    private Dynamic dynamic;

    public Release(User user, Dynamic dynamic) {
        this.user = user;
        this.dynamic = dynamic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public User getUser() {
        return user;
    }

    public Dynamic getDynamic() {
        return dynamic;
    }
}
