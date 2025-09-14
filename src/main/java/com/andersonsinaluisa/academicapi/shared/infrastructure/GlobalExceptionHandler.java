package com.andersonsinaluisa.academicapi.shared.infrastructure;

import com.andersonsinaluisa.academicapi.persons.application.exception.BirthDateInvalidException;
import com.andersonsinaluisa.academicapi.persons.application.exception.TeacherAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TeacherAlreadyExistsException.class)
    public ResponseEntity<String> handleTeacherExists(TeacherAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    @ExceptionHandler(BirthDateInvalidException.class)
    public ResponseEntity<String> handleBirthDateInvalid(BirthDateInvalidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
