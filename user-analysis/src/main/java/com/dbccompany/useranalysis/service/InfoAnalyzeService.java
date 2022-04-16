package com.dbccompany.useranalysis.service;

import com.dbccompany.useranalysis.dto.InfoBuyDTO;
import com.dbccompany.useranalysis.dto.InfoExpose;
import com.dbccompany.useranalysis.entity.InfoAnalyzeEntity;
import com.dbccompany.useranalysis.repository.InfoAnalyzeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InfoAnalyzeService {
    private final InfoAnalyzeRepository infoAnalyzeRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "analysis",
            containerFactory = "listenerContainerFactory")
    public void saveInfoAnalyze(@Payload String msg) throws JsonProcessingException {
        InfoBuyDTO infoBuy = objectMapper.readValue(msg, InfoBuyDTO.class);
        InfoAnalyzeEntity infoEntity = InfoAnalyzeEntity.builder()
                    .productName(infoBuy.getProductName())
                    .soldNumber(infoBuy.getQntItens())
                    .totalAmount(infoBuy.getQntItens() * infoBuy.getPrice())
                    .soldDay(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "br")))
                    .build();
        infoAnalyzeRepository.save(infoEntity);
    }

    public List<InfoExpose> groupByProductNameAndSoldNumber (){
        return infoAnalyzeRepository.groupByProductNameAndSoldNumber().stream()
                .map(inf-> objectMapper.convertValue(inf, InfoExpose.class))
                .collect(Collectors.toList());
    }

    public List<InfoExpose> groupBySoldDay (){
        return infoAnalyzeRepository.groupBySoldDay().stream()
                .map(inf-> objectMapper.convertValue(inf, InfoExpose.class))
                .collect(Collectors.toList());
    }

    public List<InfoExpose> groupByTotalAmount (){
        return infoAnalyzeRepository.groupByTotalAmount().stream()
                .map(inf-> objectMapper.convertValue(inf, InfoExpose.class))
                .collect(Collectors.toList());
    }

}
