package com.dbccompany.logapi.dataTransfer;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogDTO {
    private String text;
    private TypeLog type;
    private LocalDateTime operationDate;
}
