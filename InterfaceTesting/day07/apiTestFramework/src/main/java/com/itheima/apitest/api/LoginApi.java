package com.itheima.apitest.api;

import com.itheima.apitest.utils.HttpClientUtil;
import com.itheima.apitest.utils.ResponseData;

import java.util.HashMap;
import java.util.Map;

public class LoginApi {
    private final String verifyCodeUrl = "http://tpshop-test.itheima.net/index.php?m=Home&c=User&a=verify";
    private final String loginUrl = "http://tpshop-test.itheima.net/index.php?m=Home&c=User&a=do_login";

    // 获取验证码
    public ResponseData getVerifyCode() throws Exception {
        return HttpClientUtil.get(this.verifyCodeUrl, null);
    }

    // 登录
    public ResponseData login(String username, String password, String code) throws Exception {
        Map<String, String> formParam = new HashMap<>();
        formParam.put("username", username);
        formParam.put("password", password);
        formParam.put("verify_code", code);
        return HttpClientUtil.postForm(this.loginUrl, null, formParam);
    }

}
