package com.andersonsinaluisa.academicapi.academic.domain.entities.grading;

import lombok.*;

/**
 * Represents the final grade of a student for a subject in a school year.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinalGrade {
    public Long studentId;
    public Long subjectId;
    public Long schoolYearId;
    public Double finalScore;
    public boolean passed;
}
