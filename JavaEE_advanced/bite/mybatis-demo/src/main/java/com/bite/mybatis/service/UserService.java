package com.bite.mybatis.service;

import com.bite.mybatis.mapper.UserInfoMapper;
import com.bite.mybatis.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> queryAllUser() {
        return userInfoMapper.queryUserList();
    }

    public UserInfo queryUserByNameAndPassword(String name,String password) {
        List<UserInfo> userInfos = userInfoMapper.queryUserByNameAndPassword(name, password);
        if (userInfos.size()>0){
            return userInfos.get(0);
        }
        return null;
    }
}
