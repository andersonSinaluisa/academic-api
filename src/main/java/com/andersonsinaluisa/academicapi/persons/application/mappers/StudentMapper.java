package com.andersonsinaluisa.academicapi.persons.application.mappers;

import com.andersonsinaluisa.academicapi.persons.application.dtos.StudentInputDto;
import com.andersonsinaluisa.academicapi.persons.application.dtos.StudentOutputDto;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.BirthDate;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.FullName;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.Identification;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.PhoneNumber;

public class StudentMapper {

    public static Student fromDtoToDomain(StudentInputDto studentInputDto){
        return Student.builder()
                .uuidCurrentParallel(studentInputDto.uuidCurrentParallel)
                .uuidCurrentSection(studentInputDto.uuidCurrentSection)
                .uuidCurrentSchoolYear(studentInputDto.uuidCurrentSchoolYear)
                .address(studentInputDto.address)
                .birthDate(new BirthDate(studentInputDto.birthDate))
                .fullName(new FullName(studentInputDto.fistName,studentInputDto.lastName))
                .gender(studentInputDto.gender)
                .identification(new Identification(studentInputDto.identification))
                .image(studentInputDto.image)
                .nacionality(studentInputDto.nacionality)
                .phone(new PhoneNumber(studentInputDto.phone))
                .uuidUser(studentInputDto.uuidUser)
                .build();
    }

    public static StudentOutputDto fromDomainToDto(Student student){
        return StudentOutputDto.builder()
                .id(student.id)
                .address(student.address)
                .birthDate(student.birthDate.getValue())
                .image(student.image)
                .nacionality(student.nacionality)
                .phone(student.phone.getValue())
                .lastName(student.fullName.getLastName())
                .fistName(student.fullName.getFirstName())
                .uuidUser(student.uuidUser)
                .identification(student.identification.getValue())
                .nacionality(student.nacionality)


                .build();
    }
}
