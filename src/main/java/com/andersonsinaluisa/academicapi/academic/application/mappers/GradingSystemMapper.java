package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.GradingSystemDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.grading.GradingSystem;

public class GradingSystemMapper {
    public static GradingSystem fromDtoToDomain(GradingSystemDto dto) {
        return GradingSystem.builder()
                .id(dto.id)
                .name(dto.name)
                .description(dto.description)
                .numberOfTerms(dto.numberOfTerms)
                .passingScore(dto.passingScore)
                .build();
    }

    public static GradingSystemDto fromDomainToDto(GradingSystem gradingSystem) {
        return GradingSystemDto.builder()
                .id(gradingSystem.id)
                .name(gradingSystem.name)
                .description(gradingSystem.description)
                .numberOfTerms(gradingSystem.numberOfTerms)
                .passingScore(gradingSystem.passingScore)
                .build();
    }
}

