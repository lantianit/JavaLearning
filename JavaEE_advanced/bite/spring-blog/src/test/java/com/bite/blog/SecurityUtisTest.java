package com.bite.blog;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class SecurityUtisTest {
    @Test
    public void encrypt(){
        String password = "123456";
        String md5Str = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(md5Str);
        String salt = UUID.randomUUID().toString().replace("-","");
        System.out.println(salt);
        //密文内容: 盐值+明文拼接的字符串进行加密的
        String securityPassword = DigestUtils.md5DigestAsHex((salt+password).getBytes());
        //salt+密文  存储在数据库中
        String finalPassword = salt+securityPassword;
        System.out.println(finalPassword);
    }

    @Test
    public void verify(){
        String inputPassword = "123456";
        String sqlPassword = "804b5004e0814aaaade596bfc0f36256cad0115f4beb0ff1b660bd55f9cd5f7e";
        //sqlPassword  是  salt+ md5(salt+password)
        if (sqlPassword==null || sqlPassword.length()!=64){
            System.out.println("校验失败");
        }
        String salt = sqlPassword.substring(0,32);
        String secretPassword = DigestUtils.md5DigestAsHex((salt+inputPassword).getBytes());
        String finalPassword = salt +secretPassword;
        if (finalPassword.equals(sqlPassword)) {
            System.out.println("校验成功");
        }else {
            System.out.println("校验失败");
        }
    }
}
