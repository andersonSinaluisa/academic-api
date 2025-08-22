package com.andersonsinaluisa.academicapi.academic.infrastructure.reports;

import com.andersonsinaluisa.academicapi.academic.domain.repository.PerformanceStatsRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class PerformanceStatsRepositoryImpl implements PerformanceStatsRepository {
    @Override
    public Flux<Double> findGrades(String courseId, String subjectId, String academicYearId) {
        return Flux.empty();
    }
}

