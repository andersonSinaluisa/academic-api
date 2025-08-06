package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.behavior;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name="disciplinary_action")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class DisciplinaryActionTable {
    @Id
    public Long id;
    public Long studentId;
    public LocalDate date;
    public String action;
    public String description;
}

