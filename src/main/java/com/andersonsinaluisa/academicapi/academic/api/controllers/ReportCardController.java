package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.ReportCardDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.reports.GetReportCardUseCase;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/report-cards")
@RequiredArgsConstructor
@Validated
public class ReportCardController {

    private final GetReportCardUseCase getReportCardUseCase;

    @GetMapping("/{studentId}")
    public Mono<ReportCardDto> get(@PathVariable Long studentId,
                                   @RequestParam @NotBlank String academicYearId) {
        return getReportCardUseCase.execute(studentId, academicYearId);
    }
}

