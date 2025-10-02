package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TeacherAssignmentOutputDto {
    public Long id;
    public Long teacherId;
    public Long courseId;
    public Long subjectId;
    public Long schoolYearId;
    public LocalDateTime createdAt;
}

