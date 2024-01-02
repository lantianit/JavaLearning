package com.itheima.apitest.script;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.apitest.api.LoginApi;
import com.itheima.apitest.utils.ResponseData;
import com.itheima.apitest.utils.TestDataUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * 1.一个模块对应一个JSON文件
 * 2.一个接口对应一个JSON文件****
 * 3.一个测试用例对应一个JSON文件
 */

public class TestLogin {
    private static final Logger logger = LoggerFactory.getLogger(TestLogin.class);

    private LoginApi loginApi = new LoginApi();


    // 构造测试数据--定义数据的提供者
    @DataProvider
    public Object[][] buildData() throws IOException {
        // 读取JSON文件
//        String jsonStr = FileUtils.readFileToString(new File("./data/login.json"), "UTF-8");
//        // 解析JSON数据
//        JSONArray jsonArray = JSONObject.parseArray(jsonStr);
//
//        // 组装数据
//        Object[][] testData = new Object[jsonArray.size()][];
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            String username = jsonObject.getString("username");
//            String password = jsonObject.getString("password");
//            String code = jsonObject.getString("code");
//            int statusCode = jsonObject.getIntValue("statusCode");
//            int status = jsonObject.getIntValue("status");
//            String msg = jsonObject.getString("msg");
//
//            Object[] caseData = new Object[]{username, password, code, statusCode, status, msg};
//            testData[i] = caseData;
//        }
//        logger.info("testData==={}", Arrays.deepToString(testData));
        return TestDataUtil.loadTestData("./data/login.json", "username,password,code,statusCode,status,msg");
    }


    @DataProvider
    public Object[][] buildAddEmpData() throws IOException {
        return TestDataUtil.loadTestData("./data/emp.json", "name,mobile,statusCode,status,msg");
    }


    // 登录
    @Test(dataProvider = "buildData")
    public void test00Login(String username, String password, String code,
                            int statusCode, int status, String msg) throws Exception {
        logger.info("username={} password={} code={}", username, password, code);

        // 获取图片验证码
        ResponseData r1 = this.loginApi.getVerifyCode();
        Assert.assertEquals(r1.getStatusCode(), 200);

        // 登录
        ResponseData r2 = this.loginApi.login(username, password, code);
        logger.info("login r2==" + r2);
        Assert.assertEquals(r2.getStatusCode(), statusCode);
        Assert.assertEquals(r2.getBody().getIntValue("status"), status);
        Assert.assertTrue(r2.getBody().getString("msg").contains(msg));
    }


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
