package com.dbccompany.kafkareceita.service;

import com.dbccompany.kafkareceita.dataTransfer.BuyDTO;
import com.dbccompany.kafkareceita.dataTransfer.ProductFormedDTO;
import com.dbccompany.kafkareceita.entity.ProductEntity;
import com.dbccompany.kafkareceita.exceptions.ObjectNotFoundException;
import com.dbccompany.kafkareceita.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ObjectMapper objectMapper;
    private final ProductRepository productRepository;
    private final ProducerService producerService;

    public List<ProductFormedDTO> findByProductNameContainingIgnoreCase(String productName) {
        log.info("Chamada de método service:: Encontrar prudoto pelo nome.");
        return productRepository.findByProductNameContainingIgnoreCase(productName)
                .stream()
                .map(productEntity -> objectMapper.convertValue(productEntity, ProductFormedDTO.class))
                .collect(Collectors.toList());

    }

    public Page<ProductFormedDTO> orderByNumberClicks(Integer paginaSolicitada, Integer tamanhoPagina) {
        log.info("Chamada de método service:: ordenar por numeros de cliques.");
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoPagina, Sort.by("numberClicks").descending());
        return productRepository.findAll(pageable).map(productEntity -> objectMapper.convertValue(productEntity, ProductFormedDTO.class));
    }

    public void buy(BuyDTO buy) throws JsonProcessingException, ObjectNotFoundException {

        ProductEntity productEntity = productRepository.findById(buy.getProductId()).orElseThrow(() -> new ObjectNotFoundException("Product not found!"));
        if (!(productEntity.getStock() >= buy.getQntItens())) {
            throw new ObjectNotFoundException("unavailable quantity!");
        }
        productEntity.setStock(productEntity.getStock() - buy.getQntItens());
        productRepository.save(productEntity);
        producerService.sendMessage(buy);
    }
}
