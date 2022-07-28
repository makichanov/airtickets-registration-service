package com.makichanov.core.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditData {
    private String id;

    private String endpoint;

    private String date;

    private int statusCode;
}
