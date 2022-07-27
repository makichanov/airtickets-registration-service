package com.makichanov.audit.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Audit data")
public class AuditDataResponse {

    @Schema(description = "Id of audit data")
    private String id;

    @Schema(description = "Endpoint address")
    private String endpoint;

    @Schema(description = "Date of request")
    private String date;

    @Schema(description = "Request response code")
    private int statusCode;
}
