package com.example.java_gobang.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insert(User user);

    User selectByName(String username);

    void userWin(int userId);

    void userLose(int userId);
}