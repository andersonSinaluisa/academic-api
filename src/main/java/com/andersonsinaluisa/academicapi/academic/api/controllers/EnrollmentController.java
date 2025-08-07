package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.EnrollmentDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.enrollment.CreateEnrollmentUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.enrollment.ListEnrollmentUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.enrollment.UpdateEnrollmentUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.enrollment.DeleteEnrollmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final CreateEnrollmentUseCase createEnrollmentUseCase;
    private final ListEnrollmentUseCase listEnrollmentUseCase;
    private final UpdateEnrollmentUseCase updateEnrollmentUseCase;
    private final DeleteEnrollmentUseCase deleteEnrollmentUseCase;

    @PostMapping
    public Mono<EnrollmentDto> create(@RequestBody EnrollmentDto dto) {
        return createEnrollmentUseCase.execute(dto);
    }

    @GetMapping
    public Flux<EnrollmentDto> list() {
        return listEnrollmentUseCase.execute();
    }

    @PutMapping("/{id}")
    public Mono<EnrollmentDto> update(@PathVariable Long id, @RequestBody EnrollmentDto dto) {
        return updateEnrollmentUseCase.execute(id, dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return deleteEnrollmentUseCase.execute(id);
    }
}
