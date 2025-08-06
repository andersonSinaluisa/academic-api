package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AttendanceDto {
    public Long id;
    public String subjectId;
    public Long studentId;
    public LocalDate date;
    public String status;
    public String observation;
}

