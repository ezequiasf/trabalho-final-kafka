package com.dbccompany.useranalysis.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GraphProductTotalAmount {
    private String productName;
    private double totalAmount;
}
