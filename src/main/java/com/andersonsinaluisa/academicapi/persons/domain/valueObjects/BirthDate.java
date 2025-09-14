package com.andersonsinaluisa.academicapi.persons.domain.valueObjects;

import com.andersonsinaluisa.academicapi.persons.application.exception.BirthDateInvalidException;

import java.time.LocalDate;
import java.time.Period;

public class BirthDate {
    private final LocalDate value;

    public BirthDate(LocalDate value) {
        if (value.isAfter(LocalDate.now())) {
            throw new BirthDateInvalidException("Birth date cannot be in the future");
        }
        this.value = value;
    }

    public int getAge() {
        return Period.between(value, LocalDate.now()).getYears();
    }

    public LocalDate getValue() {
        return value;
    }
}
