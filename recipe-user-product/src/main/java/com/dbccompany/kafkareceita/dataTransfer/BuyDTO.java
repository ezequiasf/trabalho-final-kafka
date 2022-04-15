package com.dbccompany.kafkareceita.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTO {
    private String userEmail;
    private String fullName;
    private String productId;
    private Integer qntItens;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cvv;
}
