package com.dbccompany.processamentocartao.enumTemplates;

import lombok.Getter;

@Getter
public enum SituacoesUsuario implements TemplateSituations {
    COMPRA("compra-produto.ftl");

    private final String templateName;

    SituacoesUsuario(String templateName) {
        this.templateName = templateName;
    }
}
