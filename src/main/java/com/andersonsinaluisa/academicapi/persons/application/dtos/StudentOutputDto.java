package com.andersonsinaluisa.academicapi.persons.application.dtos;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class StudentOutputDto {
    public Long id;
    public String fistName;
    public String lastName;
    public String phone;
    public LocalDate birthDate;
    public String uuidUser;
    public String address;
    public String identification;
    public String nacionality;
    public String gender;
    public String image;
    public LocalDate createdAt;
}
