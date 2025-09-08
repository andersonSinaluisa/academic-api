package com.andersonsinaluisa.academicapi.persons.application.usecases.student;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.entities.StudentRepresentative;
import com.andersonsinaluisa.academicapi.persons.domain.repository.RepresentativeRepository;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepository;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepresentiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CreateStudentUseCase {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RepresentativeRepository representativeRepository;
    @Autowired
    private StudentRepresentiveRepository studentRepresentiveRepository;


    public Mono<Student> execute(Student student) {
        return studentRepository.create(student);
    }

}
