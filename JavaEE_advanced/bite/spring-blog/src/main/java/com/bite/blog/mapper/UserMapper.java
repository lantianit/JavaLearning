package com.bite.blog.mapper;

import com.bite.blog.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //根据用户名, 查询用户信息
    // * 替换成具体字段
    @Select("select * from user where user_name = #{userName} and delete_flag = 0")
    UserInfo selectByName(String userName);

    //根据用户ID, 查询用户信息
    @Select("select * from user where id = #{userId} and delete_flag=0")
    UserInfo selectById(Integer userId);
}
