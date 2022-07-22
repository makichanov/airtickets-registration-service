package com.makichanov.audit.consumer;

import com.makichanov.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@EnableJms
@Component
@Slf4j
@RequiredArgsConstructor
public class AuditDataConsumer {
    private final AuditService auditService;

    @JmsListener(destination = "ars-queue")
    public void listener(String message) {
        log.info(message);
    }
}
