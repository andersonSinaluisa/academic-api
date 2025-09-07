package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PromotionActDto {
    public Long id;
    public String courseId;
    public String academicYearId;
    public Long generatedBy;
    public LocalDate generatedAt;
}
