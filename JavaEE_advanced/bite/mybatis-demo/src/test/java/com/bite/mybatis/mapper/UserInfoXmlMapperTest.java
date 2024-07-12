package com.bite.mybatis.mapper;

import com.bite.mybatis.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserInfoXmlMapperTest {
    @Autowired
    private UserInfoXmlMapper userInfoXmlMapper;

    @Test
    void queryUserList() {
        log.info(userInfoXmlMapper.queryUserList().toString());
    }

    @Test
    void queryUserList2() {
        log.info(userInfoXmlMapper.queryUserList2().toString());
    }

    @Test
    void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("lisi");
        userInfo.setAge(15);
        userInfo.setPassword("123456");
        userInfo.setGender(1);
        Integer result = userInfoXmlMapper.insert(userInfo);
        log.info("result:"+result+",id:"+userInfo.getId());
    }

    @Test
    void delete() {
        userInfoXmlMapper.delete(9);
    }

    @Test
    void update() {
        userInfoXmlMapper.update("admin",2);
    }

    @Test
    void insert2() {
        UserInfo userInfo = new UserInfo();
//        userInfo.setUsername("lisi");
        userInfo.setAge(15);
        userInfo.setPassword("123456");
//        userInfo.setGender(1);
//        userInfo.setPhone();
        Integer result = userInfoXmlMapper.insert2(userInfo);
        log.info("result:"+result+",id:"+userInfo.getId());
    }

    @Test
    void queryUserByWhere() {
        log.info(userInfoXmlMapper.queryUserByWhere(null,null).toString());
    }

    @Test
    void update2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("lisi");
//        userInfo.setAge(15);
        userInfo.setPassword("123456");
        userInfo.setId(18);
        Integer result = userInfoXmlMapper.update2(userInfo);
    }

    @Test
    void batchDelete() {
        userInfoXmlMapper.batchDelete(Arrays.asList(14,15,16,17));
    }
}