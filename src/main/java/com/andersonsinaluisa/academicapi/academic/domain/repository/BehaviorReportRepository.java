package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.behavior.BehaviorReport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BehaviorReportRepository {
    Mono<BehaviorReport> save(BehaviorReport report);
    Flux<BehaviorReport> findAll();
    Mono<BehaviorReport> findById(Long id);
    Mono<Void> deleteById(Long id);
}

