package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.BehaviorReportDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.behavior.LogBehaviorReportUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.behavior.ListBehaviorReportUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.behavior.UpdateBehaviorReportUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.behavior.DeleteBehaviorReportUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/behavior-reports")
@RequiredArgsConstructor
public class BehaviorReportController {

    private final LogBehaviorReportUseCase logBehaviorReportUseCase;
    private final ListBehaviorReportUseCase listBehaviorReportUseCase;
    private final UpdateBehaviorReportUseCase updateBehaviorReportUseCase;
    private final DeleteBehaviorReportUseCase deleteBehaviorReportUseCase;

    @PostMapping
    public Mono<BehaviorReportDto> log(@RequestBody BehaviorReportDto dto) {
        return logBehaviorReportUseCase.execute(dto);
    }

    @GetMapping
    public Flux<BehaviorReportDto> list() {
        return listBehaviorReportUseCase.execute();
    }

    @PutMapping("/{id}")
    public Mono<BehaviorReportDto> update(@PathVariable Long id, @RequestBody BehaviorReportDto dto) {
        return updateBehaviorReportUseCase.execute(id, dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return deleteBehaviorReportUseCase.execute(id);
    }
}
