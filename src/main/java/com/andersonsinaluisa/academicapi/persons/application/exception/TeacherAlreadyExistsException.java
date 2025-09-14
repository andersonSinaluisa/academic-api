package com.andersonsinaluisa.academicapi.persons.application.exception;

public class TeacherAlreadyExistsException extends RuntimeException {
    public TeacherAlreadyExistsException(String message) {
        super(message);
    }
}