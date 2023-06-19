package com.example.demo.mapper;

import com.example.demo.entity.Userinfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest // 1.表明当前单元测试是运行在 Spring Boot 环境中的
class UserMapperTest {

    // 2.注入测试对象
    @Autowired
    private UserMapper userMapper;

    @Test
    void getUserById() {
        // 3.添加单元测试的业务代码
        Userinfo userinfo = userMapper.getUserById(1);
        System.out.println(userinfo);
//        Assertions.assertEquals("admin", userinfo.getUsername());
    }

    @Test
    void getAll() {
        List<Userinfo> list = userMapper.getAll();
        System.out.println(list);
    }

    @Test
    void add() {
        // 伪代码，构建对象并设置相应的值
        Userinfo userinfo = new Userinfo();
//        userinfo.setUsername("李四");
        userinfo.setPassword("123456");
        userinfo.setCreatetime(LocalDateTime.now());
        userinfo.setUpdatetime(LocalDateTime.now());
        // 调用 mybatis 添加方法执行添加操作
        int result = userMapper.add(userinfo);
        System.out.println("添加：" + result);

        int uid = userinfo.getId();
        System.out.println("用户Id:" + uid);

        Assertions.assertEquals(1, result);
    }

    @Test
    void addGetId() {
        // 伪代码，构建对象并设置相应的值
        Userinfo userinfo = new Userinfo();
//        userinfo.setUsername("小六");
        userinfo.setPassword("123456");
        userinfo.setCreatetime(LocalDateTime.now());
        userinfo.setUpdatetime(LocalDateTime.now());
        // 调用 mybatis 添加方法执行添加操作
        int result = userMapper.addGetId(userinfo);
        System.out.println("添加：" + result);

        int uid = userinfo.getId();
        System.out.println("用户Id:" + uid);

        Assertions.assertEquals(1, result);
    }

    @Test
    void upUserName() {
        // 构建测试数据
        Userinfo userinfo = new Userinfo();
        userinfo.setId(5);
//        userinfo.setUsername("老老六");
        int result = userMapper.upUserName(userinfo);
        System.out.println("修改：" + result);
        Assertions.assertEquals(1, result);
    }


    @Test
    @Transactional
    void delById() {
        Integer id = 4;
        int result = userMapper.delById(id);
        System.out.println("删除：" + result);
        Assertions.assertEquals(1, result);
    }

    @Test
    void getUserByName() {
        List<Userinfo> list = userMapper.getUserByName("张三");
        System.out.println("查询：" + list.size());
    }

    @Test
    void getListByOrder() {
        List<Userinfo> list = userMapper.getListByOrder("asc");
        System.out.println(list);

    }

    @Test
    void login() {
        String username = "admin";
        String password = "' or 1='1"; // sql 注入【安全问题】
        Userinfo userinfo = userMapper.login(username, password);
        System.out.println("登录状态：" + (userinfo == null ? "失败" : "成功"));

    }

    @Test
    void getListByName() {
        String username = "m";
        List<Userinfo> list = userMapper.getListByName(username);
        System.out.println("list:" + list);
    }

    @Test
    void add2() {
        Userinfo userinfo = new Userinfo();
        userinfo.setUsername("王五");
        userinfo.setPhoto(null);
        userinfo.setPassword("123");
        int result = userMapper.add2(userinfo);
        System.out.println("添加：" + result);
    }

    @Test
    void add3() {
        Userinfo userinfo = new Userinfo();
        userinfo.setUsername("老六");
        userinfo.setPassword("123");
        userinfo.setPhoto("default.png");
        int result = userMapper.add3(userinfo);
        System.out.println("添加：" + result);

    }

    @Test
    void getListByParam() {
        List<Userinfo> list = userMapper.getListByParam(null, "123");
        System.out.println(list);
    }

    @Test
    void update2() {
        Userinfo user = new Userinfo();
        user.setId(7);
        user.setUsername(null);
        user.setPassword(null);
        user.setPhoto("default.png");
        int result = userMapper.update2(user);
        System.out.println("修改：" + result);
    }

    @Test
    void dels() {
        List<Integer> ids = new ArrayList<>();
        ids.add(6);
        ids.add(7);
        int result = userMapper.dels(ids);
        System.out.println("删除：" + result);
    }
}