package com.andersonsinaluisa.academicapi.academic.domain.entities.grading;

import lombok.*;

/**
 * Represents a score obtained by a student for a particular assessment within a term.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    public Long id;
    public Long assessmentId;
    public Long studentId;
    public Long subjectId;
    public Long termId;
    public Long assessmentTypeId;
    public Double score;
    public String feedback;
}
