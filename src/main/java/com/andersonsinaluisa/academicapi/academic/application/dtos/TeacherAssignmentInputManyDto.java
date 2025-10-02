package com.andersonsinaluisa.academicapi.academic.application.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeacherAssignmentInputManyDto {
    @NotNull
    public Long teacherId;

    public List<CourseSubject> listAssignment;

}
