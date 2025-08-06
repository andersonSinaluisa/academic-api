package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.assessment;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name="assessment")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class AssessmentTable {
    @Id
    public Long id;
    public String subjectId;
    public Long teacherId;
    public String academicYearId;
    public String title;
    public String description;
    public Long assessmentTypeId;
    public Double maxScore;
    public LocalDate date;
}

