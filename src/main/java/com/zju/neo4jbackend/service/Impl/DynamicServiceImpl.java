package com.zju.neo4jbackend.service.Impl;

import com.zju.neo4jbackend.common.ServerResponse;
import com.zju.neo4jbackend.entity.Dynamic;
import com.zju.neo4jbackend.entity.User;
import com.zju.neo4jbackend.repository.DynamicRepository;
import com.zju.neo4jbackend.repository.UserRepository;
import com.zju.neo4jbackend.service.IDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicServiceImpl implements IDynamicService {

    @Autowired
    private DynamicRepository dynamicRepository;

    @Autowired
    private UserRepository userRepository;

    public ServerResponse releaseDynamic(String username,String message, String imgUrl){
        if(message==null&&imgUrl==null){
            return ServerResponse.createByErrorMessage("动态内容不能为空");
        }
        Dynamic dynamic = new Dynamic(username,message,imgUrl);
        Dynamic d = dynamicRepository.save(dynamic);
        dynamicRepository.release(username, d.getId());
        if(d!=null){
            return ServerResponse.createBySuccessMessage("动态发布成功");
        }
        return ServerResponse.createByErrorMessage("动态发布失败");
    }

    public ServerResponse getDynamicList(String username){
        if(username==null){
            return ServerResponse.createByErrorMessage("传入用户名为空");
        }
        User u = userRepository.findByUsername(username);
        if(u==null){
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        List<Dynamic> dynamicList = dynamicRepository.getDynamicsByCreateUsername(username);
        return ServerResponse.createBySuccess("动态列表",dynamicList);
    }

    public ServerResponse deleteDynamic(String username,Long dynamicId){
        if(dynamicId==null){
            return ServerResponse.createByErrorMessage("参数错误，需要动态ID");
        }
        List<Dynamic> dynamicList = dynamicRepository.getDynamicsByCreateUsername(username);
        for(Dynamic dynamic:dynamicList){
            if(dynamic.getId()==dynamicId){
                dynamicRepository.delRelease(username,dynamicId);
                dynamicRepository.delete(dynamic);
                return ServerResponse.createBySuccessMessage("删除成功");
            }
        }
        return ServerResponse.createByErrorMessage("动态不存在");
    }
}
