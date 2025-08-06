package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.grading;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("grade")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class GradeEntity {
    @Id
    public Long id;
    public Long assessmentId;
    public Long studentId;
    public Long subjectId;
    public Long termId;
    public Long assessmentTypeId;
    public Double score;
    public String feedback;
}
