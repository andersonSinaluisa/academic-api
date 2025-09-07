package com.andersonsinaluisa.academicapi.academic.infrastructure.reports;

import com.andersonsinaluisa.academicapi.academic.domain.repository.AttendanceStatsRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class AttendanceStatsRepositoryImpl implements AttendanceStatsRepository {
    @Override
    public Flux<String> findStatuses(String courseId, String academicYearId) {
        return Flux.empty();
    }
}

