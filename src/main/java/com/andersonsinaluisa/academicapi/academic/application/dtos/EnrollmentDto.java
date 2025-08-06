package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EnrollmentDto {
    public Long id;
    public Long studentId;
    public String courseId;
    public String academicYearId;
    public LocalDate enrollmentDate;
    public String status;
}

