package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AssessmentDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.assessment.Assessment;

public class AssessmentMapper {
    public static Assessment fromDtoToDomain(AssessmentDto dto) {
        return Assessment.builder()
                .id(dto.id)
                .subjectId(dto.subjectId)
                .teacherId(dto.teacherId)
                .academicYearId(dto.academicYearId)
                .title(dto.title)
                .description(dto.description)
                .assessmentTypeId(dto.assessmentTypeId)
                .maxScore(dto.maxScore)
                .date(dto.date)
                .build();
    }

    public static AssessmentDto fromDomainToDto(Assessment assessment) {
        return AssessmentDto.builder()
                .id(assessment.id)
                .subjectId(assessment.subjectId)
                .teacherId(assessment.teacherId)
                .academicYearId(assessment.academicYearId)
                .title(assessment.title)
                .description(assessment.description)
                .assessmentTypeId(assessment.assessmentTypeId)
                .maxScore(assessment.maxScore)
                .date(assessment.date)
                .build();
    }
}

