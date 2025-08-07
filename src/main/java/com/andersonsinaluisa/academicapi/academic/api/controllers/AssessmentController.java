package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AssessmentDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.assessment.RegisterAssessmentUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.assessment.ListAssessmentUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.assessment.UpdateAssessmentUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.assessment.DeleteAssessmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/assessments")
@RequiredArgsConstructor
public class AssessmentController {

    private final RegisterAssessmentUseCase registerAssessmentUseCase;
    private final ListAssessmentUseCase listAssessmentUseCase;
    private final UpdateAssessmentUseCase updateAssessmentUseCase;
    private final DeleteAssessmentUseCase deleteAssessmentUseCase;

    @PostMapping
    public Mono<AssessmentDto> register(@RequestBody AssessmentDto dto) {
        return registerAssessmentUseCase.execute(dto);
    }

    @GetMapping
    public Flux<AssessmentDto> list() {
        return listAssessmentUseCase.execute();
    }

    @PutMapping("/{id}")
    public Mono<AssessmentDto> update(@PathVariable Long id, @RequestBody AssessmentDto dto) {
        return updateAssessmentUseCase.execute(id, dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return deleteAssessmentUseCase.execute(id);
    }
}
