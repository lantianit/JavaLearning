package com.bite.trans.service;

import com.bite.trans.mapper.LogInfoMapper;
import com.bite.trans.model.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class LogInfoService {
    @Autowired
    private LogInfoMapper logInfoMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer insertLog(LogInfo logInfo){
       Integer res =  logInfoMapper.insertLog(logInfo);
        try {
            int a = 10/0;
        }catch (Exception e){
            e.printStackTrace();
            //设置当前事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return res;
    }
}
