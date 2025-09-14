package com.andersonsinaluisa.academicapi.persons.infrastructure.database.mappers;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.entities.TypePerson;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.BirthDate;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.FullName;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.Identification;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.PhoneNumber;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.StudentTable;

public abstract class StudentTableMapper {

    public static Student fromPersistenceToDomain(StudentTable data){
        return Student.builder()
                .id(data.id)
                .fullName(new FullName(data.firstName,data.lastName))
                .phone(new PhoneNumber(data.phone))
                .birthDate(new BirthDate(data.birthDate))
                .typePerson(TypePerson.STUDENT)
                .uuidUser(data.uuidUser)
                .address(data.address)
                .identification(new Identification(data.identification))
                .nacionality(data.nationality)
                .gender(data.gender)
                .image(data.image)
                .build()
                ;
    }

    public static StudentTable fromDomainToPersistence(Student student){
        return StudentTable.builder()
                .id(student.id)
                .firstName(student.fullName.getFirstName())
                .lastName(student.fullName.getLastName())
                .phone(student.phone.getValue())
                .birthDate(student.birthDate.getValue())
                .uuidUser(student.uuidUser)
                .address(student.address)
                .identification(student.identification.getValue())
                .nationality(student.nacionality)
                .gender(student.gender)
                .image(student.image)
                .build()
                ;
    }
}
