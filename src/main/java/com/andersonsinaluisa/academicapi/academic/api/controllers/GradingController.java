package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.FinalGradeDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.grading.CalculateFinalGradeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/grading")
@RequiredArgsConstructor
public class GradingController {

    private final CalculateFinalGradeUseCase calculateFinalGradeUseCase;

    @PostMapping("/final")
    public Mono<FinalGradeDto> calculate(@RequestParam Long studentId,
                                         @RequestParam Long subjectId,
                                         @RequestParam Long schoolYearId) {
        return calculateFinalGradeUseCase.execute(studentId, subjectId, schoolYearId);
    }
}
