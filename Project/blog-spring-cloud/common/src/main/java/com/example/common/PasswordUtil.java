package com.example.common;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class PasswordUtil {
    /**
     * 加盐算法 -> 格式：盐值（32）$加密之后的密码（32）
     * @param password 原密码
     * @return
     */
    public static String encrypt(String password){
        // 1.生成盐值
        String salt = UUID.randomUUID().toString().replace("-","");
        // 2.使用加密算法将盐值+原密码进行加密
        String finalPassword = DigestUtils.md5DigestAsHex((salt+password).getBytes());
        // 3.将盐值和加密后的密码一起返回
        String dbPassword = salt+"$"+finalPassword;
        return dbPassword;
    }

    /**
     * 密码验证
     * @param inputPassword
     * @param dbPassword
     * @return
     */
    public static boolean decrypt(String inputPassword,String dbPassword){
        // 1.验证参数
        if(!StringUtils.hasLength(inputPassword) || !StringUtils.hasLength(dbPassword) ||
                dbPassword.length()!=65 || !dbPassword.contains("$")){
            return false;
        }
        // 2.将用户输入的密码和数据库的盐值进行加密，得到待验证的加密密码
        // 2.1 得到盐值 & 最终正确的密码
        String[] dbPasswordArray = dbPassword.split("\\$");
        String salt = dbPasswordArray[0];
        String finalPassword = dbPasswordArray[1];
        // 2.2 使用数据库的盐值+用户输入的密码进行加密=待验证的加密密码
        String userPassword = DigestUtils.md5DigestAsHex((salt+inputPassword).getBytes());
        // 3.将待验证密的加密密码和数据的加密的密码进行对比
        if(userPassword.equals(finalPassword)){
            return true;
        }
        // 4.将结果返回给调用方
        return false;
    }
}