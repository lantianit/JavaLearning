package com.example.kafkastudyimooc.wechat.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.kafkastudyimooc.wechat.conf.WechatTemplateProperties;
import com.example.kafkastudyimooc.wechat.utils.FileUtils;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.internals.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author yourname <zhanghao38@kuaishou.com>
 * Created on 2024-01-25
 */

@Slf4j
@Service
public class WechatTemplateServiceImpl implements WechatTemplateService{
    @Autowired
    private  WechatTemplateProperties properties;

    @Autowired
    private Producer producer;

    @Override
    public WechatTemplateProperties.WechatTemplate getWechatTemplate() {
        List<WechatTemplateProperties.WechatTemplate> templates = properties.getTemplates();
        Optional<WechatTemplateProperties.WechatTemplate> wechatTemplate
                = templates.stream().filter((template) -> template.active).findFirst();
        return wechatTemplate.isPresent() ? wechatTemplate.get() : null;

    }

    @Override
    public void templateReported(JSONObject reportInfo) {
        log.info("templateReported : [{}]",reportInfo);

        String topicName = "zh";
        String templateId = reportInfo.getString("templateId");
        JSONArray reportData = reportInfo.getJSONArray("result");
        ProducerRecord<String,Object> record =
                new ProducerRecord<>(topicName,templateId,reportData);
        producer.send(record);

    }

    @Override
    public JSONObject templateStatistics(String templated) {
        if (properties.getTemplateResultType() == 0) {
            return FileUtils.readFile2JsonObject(properties.getTemplateResultFilePath()).get();
        } else {
            //DB
        }
        return null;
    }
}
