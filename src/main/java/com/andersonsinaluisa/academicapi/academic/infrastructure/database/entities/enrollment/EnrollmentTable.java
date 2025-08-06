package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.enrollment;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name="enrollment")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class EnrollmentTable {
    @Id
    public Long id;
    public Long studentId;
    public String courseId;
    public String academicYearId;
    public LocalDate enrollmentDate;
    public String status;
}

