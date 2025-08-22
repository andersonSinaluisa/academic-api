package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.usecases.reports.ExportReportCardUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.reports.ExportStatisticsUseCase;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/exports")
@RequiredArgsConstructor
@Validated
public class ExportController {

    private final ExportReportCardUseCase reportCardUseCase;
    private final ExportStatisticsUseCase statisticsUseCase;

    @GetMapping(value = "/report-card/{studentId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono<ResponseEntity<byte[]>> exportReportCard(@PathVariable Long studentId,
                                                         @RequestParam @NotBlank String academicYearId,
                                                         @RequestParam @NotBlank String format) {
        return reportCardUseCase.execute(studentId, academicYearId, format)
                .map(bytes -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report-card." + format.toLowerCase())
                        .body(bytes));
    }

    @GetMapping(value = "/statistics", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono<ResponseEntity<byte[]>> exportStatistics(@RequestParam @NotBlank String courseId,
                                                         @RequestParam @NotBlank String academicYearId,
                                                         @RequestParam @NotBlank String format) {
        return statisticsUseCase.execute(courseId, academicYearId, format)
                .map(bytes -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=statistics." + format.toLowerCase())
                        .body(bytes));
    }
}

