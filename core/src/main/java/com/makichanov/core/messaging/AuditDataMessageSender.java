package com.makichanov.core.messaging;

import com.makichanov.messaging.document.AuditData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuditDataMessageSender {
    private final JmsTemplate jmsTemplate;
    private final Queue queue;

    public void sendAuditData(AuditData auditData) {
        log.info("Audit data {} is sending to destination point {}", auditData, queue);
        jmsTemplate.convertAndSend(queue, auditData);
    }
}
