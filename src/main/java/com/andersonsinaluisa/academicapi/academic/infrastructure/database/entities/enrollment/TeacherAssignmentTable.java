package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.enrollment;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name="teacher_assignment")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TeacherAssignmentTable {
    @Id
    public Long id;
    public Long teacherId;
    public String subjectId;
    public String courseId;
    public Long academicYearId;
    public LocalDateTime createdAt;
}

