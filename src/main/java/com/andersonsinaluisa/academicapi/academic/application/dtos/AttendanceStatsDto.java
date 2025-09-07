package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceStatsDto {
    public String courseId;
    public String academicYearId;
    public Long totalStudents;
    public Double present;
    public Double absent;
    public Double justified;
}

