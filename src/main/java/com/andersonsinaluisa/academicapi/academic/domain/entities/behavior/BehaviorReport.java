package com.andersonsinaluisa.academicapi.academic.domain.entities.behavior;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BehaviorReport {
    public Long id;
    public Long studentId;
    public Long termId;
    public Double score;
    public String observations;
}

