package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.ReportCardDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.reports.GenerateReportCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/report-cards")
@RequiredArgsConstructor
public class ReportCardController {

    private final GenerateReportCardUseCase generateReportCardUseCase;

    @GetMapping
    public Flux<ReportCardDto> list() {
        return generateReportCardUseCase.execute();
    }
}
