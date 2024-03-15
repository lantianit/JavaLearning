package com.example.redisson.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class LockController {

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("/test")
    public void test(@RequestBody List<Long> ids){
        System.out.println(ids);
    }

    @GetMapping("/lock")
    public String lockResource() throws InterruptedException {
        String lockKey = "myLock";
        // 获取锁
        RLock lock = redissonClient.getLock(lockKey);
//        RLock lock = redissonClient.getFairLock(lockKey);  // 获取公平锁
//        RReadWriteLock lock = redissonClient.getReadWriteLock(lockKey); // 获取读写锁
//        lock.readLock();  // 读锁
//        lock.writeLock(); // 写锁
        try {
            // 尝试加锁，锁的超时时间是 10s
            boolean isLocked = lock.tryLock(10, TimeUnit.SECONDS);
            if (isLocked) {
                // 成功获取到锁
                try {
                    // 模拟业务处理
                    TimeUnit.SECONDS.sleep(5);
                    return "成功获取锁，并执行业务代码";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            } else {
                // 获取锁失败
                return "获取锁失败";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "执行 return 方法";
    }
}