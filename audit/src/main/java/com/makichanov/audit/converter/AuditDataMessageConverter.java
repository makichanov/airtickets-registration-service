package com.makichanov.audit.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makichanov.ars.data.message.AuditMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditDataMessageConverter implements MessageConverter {
    private final ObjectMapper objectMapper;

    public AuditDataMessageConverter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    @NonNull
    public Message toMessage(@NonNull Object object, @NonNull Session session) throws JMSException, MessageConversionException {
        AuditMessage auditMessage = (AuditMessage) object;

        String jsonAuditData = null;
        try {
            jsonAuditData = objectMapper.writeValueAsString(auditMessage);
        } catch (JsonProcessingException e) {
            log.error("Error converting AuditData to json", e);
        }

        TextMessage message = session.createTextMessage();
        message.setText(jsonAuditData);

        return message;
    }

    @Override
    @NonNull
    public Object fromMessage(@NonNull Message message) throws JMSException, MessageConversionException {
        String jsonAuditData = ((TextMessage) message).getText();
        AuditMessage auditData = null;
        try {
            auditData = objectMapper.readValue(jsonAuditData, AuditMessage.class);
        } catch (JsonProcessingException e) {
            log.error("Error converting json to AuditData object", e);
        }
        return auditData;
    }
}