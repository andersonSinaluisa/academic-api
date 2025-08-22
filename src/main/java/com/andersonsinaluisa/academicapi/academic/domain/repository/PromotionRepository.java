package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.promotion.Promotion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PromotionRepository {
    Mono<Promotion> save(Promotion promotion);
    Flux<Promotion> findByStudentId(Long studentId);
}
