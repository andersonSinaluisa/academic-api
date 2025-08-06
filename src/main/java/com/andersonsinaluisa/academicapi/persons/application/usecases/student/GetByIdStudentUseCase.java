package com.andersonsinaluisa.academicapi.persons.application.usecases.student;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetByIdStudentUseCase {

    private StudentRepository studentRepository;

    public Mono<Student> execute(Long Id){
        return studentRepository.getById(Id);
    }
}
