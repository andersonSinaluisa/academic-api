package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PlanningOutputDto {
    public Long id;
    public Long teacherId;
    public Long subjectId;
    public Long courseId;
    public Long schoolYearId;
    public String topic;
    public String description;
    public Integer week;
    public LocalDateTime createdAt;
}
