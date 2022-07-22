package com.makichanov.audit.service;

import com.makichanov.audit.document.AuditData;
import com.makichanov.audit.repository.AuditDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditDataRepository repository;

    public List<AuditData> readAll() {
        return repository.findAll();
    }

    public AuditData create(AuditData auditData) {
        return repository.save(auditData);
    }
}
