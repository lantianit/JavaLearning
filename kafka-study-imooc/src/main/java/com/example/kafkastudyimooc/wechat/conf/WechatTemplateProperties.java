package com.example.kafkastudyimooc.wechat.conf;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author yourname <zhanghao38@kuaishou.com>
 * Created on 2024-01-25
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "template")
public class WechatTemplateProperties {

    private List<WechatTemplate> templates;
    private int templateResultType;
    private String templateResultFilePath;

    @Data
    public static class WechatTemplate {
        private String templateId;
        private String templateFilePath;
        public boolean active;
    }
}
