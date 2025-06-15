package com.pubsub;

public class PubSubDemo {

    public static void main(String[] args) throws InterruptedException {

        PubSubManager pubSubManager = PubSubManager.getInstance();

        // ==========================
        // Test 1: Single Topic, Two Consumers, Two Partitions
        // ==========================
        String topic1 = "topic_1";
        pubSubManager.createTopic(topic1);

        Consumer consumer1 = new Consumer("c1", "cname1");
        Consumer consumer2 = new Consumer("c2", "cname2");
        pubSubManager.subscribe(topic1, consumer1);
        pubSubManager.subscribe(topic1, consumer2);

        Producer producer1 = new Producer("p1", topic1);

        System.out.println("############### Test: Single topic, 2 consumers, 2 partitions ###############");

        for (int i = 0; i < 5; i++) {
            pubSubManager.publishMessage(producer1, "hi " + i);
            Thread.sleep(200);
        }

        Thread.sleep(500);

        // ==========================
        // Test 2: Unsubscribe consumer1 and publish again
        // ==========================
        pubSubManager.unsubscribe(topic1, consumer1);

        for (int i = 0; i < 5; i++) {
            pubSubManager.publishMessage(producer1, "hi second " + i);
            Thread.sleep(200);
        }

        Thread.sleep(500);

        // ==========================
        // Test 3: Subscribe a new consumer and publish again
        // ==========================
        Consumer consumer3 = new Consumer("c3", "cname3");
        pubSubManager.subscribe(topic1, consumer3);

        for (int i = 0; i < 5; i++) {
            pubSubManager.publishMessage(producer1, "hi third " + i);
            Thread.sleep(200);
        }

        Thread.sleep(500);

        // ==========================
        // Test 4: Delete topic and try publishing again
        // ==========================
        pubSubManager.deleteTopic(topic1);

        for (int i = 0; i < 5; i++) {
            pubSubManager.publishMessage(producer1, "hi third deleted " + i);
            Thread.sleep(200);
        }

        Thread.sleep(500);

        // ==========================
        // Test 5: New topic and producer/consumer
        // ==========================
        String topic2 = "topic_2";
        pubSubManager.createTopic(topic2);

        Producer producer2 = new Producer("p2", topic2);
        Consumer consumer4 = new Consumer("c4", "cname4");
        pubSubManager.subscribe(topic2, consumer4);

        for (int i = 0; i < 5; i++) {
            pubSubManager.publishMessage(producer2, "hi fourth " + i);
            Thread.sleep(200);
        }

        System.out.println("############### All tests complete ###############");
    }
}
