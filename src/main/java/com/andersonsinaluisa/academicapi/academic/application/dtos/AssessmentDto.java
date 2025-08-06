package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AssessmentDto {
    public Long id;
    public String subjectId;
    public String teacherId;
    public String academicYearId;
    public String title;
    public String description;
    public Long assessmentTypeId;
    public Double maxScore;
    public LocalDate date;
}

