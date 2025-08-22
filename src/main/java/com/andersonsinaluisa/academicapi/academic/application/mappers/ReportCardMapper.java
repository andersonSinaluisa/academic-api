package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.ReportCardDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.reports.ReportCard;
import com.andersonsinaluisa.academicapi.academic.domain.valueObjects.Score;

import java.util.Collections;

public class ReportCardMapper {
    public static ReportCard fromDtoToDomain(ReportCardDto dto) {
        return ReportCard.builder()
                .id(dto.id)
                .academicYearId(dto.academicYearId)
                .studentId(dto.studentId)
                .averageScore(new Score(dto.averageScore))
                .status(dto.status)
                .build();
    }

    public static ReportCardDto fromDomainToDto(ReportCard reportCard) {
        return ReportCardDto.builder()
                .id(reportCard.id)
                .academicYearId(reportCard.academicYearId)
                .studentId(reportCard.studentId)
                .averageScore(reportCard.averageScore.getValue())
                .status(reportCard.status)
                .subjects(Collections.emptyList())
                .build();
    }
}

