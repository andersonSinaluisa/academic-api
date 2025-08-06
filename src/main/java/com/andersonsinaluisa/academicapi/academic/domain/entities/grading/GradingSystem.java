package com.andersonsinaluisa.academicapi.academic.domain.entities.grading;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradingSystem {
    public Long id;
    public String name;
    public String description;
    public Integer numberOfTerms;
    public Double passingScore;
}

