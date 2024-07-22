import com.example.mq.common.BinaryTool;
import com.example.mq.common.MqException;
import com.example.mq.mqserver.core.MSGQueue;
import com.example.mq.mqserver.core.Message;

import java.io.*;
import java.util.Scanner;

class StudyMessageFileManager {
    
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
    
    private String getQueueStatPath(String queueName) {
        return getQueueDir(queueName) + "/queue_stat.txt";
    }
    
    private Stat readStat(String queueName) throws IOException {
        Stat stat = new Stat();
        try (InputStream inputStream = new FileInputStream(getQueueDataPath(queueName))){
            Scanner scanner = new Scanner(inputStream);
            stat.totalCount = scanner.nextInt();
            stat.validCount = scanner.nextInt();
            return stat;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void writeStat(String queueName, Stat stat) {
        try (OutputStream outputStream = new FileOutputStream(getQueueDataPath(queueName))) {
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write(stat.totalCount + "\t" + stat.validCount);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void createQueueFiles(String queueName) throws IOException {
        File baseDir = new File(getQueueDir(queueName));
        if (!baseDir.exists()) {
            boolean ok = baseDir.mkdirs();
            if (!ok) {
                throw new IOException("创建目录失败！baseDir=" + baseDir.getAbsolutePath());
            }
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
        if (!checkFilesExits(queue.getName())) {
            throw new MqException("[MessageFileManager] 队列对应的文件不存在! queueName=" + queue.getName());
        }
        byte[] messageBinary = BinaryTool.toBytes(message);
        synchronized (queue) {
            File queueDataFile = new File(getQueueDataPath(queue.getName()));
            message.setOffsetBeg(queueDataFile.length() + 4);
            message.setOffsetEnd(queueDataFile.length() + 4 + messageBinary.length);
            try (OutputStream outputStream = new FileOutputStream(queueDataFile, true)) {
                try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
                    // 接下来要先写当前消息的长度, 占据 4 个字节的~~
                    dataOutputStream.writeInt(messageBinary.length);
                    // 写入消息本体
                    dataOutputStream.write(messageBinary);
                }
            }
            
        }
    }
    
    public void gc(MSGQueue queue) throws MqException, IOException {
        synchronized (queue) {
            long gcBeg = System.currentTimeMillis();
            File queueDataNewFile = new File(getQueueDataPath(queue.getName()));
            if (queueDataNewFile.exists()) {
                // 正常情况下, 这个文件不应该存在. 如果存在, 就是意外~~ 说明上次 gc 了一半, 程序意外崩溃了.
                throw new MqException("[MessageFileManager] gc 时发现该队列的 queue_data_new 已经存在! queueName=" + queue.getName());
            }
            boolean ok = queueDataNewFile.createNewFile();
            if (!ok) {
                throw new MqException()
            }
            
        }
    }
    
}