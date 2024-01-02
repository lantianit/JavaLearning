package homework.week04.api;

import homework.week04.utils.HttpClientUtilNew;
import homework.week04.utils.ResponseData;

import java.util.HashMap;
import java.util.Map;

public class LoginApi {
    private final String loginUrl = "http://ihrm-test.itheima.net/api/sys/login";

    // 登录
    public ResponseData login(String mobile, String password) throws Exception {
        Map<String, Object> jsonParam = new HashMap<>();
        jsonParam.put("mobile", mobile);
        jsonParam.put("password", password);
        return HttpClientUtilNew.post(this.loginUrl, jsonParam);
    }



}
