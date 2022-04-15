package com.dbccompany.processamentocartao.verification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PayAnalysis {

    @KafkaListener(
            topics = "4wqheic6-recipe-buy",
            groupId = "pay",
            containerFactory = "listenerContainerFactory")
    public void creditCardAnalysis (@Payload String msg){
        System.out.println(msg );
    }
}
