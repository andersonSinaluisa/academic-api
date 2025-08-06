package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinalGradeDto {
    private Long studentId;
    private Long subjectId;
    private Long schoolYearId;
    private Double finalScore;
    private boolean isPassed;
}
