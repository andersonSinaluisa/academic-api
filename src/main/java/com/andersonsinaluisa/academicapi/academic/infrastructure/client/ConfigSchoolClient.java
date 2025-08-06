package com.andersonsinaluisa.academicapi.academic.infrastructure.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConfigSchoolClient {
    Mono<GradingSystemConfig> getGradingSystem(Long schoolYearId);
    Flux<GradingTermConfig> getGradingTerms(Long gradingSystemId);
    Flux<AssessmentTypeConfig> getAssessmentTypes(Long gradingSystemId);

    record GradingSystemConfig(Long id, Double passingScore) {}
    record GradingTermConfig(Long id, Double weight) {}
    record AssessmentTypeConfig(Long id, Double weight) {}
}
