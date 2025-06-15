package com.pubsub;

import java.util.*;

public class PubSubManager {
    private static PubSubManager pubSubManager = null;
    private final HashMap<String, Topic> topicsMap = new HashMap<>();

    private PubSubManager() {
    }

    public static synchronized PubSubManager getInstance() {
        if (pubSubManager == null) {
            pubSubManager = new PubSubManager();
        }
        return pubSubManager;
    }

    public synchronized Topic createTopic(String topicName) {
        if (topicsMap.containsKey(topicName)) {
            System.out.println("Topic already exists: " + topicName);
            return topicsMap.get(topicName);
        }
        Topic t = new Topic(UUID.randomUUID(), topicName, 2);
        for (int i = 0; i < t.getPartitions(); i++) {
            Partition p = new Partition("p_" + i, "partition_name_" + i, t);
            t.getPartitionList().add(p);
        }
        topicsMap.put(topicName, t);
        return t;
    }

    public synchronized void deleteTopic(String topicName) {
        if (!topicsMap.containsKey(topicName)) {
            System.out.println("Topic does not exist: " + topicName);
            return;
        }
        Topic t = topicsMap.remove(topicName);
        for (Partition partition : new ArrayList<>(t.getPartitionList())) {
            if (partition.getConsumer() != null) {
                unsubscribe(topicName, partition.getConsumer());
            }
        }
    }

    public synchronized void subscribe(String topicName, Consumer consumer) {
        Topic t = topicsMap.get(topicName);
        if (t == null || consumer == null) {
            System.out.println("Invalid topic or consumer");
            return;
        }

        List<Partition> partitionList = t.getPartitionList();
        if (partitionList.isEmpty()) {
            System.out.println("No partitions found in topic: " + topicName);
            return;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(partitionList.size());
        Partition partition = partitionList.get(randomIndex);

        // Attach consumer if not already
        if (partition.getConsumer() == null) {
            partition.setConsumer(consumer);
            PartitionListener listener = new PartitionListener(partition);
            partition.setListener(listener);
            new Thread(listener).start();
        }

        consumer.getSubscribedPartitions().add(partition);
        System.out.println("Subscribed " + consumer.getName() + " to partition " + partition.getPartitionId());
    }

    public synchronized void unsubscribe(String topicName, Consumer consumer) {
        if (consumer == null || topicName == null) {
            System.out.println("Invalid topic or consumer");
            return;
        }

        Topic t = topicsMap.get(topicName);
        if (t == null) {
            System.out.println("Topic does not exist: " + topicName);
            return;
        }

        Iterator<Partition> iter = consumer.getSubscribedPartitions().iterator();
        while (iter.hasNext()) {
            Partition p = iter.next();
            if (topicName.equals(p.getTopic().getName()) &&
                    p.getConsumer() != null &&
                    p.getConsumer().getConsumerId().equals(consumer.getConsumerId())) {

                iter.remove();
                p.setConsumer(null);
                p.getTopic().getPartitionList().remove(p);
                if (p.getListener() != null) {
                    p.getListener().state = PartitionListener.ListenerState.CLOSED;
                }
                System.out.println("Unsubscribed " + consumer.getName() + " from partition " + p.getPartitionId());
            }
        }
    }

    public synchronized void publishMessage(Producer producer, String message) {
        Topic t = topicsMap.get(producer.getTopicName());
        if (t == null) {
            System.out.println("Topic does not exist: " + producer.getTopicName());
            return;
        }

        List<Partition> partitions = t.getPartitionList();
        if (partitions.isEmpty()) {
            System.out.println("No partitions found in topic: " + t.getName());
            return;
        }

        Random random = new Random();
        int chosenPartition = random.nextInt(partitions.size());
        partitions.get(chosenPartition).getMessages().add(message);
        System.out.println("Message published to partition " + partitions.get(chosenPartition).getPartitionId()+" consumer : "+partitions.get(chosenPartition).getConsumer().getName());
    }
}
