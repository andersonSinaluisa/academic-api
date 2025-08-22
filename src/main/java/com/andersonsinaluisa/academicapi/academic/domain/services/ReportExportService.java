package com.andersonsinaluisa.academicapi.academic.domain.services;

import reactor.core.publisher.Mono;

public interface ReportExportService {
    Mono<byte[]> exportReportCard(Long studentId, String academicYearId, String format);
    Mono<byte[]> exportStatistics(String courseId, String academicYearId, String format);
}

