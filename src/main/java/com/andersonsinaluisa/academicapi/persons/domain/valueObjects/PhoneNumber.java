package com.andersonsinaluisa.academicapi.persons.domain.valueObjects;

public class PhoneNumber {
    private final String value;

    public PhoneNumber(String value) {
        if (!value.matches("^\\+?[0-9]{7,15}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
