package com.makichanov.messaging.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class AuditData {

    @Id
    private String id;

    private String endpoint;

    private String date;

    private String responseCode;

}
