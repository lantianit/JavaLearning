package com.example.mq;

import com.example.mq.mqserver.BrokerServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MqApplication {
	public static ConfigurableApplicationContext context;
	
	

	public static void main(String[] args) throws IOException {
		context = SpringApplication.run(MqApplication.class, args);

	}

}
