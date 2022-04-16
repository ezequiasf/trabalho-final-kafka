package com.dbccompany.useranalysis.analysis;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserAnalysis {

    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "analysis",
            containerFactory = "listenerContainerFactory")
    public void analysis (@Payload String msg){
        System.out.println(msg);
    }
}
