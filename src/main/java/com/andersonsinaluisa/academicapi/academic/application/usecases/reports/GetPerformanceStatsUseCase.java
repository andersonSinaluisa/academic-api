package com.andersonsinaluisa.academicapi.academic.application.usecases.reports;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PerformanceStatsDto;
import com.andersonsinaluisa.academicapi.academic.domain.repository.PerformanceStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetPerformanceStatsUseCase {

    private final PerformanceStatsRepository repository;

    public Mono<PerformanceStatsDto> execute(String courseId, String subjectId, String academicYearId) {
        return repository.findGrades(courseId, subjectId, academicYearId)
                .collectList()
                .map(grades -> buildDto(courseId, subjectId, academicYearId, grades));
    }

    private PerformanceStatsDto buildDto(String courseId, String subjectId, String year, List<Double> grades) {
        double average = grades.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double highest = grades.stream().mapToDouble(Double::doubleValue).max().orElse(0);
        double lowest = grades.stream().mapToDouble(Double::doubleValue).min().orElse(0);
        Map<String, Long> distribution = new LinkedHashMap<>();
        distribution.put("0-4", grades.stream().filter(g -> g >= 0 && g <= 4).count());
        distribution.put("5-6", grades.stream().filter(g -> g >= 5 && g <= 6).count());
        distribution.put("7-8", grades.stream().filter(g -> g >= 7 && g <= 8).count());
        distribution.put("9-10", grades.stream().filter(g -> g >= 9 && g <= 10).count());
        return PerformanceStatsDto.builder()
                .courseId(courseId)
                .subjectId(subjectId)
                .academicYearId(year)
                .average(average)
                .highest(highest)
                .lowest(lowest)
                .distribution(distribution)
                .build();
    }
}

