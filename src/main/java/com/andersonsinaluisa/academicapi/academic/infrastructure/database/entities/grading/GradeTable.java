package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.grading;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="grade")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class GradeTable {
    @Id
    public Long id;
    public Long assessmentId;
    public Long studentId;
    public Double score;
    public String feedback;
}

