package com.andersonsinaluisa.academicapi.academic.infrastructure.promotion;

import com.andersonsinaluisa.academicapi.academic.domain.services.FinalAverageCalculator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SimpleFinalAverageCalculator implements FinalAverageCalculator {
    @Override
    public Mono<Double> calculate(Long studentId, String academicYearId) {
        return Mono.just(0.0); // placeholder implementation
    }
}
