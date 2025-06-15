package com.pubsub;


import lombok.NoArgsConstructor;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
public class Producer {
    private String producerId;
    private String topicName;
}
