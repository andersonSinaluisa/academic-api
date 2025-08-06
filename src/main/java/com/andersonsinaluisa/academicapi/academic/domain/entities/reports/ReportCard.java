package com.andersonsinaluisa.academicapi.academic.domain.entities.reports;

import com.andersonsinaluisa.academicapi.academic.domain.valueObjects.Score;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportCard {
    public Long id;
    public String academicYearId;
    public Long studentId;
    public Score averageScore;
    public String status;
}

