package com.andersonsinaluisa.academicapi.academic.application.usecases.reports;

import com.andersonsinaluisa.academicapi.academic.domain.repository.AttendanceStatsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class GetAttendanceStatsUseCaseTest {

    private final AttendanceStatsRepository repository = Mockito.mock(AttendanceStatsRepository.class);
    private final GetAttendanceStatsUseCase useCase = new GetAttendanceStatsUseCase(repository);

    @Test
    void calculatesPercentages() {
        Mockito.when(repository.findStatuses("c1", "2024"))
                .thenReturn(Flux.just("PRESENT", "ABSENT", "PRESENT", "JUSTIFIED"));

        StepVerifier.create(useCase.execute("c1", "2024"))
                .assertNext(dto -> {
                    org.junit.jupiter.api.Assertions.assertEquals(4L, dto.totalStudents);
                    org.junit.jupiter.api.Assertions.assertEquals(50.0, dto.present);
                    org.junit.jupiter.api.Assertions.assertEquals(25.0, dto.absent);
                    org.junit.jupiter.api.Assertions.assertEquals(25.0, dto.justified);
                })
                .verifyComplete();
    }
}

