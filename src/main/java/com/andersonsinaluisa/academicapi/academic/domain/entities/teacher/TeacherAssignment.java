package com.andersonsinaluisa.academicapi.academic.domain.entities.teacher;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Assignment of a teacher to a subject and course for a given school year.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAssignment {
    public Long id;
    public Long teacherId;
    public Long courseId;
    public Long subjectId;
    public Long schoolYearId;
    public LocalDateTime createdAt;
}
