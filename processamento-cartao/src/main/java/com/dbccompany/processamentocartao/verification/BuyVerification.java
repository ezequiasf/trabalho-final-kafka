package com.dbccompany.processamentocartao.verification;

import com.dbccompany.processamentocartao.dto.InfoBuyDTO;
import com.dbccompany.processamentocartao.enumTemplates.SituacoesCompra;
import com.dbccompany.processamentocartao.templateObjects.TemplateCompra;
import com.dbccompany.processamentocartao.utils.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BuyVerification {

    private final ObjectMapper objectMapper;
    private final EmailUtil emailUtil;

    private boolean checkValidation(String cardNumber) {
        String numbers = cardNumber.replace(" ", "");
        if (!(numbers.length() >= 13 && numbers.length() <= 16)) {
            return false;
        }
        List<Integer> numbersInt = Arrays.stream(numbers.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        for (int i = 0; i < numbersInt.size(); i++) {
            if (i % 2 == 0) {
                int validNumber = numbersInt.get(i) * 2;
                int resultNumber;
                if (validNumber > 9) {
                    resultNumber = validNumber % 10 + 1;
                } else {
                    resultNumber = validNumber;
                }
                numbersInt.set(i, resultNumber);
            }
        }
        int sumResult = numbersInt.stream().reduce(Integer::sum).orElse(0);
        return sumResult % 10 == 0;
    }

    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "verify",
            containerFactory = "listenerContainerFactory")
    public void verifyPayment(@Payload String msg) throws JsonProcessingException {
        System.out.println(msg);
        InfoBuyDTO buy = objectMapper.readValue(msg, InfoBuyDTO.class);

        if (checkValidation(buy.getCardNumber())) {
            emailUtil.enviarEmailTemplate(TemplateCompra.EMAIL_EMPRESA
                    , buy.getEmail(), "Compra"
                    , new TemplateCompra(buy)
                    , SituacoesCompra.SUCESSO);
        } else {
            emailUtil.enviarEmailTemplate(TemplateCompra.EMAIL_EMPRESA
                    , buy.getEmail(), "Compra"
                    , new TemplateCompra(buy)
                    , SituacoesCompra.FALHA);
        }
    }
}
