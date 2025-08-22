package com.andersonsinaluisa.academicapi.academic.application.usecases.reports;

import com.andersonsinaluisa.academicapi.academic.domain.repository.PerformanceStatsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class GetPerformanceStatsUseCaseTest {

    private final PerformanceStatsRepository repository = Mockito.mock(PerformanceStatsRepository.class);
    private final GetPerformanceStatsUseCase useCase = new GetPerformanceStatsUseCase(repository);

    @Test
    void calculatesStatistics() {
        Mockito.when(repository.findGrades("c1", "s1", "2024"))
                .thenReturn(Flux.just(3.0, 5.5, 7.8, 9.2));

        StepVerifier.create(useCase.execute("c1", "s1", "2024"))
                .assertNext(dto -> {
                    org.junit.jupiter.api.Assertions.assertEquals(6.375, dto.average);
                    org.junit.jupiter.api.Assertions.assertEquals(9.2, dto.highest);
                    org.junit.jupiter.api.Assertions.assertEquals(3.0, dto.lowest);
                    org.junit.jupiter.api.Assertions.assertEquals(1L, dto.distribution.get("0-4"));
                    org.junit.jupiter.api.Assertions.assertEquals(1L, dto.distribution.get("5-6"));
                    org.junit.jupiter.api.Assertions.assertEquals(1L, dto.distribution.get("7-8"));
                    org.junit.jupiter.api.Assertions.assertEquals(1L, dto.distribution.get("9-10"));
                })
                .verifyComplete();
    }
}

