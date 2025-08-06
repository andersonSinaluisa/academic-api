package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.reports;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="report_card")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ReportCardTable {
    @Id
    public Long id;
    public String academicYearId;
    public Long studentId;
    public Double averageScore;
    public String status;
}

