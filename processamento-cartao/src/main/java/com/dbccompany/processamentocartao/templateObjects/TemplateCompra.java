package com.dbccompany.processamentocartao.templateObjects;

import com.dbccompany.processamentocartao.enumTemplates.SituacoesCompra;
import com.dbccompany.processamentocartao.enumTemplates.TemplateSituations;

public class TemplateCompra extends TemplateObject {

    @Override
    public String getTemplate(TemplateSituations situacao) {
        return ((SituacoesCompra) situacao).getTemplateName();
    }
}
