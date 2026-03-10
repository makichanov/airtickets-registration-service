package com.makichanov.core.messaging;

import com.makichanov.ars.data.message.AuditMessage;
import jakarta.jms.Queue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuditDataMessageSender {
    private final JmsTemplate jmsTemplate;
    private final Queue queue;

    public void sendAuditData(AuditMessage auditMessage) {
        log.info("Audit data {} is sending to destination point {}", auditMessage, queue);
        jmsTemplate.convertAndSend(queue, auditMessage);
    }
}
