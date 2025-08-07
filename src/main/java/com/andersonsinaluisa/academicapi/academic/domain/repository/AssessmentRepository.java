package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.assessment.Assessment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AssessmentRepository {
    Mono<Assessment> save(Assessment assessment);
    Flux<Assessment> findAll();
    Mono<Assessment> findById(Long id);
    Mono<Void> deleteById(Long id);
}

