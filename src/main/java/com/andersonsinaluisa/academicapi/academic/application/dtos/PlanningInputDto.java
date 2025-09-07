package com.andersonsinaluisa.academicapi.academic.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanningInputDto {
    @NotNull
    public Long teacherId;
    @NotNull
    public Long subjectId;
    @NotNull
    public Long courseId;
    @NotNull
    public Long schoolYearId;
    @NotBlank
    public String topic;
    @NotBlank
    public String description;
    @NotNull
    public Integer week;
}
