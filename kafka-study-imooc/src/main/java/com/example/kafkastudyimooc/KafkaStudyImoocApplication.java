package com.example.kafkastudyimooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class KafkaStudyImoocApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaStudyImoocApplication.class, args);
    }


}
