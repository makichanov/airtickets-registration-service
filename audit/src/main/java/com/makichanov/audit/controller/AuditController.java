package com.makichanov.audit.controller;

import com.makichanov.audit.mapper.AuditDataMapper;
import com.makichanov.audit.response.AuditDataResponse;
import com.makichanov.audit.service.AuditService;
import com.makichanov.messaging.document.AuditData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/audit", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Audit controller", description = "Read audit data")
public class AuditController {

    private final AuditService auditService;
    private final AuditDataMapper mapper;

    @GetMapping
    @Operation(summary = "Read all audit data",
            description = "Returns audit data by specified page and/or page size values")
    public List<AuditDataResponse> readAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") @Positive Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") @Positive Integer pageSize) {
        List<AuditData> auditData = auditService.readAll(pageNum, pageSize);
        return mapper.map(auditData);
    }

}
