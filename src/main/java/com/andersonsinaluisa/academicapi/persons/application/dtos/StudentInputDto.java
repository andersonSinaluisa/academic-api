package com.andersonsinaluisa.academicapi.persons.application.dtos;

import com.andersonsinaluisa.academicapi.persons.domain.entities.TypePerson;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.*;

import java.time.LocalDate;
import java.util.List;

public class StudentInputDto {
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
    public String uuidCurrentParallel;
    public String uuidCurrentSchoolYear;
    public String uuidCurrentSection;
}
