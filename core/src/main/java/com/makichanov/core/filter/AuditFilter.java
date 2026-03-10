package com.makichanov.core.filter;

import com.makichanov.ars.data.message.AuditMessage;
import com.makichanov.core.messaging.AuditDataMessageSender;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditFilter extends OncePerRequestFilter {
    private final AuditDataMessageSender messageSender;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
        AuditMessage auditMessage = AuditMessage.builder()
                .endpoint(request.getRequestURI())
                .date(LocalDateTime.now().toString())
                .statusCode(response.getStatus())
                .build();
        log.info("Registered audit data, {}", auditMessage);
        messageSender.sendAuditData(auditMessage);
    }
}