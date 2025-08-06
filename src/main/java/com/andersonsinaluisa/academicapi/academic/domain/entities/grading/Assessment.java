package com.andersonsinaluisa.academicapi.academic.domain.entities.grading;

import lombok.*;

/**
 * Basic information about an assessment belonging to a subject and term.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assessment {
    public Long id;
    public Long subjectId;
    public Long termId;
    public Long assessmentTypeId;
    public Double maxScore;
}
