package com.example.demo.mapper;

import com.example.demo.entity.vo.ArticleinfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {

    ArticleinfoVO getById(@Param("id") Integer id);

}