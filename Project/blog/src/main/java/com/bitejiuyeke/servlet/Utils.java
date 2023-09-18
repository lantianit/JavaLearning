package com.bitejiuyeke.servlet;

import com.bitejiuyeke.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author 比特就业课
 * @Date 2022-07-06
 */
public class Utils {

    /**
     * 校验用户是否登录
     *
     * @param request HttpServletRequest
     * @return 已登录：当前用户<br>
     *         未登录：null
     */
    public static User checkLogin (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return null;
        }
        return user;
    }
}
