package com.pubsub;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Consumer {

    private String consumerId;
    private String name;

    // Always initialized to a thread-safe list
    private List<Partition> subscribedPartitions = Collections.synchronizedList(new ArrayList<>());

    public Consumer(String consumerId, String name) {
        this.consumerId = consumerId;
        this.name = name;
    }
}
