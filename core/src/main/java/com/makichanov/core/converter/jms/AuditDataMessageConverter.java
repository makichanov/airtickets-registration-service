package com.makichanov.core.converter.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makichanov.core.model.request.AuditData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
@Slf4j
public class AuditDataMessageConverter implements MessageConverter {
    private final ObjectMapper objectMapper;

    public AuditDataMessageConverter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        AuditData auditData = (AuditData) object;

        String jsonAuditData = null;
        try {
            jsonAuditData = objectMapper.writeValueAsString(auditData);
        } catch (JsonProcessingException e) {
            log.error("Error converting AuditData to json", e);
        }

        TextMessage message = session.createTextMessage();
        message.setText(jsonAuditData);

        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        String jsonAuditData = ((TextMessage) message).getText();
        AuditData auditData = null;
        try {
            auditData = objectMapper.readValue(jsonAuditData, AuditData.class);
        } catch (JsonProcessingException e) {
            log.error("Error converting json to AuditData object", e);
        }
        return auditData;
    }
}
