package com.dbccompany.useranalysis.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InfoExpose {
    private String productName;
    private Integer soldNumber;
    private String soldDay;
    private double totalAmount;
}
