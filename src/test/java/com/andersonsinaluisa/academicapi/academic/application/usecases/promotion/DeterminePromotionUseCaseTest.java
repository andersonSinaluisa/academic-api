package com.andersonsinaluisa.academicapi.academic.application.usecases.promotion;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionRequestDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.PromotionMapper;
import com.andersonsinaluisa.academicapi.academic.domain.entities.promotion.Promotion;
import com.andersonsinaluisa.academicapi.academic.domain.repository.PromotionRepository;
import com.andersonsinaluisa.academicapi.academic.domain.services.FinalAverageCalculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.Stream;

class DeterminePromotionUseCaseTest {

    private final FinalAverageCalculator calculator = Mockito.mock(FinalAverageCalculator.class);
    private final PromotionRepository repository = Mockito.mock(PromotionRepository.class);
    private final PromotionMapper mapper = new PromotionMapper();
    private final DeterminePromotionUseCase useCase = new DeterminePromotionUseCase(calculator, repository, mapper);

    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(7.5, "PROMOTED"),
                Arguments.of(6.2, "SUPLETORIO"),
                Arguments.of(5.4, "REMEDIAL"),
                Arguments.of(4.7, "GRACIA"),
                Arguments.of(3.2, "NOT_PROMOTED")
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void determineStatus(double average, String expectedStatus) {
        PromotionRequestDto dto = PromotionRequestDto.builder()
                .studentId(1L)
                .academicYearId("2024")
                .build();
        Mockito.when(calculator.calculate(1L, "2024")).thenReturn(Mono.just(average));
        Mockito.when(repository.save(Mockito.any(Promotion.class)))
                .thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(useCase.execute(dto))
                .assertNext(result -> {
                    org.junit.jupiter.api.Assertions.assertEquals(expectedStatus, result.status);
                })
                .verifyComplete();
    }
}
