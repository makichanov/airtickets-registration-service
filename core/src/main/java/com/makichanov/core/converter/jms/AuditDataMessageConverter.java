package com.makichanov.core.converter.jms;

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

    public AuditDataMessageConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @NonNull
    public Message toMessage(@NonNull Object object, @NonNull Session session) throws JMSException, MessageConversionException {
        if (!(object instanceof AuditMessage auditMessage)) {
            throw new MessageConversionException("Cannot convert [" + object + "] to AuditData");
        }

        String jsonAuditMessage;
        try {
            jsonAuditMessage = objectMapper.writeValueAsString(auditMessage);
        } catch (JsonProcessingException e) {
            throw new MessageConversionException("Error converting AuditData to json", e);
        }

        TextMessage message = session.createTextMessage();
        message.setText(jsonAuditMessage);

        return message;
    }

    @Override
    public Object fromMessage(@NonNull Message message) throws JMSException, MessageConversionException {
        if (!(message instanceof TextMessage textMessage)) {
            throw new MessageConversionException("Message is not a TextMessage");
        }

        String jsonAuditData = textMessage.getText();

        if (jsonAuditData == null) {
            return null;
        }

        try {
            return objectMapper.readValue(jsonAuditData, AuditMessage.class);
        } catch (JsonProcessingException e) {
            throw new MessageConversionException("Error converting json to AuditMessage object", e);
        }
    }
}