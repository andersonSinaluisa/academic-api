package com.andersonsinaluisa.academicapi.persons.application.mappers;

import com.andersonsinaluisa.academicapi.persons.application.dtos.TeacherInputDto;
import com.andersonsinaluisa.academicapi.persons.application.dtos.TeacherOutputDto;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.BirthDate;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.FullName;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.Identification;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.PhoneNumber;

public class TeacherMapper {
    public static Teacher fromDtoToDomain(TeacherInputDto dto){
        return Teacher.builder()
                .address(dto.address)
                .birthDate(new BirthDate(dto.birthDate))
                .fullName(new FullName(dto.firstName,dto.lastName))
                .gender(dto.gender)
                .identification(new Identification(dto.identification))
                .image(dto.image)
                .nacionality(dto.nationality)
                .phone(new PhoneNumber(dto.phone))
                .uuidUser(dto.uuidUser)
                .build();
    }

    public static TeacherOutputDto fromDomainToDto(Teacher teacher){
        return TeacherOutputDto.builder()
                .id(teacher.id)
                .address(teacher.address)
                .birthDate(teacher.birthDate.getValue())
                .firstName(teacher.fullName.getFirstName())
                .lastName(teacher.fullName.getLastName())
                .gender(teacher.gender)
                .identification(teacher.identification.getValue())
                .image(teacher.image)
                .nationality(teacher.nacionality)
                .phone(teacher.phone.getValue())
                .uuidUser(teacher.uuidUser)
                .build();
    }
}
