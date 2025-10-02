package com.andersonsinaluisa.academicapi.academic.application.dtos;

import com.andersonsinaluisa.academicapi.persons.application.dtos.TeacherOutputDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
public class TeacherAssignmentOutputManyDto{
    public Long id;
    public Long teacherId;
    public TeacherOutputDto teacher;
    public List<CourseSubject> listAssignment;
    public LocalDateTime createdAt;

}
