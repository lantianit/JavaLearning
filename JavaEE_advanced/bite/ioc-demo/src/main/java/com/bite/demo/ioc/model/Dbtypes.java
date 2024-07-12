package com.bite.demo.ioc.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "dbtypes")
@Data
public class Dbtypes {
    private List<String> name;
    private Map<String,String> map;
}
