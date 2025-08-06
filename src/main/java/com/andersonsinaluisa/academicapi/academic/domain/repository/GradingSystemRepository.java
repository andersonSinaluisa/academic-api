package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.grading.GradingSystem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GradingSystemRepository {
    Mono<GradingSystem> save(GradingSystem gradingSystem);
    Flux<GradingSystem> findAll();
}

