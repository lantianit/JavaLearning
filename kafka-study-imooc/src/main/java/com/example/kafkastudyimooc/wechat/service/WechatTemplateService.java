package com.example.kafkastudyimooc.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.example.kafkastudyimooc.wechat.conf.WechatTemplateProperties;

/**
 * @author yourname <zhanghao38@kuaishou.com>
 * Created on 2024-01-25
 */
public interface WechatTemplateService {
    // 获取微信调查问卷模板
    WechatTemplateProperties.WechatTemplate getWechatTemplate();
    // 上报微信调查问卷结果
    void templateReported(JSONObject reportInfo);
    // 获取调查问卷的统计结果
    JSONObject templateStatistics(String templated);
}
