package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.enrollment.Enrollment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EnrollmentRepository {
    Mono<Enrollment> save(Enrollment enrollment);
    Flux<Enrollment> findAll();
    Mono<Enrollment> findById(Long id);
    Mono<Void> deleteById(Long id);
}

