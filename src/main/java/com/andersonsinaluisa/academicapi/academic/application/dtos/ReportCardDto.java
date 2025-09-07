package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ReportCardDto {
    public Long id;
    public String academicYearId;
    public Long studentId;
    public Double averageScore;
    public String status;
    public List<SubjectDto> subjects;

    @Data
    @Builder
    public static class SubjectDto {
        public String subjectId;
        public String name;
        public Map<String, Double> scores;
    }
}

