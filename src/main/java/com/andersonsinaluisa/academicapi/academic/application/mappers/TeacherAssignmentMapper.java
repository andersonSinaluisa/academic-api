package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentInputDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentOutputDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.teacher.TeacherAssignment;

public class TeacherAssignmentMapper {
    public static TeacherAssignment toDomain(TeacherAssignmentInputDto dto) {
        return TeacherAssignment.builder()
                .teacherId(dto.teacherId)
                .courseId(dto.courseId)
                .subjectId(dto.subjectId)
                .schoolYearId(dto.schoolYearId)
                .build();
    }

    public static TeacherAssignmentOutputDto toOutputDto(TeacherAssignment assignment) {
        return TeacherAssignmentOutputDto.builder()
                .id(assignment.id)
                .teacherId(assignment.teacherId)
                .courseId(assignment.courseId)
                .subjectId(assignment.subjectId)
                .schoolYearId(assignment.schoolYearId)
                .createdAt(assignment.createdAt)
                .build();
    }
}
