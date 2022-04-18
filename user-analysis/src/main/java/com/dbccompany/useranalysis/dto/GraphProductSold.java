package com.dbccompany.useranalysis.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GraphProductSold {
    private String productName;
    private Integer soldNumber;
}
