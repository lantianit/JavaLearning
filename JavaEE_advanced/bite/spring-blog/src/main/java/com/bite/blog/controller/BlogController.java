package com.bite.blog.controller;

import com.bite.blog.constants.Constant;
import com.bite.blog.model.BlogInfo;
import com.bite.blog.service.BlogService;
import com.bite.blog.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/getList")
    public List<BlogInfo> queryBlogList(){
        return blogService.queryBlogList();
    }
    @RequestMapping("/getBlogDetail")
    public BlogInfo queryBlogDetail(Integer blogId,HttpServletRequest request){
        log.info("getBlogDetail, 接收参数blogId:"+blogId);
        BlogInfo blogInfo = blogService.queryBlogDetail(blogId);
        //1. 获取登录用户信息
        //2. 判断登录用户是否为作者
        String user_token = request.getHeader(Constant.USER_TOKEN_HEADER);
        Integer userId = JwtUtils.getUserIdFromToken(user_token);
        if (userId!=null && userId==blogInfo.getUserId()){
            blogInfo.setLoginUser(true);
        }else {
            blogInfo.setLoginUser(false);
        }
        log.info("queryBlogDetail, 接收参数:{}, 返回结果{}", blogId, blogInfo);
        return blogInfo;
    }
    @RequestMapping("/add")
    public Boolean publishBlog(String title, String content, HttpServletRequest request){
        log.info("publishBlog, 接收参数: title:{},content:{}",title, content);
        //1. 参数校验
        //2. 获取当前登录用户
        //3. 博客发布
        if (!StringUtils.hasLength(title) || !StringUtils.hasLength(content)){
            log.error("title or content 为空");
            return false;
        }
        String user_token = request.getHeader(Constant.USER_TOKEN_HEADER);
        Integer userId = JwtUtils.getUserIdFromToken(user_token);
        if (userId==null || userId<=0){
            log.error("用户未登录");
            return false;
        }
        BlogInfo blogInfo = new BlogInfo(title, content, userId);
        Integer result = blogService.publishBlog(blogInfo);
        if (result<1){
            log.error("博客发布失败");
            return false;
        }
        return true;

    }
    /**
     * 编辑博客
     */
    @RequestMapping("/update")
    public Boolean update(Integer blogId, String title, String content){
        log.info("updateBlog,接收参数 title:{}, content:{}", title,content);
        if (blogId==null || !StringUtils.hasLength(title) || !StringUtils.hasLength(content)){
            log.error("ID/标题/内容不合法");
            return false;
        }
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(blogId);
        blogInfo.setTitle(title);
        blogInfo.setContent(content);
        Integer result = blogService.updateBlog(blogInfo);
        if (result<1){
            return false;
        }
        return true;

    }
    /**
     * 删除博客
     */
    @RequestMapping("/delete")
    public Boolean delete(Integer blogId){
        log.info("deleteBlog, blogId:"+blogId);
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(blogId);
        blogInfo.setDeleteFlag(1);
        Integer result = blogService.updateBlog(blogInfo);
        if (result<1){
            return false;
        }
        return true;
    }
}
