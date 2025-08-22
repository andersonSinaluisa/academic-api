package com.andersonsinaluisa.academicapi.academic.infrastructure.reports;

import com.andersonsinaluisa.academicapi.academic.domain.services.ReportExportService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SimpleReportExportService implements ReportExportService {
    @Override
    public Mono<byte[]> exportReportCard(Long studentId, String academicYearId, String format) {
        return Mono.just(new byte[0]);
    }

    @Override
    public Mono<byte[]> exportStatistics(String courseId, String academicYearId, String format) {
        return Mono.just(new byte[0]);
    }
}

