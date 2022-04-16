package com.dbccompany.logapi.entity;

import com.dbccompany.logapi.dataTransfer.TypeLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "logs")
public class LogEntity {
    @Id
    private String objectId;
    private String text;
    private TypeLog type;
    private LocalDateTime operationDate;
}
