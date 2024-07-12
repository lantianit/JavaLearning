package com.example.demo.utils;

import com.example.demo.model.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = new UserInfo();
        userInfo.setName("zhangsan");
        userInfo.setAge(18);
        userInfo.setId(12);

        //对象转json
        String s = objectMapper.writeValueAsString(userInfo);

        System.out.println(s);

        //json字符串转成Java对象
        UserInfo userInfo1 = objectMapper.readValue(s, UserInfo.class);
        System.out.println(userInfo1);

    }
}
