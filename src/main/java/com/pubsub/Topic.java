package com.pubsub;

import lombok.NoArgsConstructor;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
public class Topic {
    private UUID topicId;
    private String name;
    private int partitions;
    private List<Partition> partitionList;


    public Topic(UUID uuid, String name, int partitions) {
        this.name = name;
        this.topicId = uuid;
        this.partitions = partitions;
        partitionList = new ArrayList<>();
    }
}
