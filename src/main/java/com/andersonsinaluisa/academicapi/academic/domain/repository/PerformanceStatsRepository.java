package com.andersonsinaluisa.academicapi.academic.domain.repository;

import reactor.core.publisher.Flux;

public interface PerformanceStatsRepository {
    Flux<Double> findGrades(String courseId, String subjectId, String academicYearId);
}

