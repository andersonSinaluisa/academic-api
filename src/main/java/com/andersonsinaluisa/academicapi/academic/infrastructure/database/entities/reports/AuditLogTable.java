package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.reports;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name="audit_log")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class AuditLogTable {
    @Id
    public Long id;
    public String entity;
    public Long entityId;
    public String action;
    public String changedBy;
    public LocalDateTime changedAt;
    public String oldValue;
    public String newValue;
}

