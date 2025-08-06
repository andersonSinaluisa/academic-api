package com.andersonsinaluisa.academicapi.persons.domain.repository;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.entities.StudentRepresentative;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepresentiveRepository {


    Mono<Void> unAssign(Long studentId, Long representativeId);
    Mono<StudentRepresentative> assign(StudentRepresentative studentRepresentative);


}
