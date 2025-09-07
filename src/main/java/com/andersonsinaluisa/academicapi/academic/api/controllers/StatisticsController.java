package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AttendanceStatsDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.PerformanceStatsDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.reports.GetAttendanceStatsUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.reports.GetPerformanceStatsUseCase;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@Validated
public class StatisticsController {

    private final GetAttendanceStatsUseCase attendanceUseCase;
    private final GetPerformanceStatsUseCase performanceUseCase;

    @GetMapping("/attendance")
    public Mono<AttendanceStatsDto> attendance(@RequestParam @NotBlank String courseId,
                                               @RequestParam @NotBlank String academicYearId) {
        return attendanceUseCase.execute(courseId, academicYearId);
    }

    @GetMapping("/performance")
    public Mono<PerformanceStatsDto> performance(@RequestParam @NotBlank String courseId,
                                                 @RequestParam @NotBlank String subjectId,
                                                 @RequestParam @NotBlank String academicYearId) {
        return performanceUseCase.execute(courseId, subjectId, academicYearId);
    }
}

