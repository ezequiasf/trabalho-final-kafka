package com.dbccompany.kafkareceita.controller;

import com.dbccompany.kafkareceita.dataTransfer.BuyDTO;
import com.dbccompany.kafkareceita.dataTransfer.ProductFormedDTO;
import com.dbccompany.kafkareceita.exceptions.ObjectNotFoundException;
import com.dbccompany.kafkareceita.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @ApiOperation(value = "Retorna um produto pelo seu Nome.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Produto retornado com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findByProductName")
    public List<ProductFormedDTO> findByProductNameContainingIgnoreCase(@RequestParam("productName") String productName) {
        return productService.findByProductNameContainingIgnoreCase(productName);
    }

    @ApiOperation(value = "Ordena os produtos pelo numero de clicks.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ordenação de produtos retornado com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/orderForNumberClicks")
    public Page<ProductFormedDTO> orderByNumberClicks(Integer paginaSolicitada, Integer tamanhoPagina) {
        return productService.orderByNumberClicks(paginaSolicitada, tamanhoPagina);
    }

    @PostMapping("/buy")
    public void buy(@RequestBody BuyDTO buy) throws JsonProcessingException, ObjectNotFoundException {
        productService.buy(buy);
    }
}
