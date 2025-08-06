package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GradingSystemDto {
    public Long id;
    public String name;
    public String description;
    public Integer numberOfTerms;
    public Double passingScore;
}

