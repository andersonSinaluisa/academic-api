package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.reports.ReportCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReportCardRepository {
    Mono<ReportCard> save(ReportCard reportCard);
    Flux<ReportCard> findAll();
    Mono<ReportCard> findByStudentIdAndAcademicYearId(Long studentId, String academicYearId);
    Flux<ReportCard> findByStudentId(Long studentId);
}

