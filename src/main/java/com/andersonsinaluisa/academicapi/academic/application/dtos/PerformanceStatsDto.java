package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class PerformanceStatsDto {
    public String courseId;
    public String subjectId;
    public String academicYearId;
    public Double average;
    public Double highest;
    public Double lowest;
    public Map<String, Long> distribution;
}

