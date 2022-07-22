package com.makichanov.audit.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class AuditData {

    @Id
    private String id;

    private String endpoint;

    private LocalDateTime date;

    private int responseCode;

}
