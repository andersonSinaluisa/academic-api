package com.andersonsinaluisa.academicapi.academic.application.usecases.promotion;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionRequestDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionResultDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.PromotionMapper;
import com.andersonsinaluisa.academicapi.academic.domain.entities.promotion.Promotion;
import com.andersonsinaluisa.academicapi.academic.domain.entities.promotion.PromotionStatus;
import com.andersonsinaluisa.academicapi.academic.domain.repository.PromotionRepository;
import com.andersonsinaluisa.academicapi.academic.domain.services.FinalAverageCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DeterminePromotionUseCase {

    private final FinalAverageCalculator averageCalculator;
    private final PromotionRepository repository;
    private final PromotionMapper mapper;

    public Mono<PromotionResultDto> execute(PromotionRequestDto dto) {
        return averageCalculator.calculate(dto.studentId, dto.academicYearId)
                .map(avg -> {
                    PromotionStatus status;
                    if (avg >= 7) {
                        status = PromotionStatus.PROMOTED;
                    } else if (avg >= 6) {
                        status = PromotionStatus.SUPLETORIO;
                    } else if (avg >= 5) {
                        status = PromotionStatus.REMEDIAL;
                    } else if (avg >= 4.5) {
                        status = PromotionStatus.GRACIA;
                    } else {
                        status = PromotionStatus.NOT_PROMOTED;
                    }
                    return Promotion.builder()
                            .studentId(dto.studentId)
                            .academicYearId(dto.academicYearId)
                            .finalAverage(avg)
                            .status(status)
                            .generatedAt(LocalDate.now())
                            .build();
                })
                .flatMap(repository::save)
                .map(mapper::toDto);
    }
}
