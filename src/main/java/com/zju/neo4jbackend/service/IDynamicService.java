package com.zju.neo4jbackend.service;

import com.zju.neo4jbackend.common.ServerResponse;

public interface IDynamicService {

    ServerResponse releaseDynamic(String username,String message,String imgUri);

    ServerResponse getDynamicList(String username);

    ServerResponse deleteDynamic(String username,Long dynamicId);
}
