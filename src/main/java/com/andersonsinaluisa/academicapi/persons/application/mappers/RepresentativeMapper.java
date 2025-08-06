package com.andersonsinaluisa.academicapi.persons.application.mappers;

import com.andersonsinaluisa.academicapi.persons.application.dtos.RepresentativeInputDto;
import com.andersonsinaluisa.academicapi.persons.application.dtos.RepresentativeOutputDto;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.entities.TypePerson;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.BirthDate;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.FullName;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.Identification;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.PhoneNumber;
import reactor.core.publisher.Mono;

public class RepresentativeMapper {

    public static Representative fromDtoToDomain(
            RepresentativeInputDto  representativeInputDto){
        return Representative.builder()
                .fullName(new FullName(representativeInputDto.firstName,representativeInputDto.lastName))
                .birthDate(new BirthDate(representativeInputDto.birthDate))
                .uuidUser(representativeInputDto.uuidUser)
                .phone(new PhoneNumber(representativeInputDto.phone))
                .image(representativeInputDto.image)
                .typePerson(TypePerson.REPRESENTATIVE)
                .nacionality(representativeInputDto.nacionality)
                .identification(new Identification(representativeInputDto.identification))
                .gender(representativeInputDto.gender)
                .build();
    }

    public static RepresentativeOutputDto fromDomainToDto(Representative representative){
        return RepresentativeOutputDto.builder()
                .id(representative.id)
                .address(representative.address)
                .birthDate(representative.birthDate.getValue())
                .image(representative.image)
                .nacionality(representative.nacionality)
                .phone(representative.phone.getValue())
                .lastName(representative.fullName.getLastName())
                .fistName(representative.fullName.getFirstName())
                .uuidUser(representative.uuidUser)
                .identification(representative.identification.getValue())
                .nacionality(representative.nacionality)
                .build();
    }
}
