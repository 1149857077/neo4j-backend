package com.zju.neo4jbackend.repository;

import com.zju.neo4jbackend.entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Component;


@Component
public interface UserRepository extends Neo4jRepository<User,Long> {

    User findByUsername(String username);

    @Query("MATCH (a),(b) WHERE a.username={0} AND b.username={1} CREATE (a)-[:Friend]->(b)")
    void becomeFriend(String username1, String username2);

    @Query("MATCH (a)-[f:Friend]->(b) WHERE a.username={0} AND b.username={1} DETACH DELETE f")
    void delFriend(String username1, String username2);


}
