package httpclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestHttpClient {
    @Test
    public void test01Get() throws IOException {
        // 创建http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //CloseableHttpClient client = HttpClients.createDefault();

        // 创建Get请求对象
        HttpGet httpGet = new HttpGet("http://www.baidu.com");

        // 使用http客户端发送请求，并获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 获取响应数据-响应体数据
        HttpEntity entity = response.getEntity();
        String data = EntityUtils.toString(entity);
        System.out.println("data=" + data);

        // 释放资源
        response.close();
        httpClient.close();
    }

    @Test
    public void test02PostForm() throws Exception {
        // 需求：
        //请求TPshop项目的登录接口，请求数据（username: 13012345678, password: 123456, verify_code: 1234）
        //登录接口URL：http://tpshop-test.itheima.net/index.php?m=Home&c=User&a=do_login

        // 创建http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求对象
        HttpPost httpPost = new HttpPost("http://tpshop-test.itheima.net/index.php?m=Home&c=User&a=do_login");
        // 组装请求体数据-请求参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "13012345678"));
        params.add(new BasicNameValuePair("password", "123456"));
        params.add(new BasicNameValuePair("verify_code", "1234"));
        // 设置为请求体
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        // 使用http客户端发送请求，并获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 获取响应数据-响应体数据
        String data = EntityUtils.toString(response.getEntity());
        System.out.println("data==" + data);

        // 释放资源
        response.close();
        httpClient.close();
    }


    @Test
    public void test03PostJson() throws IOException {
        // 需求：
        //请求IHRM项目的登录接口，请求数据（{"mobile":"13800000002", "password":"123456"}）
        //登录接口URL：http://ihrm-test.itheima.net/api/sys/login

        // 创建http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求对象
        HttpPost httpPost = new HttpPost("http://ihrm-test.itheima.net/api/sys/login");
        // 组装请求数据-JSON格式数据
        // String jsonData = "{\"mobile\":\"13800000002\", \"password\":\"123456\"}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", "13800000002");
        jsonObject.put("password", "123456");
        String jsonData = jsonObject.toString();
        // 设置为请求体
        StringEntity se = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
        httpPost.setEntity(se);

        // 使用http客户端发送请求，并获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 获取响应数据-响应体数据
        String data = EntityUtils.toString(response.getEntity());
        System.out.println("data==" + data);
        // 解析返回的JSON数据
        JSONObject jsonObject1 = JSONObject.parseObject(data);
        boolean success = jsonObject1.getBooleanValue("success");
        System.out.println("success===" + success);
        int code = jsonObject1.getIntValue("code");
        System.out.println("code===" + code);

        // 释放资源
        response.close();
        httpClient.close();
    }

    @Test
    public void test04Response() throws IOException {
        // 创建http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Get请求对象
        HttpGet httpGet = new HttpGet("http://www.baidu.com");

        // 使用http客户端发送请求，并获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 获取响应数据
        // 获取响应状态码
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        System.out.println("statusCode===" + statusCode);
        // 获取响应头
        Header[] headers = response.getAllHeaders();
        for (Header header : headers) {
            System.out.println(header.getName() + " : " + header.getValue());
        }
        Header contentType = response.getFirstHeader("Content-Type");
        System.out.println(contentType.getValue());
        // 获取响应体数据
        HttpEntity entity = response.getEntity();
        String data = EntityUtils.toString(entity, "UTF-8");
        System.out.println("data=" + data);

        // 释放资源
        response.close();
        httpClient.close();
    }


    @Test
    public void test05SetHeader() throws IOException {
        // 需求：
        //请求IHRM项目的登录接口，请求数据（{"mobile":"13800000002", "password":"123456"}）
        //登录接口URL：http://ihrm-test.itheima.net/api/sys/login

        // 创建http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求对象
        HttpPost httpPost = new HttpPost("http://ihrm-test.itheima.net/api/sys/login");
        // 设置请求头
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("area", "010");
        // 设置请求体
        String jsonData = "{\"mobile\":\"13800000002\", \"password\":\"123456\"}";
        httpPost.setEntity(new StringEntity(jsonData));

        // 使用http客户端发送请求，并获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 获取响应数据-响应体数据
        String data = EntityUtils.toString(response.getEntity());
        System.out.println("data==" + data);

        // 释放资源
        response.close();
        httpClient.close();
    }

}
