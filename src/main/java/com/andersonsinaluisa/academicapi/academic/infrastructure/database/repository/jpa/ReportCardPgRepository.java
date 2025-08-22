package com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa;

import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.reports.ReportCardTable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReportCardPgRepository extends ReactiveCrudRepository<ReportCardTable, Long> {
    Mono<ReportCardTable> findByStudentIdAndAcademicYearId(Long studentId, String academicYearId);
    Flux<ReportCardTable> findByStudentId(Long studentId);
}

