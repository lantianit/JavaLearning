package httpclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import java.io.IOException;

public class AssertUtil {

    /**
     * 公共的断言的方法
     *
     * @param response   响应对象
     * @param statusCode 期望的响应状态码
     * @param status     期望的JSON数据中的status字段
     * @param msg        期望的JSON数据中的msg字段
     */
    public static void commonAssert(CloseableHttpResponse response, int statusCode, int status, String msg) throws IOException {
        // 响应状态码
        Assert.assertEquals(response.getStatusLine().getStatusCode(), statusCode);

        // 响应体中的公共字段
        String loginResponseData = EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(loginResponseData);
        System.out.println("jsonObject===" + jsonObject);
        Assert.assertEquals(jsonObject.getIntValue("status"), status);
        Assert.assertTrue(jsonObject.getString("msg").contains(msg));
    }

}
