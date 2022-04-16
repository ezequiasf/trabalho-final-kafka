package com.dbccompany.useranalysis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "buy_analysis")
public class InfoAnalyzeEntity {
    @Id
    private String objectId;
    private String productName;
    private Integer soldNumber;
    private double totalAmount;
    private String soldDay;
}
