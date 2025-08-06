package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.attendance.Attendance;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AttendanceRepository {
    Mono<Attendance> save(Attendance attendance);
    Flux<Attendance> findAll();
}

