package com.example.demo.controller;

import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@RequestMapping("/test") // 路由注册
//@ResponseBody // 告诉程序我返回的是一个数据而非页面
//@Controller // 让框架启动的时候加载当前（只有加载的类，别人才能使用[访问]）
@RestController // @RestController = @Controller+@ResponseBody
@Slf4j
public class TestController {
    //    @RequestMapping("/hi")
//    @RequestMapping(value = "/hi", method = RequestMethod.POST)
//    @PostMapping("/hi")
    @GetMapping("/hi")
    public String sayHi(String name, Integer v) {
        return "Hi," + name + " |v=" + v;
    }


    @GetMapping("/num")
    public String getNum(Integer num) {
        return "num=" + num;
    }

    // 接收普通对象
    @PostMapping("/show-user")
    public String showUser(User user) {
        return user.toString();
    }

    // 接收 JSON 对象（和第三方系统通讯时常见的场景）
    @PostMapping("/show-json-user")
    public String showJSONUser(@RequestBody User user) {
        return user.toString();
    }


    @GetMapping("/show-time")
//    public String showTime(String t, String t2) {
    public String showTime(@RequestParam(value = "t", required = false) String startTime,
                           @RequestParam("t2") String endTime) {
        // startTime/entTime...
        return "开始时间：" + startTime + " | 结束时间：" + endTime;
    }

    @RequestMapping("/login/{username}/{password}")
    public String login(@PathVariable("username") String username,
                        @PathVariable("password") String password) {
        return username + ":" + password;
    }

    @RequestMapping("/show/{username}/and/{password}")
    public String showInfo(@PathVariable("password") String pwd,
                           @PathVariable("username") String username) {
        return username + ":" + pwd;
    }

    @RequestMapping("/upfile")
    public String upfile(@RequestPart("myfile") MultipartFile file) throws IOException {
        String path = "D:\\Generate\\img.png";
        // 保存文件
        file.transferTo(new File(path));
        return path;
    }

    @RequestMapping("/myupfile")
    public String myUpFile(@RequestPart("myfile") MultipartFile file) throws IOException {
        // 根目录
        String path = "D:\\Generate\\";
        // 根目录 + 【唯一的文件名】
        path += UUID.randomUUID().toString().replace("-", "");
        // 根目录 + 唯一的明文件 + 【文件的后缀】  ex:.png
        path += file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //
        file.transferTo(new File(path));
        return path;
    }

    // Spring MVC(Spring Web) 内置了 HttpServletRequest 和 HttpServletResponse
    @GetMapping("/getparam")
    public String getParam(HttpServletRequest req) {
        return req.getParameter("username");
    }

    @RequestMapping("/getck")
    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie item : cookies) {
            log.error(item.getName() + ":" + item.getValue());
        }
        return "get cookie~";
    }

    @RequestMapping("/getck2")
    public String getCookie2(@CookieValue("zhangsan") String val) {
        return "Cookie Value:" + val;
    }


    @RequestMapping("/getua")
    public String getUA(@RequestHeader("User-Agent") String userAgent) {
        return userAgent;
    }


    @RequestMapping("/setsess")
    public String setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userinfo", "userinfo");
        return "Set Session Success.";
    }

    @RequestMapping("/getsess")
    public String getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 切记一定要加 false
        if (session != null && session.getAttribute("userinfo") != null) {
            return (String) session.getAttribute("userinfo");
        } else {
            return "暂无 Session 信息";
        }
    }

    @RequestMapping("/getsess2")
    public String getSession2(@SessionAttribute(value = "userinfo", required = false) String userinfo) {
        return userinfo;
    }


    @RequestMapping("/respjson")
    public HashMap<String, String> respJson() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Java", "Java Value");
        map.put("MySQL", "MySQL Value");
        map.put("Redis", "Redis Value");
        return map;
    }

}
