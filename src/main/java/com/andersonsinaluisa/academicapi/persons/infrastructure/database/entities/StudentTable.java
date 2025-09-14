package com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Person;
import com.andersonsinaluisa.academicapi.persons.domain.entities.TypePerson;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@Table(name="student")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class StudentTable {

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
    public String uuidCourse;
    public String uuidParallel;
    public String uuidSchoolYear;
}
