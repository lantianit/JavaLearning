package com.bite.trans.controller;

import com.bite.trans.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequestMapping("/trans")
@RestController
public class TransactionalController {
    @Autowired
    private UserService userService;

    @Transactional
    @RequestMapping("/r1")
    public String registry(String userName, String password){

        Integer result = userService.insertUser(userName,password);
        log.info("数据插入成功, result:"+result);
        int a=10/0;
        return "注册成功";
    }

    @Transactional
    @RequestMapping("/r2")
    public String r2(String userName, String password) throws IOException {

        Integer result = userService.insertUser(userName,password);
        log.info("数据插入成功, result:"+result);
        if (true){
            throw new IOException();  //事务提交
        }

        return "注册成功";
    }

    @Transactional
    @RequestMapping("/r3")
    public String r3(String userName, String password) {

        Integer result = userService.insertUser(userName,password);
        log.info("数据插入成功, result:"+result);
        if (true){
            throw new NullPointerException();  //事务回滚
        }

        return "注册成功";
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping("/r4")
    public String r4(String userName, String password) throws IOException {

        Integer result = userService.insertUser(userName,password);
        log.info("数据插入成功, result:"+result);
        if (true){
            throw new IOException();  //事务回滚
        }

        return "注册成功";
    }

    @Transactional
    @RequestMapping("/r5")
    public String r5(String userName, String password) {

        Integer result = userService.insertUser(userName,password);
        log.info("数据插入成功, result:"+result);
        try {
            int a = 10/0;
        }catch (Exception e){
            log.error("发生了异常...");  //事务提交
        }

        return "注册成功";
    }

    @Transactional
    @RequestMapping("/r6")
    public String r6(String userName, String password) {

        Integer result = userService.insertUser(userName,password);
        log.info("数据插入成功, result:"+result);
        try {
            int a = 10/0;
        }catch (Exception e){
            log.error("发生了异常...");
            throw e;   //重新抛出异常, 事务回滚
        }

        return "注册成功";
    }

    @Transactional(isolation = Isolation.DEFAULT)
    @RequestMapping("/r7")
    public String r7(String userName, String password) {

        Integer result = userService.insertUser(userName,password);
        log.info("数据插入成功, result:"+result);
        try {
            int a = 10/0;
        }catch (Exception e){
            log.error("发生了异常...");
            //手动设置回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return "注册成功";
    }
}
