package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.grading;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="grading_term")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class GradingTermTable {
    @Id
    public Long id;
    public Long gradingSystemId;
    public String name;
    public Integer order;
    public Double weight;
}

