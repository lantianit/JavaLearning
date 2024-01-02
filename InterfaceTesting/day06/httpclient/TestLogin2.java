package httpclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestLogin2 {
    private CloseableHttpClient httpClient;

    private static final String codeUrl = App.BASE_URL + "/index.php?m=Home&c=User&a=verify";
    private static final String loginUrl = App.BASE_URL + "/index.php?m=Home&c=User&a=do_login";

    @BeforeMethod
    public void before() {
        // 创建http客户端
        this.httpClient = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void after() {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 登录成功
    @Test
    public void test01LoginSuccess() throws IOException {
        // 获取图片验证码
        HttpGet codeHttpGet = new HttpGet(codeUrl);
        CloseableHttpResponse r1 = httpClient.execute(codeHttpGet);
        // 断言
        Assert.assertEquals(r1.getStatusLine().getStatusCode(), 200);

        // 登录
        HttpPost loginHttpPost = new HttpPost(loginUrl);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "13012345678"));
        params.add(new BasicNameValuePair("password", "123456"));
        params.add(new BasicNameValuePair("verify_code", "8888"));
        loginHttpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        CloseableHttpResponse r2 = httpClient.execute(loginHttpPost);

        // 断言
        AssertUtil.commonAssert(r2, 200, 1, "登陆成功");
//        Assert.assertEquals(r2.getStatusLine().getStatusCode(), 200);
//        String loginResponseData = EntityUtils.toString(r2.getEntity(), "UTF-8");
//        System.out.println("loginResponseData==" + loginResponseData);
//        JSONObject jsonObject = JSONObject.parseObject(loginResponseData);
//        System.out.println("jsonObject===" + jsonObject);
//        Assert.assertEquals(jsonObject.getIntValue("status"), 1);
//        Assert.assertEquals(jsonObject.getString("msg"), "登陆成功");

        // 释放资源
        r1.close();
        r2.close();
    }

    // 账号不存在
    @Test
    public void test02UsernameNotExist() throws IOException {
        // 获取图片验证码
        HttpGet codeHttpGet = new HttpGet(codeUrl);
        CloseableHttpResponse r1 = httpClient.execute(codeHttpGet);
        // 断言
        Assert.assertEquals(r1.getStatusLine().getStatusCode(), 200);

        // 登录
        HttpPost loginHttpPost = new HttpPost(loginUrl);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "13088888888"));
        params.add(new BasicNameValuePair("password", "123456"));
        params.add(new BasicNameValuePair("verify_code", "8888"));
        loginHttpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        CloseableHttpResponse r2 = httpClient.execute(loginHttpPost);

        // 断言
//        Assert.assertEquals(r2.getStatusLine().getStatusCode(), 200);
//        String loginResponseData = EntityUtils.toString(r2.getEntity(), "UTF-8");
//        System.out.println("loginResponseData==" + loginResponseData);
//        JSONObject jsonObject = JSONObject.parseObject(loginResponseData);
//        System.out.println("jsonObject===" + jsonObject);
//        Assert.assertEquals(jsonObject.getIntValue("status"), -1);
//        Assert.assertEquals(jsonObject.getString("msg"), "账号不存在!");
//        Assert.assertTrue(jsonObject.getString("msg").contains("账号不存在"));
        AssertUtil.commonAssert(r2, 200, -1, "账号不存在");

        // 释放资源
        r1.close();
        r2.close();
    }

    // 密码错误
    @Test
    public void test03PasswordError() throws IOException {
        // 获取图片验证码
        HttpGet codeHttpGet = new HttpGet(codeUrl);
        CloseableHttpResponse r1 = httpClient.execute(codeHttpGet);
        // 断言
        Assert.assertEquals(r1.getStatusLine().getStatusCode(), 200);
        String contentType = r1.getFirstHeader("Content-Type").getValue();
        System.out.println("contentType===" + contentType);
        Assert.assertTrue(contentType.contains("image"));

        // 登录
        HttpPost loginHttpPost = new HttpPost(loginUrl);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "13012345678"));
        params.add(new BasicNameValuePair("password", "666666"));
        params.add(new BasicNameValuePair("verify_code", "8888"));
        loginHttpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        CloseableHttpResponse r2 = httpClient.execute(loginHttpPost);

        // 断言
//        Assert.assertEquals(r2.getStatusLine().getStatusCode(), 200);
//        String loginResponseData = EntityUtils.toString(r2.getEntity(), "UTF-8");
//        System.out.println("loginResponseData==" + loginResponseData);
//        JSONObject jsonObject = JSONObject.parseObject(loginResponseData);
//        System.out.println("jsonObject===" + jsonObject);
//        Assert.assertEquals(jsonObject.getIntValue("status"), -2);
//        Assert.assertTrue(jsonObject.getString("msg").contains("密码错误"));
        AssertUtil.commonAssert(r2, 200, -2, "密码错误");

        // 释放资源
        r1.close();
        r2.close();
    }
}
