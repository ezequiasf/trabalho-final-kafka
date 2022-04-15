package com.dbccompany.processamentocartao.templateObjects;

import com.dbccompany.processamentocartao.enumTemplates.TemplateSituations;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
public abstract class TemplateObject {
    private HashMap<String,Object> DADOS = new HashMap<>();
    public final static String EMAIL_EMPRESA = "ezequias.barros@dbccompany.com.br";
    public abstract String getTemplate(TemplateSituations situacao);
}
