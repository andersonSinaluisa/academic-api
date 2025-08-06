package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.grading;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="grading_system")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class GradingSystemTable {
    @Id
    public Long id;
    public String name;
    public String description;
    public Integer numberOfTerms;
    public Double passingScore;
}

