package com.bite.blog.controller;

import com.bite.blog.constants.Constant;
import com.bite.blog.model.Result;
import com.bite.blog.model.UserInfo;
import com.bite.blog.service.UserService;
import com.bite.blog.utils.JwtUtils;
import com.bite.blog.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    //登录接口
    @RequestMapping("/login")
    public Result login(String userName, String password){
        //1. 对参数进行校验
        //2. 对密码进行校验
        //3. 如果校验成功, 生成token
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)){
            return Result.fail("用户名或密码不能为空");
        }
        UserInfo userInfo = userService.queryUserByName(userName);
        if (userInfo==null || userInfo.getId()<=0){
            return Result.fail("用户不存在");
        }
        //密码校验
//        if (!password.equals(userInfo.getPassword())){
//            return Result.fail("密码错误");
//        }
        if (!SecurityUtils.verify(password, userInfo.getPassword())){
            return Result.fail("密码错误");
        }
        //密码正确了, 生成token
        Map<String,Object> claim = new HashMap<>();
        claim.put(Constant.USER_CLAIM_ID, userInfo.getId());
        claim.put(Constant.USER_CLAIM_NAME, userInfo.getUserName());
        return Result.success(JwtUtils.genToken(claim));

    }
    /**
     * 获取当前登录用户的信息
     */
    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(HttpServletRequest request){
        //1. 获取token, 从token中获取ID
        //2. 根据ID, 获取用户信息
        String user_token = request.getHeader(Constant.USER_TOKEN_HEADER);
        Integer userId = JwtUtils.getUserIdFromToken(user_token);
        if (userId==null || userId<=0){
            return null;
        }
        UserInfo userInfo = userService.queryUserById(userId);
        userInfo.setPassword("");
        return userInfo;
    }
    /**
     * 根据博客ID, 获取作者信息
     */
    @RequestMapping("/getAuthorInfo")
    public UserInfo getAuthorInfo(Integer blogId){

        if (blogId!=null && blogId <1){
            return null;
        }
        UserInfo authorInfoByBlogId = userService.getAuthorInfoByBlogId(blogId);
        authorInfoByBlogId.setPassword("");
        return authorInfoByBlogId;
    }
}
