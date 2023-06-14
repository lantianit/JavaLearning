package com.example.demo.mapper;

import com.example.demo.entity.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据用户 id 查询用户信息
     *
     * @param userId
     * @return
     */
    Userinfo getUserById(@Param("userId") Integer userId);


    List<Userinfo> getUserByName(@Param("username") String username);

    /**
     * 查询全部
     *
     * @return
     */
    List<Userinfo> getAll();

    /**
     * 添加用户对象
     *
     * @param userinfo
     * @return
     */
    int add(Userinfo userinfo);

    /**
     * 添加用户并返回用户 id
     *
     * @param userinfo
     * @return
     */
    int addGetId(Userinfo userinfo);


    /**
     * 修改用户
     *
     * @param userinfo
     * @return
     */
    int upUserName(Userinfo userinfo);

    /**
     * 根据用户 id 删除用户信息
     *
     * @param id
     * @return
     */
    int delById(@Param("id") Integer id);

    List<Userinfo> getListByOrder(@Param("order") String order);

    Userinfo login(@Param("username") String username,
                   @Param("password") String password);

    List<Userinfo> getListByName(@Param("username") String username);


    int add2(Userinfo userinfo);

    int add3(Userinfo userinfo);

    List<Userinfo> getListByParam(String username, String password);

    int update2(Userinfo userinfo);

    int dels(List<Integer> ids);

}