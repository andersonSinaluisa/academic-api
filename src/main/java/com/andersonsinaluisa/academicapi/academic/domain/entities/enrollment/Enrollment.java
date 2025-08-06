package com.andersonsinaluisa.academicapi.academic.domain.entities.enrollment;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    public Long id;
    public Long studentId;
    public String courseId;
    public String academicYearId;
    public LocalDate enrollmentDate;
    public String status;
}

