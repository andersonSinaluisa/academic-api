package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportCardDto {
    public Long id;
    public String academicYearId;
    public Long studentId;
    public Double averageScore;
    public String status;
}

