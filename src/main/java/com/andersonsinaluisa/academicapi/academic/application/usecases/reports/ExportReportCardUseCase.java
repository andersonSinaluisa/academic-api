package com.andersonsinaluisa.academicapi.academic.application.usecases.reports;

import com.andersonsinaluisa.academicapi.academic.domain.services.ReportExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExportReportCardUseCase {

    private final ReportExportService exportService;

    public Mono<byte[]> execute(Long studentId, String academicYearId, String format) {
        return exportService.exportReportCard(studentId, academicYearId, format);
    }
}

