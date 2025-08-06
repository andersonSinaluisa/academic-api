package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.behavior;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="behavior_report")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class BehaviorReportTable {
    @Id
    public Long id;
    public Long studentId;
    public Long termId;
    public Double score;
    public String observations;
}

