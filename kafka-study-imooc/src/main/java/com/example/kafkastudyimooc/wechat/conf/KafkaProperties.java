package com.example.kafkastudyimooc.wechat.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yourname <zhanghao38@kuaishou.com>
 * Created on 2024-01-26
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "wechat.kafka")
public class KafkaProperties {

    private String bootstrap_servers;
    private String acks_config;
    private String partitioner_class;

}
