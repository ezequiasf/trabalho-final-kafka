package com.dbccompany.processamentocartao.enumTemplates;

import lombok.Getter;

@Getter
public enum SituacoesCompra implements TemplateSituations {
    SUCESSO("compra-sucesso.ftl"),
    FALHA("compra-falha.ftl");

    private final String templateName;

    SituacoesCompra(String templateName) {
        this.templateName = templateName;
    }
}
