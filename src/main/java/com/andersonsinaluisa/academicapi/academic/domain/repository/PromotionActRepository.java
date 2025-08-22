package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.promotion.PromotionAct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PromotionActRepository {
    Mono<PromotionAct> save(PromotionAct act);
    Mono<PromotionAct> findById(Long id);
    Flux<PromotionAct> findByCourseIdAndAcademicYearId(String courseId, String academicYearId);
    Mono<Void> deleteById(Long id);
}
