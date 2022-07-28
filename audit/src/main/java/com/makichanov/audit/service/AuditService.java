package com.makichanov.audit.service;

import com.makichanov.audit.model.AuditData;
import com.makichanov.audit.repository.AuditDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditDataRepository repository;

    public List<AuditData> readAll(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        return repository.findAll(pageRequest)
                .getContent();
    }

    public AuditData create(AuditData auditData) {
        return repository.save(auditData);
    }
}
