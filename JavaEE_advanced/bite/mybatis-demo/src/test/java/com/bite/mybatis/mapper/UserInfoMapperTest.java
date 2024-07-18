package com.bite.mybatis.mapper;

import com.bite.mybatis.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @BeforeEach
    void setUp() {
        log.info("setUp...");
    }

    @AfterEach
    void tearDown() {
        log.info("tearDown...");
    }

    @Test
    void queryUserList() {
        log.info(userInfoMapper.queryUserList().toString());
    }

    @Test
    void queryUserInfo() {
        log.info(userInfoMapper.queryUserInfo(1).toString());
    }
    @Test
    void queryUserInfo2() {
        log.info(userInfoMapper.queryUserInfo2(1).toString());
    }
    @Test
    void testQueryUserInfo() {
        log.info(userInfoMapper.queryUserInfoByDF(1,0).toString());
    }

    @Test
    void queryUserInfoParam() {
        log.info(userInfoMapper.queryUserInfoParam(1,0).toString());
    }

    @Test
    void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhaoliu");
        userInfo.setPassword("123456");
        userInfo.setAge(14);
        userInfo.setGender(0);
        userInfo.setPhone("15677777888");
        userInfoMapper.insert(userInfo);
    }

    @Test
    void insertByParam() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhaoliu");
        userInfo.setPassword("123456");
        userInfo.setAge(14);
        userInfo.setGender(0);
        userInfo.setPhone("15677777888");
        Integer result = userInfoMapper.insertByParam(userInfo);
        log.info("插入数据result:"+result+",id:"+userInfo.getId());
    }

    @Test
    void delete() {
        userInfoMapper.delete(8);
    }

    @Test
    void update() {
        userInfoMapper.update("zhaoliu",7);
    }

    @Test
    void updateByOb() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhaoliu666666");
        userInfo.setPassword("1234566666666");
        userInfo.setAge(99);
        userInfo.setId(7);
        userInfoMapper.updateByOb(userInfo);
    }


    @Test
    void queryUserInfoByName() {
        log.info(userInfoMapper.queryUserInfoByName("admin").toString());
    }

    @Test
    void queryUserInfoByName2() {
        log.info(userInfoMapper.queryUserInfoByName2("' or 1='1").toString());
    }

    @Test
    void queryUserByOrder() {
        log.info(userInfoMapper.queryUserByOrder("desc").toString());
    }

    @Test
    void queryUserByLike() {
        log.info(userInfoMapper.queryUserByLike("admin").toString());
    }

    @Test
    void batchDelete() {
        userInfoMapper.batchDelete(Arrays.asList(11,12,13));
    }
}