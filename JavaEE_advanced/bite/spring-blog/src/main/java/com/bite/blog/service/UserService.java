package com.bite.blog.service;

import com.bite.blog.mapper.BlogMapper;
import com.bite.blog.mapper.UserMapper;
import com.bite.blog.model.BlogInfo;
import com.bite.blog.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BlogMapper blogMapper;
    public UserInfo queryUserByName(String userName) {
        return userMapper.selectByName(userName);
    }

    public UserInfo queryUserById(Integer userId) {
        return userMapper.selectById(userId);
    }

    public UserInfo getAuthorInfoByBlogId(Integer blogId) {
        //1. 根据博客ID, 获取作者ID
        //2. 根据作者ID, 获取作者信息
        BlogInfo blogInfo = blogMapper.selectById(blogId);
        if (blogInfo==null && blogInfo.getUserId()<1){
            return null;
        }
        UserInfo userInfo = userMapper.selectById(blogInfo.getUserId());
        return userInfo;
    }
}
