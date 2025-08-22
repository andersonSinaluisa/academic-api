package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PromotionResultDto {
    public Long studentId;
    public String academicYearId;
    public Double finalAverage;
    public String status;
    public LocalDate generatedAt;
}
