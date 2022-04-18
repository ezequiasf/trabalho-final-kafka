package com.dbccompany.useranalysis.controller;

import com.dbccompany.useranalysis.dto.GraphDaySold;
import com.dbccompany.useranalysis.dto.GraphProductSold;
import com.dbccompany.useranalysis.dto.GraphProductTotalAmount;
import com.dbccompany.useranalysis.service.InfoAnalyzeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/infoDashboard")
@RequiredArgsConstructor
public class InfoAnalyzeController {
    private final InfoAnalyzeService infoAnalyzeService;

    @ApiOperation(value = "Retorna uma lista de informações de uso dos usuários da plataforma de receitas.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Os usuários foram listadas com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema."),})
    @GetMapping("/productAndSoldNumber")
    public List<GraphProductSold> groupByProductNameAndSoldNumber() {
        return infoAnalyzeService.groupByProductNameAndSoldNumber();
    }

    @ApiOperation(value = "Retorna uma lista de informações de uso dos usuários da plataforma de receitas.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Os usuários foram listadas com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema."),})
    @GetMapping("/soldDay")
    public List<GraphDaySold> groupBySoldDay() {
        return infoAnalyzeService.groupBySoldDay();
    }

    @ApiOperation(value = "Retorna uma lista de informações de uso dos usuários da plataforma de receitas.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Os usuários foram listadas com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema."),})
    @GetMapping("/totalAmount")
    public List<GraphProductTotalAmount> groupByTotalAmount() {
        return infoAnalyzeService.groupByTotalAmount();
    }
}
