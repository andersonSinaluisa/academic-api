package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.grading;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="academic_year_grading")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class AcademicYearGradingTable {
    @Id
    public Long id;
    public String academicYearId;
    public Long gradingSystemId;
}

