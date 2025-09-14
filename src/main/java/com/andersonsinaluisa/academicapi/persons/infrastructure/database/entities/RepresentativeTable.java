package com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name="representative")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class RepresentativeTable  {
    @Id
    public Long id;
    public String firstName;
    public String lastName;
    public String phone;
    public LocalDate birthDate;
    public String uuidUser;
    public String address;
    public String identification;
    public String nationality;
    public String gender;
    public String image;
    public boolean deleted;
    public LocalDate createdAt;
    public LocalDate updatedAt;
    public LocalDate deletedAt;
    public String tenantId;
    public boolean isActive;
}
