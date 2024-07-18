package com.example;

import com.example.mq.MqApplication;
import com.example.mq.mqserver.core.Binding;
import com.example.mq.mqserver.core.Exchange;
import com.example.mq.mqserver.core.ExchangeType;
import com.example.mq.mqserver.core.MSGQueue;
import com.example.mq.mqserver.datacenter.DataBaseManager;
import org.apache.ibatis.javassist.bytecode.ExceptionTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.TooManyListenersException;

@SpringBootTest
 class t {
    
    private DataBaseManager dataBaseManager = new DataBaseManager();
    
    @BeforeEach
    public void setUp() {
        MqApplication.context = SpringApplication.run(MqApplication.class);
        dataBaseManager.init();
    }
    
    @AfterEach
    public void tearDown() {
        MqApplication.context.close();
        dataBaseManager.deleteDB();
    }
    
    @Test
    public void testInitTable() {
        List<Exchange> exchangeList = dataBaseManager.selectAllExchanges();
        List<MSGQueue> queueList = dataBaseManager.selectAllQueues();
        List<Binding> bindingList = dataBaseManager.selectAllBindings();

        Assertions.assertEquals(1, exchangeList.size());
        Assertions.assertEquals("", exchangeList.get(0).getName());
        Assertions.assertEquals(ExchangeType.DIRECT, exchangeList.get(0).getType());
        Assertions.assertEquals(0, queueList.size());
        Assertions.assertEquals(0, bindingList.size());
    }
    
    @Test
    public void testInsertExchange() {
        Exchange exchange = createTestExchange("testExchange");
    }

    private Exchange createTestExchange(String exchangeName) {
        Exchange exchange = new Exchange();
        exchange.setName(exchangeName);
        exchange.setType(ExchangeType.DIRECT);
    }

}