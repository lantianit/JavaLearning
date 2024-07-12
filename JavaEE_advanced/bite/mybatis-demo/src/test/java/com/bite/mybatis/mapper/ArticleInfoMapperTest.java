package com.bite.mybatis.mapper;

import com.bite.mybatis.model.ArticleInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleInfoMapperTest {
    @Autowired
    private ArticleInfoMapper articleInfoMapper;
    @Test
    void queryArticleInfo() {
        ArticleInfo articleInfo = articleInfoMapper.queryArticleInfo(1);
        System.out.println(articleInfo);
    }
}