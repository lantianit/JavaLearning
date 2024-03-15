package com.example.kafkastudyimooc.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.kafkastudyimooc.wechat.common.BaseResponseVO;
import com.example.kafkastudyimooc.wechat.conf.WechatTemplateProperties;
import com.example.kafkastudyimooc.wechat.service.WechatTemplateService;
import com.example.kafkastudyimooc.wechat.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yourname <zhanghao38@kuaishou.com>
 * Created on 2024-01-25
 */

@RestController
@RequestMapping(value = "/v1")
public class WechatTemplateController {
    @Autowired
    private WechatTemplateProperties properties;

    @Autowired
    private WechatTemplateService wechatTemplateService;

    @RequestMapping(value = "template",method = RequestMethod.GET)
    public BaseResponseVO getTemplate() {

        WechatTemplateProperties.WechatTemplate wechatTemplate = wechatTemplateService.getWechatTemplate();

        Map<String,Object> result = new HashMap<>();
        result.put("templateId",wechatTemplate.getTemplateId());
        result.put("template", FileUtils.readFile2JsonArray(wechatTemplate.getTemplateFilePath()));
        return BaseResponseVO.success(result);
    }

    @RequestMapping(value = "/template/result",method = RequestMethod.GET)
    public BaseResponseVO getTemplateStatistics(
            @RequestParam(value = "", required = false)String templateId) {
        JSONObject statistics = wechatTemplateService.templateStatistics(templateId);
        return BaseResponseVO.success(statistics);
    }

    @RequestMapping(value = "/template/report",method = RequestMethod.POST)
    public BaseResponseVO dataReported(
            @RequestBody String reportData) {
        wechatTemplateService.templateReported(JSONObject.parseObject(reportData));
        return BaseResponseVO.success();
    }
}
