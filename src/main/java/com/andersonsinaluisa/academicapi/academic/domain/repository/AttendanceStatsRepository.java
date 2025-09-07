package com.andersonsinaluisa.academicapi.academic.domain.repository;

import reactor.core.publisher.Flux;

public interface AttendanceStatsRepository {
    Flux<String> findStatuses(String courseId, String academicYearId);
}

