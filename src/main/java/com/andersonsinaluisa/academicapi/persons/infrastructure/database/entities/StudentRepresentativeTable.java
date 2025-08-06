package com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="student_representative")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class StudentRepresentativeTable {
    @Id
    public Long id;
    public String relationShip;
    public Long studentId;
    public Long representativeId;
    public boolean isMain;
    public String tenantId;

}
