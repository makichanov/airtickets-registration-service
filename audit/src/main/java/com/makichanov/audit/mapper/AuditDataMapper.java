package com.makichanov.audit.mapper;

import com.makichanov.audit.response.AuditDataResponse;
import com.makichanov.messaging.document.AuditData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditDataMapper {
    AuditDataResponse map(AuditData auditData);

    List<AuditDataResponse> map(List<AuditData> auditData);
}
