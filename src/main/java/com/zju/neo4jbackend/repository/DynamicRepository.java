package com.zju.neo4jbackend.repository;

import com.zju.neo4jbackend.entity.Dynamic;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DynamicRepository extends Neo4jRepository<Dynamic,Long> {

    @Query("MATCH (a:User), (b:Dynamic) WHERE a.username={0} AND ID(b)={1}"+
            "CREATE (a)-[:Release]->(b)")
    void release(String username,Long dynamicId);

    List<Dynamic> getDynamicsByCreateUsername(String username);

    @Query("MATCH (a)-[r:Release]->(b) WHERE a.username={0} AND ID(b)={1} DETACH DELETE r")
    void delRelease(String username, Long dynamicId);
}
