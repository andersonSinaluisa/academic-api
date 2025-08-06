package com.andersonsinaluisa.academicapi.academic.domain.entities.attendance;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    public Long id;
    public String subjectId;
    public Long studentId;
    public LocalDate date;
    public String status;
    public String observation;
}

