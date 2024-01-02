package com.itheima.apitest.script;

import com.itheima.apitest.api.LoginApi;
import com.itheima.apitest.utils.ResponseData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin {
    private LoginApi loginApi = new LoginApi();

    // 登录成功
    @Test
    public void test01LoginSuccess() throws Exception {
        // 获取图片验证码
        ResponseData r1 = this.loginApi.getVerifyCode();
        Assert.assertEquals(r1.getStatusCode(), 200);

        // 登录
        ResponseData r2 = this.loginApi.login("13012345678", "123456", "8888");
        System.out.println("login r2==" + r2);
        Assert.assertEquals(r2.getStatusCode(), 200);
        Assert.assertEquals(r2.getBody().getIntValue("status"), 1);
        Assert.assertTrue(r2.getBody().getString("msg").contains("登陆成功"));
    }

    // 账号不存在
    @Test
    public void test02UsernameNotExist() throws Exception {
        // 获取图片验证码
        ResponseData r1 = this.loginApi.getVerifyCode();
        Assert.assertEquals(r1.getStatusCode(), 200);

        // 登录
        ResponseData r2 = this.loginApi.login("13088888888", "123456", "8888");
        System.out.println("login r2==" + r2);
        Assert.assertEquals(r2.getStatusCode(), 200);
        Assert.assertEquals(r2.getBody().getIntValue("status"), -1);
        Assert.assertTrue(r2.getBody().getString("msg").contains("账号不存在"));
    }

    // 密码错误
    @Test
    public void test03PasswordError() throws Exception {
        // 获取图片验证码
        ResponseData r1 = this.loginApi.getVerifyCode();
        Assert.assertEquals(r1.getStatusCode(), 200);

        // 登录
        ResponseData r2 = this.loginApi.login("13012345678", "123123", "8888");
        System.out.println("login r2==" + r2);
        Assert.assertEquals(r2.getStatusCode(), 200);
        Assert.assertEquals(r2.getBody().getIntValue("status"), -2);
        Assert.assertTrue(r2.getBody().getString("msg").contains("密码错误"));
    }

}
