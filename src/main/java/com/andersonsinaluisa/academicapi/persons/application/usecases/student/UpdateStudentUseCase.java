package com.andersonsinaluisa.academicapi.persons.application.usecases.student;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateStudentUseCase {
    @Autowired
    private StudentRepository studentRepository;

    public Mono<Student> execute(Long id,Student student){
        return studentRepository.update(id,student);
    }

}
