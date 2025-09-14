package com.andersonsinaluisa.academicapi.persons.application.dtos;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class TeacherOutputDto {
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
}
