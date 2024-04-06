package com.example.mq.mqserver.core;

import java.util.HashMap;
import java.util.Map;

/*

 */
public class MSGQueue {

    // 表示队列的身份标识
    private String name;
    // 表示队列是否持久化
    private boolean durable = false;
    // 这个属性如果为true 则这个队列只能一个消费者使用
    // 这个 独占 功能，暂不实现
    private boolean exclusive = false;
    // 为true表示没有使用后，自动删除
    private boolean autoDelete = false;
    // 表示扩展参数
    private Map<String, Object> arguments = new HashMap<>();

}
