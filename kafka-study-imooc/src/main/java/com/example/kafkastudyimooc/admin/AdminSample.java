package com.example.kafkastudyimooc.admin;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.config.ConfigResource;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class AdminSample {

    public final static String TOPIC_NAME = "zh";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        AdminClient adminClient = AdminSample.adminClient();
//        System.out.println("adminClient : " + adminClient);
//        创建topic实例
//        createTopic();
        topicLists();
    }

    public static void incrPartitions(int partitions) throws ExecutionException, InterruptedException {
        AdminClient adminClient = adminClient();
        Map<String,NewPartitions> partitionsMap = new HashMap<>();
        NewPartitions newPartitions = NewPartitions.increaseTo(partitions);
        partitionsMap.put(TOPIC_NAME,newPartitions);

        CreatePartitionsResult createPartitionsResult = adminClient.createPartitions(partitionsMap);
        createPartitionsResult.all().get();
    }

    public static void alterConfig() throws Exception {
        AdminClient adminClient =adminClient();
        Map<ConfigResource,Config> configMaps = new HashMap<>();
        //组织两个参数
        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC,TOPIC_NAME);
        Config config = new Config(Arrays.asList(new ConfigEntry("preallocate","true")));
        configMaps.put(configResource,config);
        AlterConfigsResult alterConfigsResult = adminClient.alterConfigs(configMaps);
        alterConfigsResult.all().get();

    }

    public void DescribeConfig() throws Exception {
        AdminClient adminClient = adminClient();
        // TODO 这里做一个预留，集群时会讲到
//        ConfigResource configResource = new ConfigResource(ConfigResource.Type.BROKER, TOPIC_NAME);
        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, TOPIC_NAME);
        DescribeConfigsResult describeConfigsResult = adminClient.describeConfigs(Arrays.asList(configResource));
        Map<ConfigResource, Config> configResourceConfigMap = describeConfigsResult.all().get();
        configResourceConfigMap.entrySet().stream().forEach((entry) -> {
            System.out.println("configResource : " + entry.getKey() + "  Config : " + entry.getValue());
        });
    }

    public static void descTopics() throws ExecutionException, InterruptedException {
        AdminClient adminClient = adminClient();
        DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Arrays.asList(TOPIC_NAME));
        Map<String, TopicDescription> stringTopicDescriptionMap = describeTopicsResult.all().get();
        Set<Map.Entry<String, TopicDescription>> entries = stringTopicDescriptionMap.entrySet();
        entries.stream().forEach((entry)->{
            System.out.println("name:" + entry.getKey() + ", desc: " + entry.getValue());
        });
    }

    public static void delTopics() throws ExecutionException, InterruptedException {
        AdminClient adminClient = adminClient();
        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList(TOPIC_NAME));
        deleteTopicsResult.all().get();
    }

    public static void topicLists() throws ExecutionException, InterruptedException {
        AdminClient adminClient = adminClient();
        //是否查看internal选项
        ListTopicsOptions options = new ListTopicsOptions();
        options.listInternal(true);
//        ListTopicsResult listTopicsResult = adminClient.listTopics();
        ListTopicsResult listTopicsResult = adminClient.listTopics(options);
        Set<String> names = listTopicsResult.names().get();
        Collection<TopicListing> topicListings = listTopicsResult.listings().get();
//        KafkaFuture<Map<String,TopicListing>> mapkafkaFuture = listTopicsResult.namesToListings();
        //打印names
        names.stream().forEach(System.out::println);
        //印topicListings
        topicListings.stream().forEach((topicList)->{
                System.out.println(topicList);
        });
    }

    public static void createTopic() {
        AdminClient adminClient = adminClient();
        Short rs = 1;
        NewTopic newTopic = new NewTopic(TOPIC_NAME,1,rs);
        CreateTopicsResult topics = adminClient.createTopics(Arrays.asList(newTopic));
        System.out.println("createTopic result:" + topics);
    }

    public static AdminClient adminClient() {
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.52.129:9092");
        AdminClient adminClient = AdminClient.create(properties);
        return adminClient;
    }
}
