package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AssessmentDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.assessment.RegisterAssessmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/assessments")
@RequiredArgsConstructor
public class AssessmentController {

    private final RegisterAssessmentUseCase registerAssessmentUseCase;

    @PostMapping
    public Mono<AssessmentDto> register(@RequestBody AssessmentDto dto) {
        return registerAssessmentUseCase.execute(dto);
    }
}
