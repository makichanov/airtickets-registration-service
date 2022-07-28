package com.makichanov.audit.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@Schema(description = "Audit data")
public class AuditData {

    @Schema(description = "Id of audit data")
    @Id
    private String id;

    @Schema(description = "Endpoint address")
    private String endpoint;

    @Schema(description = "Date of request")
    private String date;

    @Schema(description = "Request response code")
    private int statusCode;
}
