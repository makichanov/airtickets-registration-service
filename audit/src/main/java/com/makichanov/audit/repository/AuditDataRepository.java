package com.makichanov.audit.repository;

import com.makichanov.messaging.document.AuditData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditDataRepository extends MongoRepository<AuditData, String> {
}