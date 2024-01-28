package com.example.kafkastudyimooc.consumer;



import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.time.OffsetTime;
import java.util.*;

/**
 * @author yourname <zhanghao38@kuaishou.com>
 * Created on 2024-01-26
 */
public class ConsumerSample {
    private final static String TOPIC_NAME = "zh";

    public static void main(String[] args) {
//        helloworld();
        commitedOffsetWithPartition2();
    }

    private static void commitedOffsetWithPartition2() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.52.129:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        TopicPartition p0 = new TopicPartition(TOPIC_NAME,0);
        TopicPartition p1 = new TopicPartition(TOPIC_NAME,1);

//
//        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        consumer.assign(Arrays.asList(p0));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
            //每个pattition单独获取
            for(TopicPartition partition : records.partitions()){
                List<ConsumerRecord<String, String>> pRcord = records.records(partition);
                for (ConsumerRecord<String,String> record : pRcord) {
                    System.out.printf("patition = %d, offset = %d, key = %s, value = %s%n",record.partition(), record.offset(), record.key(), record.value());
                }
                long lastOffset = pRcord.get(pRcord.size()-1).offset();
                Map<TopicPartition, OffsetAndMetadata> offset = new HashMap<>();
                offset.put(partition,new OffsetAndMetadata(lastOffset+1));
                // 提交offset
                consumer.commitSync(offset);
                System.out.println("一个partition结束");
            }
        }
    }

    private static void helloworld() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.52.129:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("partition = %d, offset = %d, key = %s, value = %s%n",record.partition(), record.offset(), record.key(), record.value());

        }
    }
    //手动提交offest
    private static void commitedOffset() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.52.129:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<String,String> record : records) {
                System.out.printf("partition = %d, offset = %d, key = %s, value = %s%n",record.partition(), record.offset(), record.key(), record.value());
                //如果失败则回滚
            }
            // 如果成功则提交
            consumer.commitAsync();
        }
    }

    //手动提交offest 并且手动控制partition
    private static void commitedOffsetWithPartition() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.52.129:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
            //每个pattition单独获取
            for(TopicPartition partition : records.partitions()){
                List<ConsumerRecord<String, String>> pRcord = records.records(partition);
                for (ConsumerRecord<String,String> record : pRcord) {
                    System.out.printf("patition = %d, offset = %d, key = %s, value = %s%n",record.partition(), record.offset(), record.key(), record.value());
                }
                long lastOffset = pRcord.get(pRcord.size()-1).offset();
                Map<TopicPartition, OffsetAndMetadata> offset = new HashMap<>();
                offset.put(partition,new OffsetAndMetadata(lastOffset+1));
                // 提交offset
                consumer.commitSync(offset);
                System.out.println("一个partition结束");
            }
        }
    }

}
