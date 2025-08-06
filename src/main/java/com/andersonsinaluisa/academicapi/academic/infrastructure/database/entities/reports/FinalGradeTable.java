package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.reports;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="final_grade")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class FinalGradeTable {
    @Id
    public Long id;
    public String academicYearId;
    public Long studentId;
    public Double averageScore;
    public String status;
}

