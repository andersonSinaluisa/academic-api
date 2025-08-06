package com.andersonsinaluisa.academicapi.persons.domain.entities;

import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public abstract class Person {

    public Long id;
    public FullName fullName;
    public PhoneNumber phone;
    public BirthDate birthDate;
    public TypePerson typePerson;
    public String uuidUser;
    public String address;
    public Identification identification;
    public List<CustomField> customFields;
    public String nacionality;
    public String gender;
    public String image;
    public int getAge() {
        return this.birthDate.getAge();
    }
}
