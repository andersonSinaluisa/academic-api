package com.andersonsinaluisa.academicapi.persons.application.usecases.student;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.entities.StudentRepresentative;
import com.andersonsinaluisa.academicapi.persons.domain.repository.RepresentativeRepository;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepository;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepresentiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CreateStudentUseCase {

    public StudentRepository studentRepository;
    public RepresentativeRepository representativeRepository;
    public StudentRepresentiveRepository studentRepresentiveRepository;


    public Mono<Student> execute(Student student) {
        return studentRepository.create(student);
    }

}
