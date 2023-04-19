package com.makichanov.core.controller;

import com.makichanov.core.provider.AuditDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/audit")
public class AuditController {

    private final AuditDataProvider auditDataProvider;

    // TODO replace Object with AuditData after merging PR
    @GetMapping
    public List<Object> readAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") @Positive Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") @Positive Integer pageSize) {
        return auditDataProvider.readAuditData(pageNum, pageSize);
    }

}
