package com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name="teacher")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TeacherTable  {
    @Id
    public Long id;
    public String firtName;
    public String lastName;
    public String phone;
    public LocalDate birthDate;
    public String uuidUser;
    public String address;
    public String identification;
    public String nacionality;
    public String gender;
    public String image;
    public boolean deleted;
    public LocalDate createdAt;
    public LocalDate updatedAt;
    public LocalDate deletedAt;
    public String tenantId;

}
