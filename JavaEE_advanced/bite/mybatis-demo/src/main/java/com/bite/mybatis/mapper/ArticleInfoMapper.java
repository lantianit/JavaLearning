package com.bite.mybatis.mapper;

import com.bite.mybatis.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleInfoMapper {

    @Select("select" +
            " ta.*, tb.username, tb.age,tb.phone, tb.gender" +
            " from articleinfo ta left join userinfo tb on ta.uid = tb.id" +
            " where ta.id=#{id}")
    ArticleInfo queryArticleInfo(Integer id);
}
