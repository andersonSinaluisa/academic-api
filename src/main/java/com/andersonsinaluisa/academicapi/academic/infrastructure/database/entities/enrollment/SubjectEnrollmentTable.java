package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.enrollment;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="subject_enrollment")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class SubjectEnrollmentTable {
    @Id
    public Long id;
    public Long enrollmentId;
    public String subjectId;
}

