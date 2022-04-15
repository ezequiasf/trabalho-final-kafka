package com.dbccompany.processamentocartao.verification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class BuyVerification {

    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "verify",
            containerFactory = "listenerContainerFactory")
    public void verifyPayment (@Payload String mensagem){
        System.out.println(mensagem);
    }
}
