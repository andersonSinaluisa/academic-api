package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PlanningInputDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.PlanningOutputDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.teacher.TeacherPlanning;

public class PlanningMapper {
    public static TeacherPlanning toDomain(PlanningInputDto dto) {
        return TeacherPlanning.builder()
                .teacherId(dto.teacherId)
                .subjectId(dto.subjectId)
                .courseId(dto.courseId)
                .schoolYearId(dto.schoolYearId)
                .topic(dto.topic)
                .description(dto.description)
                .week(dto.week)
                .build();
    }

    public static PlanningOutputDto toOutputDto(TeacherPlanning planning) {
        return PlanningOutputDto.builder()
                .id(planning.id)
                .teacherId(planning.teacherId)
                .subjectId(planning.subjectId)
                .courseId(planning.courseId)
                .schoolYearId(planning.schoolYearId)
                .topic(planning.topic)
                .description(planning.description)
                .week(planning.week)
                .createdAt(planning.createdAt)
                .build();
    }
}
