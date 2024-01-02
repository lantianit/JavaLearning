package homework.week04.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class HttpClientUtilNew {
    private static CloseableHttpClient httpClient;

    // 存放请求头数据
    public static Map<String, String> headers = new HashMap<>();

    // 静态代码块，当类加载时会自动执行里面的代码
    static {
        httpClient = HttpClientBuilder.create().build();

        // 设置固定的请求头
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


    private static void setHeaders(HttpRequest httpRequest) {
        for (String key : headers.keySet()) {
            httpRequest.setHeader(key, headers.get(key));
        }
    }

    public static ResponseData get(String url) throws Exception {
        return get(url, null);
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
        setHeaders(httpGet);

        // 发送请求，得到响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);

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
    public static ResponseData post(String url, Map<String, String> queryParam, Map<String, Object> jsonParam)
            throws Exception {
        // 构建URL
        String newUrl = buildUrl(url, queryParam);

        // 创建post请求对象
        HttpPost httpPost = new HttpPost(newUrl);
        // 设置请求头数据
        setHeaders(httpPost);
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

    public static ResponseData post(String url, Map<String, Object> jsonParam)
            throws Exception {
        return post(url, null, jsonParam);
    }

    public static ResponseData delete(String url, Map<String, String> queryParam) throws Exception {
        // 构建URL
        String newUrl = buildUrl(url, queryParam);

        // 创建get请求对象
        HttpDelete httpDelete = new HttpDelete(newUrl);
        // 设置请求头数据
        setHeaders(httpDelete);

        // 发送请求，得到响应对象
        CloseableHttpResponse response = httpClient.execute(httpDelete);

        // 构造响应数据
        ResponseData responseData = buildResponseData(response);

        // 释放资源
        response.close();
        return responseData;
    }

    public static ResponseData put(String url, Map<String, String> queryParam, Map<String, Object> jsonParam)
            throws Exception {
        // 构建URL
        String newUrl = buildUrl(url, queryParam);

        // 创建post请求对象
        HttpPut httpPut = new HttpPut(newUrl);
        // 设置请求头数据
        setHeaders(httpPut);
        // 构造请求体数据
        if (jsonParam != null) {
            String jsonString = JSONObject.toJSONString(jsonParam);
            httpPut.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
        }

        // 发送请求，得到响应对象
        CloseableHttpResponse response = httpClient.execute(httpPut);

        // 构造响应数据
        ResponseData responseData = buildResponseData(response);

        // 释放资源
        response.close();

        return responseData;
    }


}
