package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AcademicHistoryDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.promotion.GetAcademicHistoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/academic-history")
@RequiredArgsConstructor
public class AcademicHistoryController {

    private final GetAcademicHistoryUseCase getHistoryUseCase;

    @GetMapping("/{studentId}")
    public Mono<AcademicHistoryDto> get(@PathVariable Long studentId) {
        return getHistoryUseCase.execute(studentId);
    }
}
