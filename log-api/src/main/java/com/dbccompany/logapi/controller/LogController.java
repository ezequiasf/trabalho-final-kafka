package com.dbccompany.logapi.controller;

import com.dbccompany.logapi.dataTransfer.LogDTO;
import com.dbccompany.logapi.service.LogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;

    @ApiOperation(value = "Retorna uma lista de Logs.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Logs listados com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema."),})
    @GetMapping("/readAllLogs")
    public List<LogDTO> readAllLogs() {
        return logService.readAllLogs();
    }
}
