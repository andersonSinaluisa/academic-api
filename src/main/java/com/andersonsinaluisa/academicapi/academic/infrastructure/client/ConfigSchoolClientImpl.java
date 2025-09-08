package com.andersonsinaluisa.academicapi.academic.infrastructure.client;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ConfigSchoolClientImpl implements  ConfigSchoolClient{
    @Override
    public Mono<GradingSystemConfig> getGradingSystem(Long schoolYearId) {
        return null;
    }

    @Override
    public Flux<GradingTermConfig> getGradingTerms(Long gradingSystemId) {
        return null;
    }

    @Override
    public Flux<AssessmentTypeConfig> getAssessmentTypes(Long gradingSystemId) {
        return null;
    }
}
