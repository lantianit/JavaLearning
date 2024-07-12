package com.bite.trans.controller;

import com.bite.trans.model.LogInfo;
import com.bite.trans.service.LogInfoService;
import com.bite.trans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/proga")
@RestController
public class ProgaController {
    @Autowired
    private LogInfoService logInfoService;
    @Autowired
    private UserService userService;

    @Transactional
    @RequestMapping("/p1")
    public String p1(String userName, String password){
        userService.insertUser(userName,password);
        LogInfo logInfo = new LogInfo();
        logInfo.setUserName(userName);
        logInfo.setOp("用户主动注册");
        logInfoService.insertLog(logInfo);
        return "用户注册成功";
    }

}
