package com.example.demo.mapper;

import com.example.demo.entity.vo.ArticleinfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    void getById() {
        ArticleinfoVO articleinfoVO = articleMapper.getById(1);
        System.out.println(articleinfoVO);
    }
}