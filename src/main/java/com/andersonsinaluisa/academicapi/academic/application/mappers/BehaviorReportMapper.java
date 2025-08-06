package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.BehaviorReportDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.behavior.BehaviorReport;

public class BehaviorReportMapper {
    public static BehaviorReport fromDtoToDomain(BehaviorReportDto dto) {
        return BehaviorReport.builder()
                .id(dto.id)
                .studentId(dto.studentId)
                .termId(dto.termId)
                .score(dto.score)
                .observations(dto.observations)
                .build();
    }

    public static BehaviorReportDto fromDomainToDto(BehaviorReport behavior) {
        return BehaviorReportDto.builder()
                .id(behavior.id)
                .studentId(behavior.studentId)
                .termId(behavior.termId)
                .score(behavior.score)
                .observations(behavior.observations)
                .build();
    }
}

