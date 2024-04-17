package com.example.mq.mqserver.datacenter;

import com.example.mq.common.BinaryTool;
import com.example.mq.common.MqException;
import com.example.mq.mqserver.core.MSGQueue;
import com.example.mq.mqserver.core.Message;

import java.io.*;
import java.util.Scanner;

public class MessageFileManager {

    static public class Stat {
        public int totalCount;
        public int validCount;
    }

    public void init() {}

    private String getQueueDir(String queueName) {
        return "./data/" + queueName;
    }

    private String getQueueDataPath(String queueName) {
        return getQueueDir(queueName) + "/queue_data.txt";
    }

    // 这个方法用来获取该队列的消息统计文件路径
    private String getQueueStatPath(String queueName) {
        return getQueueDir(queueName) + "/queue_stat.txt";
    }

    private Stat readStat(String queueName) throws IOException {
        Stat stat = new Stat();
        try (InputStream inputStream = new FileInputStream(getQueueStatPath(queueName))) {
            Scanner scanner = new Scanner(inputStream);
            stat.totalCount = scanner.nextInt();
            stat.validCount = scanner.nextInt();
            return stat;
        }
    }

    public void writeStat(String queueName, Stat stat) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(getQueueStatPath(queueName))){
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write(stat.totalCount + "/t" + stat.validCount);
            printWriter.flush();
        }
    }

    public boolean checkFilesExits(String queueName) {
        // 判定队列的数据文件和统计文件是否都存在!!
        File queueDataFile = new File(getQueueDataPath(queueName));
        if (!queueDataFile.exists()) {
            return false;
        }
        File queueStatFile = new File(getQueueStatPath(queueName));
        if (!queueStatFile.exists()) {
            return false;
        }
        return true;
    }

    public void sendMessage(MSGQueue queue, Message message) throws MqException, IOException {
        if (checkFilesExits(queue.getName())) {
            throw new MqException("");
        }

        byte[] messageBinary = BinaryTool.toBytes(message);
        synchronized (queue) {
            File queueDataFile = new File(getQueueDataPath(queue.getName()));
            message.setOffsetBeg(queueDataFile.length() + 4);
            message.setOffsetEnd(queueDataFile.length() + 4 + messageBinary.length);

            try (OutputStream outputStream = new FileOutputStream(queueDataFile, true)) {
                try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
                    dataOutputStream.writeInt(messageBinary.length);
                    dataOutputStream.write(messageBinary);
                }
            }
            Stat stat = readStat(queue.getName());
            stat.validCount += 1;
            stat.totalCount += 1;
            writeStat(queue.getName(), stat);
        }

    }

}