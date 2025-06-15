package com.pubsub;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.LinkedBlockingQueue;

@Getter
@Setter
@NoArgsConstructor
public class Partition {

    private String partitionId;
    private String partitionName;

    private LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<>();

    private Topic topic;

    private volatile Consumer consumer;
    private volatile PartitionListener listener;

    public Partition(String partitionId, String partitionName, Topic topic) {
        this.partitionId = partitionId;
        this.partitionName = partitionName;
        this.topic = topic;
        this.messages = new LinkedBlockingQueue<>();
    }
}
