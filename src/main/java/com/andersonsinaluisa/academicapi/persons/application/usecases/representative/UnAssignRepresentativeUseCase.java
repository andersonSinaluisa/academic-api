package com.andersonsinaluisa.academicapi.persons.application.usecases.representative;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepresentiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class UnAssignRepresentativeUseCase {

    @Autowired
    private StudentRepresentiveRepository studentRepresentiveRepository;
    public Mono<Void> execute(Representative representative, Student student){

       return  studentRepresentiveRepository.unAssign(student.id,representative.id);
    }
}
