package com.dbccompany.logapi.service;

import com.dbccompany.logapi.dataTransfer.LogDTO;
import com.dbccompany.logapi.entity.LogEntity;
import com.dbccompany.logapi.repository.LogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogService {
    private final ObjectMapper objectMapper;
    private final LogRepository logRepository;


    public List<LogDTO> readAllLogs() {
        log.info("chamada de método:: Encontrar todos os Logs.");
        return convertList(logRepository.findAll());
    }

    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "logRecipe",
            containerFactory = "listenerContainerFactory")
    public void saveLog(@Payload String message) throws JsonProcessingException {
        LogDTO logDTO = objectMapper.readValue(message, LogDTO.class);
        insert(logDTO);
        log.info("Object Log:: " + logDTO);
    }

    public void insert(LogDTO log) {
        LogEntity logEntity = objectMapper.convertValue(log, LogEntity.class);
        logRepository.save(logEntity);
    }

    public List<LogDTO> convertList(List<LogEntity> logs) {
        log.info("iniciando conversão de lista...");
        return logs.stream()
                .map(l -> objectMapper.convertValue(l, LogDTO.class))
                .collect(Collectors.toList());
    }
}
