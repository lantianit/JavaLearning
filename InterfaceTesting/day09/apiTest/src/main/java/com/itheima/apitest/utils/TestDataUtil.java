package com.itheima.apitest.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.apitest.script.TestLogin;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestDataUtil {
    private static final Logger logger = LoggerFactory.getLogger(TestDataUtil.class);

    // loadTestData("./data/login.json", "username,password,code,statusCode,status,msg")
    public static Object[][] loadTestData(String filePath, String keys) throws IOException {
        // 读取JSON文件
        String jsonStr = FileUtils.readFileToString(new File(filePath), "UTF-8");
        // 解析JSON数据
        JSONArray jsonArray = JSONObject.parseArray(jsonStr);

        // 组装数据
        Object[][] testData = new Object[jsonArray.size()][];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            // 把字段名进行拆分
            String[] keyArray = keys.split(",");
            Object[] caseData = new Object[keyArray.length];
            for (int j = 0; j < keyArray.length; j++) {
                String key = keyArray[j];
                Object value = jsonObject.get(key);
                caseData[j] = value;
            }
            testData[i] = caseData;
        }
        logger.info("testData==={}", Arrays.deepToString(testData));
        return testData;
    }


}
