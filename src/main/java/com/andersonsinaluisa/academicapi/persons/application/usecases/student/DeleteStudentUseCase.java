package com.andersonsinaluisa.academicapi.persons.application.usecases.student;

import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class DeleteStudentUseCase {
    @Autowired
    private StudentRepository studentRepository;
    public Mono<Void> execute(Long Id){
        return studentRepository.delete(Id);
    }
}
