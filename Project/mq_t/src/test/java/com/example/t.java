package com.example;

import com.example.mq.mqserver.core.MSGQueue;
import com.example.mq.mqserver.datacenter.MessageFileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;

@SpringBootTest
public class t {
    
    private MessageFileManager messageFileManager = new MessageFileManager();
    
    private static final String queueName1 = "testQueue1";
    private static final String queueName2 = "testQueue2";

    @BeforeEach
    public void setUp() throws IOException {
        // 准备阶段, 创建出两个队列, 以备后用
        messageFileManager.createQueueFiles(queueName1);
        messageFileManager.createQueueFiles(queueName2);
    }

    // 这个方法就是每个用例执行完毕之后的收尾工作
    @AfterEach
    public void tearDown() throws IOException {
        // 收尾阶段, 就把刚才的队列给干掉.
        messageFileManager.destroyQueueFiles(queueName1);
        messageFileManager.destroyQueueFiles(queueName2);
    }

    @Test
    public void testCreateFiles() {
        // 创建队列文件已经在上面 setUp 阶段执行过了. 此处主要是验证看看文件是否存在.
        File queueDataFile1 = new File("./data/" + queueName1 + "/queue_data.txt");
        Assertions.assertEquals(true, queueDataFile1.isFile());
        File queueStatFile1 = new File("./data/" + queueName1 + "/queue_stat.txt");
        Assertions.assertEquals(true, queueStatFile1.isFile());

        File queueDataFile2 = new File("./data/" + queueName2 + "/queue_data.txt");
        Assertions.assertEquals(true, queueDataFile2.isFile());
        File queueStatFile2 = new File("./data/" + queueName2 + "/queue_stat.txt");
        Assertions.assertEquals(true, queueStatFile2.isFile());
    }
    
    @Test
    public void testReadWriteStat() {
        MessageFileManager.Stat stat = new MessageFileManager.Stat();
        stat.validCount = 100;
        stat.validCount = 50;
        ReflectionTestUtils.invokeMethod(messageFileManager, "writeStat", queueName1, stat);
        
        MessageFileManager.Stat newStat = ReflectionTestUtils.invokeMethod(messageFileManager,"readStat", queueName1);
        Assertions.assertEquals(100, newStat.totalCount);
        Assertions.assertEquals(50, newStat.validCount);
        System.out.println("");
    }
    
    private MSGQueue createTestQueue(String queueName) {
        MSGQueue queue = new MSGQueue();
        queue.setAutoDelete();
    }

    
}