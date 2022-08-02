package com.makichanov.core.provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Positive;
import java.util.List;

@FeignClient(name = "ars-audit", url = "localhost:8082")
public interface AuditDataProvider {

    // TODO replace Object with AuditData after merging PR
    @GetMapping("/audit")
    List<Object> readAuditData(@RequestParam(name = "page") @Positive Integer pageNum,
                               @RequestParam(name = "pageSize") @Positive Integer pageSize);

}
