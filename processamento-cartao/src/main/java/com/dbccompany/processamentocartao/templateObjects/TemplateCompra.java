package com.dbccompany.processamentocartao.templateObjects;

import com.dbccompany.processamentocartao.dto.InfoBuyDTO;
import com.dbccompany.processamentocartao.enumTemplates.SituacoesCompra;
import com.dbccompany.processamentocartao.enumTemplates.TemplateSituations;

import java.util.HashMap;

public class TemplateCompra extends TemplateObject {

    public TemplateCompra (InfoBuyDTO infoBuy){
        HashMap<String, Object> dadosCompra = new HashMap<>();
        double total = infoBuy.getQntItens()* infoBuy.getPrice() + 15;
        dadosCompra.put("productName", infoBuy.getProductName());
        dadosCompra.put("username", infoBuy.getUsername());
        dadosCompra.put("qntItems", infoBuy.getQntItens());
        dadosCompra.put("total", total);
        setDADOS(dadosCompra);
    }

    @Override
    public String getTemplate(TemplateSituations situacao) {
        return ((SituacoesCompra) situacao).getTemplateName();
    }
}
