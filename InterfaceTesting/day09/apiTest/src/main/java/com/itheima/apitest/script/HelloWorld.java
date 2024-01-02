package com.itheima.apitest.script;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
    // 获取日志器对象
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        // 调用不同的方法打印不同级别的日志
        logger.debug("debug...");
        logger.info("info...");
        logger.warn("warn...");
        logger.error("error...");

        String name = "张三";
        int age = 20;
        logger.info("name=" + name + " age=" + age);
        logger.info(String.format("name=%s age=%s", name, age));
        logger.info("name={} age={}", name, age);

        try {
            char c = "1111".charAt(6);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("出现错误了！", e);
        }

        hello();

        System.out.println("hello");
    }

    public static void hello() {
        logger.info("hello");
    }
}
