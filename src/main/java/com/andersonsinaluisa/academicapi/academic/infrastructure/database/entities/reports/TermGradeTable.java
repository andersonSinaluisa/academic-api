package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.reports;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="term_grade")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TermGradeTable {
    @Id
    public Long id;
    public Long termId;
    public String subjectId;
    public Long studentId;
    public Double averageScore;
    public String status;
}

