package com.dbccompany.useranalysis.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GraphDaySold {
    private String soldDay;
    private Integer soldNumber;
}
