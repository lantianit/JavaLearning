package com.bite.mybatis.mapper;

import com.bite.mybatis.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 访问数据库
 */
@Mapper
public interface UserInfoMapper {
    /**
     * 返回数据列表
     * @return
     */
    @Select("select id, username, password,age,gender,phone," +
            "delete_flag as deleteFlag, create_time as createTime, update_time as updateTime from userinfo")
    List<UserInfo> queryUserList();

    @Results(id = "BaseResult",value = {
            @Result(column = "delete_flag", property = "deleteFlag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    @Select("select * from userinfo where id = #{id}")
    UserInfo queryUserInfo(Integer userId);

    @Select("select * from userinfo where username = #{name}")
    UserInfo queryUserInfoByName(String name);

    @Select("select * from userinfo where id = ${id}")
    UserInfo queryUserInfo2(Integer userId);

    @Select("select * from userinfo where username = '${name}'")
    List<UserInfo> queryUserInfoByName2(String name);

    @ResultMap(value = "BaseResult")
    @Select("select * from userinfo where id = #{userId} and delete_flag = #{deleteFlag}")
    UserInfo queryUserInfoByDF(Integer userId, Integer deleteFlag);

    @Select("select * from userinfo where id = #{id} and delete_flag = #{deleteFlag}")
    UserInfo queryUserInfoParam(@Param("id")Integer userId, Integer deleteFlag);

    @Insert("insert into userinfo(username, password,age,gender, phone) " +
            "values(#{username},#{password},#{age},#{gender},#{phone})")
    Integer insert(UserInfo userInfo);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into userinfo(username, password,age,gender, phone) " +
            "values(#{userInfo.username},#{userInfo.password},#{userInfo.age},#{userInfo.gender},#{userInfo.phone})")
    Integer insertByParam(@Param("userInfo") UserInfo userInfo);

    @Delete("delete from userinfo where id= #{id}")
    Integer delete(@Param("id") Integer id);

    @Update("update userinfo set password=#{password} where id=#{id}")
    Integer update(String password,Integer id);

    @Update("update userinfo set username=#{username}, password=#{password}, age=#{age} where id=#{id}")
    Integer updateByOb(UserInfo userInfo);

    @Select("select * from userinfo where username = '${name}' and password= '${password}'")
    List<UserInfo> queryUserByNameAndPassword(String name, String password);

    @Select("select * from userinfo order by id ${order}")
    List<UserInfo> queryUserByOrder(String order);


    @Select("select * from userinfo where username like CONCAT('%',#{name},'%')")
    List<UserInfo> queryUserByLike(String name);


    @Delete("<script>" +
            "delete from userinfo where id in "+
            "<foreach collection='list' separator=',' open='(' close=')' item='id'> "+
            "#{id} "+
            "</foreach> "+
            "</script> ")
    Integer batchDelete(List<Integer> ids);

}
