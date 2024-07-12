package com.bite.blog.mapper;

import com.bite.blog.model.BlogInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogMapperTest {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    void selectAllBlog() {
        System.out.println(blogMapper.selectAllBlog());
    }

    @Test
    void selectById() {
        System.out.println(blogMapper.selectById(1));
    }

    @Test
    void updateBlog() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setTitle("update测试测试测试更新数据");
        blogInfo.setContent("update你好测试测试测试");
        blogInfo.setId(1);
        System.out.println(blogMapper.updateBlog(blogInfo));
    }
    @Test
    void deleteBlog() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(2);
        blogInfo.setDeleteFlag(1);
        System.out.println(blogMapper.updateBlog(blogInfo));
    }

    @Test
    void insertBlog() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setTitle("insert测试测试测试更新数据");
        blogInfo.setContent("insert你好测试测试测试");
        blogInfo.setUserId(2);
        System.out.println(blogMapper.insertBlog(blogInfo));
    }
}