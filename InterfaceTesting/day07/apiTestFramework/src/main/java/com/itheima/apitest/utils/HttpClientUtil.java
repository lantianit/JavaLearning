package com.itheima.apitest.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    private static CloseableHttpClient httpClient;

    // 存放请求头数据
    public static Map<String, String> headers = new HashMap<>();

    // 静态代码块，当类加载时会自动执行里面的代码
    static {
        httpClient = HttpClientBuilder.create().build();

        headers.put("area", "010");
    }


    /**
     * 构建URL
     *
     * @param url        基本URL
     * @param queryParam 查询参数
     * @return 构建之后的URL
     */
    private static String buildUrl(String url, Map<String, String> queryParam) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (queryParam != null) {
            for (String key : queryParam.keySet()) {
                uriBuilder.addParameter(key, queryParam.get(key));
            }
        }
        String newUrl = uriBuilder.toString();
        System.out.println("newUrl===" + newUrl);
        return newUrl;
    }

    // 构造响应数据
    private static ResponseData buildResponseData(CloseableHttpResponse response) throws IOException {
        String bodyStr = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println("bodyStr==" + bodyStr);

        ResponseData responseData = new ResponseData();
        responseData.setStatusCode(response.getStatusLine().getStatusCode());
        if (bodyStr.startsWith("{")) {
            responseData.setBody(JSONObject.parseObject(bodyStr));
        }
        return responseData;
    }

    /**
     * 发送GET请求
     *
     * @param url        请求路径
     * @param queryParam 查询参数
     * @return 统一处理的响应数据
     */
    public static ResponseData get(String url, Map<String, String> queryParam) throws Exception {
        // 构建URL
        String newUrl = buildUrl(url, queryParam);

        // 创建get请求对象
        HttpGet httpGet = new HttpGet(newUrl);
        // 设置请求头数据
        for (String key : headers.keySet()) {
            httpGet.setHeader(key, headers.get(key));
        }

        // 发送请求，得到响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 构造响应数据
        ResponseData responseData = buildResponseData(response);

        // 释放资源
        response.close();
        return responseData;
    }


    /**
     * 发送POST请求-提交表单数据
     *
     * @param url        请求路径
     * @param queryParam 查询参数
     * @param formParam  表单参数
     * @return 统一处理结果
     */
    public static ResponseData postForm(String url, Map<String, String> queryParam, Map<String, String> formParam)
            throws Exception {
        // 构建URL
        String newUrl = buildUrl(url, queryParam);

        // 创建post请求对象
        HttpPost httpPost = new HttpPost(newUrl);
        // 设置请求头数据
        for (String key : headers.keySet()) {
            httpPost.setHeader(key, headers.get(key));
        }
        // 构造请求体数据
        if (formParam != null) {
            List<NameValuePair> params = new ArrayList<>();
            for (String key : formParam.keySet()) {
                params.add(new BasicNameValuePair(key, formParam.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        }

        // 发送请求，得到响应对象
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 构造响应数据
        ResponseData responseData = buildResponseData(response);

        // 释放资源
        response.close();
        return responseData;
    }

    /**
     * 发送POST请求-提交JSON数据
     *
     * @param url        请求路径
     * @param queryParam 查询参数
     * @param jsonParam  JSON请求参数
     * @return 统一处理结果
     */
    public static ResponseData postJson(String url, Map<String, String> queryParam, Map<String, Object> jsonParam)
            throws Exception {
        // 构建URL
        String newUrl = buildUrl(url, queryParam);

        // 创建post请求对象
        HttpPost httpPost = new HttpPost(newUrl);
        // 设置请求头数据
        for (String key : headers.keySet()) {
            httpPost.setHeader(key, headers.get(key));
        }
        // 构造请求体数据
        if (jsonParam != null) {
            String jsonString = JSONObject.toJSONString(jsonParam);
            httpPost.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
        }

        // 发送请求，得到响应对象
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 构造响应数据
        ResponseData responseData = buildResponseData(response);

        // 释放资源
        response.close();

        return responseData;
    }


    public static void main(String[] args) throws Exception {
//        Map<String, String> queryParam = new HashMap<>();
//        queryParam.put("username", "tom");
//        queryParam.put("age", "18");
//        Map<String, Object> result = HttpClientUtil.get("http://www.baidu.com?a=123&b=1111", queryParam);
//        System.out.println("result===" + result);
//        int statusCode = (int) result.get("statusCode2");
//        String body = (String) result.get("body");
//        System.out.println("statusCode===" + statusCode);
//        System.out.println("body===" + body);

        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("username", "tom");
        queryParam.put("age", "18");
        ResponseData result = HttpClientUtil.get("http://www.baidu.com?a=123&b=1111", queryParam);
        System.out.println("statusCode===" + result.getStatusCode());
        System.out.println("body===" + result.getBody());


        // 需求：
        //请求IHRM项目的登录接口，请求数据（{"mobile":"13800000002", "password":"123456"}）
        //登录接口URL：http://ihrm-test.itheima.net/api/sys/login
//        Map<String, Object> jsonParam = new HashMap<>();
//        jsonParam.put("mobile", "13800000002");
//        jsonParam.put("password", "123456");
//        HttpClientUtil.postJson("http://ihrm-test.itheima.net/api/sys/login", null, jsonParam);


        System.out.println("hello");
    }
}
