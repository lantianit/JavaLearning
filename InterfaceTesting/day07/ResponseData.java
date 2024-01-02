package httpclient;

import com.alibaba.fastjson.JSONObject;

// 封装响应数据对象
public class ResponseData {
    // 响应状态码
    private int statusCode;
    // 响应体数据
    private JSONObject body;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public JSONObject getBody() {
        return body;
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }
}
