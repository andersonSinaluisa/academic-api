package com.andersonsinaluisa.academicapi.academic.application.usecases.reports;

import com.andersonsinaluisa.academicapi.academic.domain.services.ReportExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExportStatisticsUseCase {

    private final ReportExportService exportService;

    public Mono<byte[]> execute(String courseId, String academicYearId, String format) {
        return exportService.exportStatistics(courseId, academicYearId, format);
    }
}

