package com.example.mq.mqserver.datacenter;

import com.sun.tools.jdeprscan.scan.Scan;
import jdk.internal.util.xml.impl.Input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MessageFileManager {

    // 用于表示队列的统计信息
    static public class Stat {
        public int totalCount;
        public int validCount;
    }

    public void init(){}

    private String getQueueDir(String queueName) {
        return "./data/" + queueName;
    }

    private String getQueueDataPath(String queueName) {
        return getQueueDir(queueName) + "/queue_data.txt";
    }

    private String getQueueStatPath(String queueName) {
        return getQueueDir(queueName) + "/queue_stat.txt";
    }

    private Stat readStat(String queueName) {
        Stat stat = new Stat();
        try (InputStream inputStream = new FileInputStream(getQueueStatPath(queueName))){
            Scanner scanner = new Scanner(inputStream);
            stat.totalCount = scanner.nextInt();
            stat.validCount = scanner.nextInt();
            return stat;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeStat(String queueName){}

}