package com.andersonsinaluisa.academicapi.academic.application.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PromotionActInputDto {
    @NotNull
    public String courseId;
    @NotNull
    public String academicYearId;
    @NotNull
    public Long generatedBy;
}
