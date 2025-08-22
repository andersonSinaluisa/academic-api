package com.andersonsinaluisa.academicapi.academic.application.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherAssignmentInputDto {
    @NotNull
    public Long teacherId;
    @NotNull
    public Long courseId;
    @NotNull
    public Long subjectId;
    @NotNull
    public Long schoolYearId;
}
