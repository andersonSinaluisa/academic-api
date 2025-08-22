package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentInputDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentOutputDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.teacherassignment.GetTeacherAssignmentUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.teacherassignment.RegisterTeacherAssignmentUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.teacherassignment.SearchTeacherAssignmentsUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/teacher-assignments")
@RequiredArgsConstructor
public class TeacherAssignmentController {

    private final RegisterTeacherAssignmentUseCase registerUseCase;
    private final GetTeacherAssignmentUseCase getUseCase;
    private final SearchTeacherAssignmentsUseCase searchUseCase;

    @PostMapping
    public Mono<TeacherAssignmentOutputDto> register(@Valid @RequestBody TeacherAssignmentInputDto dto,
                                                     @RequestHeader("X-Teacher-Id") Long teacherId) {
        if (!teacherId.equals(dto.teacherId)) {
            return Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN));
        }
        return registerUseCase.execute(dto);
    }

    @GetMapping("/{id}")
    public Mono<TeacherAssignmentOutputDto> get(@PathVariable Long id,
                                                @RequestHeader("X-Teacher-Id") Long teacherId) {
        return getUseCase.execute(id, teacherId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public Flux<TeacherAssignmentOutputDto> search(@RequestParam Long teacherId,
                                                   @RequestParam(required = false) Long courseId,
                                                   @RequestHeader("X-Teacher-Id") Long headerTeacherId) {
        if (!headerTeacherId.equals(teacherId)) {
            return Flux.error(new ResponseStatusException(HttpStatus.FORBIDDEN));
        }
        return searchUseCase.execute(teacherId, courseId);
    }
}
