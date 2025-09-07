package com.andersonsinaluisa.academicapi.academic.application.usecases.reports;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AttendanceStatsDto;
import com.andersonsinaluisa.academicapi.academic.domain.repository.AttendanceStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetAttendanceStatsUseCase {

    private final AttendanceStatsRepository repository;

    public Mono<AttendanceStatsDto> execute(String courseId, String academicYearId) {
        return repository.findStatuses(courseId, academicYearId)
                .collectList()
                .map(statuses -> {
                    long total = statuses.size();
                    long present = statuses.stream().filter(s -> s.equals("PRESENT")).count();
                    long absent = statuses.stream().filter(s -> s.equals("ABSENT")).count();
                    long justified = statuses.stream().filter(s -> s.equals("JUSTIFIED")).count();
                    return AttendanceStatsDto.builder()
                            .courseId(courseId)
                            .academicYearId(academicYearId)
                            .totalStudents(total)
                            .present(total == 0 ? 0 : present * 100.0 / total)
                            .absent(total == 0 ? 0 : absent * 100.0 / total)
                            .justified(total == 0 ? 0 : justified * 100.0 / total)
                            .build();
                });
    }
}

