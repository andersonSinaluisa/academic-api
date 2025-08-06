package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.assessment;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="assessment_type")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class AssessmentTypeTable {
    @Id
    public Long id;
    public String name;
    public Double weight;
}

