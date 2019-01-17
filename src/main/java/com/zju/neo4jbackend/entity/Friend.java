package com.zju.neo4jbackend.entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity("Friend")
public class Friend {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private User user1;

    @EndNode
    private User user2;

    public Friend(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public User getUser1() {
        return user1;
    }


    public User getUser2() {
        return user2;
    }


    public Long getId() {
        return id;
    }
}
