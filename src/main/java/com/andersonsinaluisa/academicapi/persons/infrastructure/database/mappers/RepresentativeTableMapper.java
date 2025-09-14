package com.andersonsinaluisa.academicapi.persons.infrastructure.database.mappers;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.entities.TypePerson;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.BirthDate;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.FullName;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.Identification;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.PhoneNumber;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.RepresentativeTable;

public  class RepresentativeTableMapper {

    public static Representative fromPersistenceToDomain(RepresentativeTable table){
        return Representative.builder()
                .id(table.id)
                .fullName(new FullName(table.firstName,table.lastName))
                .phone(new PhoneNumber(table.phone))
                .birthDate(new BirthDate(table.birthDate))
                .typePerson(TypePerson.REPRESENTATIVE)
                .uuidUser(table.uuidUser)
                .address(table.address)
                .identification(new Identification(table.identification))
                .nacionality(table.nationality)
                .gender(table.gender)
                .image(table.image)
                .build();
    }


    public static RepresentativeTable fromDomainToPersistence(Representative object){
        return RepresentativeTable.builder()
                .firstName(object.fullName.getFirstName())
                .lastName(object.fullName.getLastName())
                .birthDate(object.birthDate.getValue())
                .gender(object.gender)
                .image(object.image)
                .address(object.address)
                .identification(object.identification.getValue())
                .phone(object.phone.getValue())
                .nationality(object.nacionality)
                .uuidUser(object.uuidUser)
                .build();
    }
}
