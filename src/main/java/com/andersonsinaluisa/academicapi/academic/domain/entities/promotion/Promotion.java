package com.andersonsinaluisa.academicapi.academic.domain.entities.promotion;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Promotion {
    public Long id;
    public Long studentId;
    public String academicYearId;
    public Double finalAverage;
    public PromotionStatus status;
    public LocalDate generatedAt;
}
