package com.bite.book.service;

import com.bite.book.mapper.UserInfoMapper;
import com.bite.book.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 从数据中查询用户信息
     * @return
     */
    public UserInfo queryByName(String userName){
        return userInfoMapper.queryByName(userName);
    }
}
