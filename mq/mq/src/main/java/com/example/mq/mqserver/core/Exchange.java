package com.example.mq.mqserver.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
    表示一个交换机
 */
public class Exchange {

    // name作为交换机的身份标识（唯一的）
    private String name;
    // 交换机类型 DIRECT, FANOUT, TOPIC
    private ExchangeType exchangeType = ExchangeType.DIRECT;
    // 该交换机是否需要持久化存储 false表示不需要 true表示需要
    private boolean durable = false;
    // 表示如果当前交换机没有被使用，是否自动删除
    private boolean autoDelete = false;
    // arguments 表示创建交换机时的 额外参数选项
    private Map<String, Objects> arguments = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExchangeType getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(ExchangeType exchangeType) {
        this.exchangeType = exchangeType;
    }

    public boolean isDurable() {
        return durable;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public boolean isAutoDelete() {
        return autoDelete;
    }

    public void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public Map<String, Objects> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Objects> arguments) {
        this.arguments = arguments;
    }
}
