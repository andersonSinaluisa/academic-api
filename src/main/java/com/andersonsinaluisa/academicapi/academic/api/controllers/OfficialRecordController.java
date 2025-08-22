package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.OfficialRecordDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.reports.GetOfficialRecordUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/official-records")
@RequiredArgsConstructor
public class OfficialRecordController {

    private final GetOfficialRecordUseCase useCase;

    @GetMapping("/{studentId}")
    public Mono<OfficialRecordDto> get(@PathVariable Long studentId) {
        return useCase.execute(studentId);
    }
}

