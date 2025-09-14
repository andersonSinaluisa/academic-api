package com.andersonsinaluisa.academicapi.persons.application.exception;

public class BirthDateInvalidException extends RuntimeException {
    public BirthDateInvalidException(String message) {
        super(message);
    }
}
