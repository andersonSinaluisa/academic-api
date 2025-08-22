package com.andersonsinaluisa.academicapi.academic.domain.entities.promotion;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PromotionAct {
    public Long id;
    public String courseId;
    public String academicYearId;
    public Long generatedBy;
    public LocalDate generatedAt;
}
