package com.dbccompany.processamentocartao.templateObjects;

import com.dbccompany.processamentocartao.enumTemplates.TemplateSituations;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
public abstract class TemplateObject {
    public final static String EMAIL_EMPRESA = "ezequiasbarros1997@gmail.com";
    private HashMap<String, Object> DADOS = new HashMap<>();

    public abstract String getTemplate(TemplateSituations situacao);
}
