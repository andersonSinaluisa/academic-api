package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.BehaviorReportDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.behavior.LogBehaviorReportUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/behavior-reports")
@RequiredArgsConstructor
public class BehaviorReportController {

    private final LogBehaviorReportUseCase logBehaviorReportUseCase;

    @PostMapping
    public Mono<BehaviorReportDto> log(@RequestBody BehaviorReportDto dto) {
        return logBehaviorReportUseCase.execute(dto);
    }
}
