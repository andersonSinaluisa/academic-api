package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PlanningInputDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.PlanningOutputDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.teacherplanning.GetTeacherPlanningUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.teacherplanning.RegisterTeacherPlanningUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.teacherplanning.SearchTeacherPlanningsUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/teacher-plannings")
@RequiredArgsConstructor
public class TeacherPlanningController {

    private final RegisterTeacherPlanningUseCase registerUseCase;
    private final GetTeacherPlanningUseCase getUseCase;
    private final SearchTeacherPlanningsUseCase searchUseCase;

    @PostMapping
    public Mono<PlanningOutputDto> register(@Valid @RequestBody PlanningInputDto dto,
                                            @RequestHeader("X-Teacher-Id") Long teacherId) {
        if (!teacherId.equals(dto.teacherId)) {
            return Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN));
        }
        return registerUseCase.execute(dto);
    }

    @GetMapping("/{id}")
    public Mono<PlanningOutputDto> get(@PathVariable Long id,
                                       @RequestHeader("X-Teacher-Id") Long teacherId) {
        return getUseCase.execute(id, teacherId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public Flux<PlanningOutputDto> search(@RequestParam Long teacherId,
                                          @RequestParam(required = false) Long subjectId,
                                          @RequestParam(required = false) Long courseId,
                                          @RequestHeader("X-Teacher-Id") Long headerTeacherId) {
        if (!headerTeacherId.equals(teacherId)) {
            return Flux.error(new ResponseStatusException(HttpStatus.FORBIDDEN));
        }
        return searchUseCase.execute(teacherId, subjectId, courseId);
    }
}
