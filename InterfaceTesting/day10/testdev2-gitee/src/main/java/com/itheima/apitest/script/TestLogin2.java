package com.itheima.apitest.script;

import com.alibaba.fastjson.JSONObject;
import com.itheima.apitest.api.LoginApi;
import com.itheima.apitest.utils.ResponseData;
import com.itheima.apitest.utils.TestDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestLogin2 {
    private static final Logger logger = LoggerFactory.getLogger(TestLogin2.class);

    private LoginApi loginApi = new LoginApi();


    // 构造测试数据--定义数据的提供者
    @DataProvider
    public Object[][] buildData() throws IOException {
        return TestDataUtil.loadTestData("./data/login2.json", "body,statusCode,status,msg");
    }


    // 登录
    @Test(dataProvider = "buildData")
    public void test00Login(JSONObject body, int statusCode, int status, String msg) throws Exception {
        logger.info("body={} statusCode={} status={}", body, statusCode, status);

        // 获取图片验证码
        ResponseData r1 = this.loginApi.getVerifyCode();
        Assert.assertEquals(r1.getStatusCode(), 200);

        // 登录
        ResponseData r2 = this.loginApi.login2(body);
        logger.info("login r2==" + r2);
        Assert.assertEquals(r2.getStatusCode(), statusCode);
        Assert.assertEquals(r2.getBody().getIntValue("status"), status);
        Assert.assertTrue(r2.getBody().getString("msg").contains(msg));
    }



}
