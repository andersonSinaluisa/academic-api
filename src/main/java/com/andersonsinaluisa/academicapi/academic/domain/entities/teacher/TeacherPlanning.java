package com.andersonsinaluisa.academicapi.academic.domain.entities.teacher;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Teaching planning created by a teacher for a subject.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherPlanning {
    public Long id;
    public Long teacherId;
    public Long subjectId;
    public Long courseId;
    public Long schoolYearId;
    public String topic;
    public String description;
    public Integer week;
    public LocalDateTime createdAt;
}
