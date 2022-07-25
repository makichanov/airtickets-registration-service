package com.makichanov.audit.consumer;

import com.makichanov.messaging.document.AuditData;
import com.makichanov.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuditDataConsumer {
    private final AuditService auditService;

    @JmsListener(destination = "ars-queue")
    public void listener(Message<AuditData> message) {
        AuditData auditData = message.getPayload();
        log.info(auditData.toString());
        auditService.create(auditData);
    }
}
