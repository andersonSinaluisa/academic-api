package com.andersonsinaluisa.academicapi.academic.domain.entities.assessment;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assessment {
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

