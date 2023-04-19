package com.makichanov.core.controller.filter;

import com.makichanov.core.messaging.AuditDataMessageSender;
import com.makichanov.core.model.request.AuditData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AuditFilter extends OncePerRequestFilter {
    private final AuditDataMessageSender messageSender;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
        AuditData auditData = AuditData.builder()
                .endpoint(request.getRequestURI())
                .date(LocalDateTime.now().toString())
                .statusCode(response.getStatus())
                .build();
        messageSender.sendAuditData(auditData);
    }
}
