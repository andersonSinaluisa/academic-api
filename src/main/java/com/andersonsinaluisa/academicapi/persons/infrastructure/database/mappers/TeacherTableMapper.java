package com.andersonsinaluisa.academicapi.persons.infrastructure.database.mappers;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.persons.domain.entities.TypePerson;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.BirthDate;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.FullName;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.Identification;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.PhoneNumber;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.TeacherTable;

import java.time.LocalDate;

public class TeacherTableMapper {

    public static Teacher fromPersistenceToDomain(TeacherTable teacherTable){
        return Teacher.builder()
                .id(teacherTable.id)
                .nacionality(teacherTable.nacionality)
                .gender(teacherTable.gender)
                .image(teacherTable.image)
                .address(teacherTable.address)
                .identification(new Identification(teacherTable.identification))
                .phone(new PhoneNumber(teacherTable.phone))
                .typePerson(TypePerson.TEACHER)
                .birthDate(new BirthDate(teacherTable.birthDate))
                .uuidUser(teacherTable.uuidUser)
                .fullName(new FullName(teacherTable.firtName,teacherTable.lastName))

                .build();
    }


    public static TeacherTable fromDomainToPersistence(Teacher teacher){
        return TeacherTable.builder()
                .firtName(teacher.fullName.getFirstName())
                .lastName(teacher.fullName.getLastName())
                .birthDate(teacher.birthDate.getValue())
                .image(teacher.image)
                .address(teacher.address)
                .identification(teacher.identification.getValue())
                .phone(teacher.phone.getValue())
                .uuidUser(teacher.uuidUser)
                .gender(teacher.gender)
                .createdAt(LocalDate.now())
                .deleted(false)
                .nacionality(teacher.nacionality)
                .build();
    }
}
