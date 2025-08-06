package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.reports;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name="term")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TermTable {
    @Id
    public Long id;
    public String academicYearId;
    public String name;
    public LocalDate startDate;
    public LocalDate endDate;
}

