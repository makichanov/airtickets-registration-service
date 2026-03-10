package com.makichanov.ars.data.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditMessage {
    private String id;

    private String endpoint;

    private String date;

    private int statusCode;
}
