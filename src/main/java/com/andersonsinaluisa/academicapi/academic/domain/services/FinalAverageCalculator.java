package com.andersonsinaluisa.academicapi.academic.domain.services;

import reactor.core.publisher.Mono;

public interface FinalAverageCalculator {
    Mono<Double> calculate(Long studentId, String academicYearId);
}
