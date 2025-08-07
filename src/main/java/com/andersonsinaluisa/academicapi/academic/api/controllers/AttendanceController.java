package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AttendanceDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.attendance.RecordAttendanceUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.attendance.ListAttendanceUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.attendance.UpdateAttendanceUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.attendance.DeleteAttendanceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final RecordAttendanceUseCase recordAttendanceUseCase;
    private final ListAttendanceUseCase listAttendanceUseCase;
    private final UpdateAttendanceUseCase updateAttendanceUseCase;
    private final DeleteAttendanceUseCase deleteAttendanceUseCase;

    @PostMapping
    public Mono<AttendanceDto> record(@RequestBody AttendanceDto dto) {
        return recordAttendanceUseCase.execute(dto);
    }

    @GetMapping
    public Flux<AttendanceDto> list() {
        return listAttendanceUseCase.execute();
    }

    @PutMapping("/{id}")
    public Mono<AttendanceDto> update(@PathVariable Long id, @RequestBody AttendanceDto dto) {
        return updateAttendanceUseCase.execute(id, dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return deleteAttendanceUseCase.execute(id);
    }
}
